package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

@Repository
public class ProdDao implements IprodDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : prodList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : prod 전체 리스트 조회
	*/
	@Override
	public List<ProdVo> prodList() {
		return sqlSession.selectList("prod.prodList");
	}

	/**
	* Method : prodPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 :prod 페이지 리스트 조회
	*/
	@Override
	public List<ProdVo> prodPagingList(PageVo pageVo) {
		return sqlSession.selectList("prod.prodPagingList", pageVo);
	}

	/**
	* Method : prodCnt
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 :prod 전체 수 조회
	*/
	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}


}
