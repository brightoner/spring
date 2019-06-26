package kr.or.ddit.prod.controller;

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
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.ControllerTestEnv;

public class ProdControllerTest extends ControllerTestEnv{


	@Test
	public void prodListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<ProdVo> prodList = (List<ProdVo>) mav.getModelMap().get("prodList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("prod/prodPagingList", viewName);
		assertEquals(10, prodList.size());
		assertEquals(8, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
		
		
	}

}
