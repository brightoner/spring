package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.main.model.MainVo;
import kr.or.ddit.user.model.UserVo;

/*
	servlet 
	 - extends HttpServlet
	 - servlet-mapping (web.xml, @WebServlet)
	 - service -> doXXX
	 
	spring
	 - pojo (Plain Old Java Object), @Controller
	 - @RequestMapping(class, method)
	 - @RequestMapping에 설정한 url method 호출
*/

@Controller
@SessionAttributes("rangers")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//메소드에 적용된 @ModelAttribute
	//@RequestMapping이 붙은 메소드가 실행될때(요청처리될때)
	//@ModelAttribute가 젇용괸 매소드가 리턴하는 값을 Model객체에 자동으로 넣어준다
	//localhost/main --> @RequestMapping("/main") : mainView --> Model에는 rangers속성이 들어가 있다
	//localhost/mainMav --> @RequestMapping("/mainMac") : mainViewMav --> Model에는 rangers속성이 들어가 있다
	@ModelAttribute("rangers")
	public List<String> rangers(){
		
		logger.debug("{}", "rangers()");
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
		
		return rangers;
	}
	
	/**
	* Method : mainView
	* 작성자 : PC22
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : main페이지 요청(viewName 사용)
	*/
	@RequestMapping("/main")
	//방법 1 : 매소드인자로 사용하는 방법 - @ModelAttribute("userVo")UserVo userVo
	public String mainView(Model model, @ModelAttribute("userVo")UserVo userVo) {
//	public String mainView(HttpServletRequest request) {
		logger.debug("mainView");
		logger.debug("model.asMap().get(\"rangers\") :{}", model.asMap().get("rangers"));
		logger.debug("userVo :{}", userVo);
		
		//방법 2 : 메소드 인자로 사용하지 않은경우의 방법 - @ModelAttribute("userVo")UserVo userVo)
//		UserVo userVo = new UserVo();
//		userVo.setName("브라운");
//		model.addAllAttributes("userVo", userVo);
		
		
		//prefix : /WEB-INF/views/  <-접두
		//subffix : .jsp  <- 접미
		
		//prefix + viewName + subffix
		// ==> /WEB_INF/views/main.jsp
		
		//아래 문장은 다음과 동일하다
//		request.setAttribute("mainUserId", "brown");
		model.addAttribute("mainUserId", "brown");
		
		//viewName
		return "main";
	}
	
	
	/**
	* Method : mainMav
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : main페이지 요청(ModelAndView 사용)
	*/
	@RequestMapping("/mainMav")
	public ModelAndView mainViewMav(@ModelAttribute("rangers") List<String> rangers) {
		
		logger.debug("ModelAndView :{}" , rangers);
		
		//viewName 을 기반으로 ModelAndView객체를 생성
		ModelAndView mav = new ModelAndView("main");
		
		//위 문장은 아래 두 문장과 같다
		//ModelAndView mav = new ModelAndView("main");
		//mav.setViewName("main");
		
//		model.addAttribute("mainUserId", "brown");
		mav.addObject("mainUserId","brown");
		
		return mav;
	}
	
	
	//@PathVariable - uri 일부를 변수 반환
	// localhost/main/pathvariable/brown
	// localhost/main/pathvariable/sally
	/**
	* Method : pathvariable
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : pathvariable로부터 사용자 아이디 가져오기(예- 도서관 사업소)
	*/
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvariable(@PathVariable("userId") String userId)  {
	
		logger.debug("userId :{}", userId);
		
		return "main";
	}
	
	//헤더정보
	/**
	* Method : header
	* 작성자 : PC22
	* 변경이력 :
	* @param accept
	* @return
	* Method 설명 : Accept header정보 가져오기
	*/
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/*, required = false*/) String accept) {
		
		logger.debug("Accept:{}", accept);
		
		return "main";
	}
	
	
	@RequestMapping("/main/view")
	public String view() {
		
		return "view";
	}
	
	
	//복수 parameter 받기(userId,name), 중첩된 parameter 받기(addr)
	//List<>타입은 @RequestParam적용
	@RequestMapping("/main/process")
	public String process(HttpServletRequest request, String[] userId, 
						@RequestParam("userId")List<String> userIdList, 
						String[] name, 		//@RequestParam("name")List<String> name, 
						MainVo mainVo) {
		
		String[] userIdArr = request.getParameterValues("userId");
	
		String userIdParameter = request.getParameter("userId");   //첫번째 값이 나온다
		logger.debug("userIdParameter :{}", userIdParameter);
		
		logger.debug("request.getParameterValues(\"userId\")");
		for(String u : userIdArr)
			logger.debug("userId : {}", u);
		
		logger.debug("String[] userId");
		for(String u : userId)
			logger.debug("userId :{}", u);
		
		logger.debug("userIdList");
		for(String u : userIdList)
			logger.debug("userIdList :{}", u);
		
		logger.debug("mainVo :{}", mainVo);
		
		return "main";
	}

	

}



