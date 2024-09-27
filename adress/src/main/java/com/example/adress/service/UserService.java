package com.example.adress.service;

import com.example.adress.domain.User;
import com.example.adress.dto.UserDto;
import com.example.adress.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    
    /**
     * DTO -> Entity
     * 
     * @param inDto
     * @return
     */
    public User setEntity(UserDto inDto) {
    	
    	User outUser = User.builder()
    			.email(inDto.getEmail())
    			.password(bCryptPasswordEncoder.encode(inDto.getPassword()))	//	비밀번호 암호화
    			.build();
    	
    	return outUser;
    }
    
    /**
     * 회원 저장 처리 시작
     * 
     * @param inDto
     * @return
     */
    public User save(UserDto inDto){
    	
    	logger.debug("post 회원가입 저장");
    	
    	User outUser = setEntity(inDto);
    	
        return userRepository.save(outUser);

    }

    /**
     *  이메일 기준으로 기존 회원 조회
     * 
     * @param inDto
     * @return
     */
    public User selectByEmail(UserDto inDto) {
    	
    	User inEntity = setEntity(inDto);
    	
    	logger.debug("서비스 :: " + inEntity.getEmail());
    	
        return userRepository.findByEmail(inEntity.getEmail())
                .orElse(null);
    	
    }

	/**
	 * 소셜 로그인
	 * 
	 */
	@Transactional
	public User whenSocialLogin(String custEmail) {
		Optional<User> outUser = userRepository.findByEmail(custEmail);
		
		// 존재하는 고객인 경우, 해당 고객 정보 반환
		if (outUser.isPresent()) {
			return outUser.get(); 
		}
		
		// 새로운 고객 등록을 위해 고객 정보 설정
		UserDto newDto = new UserDto();
		newDto.setEmail(custEmail);
		newDto.setPassword("");
		
	    return save(newDto);
	}
}
