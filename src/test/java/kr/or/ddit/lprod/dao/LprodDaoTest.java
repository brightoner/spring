package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class LprodDaoTest extends LogicTestEnv {
	
	@Resource(name="lprodDao")
	private IlprodDao lprodDao;

	/**
	* Method : lprodListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : lprod 전체 리스트 조회
	*/
	@Test
	public void lprodListTest() {
		/*** Given ***/

		/*** When ***/
		List<LprodVo> lprodList = lprodDao.lprodList();

		/*** Then ***/
		assertNotNull(lprodList);
		assertTrue(lprodList.size() >= 10);
		assertEquals(13, lprodList.size());

	}
	
	/**
	* Method : usersCntTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 :전제 lprod 조회 테스트
	*/
	@Test
	public void usersCntTest() {
		/***Given***/
		

		/***When***/
		int lprodCnt = lprodDao.lprodCnt();

		/***Then***/
		assertEquals(13, lprodDao.lprodCnt());
	}
	
	/**
	* Method : userPagingListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : lprod 페이징 리스트 조회 테스트
	*/
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);

		/***When***/
		List<LprodVo> lprodList = lprodDao.lprodPagingList(pageVo);

		/***Then***/
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
	}

}
