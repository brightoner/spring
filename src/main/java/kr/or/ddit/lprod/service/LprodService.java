package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

@Service
public class LprodService implements IlprodService {
	
	@Resource(name="lprodDao")
	private IlprodDao lprodDao;

	/**
	* Method : lprodList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 :lprod 전체 리스트 조회
	*/
	@Override
	public List<LprodVo> lprodList() {
		return lprodDao.lprodList();
	}

	/**
	* Method : lprodPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lpord 페이징 리스트 조회
	*/
	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		//1.List<UserVo>, userCnt 를 필드로 하는 vo
		//2.List<Object> resultList = new ArrayList<Object>();   <--주의!!
		// result.add(userList);
		// result.add(userCnt);
		//3.Map<String, Object> resultMap = new HashMap<String, Object>();  <-- 추천!!!!!!!
		//  resuqltMap.put("userList", userList);
		//  resuqltMap.put("userCnt", userCnt);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		
		//lprodCnt 대신에 paginationSize로 변경
		int lprodCnt = lprodDao.lprodCnt();
		//pageSize 대신에 pageVo.getPageSize(); 로 변경
		int paginationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}



}
