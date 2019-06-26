package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IprodDao {
	
	/**
	* Method : prodList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : prod 전체 정보 조회
	*/
	List<ProdVo> prodList();
	
	/**
	* Method : prodPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 :prod 체이징 리스트 조회
	*/
	List<ProdVo> prodPagingList(PageVo pageVo);
	
	/**
	* Method : prodCnt
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : prod 전체 수 조회
	*/
	int prodCnt();

}