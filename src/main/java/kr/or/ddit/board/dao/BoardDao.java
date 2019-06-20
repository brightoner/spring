package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDao implements IboardDao {

	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}

	

}
