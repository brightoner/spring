package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardService;

@Configuration  //자바파일을 설정파일로
public class ApplicationIocConfig {

	//	<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDao"/>
	// ==> 변경
	@Bean
	public IboardDao boardDao() {
		return new BoardDao();
	}
	
	
	/*
	<bean id="boardService" class="kr.or.ddit.board.service.BoardService">
		<property name="boardDao" ref="boardDao"></property>
	</bean>
	*/
	// ==> 변경
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		
		return boardService;
	}
	
	
}
