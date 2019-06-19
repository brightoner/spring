package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IboardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJunitTest {

	@Resource(name="boardService")	////application-ioc-st.xml 에서의 bean id값    //container로부터 주입받는다 - DI
	private IboardService boardService;
	
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

}
