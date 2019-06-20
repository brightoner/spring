package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IboardDao;

public class BoardService implements IboardService {
	
	//filed 명
	private IboardDao boardDao;
	
	//생성자 주입(application-ioc-test.xml 에서)
	public BoardService(IboardDao boardDao) {
		this.boardDao = boardDao;
	}

	//기본생성자 <--- 만드는습관!!!
	public BoardService() {
		
	}
	
	
	public IboardDao getBoardDao() {
		return this.boardDao;
	}

	//setter 주입 (application-ioc-test.xml 에서)
	public void setBoardDao(IboardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}

}
