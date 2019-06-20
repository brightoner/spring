package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJunitTest {

	
	
	//boardDao : spring boardDao(scope=singleton)
	//boardDaoProtopyte : spring boardDaoPrototype(scope=singleton)
	//boardDaoProtopyte2 : spring boardDaoPrototype2(scope=singleton)
	// 1. boardDao boardDaoPrototype : spring bead id 가 다르므로 다른객체
	// 2. boardDaoPrototype boardDaoPrototype2 : 두 객체는 같은 스프링빈이지만 scope가  prototype이므로 다른객체 여아한다
	@Resource(name="boardDao")
	private IboardDao boardDao;
	
	@Resource(name="boardDaoPrototype")
	private IboardDao boardDaoPrototype;
	
	@Resource(name="boardDaoPrototype")
	private IboardDao boardDaoPrototype2;


	//field 기준 boardService, boardService2 : spring boardService bean (scope=singleton)
		//			 boardServiceConstructor : spring boardServiceConstructor bean (scope=singleton)
		// 1. boardService, boardService2 - 같아야함
		// 2. boardService, boardServiceConstructor - 같은클래서에서 만들어진 객체지만 spring singleton 개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor - 같은클래서에서 만들어진 객체지만 spring singleton 개념에 따라 다른 객체
	@Resource(name="boardService")	////application-ioc-st.xml 에서의 bean id값    //container로부터 주입받는다 - DI
	private IboardService boardService;
	
	@Resource(name="boardService")
	private IboardService boardService2;
	
	@Resource(name="boardServiceConstructor")
	private IboardService boardServiceConstructor;
	
	/**
	* Method : SpringIocTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 스프링 컨터이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		
		//위에 어노테이션 생성으로 인해 밑에 주석되는 부분의 필요없어진다 (SpringIocTest 와 비교!!!)
		
		/***Given***/
//		//스프링 컨테이너를 생성
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");

		/***When***/
//		IboardService boardService = (IboardService)context.getBean("boardService");
		String msg = boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
	
	}
	
	/**
	* Method : springSingletonScopoTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springSingletonScopoTest() {
		/***Given***/
		// 1. boardService, boardService2 - 같아야함
		// 2. boardService, boardServiceConstructor - 같은클래서에서 만들어진 객체지만 spring singleton 개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor - 같은클래서에서 만들어진 객체지만 spring singleton 개념에 따라 다른 객체

		/***When***/
		

		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);	// 객체끼리 비교 - 더블쿼테이션X
		assertNotEquals("boardService", boardServiceConstructor);
		assertNotEquals("boardService2", boardServiceConstructor);
		
	}
	
	@Test
	public void springPrototypeScopeTest() {
		/***Given***/
		// 1. boardDao boardDaoPrototype : spring bead id 가 다르므로 다른객체
		// 2. boardDaoPrototype boardDaoPrototype2 : 두 객체는 같은 스프링빈이지만 scope가  prototype이므로 다른객체 여아한다

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDao, boardDaoPrototype2);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
		
	}
	
	
	

}





















