package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public interface IlprodDao {
	
	/**
	* Method : lprodList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 정보 조회
	*/
	List<LprodVo> lprodList();
	
	/**
	* Method : lprodPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lprod페이징 리스트 조회
	*/
	List<LprodVo> lprodPagingList(PageVo pageVo);
	
	/**
	* Method : lprodCnt
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 수 조회
	*/
	int lprodCnt();

}
