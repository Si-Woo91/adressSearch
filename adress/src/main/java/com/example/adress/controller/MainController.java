package com.example.adress.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.adress.domain.User;
import com.example.adress.dto.UserDto;
//import com.example.adress.service.KakaoService;
import com.example.adress.service.UserService;
import com.example.adress.util.Utiles;

import jakarta.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    private final UserService userService;
    
    /**
     * 메인페이지(로그인)
     *
     */
    @GetMapping(value = "/")
    public String mainPage(Model model, @RequestParam(value="error", required = false) String error,
	        @RequestParam(value = "exception", required = false) String exception){
    	
    	logger.debug("error : " + error);
    	logger.debug("exception : " + exception);
    	
    	model.addAttribute("error", error);
    	model.addAttribute("exception", exception);

        return "login";
    }

    /**
     * 회원가입 페이지
     *
     */
    @GetMapping(value = "/signup")
    public String signUp(){

        return "signup";
    }
    
    /**
     * 주소검색 페이지
     * 
     * @return
     */
    @GetMapping(value = "/adressSearch")
    public String addressSearch(){
    	
    	return "adressSearch";
    }
    
    /**
     * 회원가입 저장시
     * 
     * @param inDto
     * @param model
     * @return
     */
    @PostMapping(value = "/signup")
    public String sendSignup(UserDto inDto, Model model) {
    	
    	logger.debug("회원가입 저장 컨트롤러");
    	
    	User outEntity = userService.selectByEmail(inDto);
    	
    	// 기존 회원이면 메시지 출력
    	if(!Utiles.isNullOrEmpty(outEntity)) {
    		
    		model.addAttribute("msg", "가입 되어 있는 이메일입니다.");
    		model.addAttribute("url", "/signup");
    		
    		return "alert";
    		
    	}
    	
    	userService.save(inDto);
    	
    	return "redirect:/";
    }
	
}
