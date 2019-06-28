package kr.or.ddit.exception.controller;

import java.io.IOException;

import org.apache.ibatis.javassist.compiler.NoFieldException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.exception.NoFileException;

@Controller
public class ExceptionController {
	
	@RequestMapping("/exception")
	public String exception() {
		throw new ArithmeticException();
	}
	
//	@ExceptionHandler({ArithmeticException.class})
//	public String handleException() {
//		return "exception";
//	}
	
	@RequestMapping("/ioException")
	public String ioException() throws IOException {
		
		//mybatis-config-not_exists.xml파일이 존재하지 않기 때문에 예외발생(500error-서버 내부 오류)
		ClassPathResource cpr = new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config-not_exists.xml");
		cpr.getInputStream();
		
		return "main";
	}
	
	
	@RequestMapping("/nofileException")
	public String nofileException() throws NoFileException{
		
		//mybatis-config-not_exists.xml파일이 존재하지 않기 때문에 예외발생(500error-서버 내부 오류)
		try {
		ClassPathResource cpr = new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config-not_exists.xml");
		cpr.getInputStream();
		}catch(Exception e) {
			//발생한 예외를 개발자가 새로운 예외로 던진다
			throw new NoFileException();
		}
		return "main";
	}
	

}



