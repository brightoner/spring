package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface IuserService {
	
	/**
	* Method : userList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 :사용자 전체 리스트 조회
	*/
	List<UserVo> userList();
	
	/**
	* Method : insertUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVo userVo);
	
	/**
	* Method : deleteUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);
	
	
	/**
	* Method : getUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보조회
	*/
	UserVo getUser(String userId);
	
	/**
	* Method : updateDataUser
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 수정
	*/
	int updateDataUser(UserVo userVo);
	
	/**
	* Method : userPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 페이징리스트처리
	*/
	Map<String, Object> userPagingList(PageVo pageVo);
	
	

}
