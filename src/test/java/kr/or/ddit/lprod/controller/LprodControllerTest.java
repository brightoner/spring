package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodControllerTest extends ControllerTestEnv{

	

	@Test
	public void lprodListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList").param("page", "1").param("pageSize", "10")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(10, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(1, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
		
		
	}

}
