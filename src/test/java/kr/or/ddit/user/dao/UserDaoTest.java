package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:kr/or/ddit/config/spring/root-context.xml",
//						"classpath:kr/or/ddit/config/spring/application-datasource.xml",
//						"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
// ==> 어노테이션들을 LogicTestEnv extend해서 쓴다
public class UserDaoTest extends LogicTestEnv{

	@Resource(name="userDao")
	private IuserDao userDao;

	/**
	* Method : userListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Test
	public void userListTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userDao.userList();

		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size()>= 100);
		assertEquals(116, userList.size());
		
	}
	
	/**
	* Method : insertUserTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void insertUserTest(){
		/***Given***/
		//사용자 정보를 담고있는 vo객체 준비
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("대덕인2", "userTest3", "중앙로3", "userTest12345", "대전광역시 중구 중앙로76", "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		//userDao.insertUser()
		int insertCnt = userDao.insertUser(userVo);
				
		/***Then***/
		//insertCnt(1)
		assertEquals(1, insertCnt);
		
		//data 삭제
		//userDao.deleteUser(userId);
		int deleteCnt = userDao.deleteUser(userVo.getUserId());
		assertEquals(1, deleteCnt);

	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId ="brown";

		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운", userVo.getName());
		assertEquals("곰", userVo.getAlias());
	}
	
	/**
	* Method : usersCntTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 전체사용자수 조회 테스트
	*/
	@Test
	public void usersCntTest() {
		/***Given***/
		

		/***When***/
		int usersCnt = userDao.usersCnt();

		/***Then***/
		assertEquals(116, userDao.usersCnt());
	}
	
	@Test
	public void updataDataUserTest(){
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("더덕인", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩 2층 204호", "34940", sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		/***When***/
		int updateDataUser = userDao.updateDataUser(userVo);

		/***Then***/
		assertEquals(1, updateDataUser);
	}
	
	
	
	/**
	* Method : userPagingListTest
	* 작성자 : PC22
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 조회 테스트
	*/
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);

		/***When***/
		List<UserVo> userList = userDao.userPagingList(pageVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}
	
	

}
