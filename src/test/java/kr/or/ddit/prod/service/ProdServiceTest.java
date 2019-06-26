package kr.or.ddit.prod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest extends LogicTestEnv{

	@Resource(name="prodService")
	private IprodService prodService;

	@Test
	public void prodListTest() {
		/*** Given ***/

		/*** When ***/
		List<ProdVo> prodList = prodService.prodList();

		/*** Then ***/
		assertNotNull(prodList);
		assertTrue(prodList.size() >= 50);
		assertEquals(74, prodList.size());

	}
	
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);

		/***When***/
		Map<String, Object> resultMap = prodService.prodPagingList(pageVo);
		List<ProdVo> prodList = (List<ProdVo>) resultMap.get("prodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		
		//pageingList assert
		assertNotNull(prodList);
		assertEquals(10, prodList.size());
		
		//paginationSize assert
		assertEquals(8, paginationSize);
	}
	
	
}
