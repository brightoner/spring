package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest {
	
	@Resource(name="bService")		//application-ioc-st.xml 에서의 bean id값
	private IboardService boardService;
	@Resource(name="bDao")			//application-ioc-st.xml 에서의 bean id값
	private IboardDao boardDao;

	@Test
	public void springIocStTest() {
		/***Given***/
		

		/***When***/
		boardService.getBoardDao();

		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
	
	}

}
