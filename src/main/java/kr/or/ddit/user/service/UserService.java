package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVo;

@Transactional
@Service
public class UserService implements IuserService {
	
	@Resource(name="userDao")
	private IuserDao userDao;

	/**
	* Method : userList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 :사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVo> userList() {
		return userDao.userList();
	}

	/**
	* Method : insertUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	/**
	* Method : deleteUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	/**
	* Method : getUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보조회
	*/
	@Override
	public UserVo getUser(String userId) {
		return userDao.getUser(userId);
	}

	/**
	* Method : updateDataUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	@Override
	public int updateDataUser(UserVo userVo) {
		return userDao.updateDataUser(userVo);
	}

	/**
	* Method : userPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 페이징 처리에 관한것 모두 사용
	*/
	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		//1.List<UserVo>, userCnt 를 필드로 하는 vo
		//2.List<Object> resultList = new ArrayList<Object>();   <--주의!!
		// result.add(userList);
		// result.add(userCnt);
		//3.Map<String, Object> resultMap = new HashMap<String, Object>();  <-- 추천!!!!!!!
		//  resuqltMap.put("userList", userList);
		//  resuqltMap.put("userCnt", userCnt);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVo));
		
		//usersCnt 대신에 paginationSize로 변경
		int usersCnt = userDao.usersCnt();
		//pageSize 대신에 pageVo.getPageSize(); 로 변경
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}
	

	
	

}
