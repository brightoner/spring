package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.jar.Attributes.Name;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml",
//						"classpath:kr/or/ddit/config/spring/root-context.xml",
//						"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
//						"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
//일반자바환경 --> 웹환경
//applicationContext --> 웹환경의 applicationContext 생성
public class MainControllerTest extends ControllerTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);

	//@Autowired 는 타입이 같은것을 검색!! 단 하나만 존재해야한다
	//	ex) IuserDao 를 UserDao 한개만 implement하면 가능하지만, UserDao2도 존재하면 사용X
	//여러개일때는 어노테이션 을 더 추가해야함
	@Autowired
	private WebApplicationContext ctx;	//spring container
	//브라우저에서 요청과 응답을 의미하는 객체
	private MockMvc mockMvc;			//dispatcher servlet
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	

	//test 두가지중 편한거 하나 사용
	
	/**
	* Method : mainViewTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : MainView 호출 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();	//mockMvc 가 DispacherServlet이다!! 중요!!
																			//preferences - favorite 에 추가해야 사용가능!!
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("mainUserId");
		
		/***Then***/
		assertEquals("tiles.main", viewName);
		assertEquals("brown", userId);
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVo"));
		
	}
	
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		/***Given***/
		

		/***When***/
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk())
				.andExpect(view().name("tiles.main"))
				.andExpect(model().attribute("mainUserId", "brown"))
				.andExpect(model().attributeExists("rangers"))
				.andExpect(model().attributeExists("userVo"));
				
									 
		/***Then***/
		
	}
	
	/**
	* Method : mainViewMavTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : ModelAndView객체를 이용한 main페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		//viewName 이 기대하는 문자열로 리턴되는지
		assertEquals("main", mav.getViewName());
		
		logger.debug("mav.getModel :{}", mav.getModel());
		
		//model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mav.getModel().get("mainUserId"));
		//@RequestMapping("/mainMav")전에 @ModelAttribute("rangers")가 먼저 실행되서 rangers값이 들어간다
		assertNotNull(mav.getModel().get("rangers"));
		
	}
	
	/**
	* Method : pathVariableTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : @PathVariable 테스트
	 * @throws Exception 
	*/
	@Test
	public void pathVariableTest() throws Exception {
	
		mockMvc.perform(get("/main/pathvariable/brown"))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
	}
	
	/**
	* Method : pathVariableTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : @PathVariable 테스트
	 * @throws Exception 
	*/
	@Test
	public void pathVariableTest2() throws Exception {
		
		mockMvc.perform(get("/main/pathvariable/sally"))
					.andExpect(status().is(200))
					.andExpect(view().name("main"));
	}
	
	/**
	* Method : requestHeaferTest
	* 작성자 : PC22
	* 변경이력 :
	* @throws Exception
	* Method 설명 : @RequestHeafer test
	*/
	@Test
	public void requestHeaferTest() throws Exception {
		
		mockMvc.perform(get("/main/header").accept("text/html"))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
	}
	
	

}
