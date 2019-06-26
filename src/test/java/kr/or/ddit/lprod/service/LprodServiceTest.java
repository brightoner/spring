package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodServiceTest extends LogicTestEnv {

	@Resource(name = "lprodService")
	private IlprodService lprodService;

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
		List<LprodVo> lprodList = lprodService.lprodList();

		/*** Then ***/
		assertNotNull(lprodList);
		assertTrue(lprodList.size() >= 10);
		assertEquals(13, lprodList.size());

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
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		
		//pageingList assert
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
		
		//paginationSize assert
		assertEquals(2, paginationSize);
	}

}
