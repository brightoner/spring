package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encryp.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	public IuserService userService;
	
	/**
	* Method : userList
	* 작성자 : PC22
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트
	*/
	@RequestMapping("/list")
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		
		return "user/userList";
	}
	
	
	/**
	* Method : userListExcel
	* 작성자 : PC22
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 엑셀 파일 다운로드(poi이용)
	*/
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model, String filename) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birthStr");
		
		filename = filename == null ? "excel" : filename;
		model.addAttribute("filename", filename);
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
	}
	
	
	/**
	* Method : userPagingList
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트
	*/
	@RequestMapping("/pagingList")
	//방법1
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page, 
//								@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
//								Model model) {
//		
//		PageVo pageVo = new PageVo(page,pageSize);
	
	//방법2 - 메소드 인자로 사용 - PageVo 에서 getter부분에서 변경해줌
	public String userPagingList(PageVo pageVo, Model model) {
		
		logger.debug("pageVo :{}", pageVo);
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("userList",userList );
		model.addAttribute("paginationSize",paginationSize);
		model.addAttribute("pageVo", pageVo);
		
//		return "user/userPagingList";
		return "tiles.userPagingList";
	}
	
	
	/**
	* Method : pagingListAjax
	* 작성자 : PC22
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 :사용자 페이징 리스트 ajax처리
	*/
	@RequestMapping("/pagingListAjax")
	public String pagingListAjax(PageVo pageVo, Model model) {
		
		model.addAttribute("data", userService.userPagingList(pageVo));
		
		//{data : {userList : [{userId : 'brown', name : '브라운'...}, {}, {}...., {}], paginationSize : 11}, pageVo : {page:1, pageSize : 10}}
		
		return "jsonView";
	}
	
	
	@RequestMapping("/pagingListAjaxHtml")
	public String pagingListAjaxHtml(PageVo pageVo, Model model) {
		
		model.addAttribute("data", userService.userPagingList(pageVo));
		
		//{data : {userList : [{userId : 'brown', name : '브라운'...}, {}, {}...., {}], paginationSize : 11}, pageVo : {page:1, pageSize : 10}}
		
		return "user/userPagingListAjaxHtml";
	}
	
	
	@RequestMapping("/pagingListAjaxView")
	public String pagingListAjaxView() {
		
		return "tiles.pagingListAjaxView";
	}
	
	
	
	/**
	* Method : user
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
//		UserVo userVo = userService.getUser(userId);
//		model.addAttribute("userVo", userVo);
		
		model.addAttribute("userVo", userService.getUser(userId));
		
		return "user/user";
	}
	
	
	/**
	* Method : userJson
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 json응답 (http://localhost/user/userJson?userId=brown)
	*/
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
		
//		UserVo userVo = userService.getUser(userId);
//		model.addAttribute("userVo", userVo);
		
		model.addAttribute("userVo", userService.getUser(userId));
		
		return "jsonView";
	}
	
	
	/**
	* Method : userForm
	* 작성자 : PC22
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록화면 
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록 ( properties - msg ==> validation)
	*/
//	@RequestMapping(path="/form", method = RequestMethod.POST)
	public String userForm(UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {		//UserVo userVo,BindingResult result 순서 꼭 지키기!!
//		public String userForm(UserVo userVo, String userId, @RequestPart("profile")MultipartFile file, Model model) {
		logger.debug("userForm profile");
		
		//유효성 검증
		new UserVoValidator().validate(userVo, result);
		//error 검사
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName ="";
		
		//위에 parameter인자로userId 선언 안할때 사용
//		UserVo dbUser = userService.getUser(userVo.getUserId());
		UserVo dbUser = userService.getUser(userId);
		
		if(dbUser ==null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			//비밀번호 암호화
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt == 1)
//				viewName = "redirect:/userPagingList";
				viewName = "redirect:/user/pagingList";
		}else {
			model.addAttribute("msg", "이미 존재하는 사용자 입니다");
			//doGet매서드 호출
			viewName = userForm();
		}
		return viewName;
	}
	
	
	/**
	* Method : userFormJsr
	* 작성자 : PC22
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록 (Jsr방법으로 validation)
	*/
	@RequestMapping(path="/form", method = RequestMethod.POST)
	public String userFormJsr(@Valid UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {		//UserVo userVo,BindingResult result 순서 꼭 지키기!!
//		public String userForm(UserVo userVo, String userId, @RequestPart("profile")MultipartFile file, Model model) {
		logger.debug("userForm profile");
		
		//error 검사
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName ="";
		
		//위에 parameter인자로userId 선언 안할때 사용
//		UserVo dbUser = userService.getUser(userVo.getUserId());
		UserVo dbUser = userService.getUser(userId);
		
		if(dbUser ==null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			//비밀번호 암호화
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);
			
			if(insertCnt == 1)
//				viewName = "redirect:/userPagingList";
				viewName = "redirect:/user/pagingList";
		}else {
			model.addAttribute("msg", "이미 존재하는 사용자 입니다");
			//doGet매서드 호출
			viewName = userForm();
		}
		return viewName;
	}
	
	
	
	/**
	* Method : profile
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @param request
	* @param response
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	*/
	@RequestMapping("/profile")
	//반환타입이 없다
	public String profile(String userId, Model model) throws IOException {
		
		//사용자 정보(path)를 조회
		UserVo userVo = userService.getUser(userId);
		
		/*
		// path정보로 file을 읽어 들여서
		ServletOutputStream sos = response.getOutputStream();

		FileInputStream fis = null;
		String filePath = null;

		// 사용자가 업로드한 파일이 존재할 경우 : path
		if (userVo.getPath() != null)
			filePath = userVo.getPath();

		// 사용자가 업로드한 파일이 존재하지 않을 경우 : no_image.gif
		else
			// webapp--> img--> no-_mage.gif
			filePath = request.getServletContext().getRealPath("/img/no_image.gif");

		File file = new File(filePath);
		fis = new FileInputStream(file);
		byte[] buffer = new byte[512];

		// response객체에 스트림으로 써준다
		while (fis.read(buffer, 0, 512) != -1) {
			sos.write(buffer);
		}

		fis.close();
		sos.close();
	}
		*/
		
		model.addAttribute("userVo", userVo);
		
		return "profileView";
		
		
	}
	
	
	/**
	* Method : userModify
	* 작성자 : PC22
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 :사용자 수정 화면 요청
	*/
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		
//		UserVo userVo = userService.getUser(userId);
//		model.addAttribute("userVo", userVo);
		model.addAttribute("userVo", userService.getUser(userId));
		
		return "user/userModify";
	}
	
	//사용자정보수정
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVo userVo, MultipartFile profile, HttpSession session, Model model,
							RedirectAttributes redirectAttributes) {
		
		//추후 ajax 요청으로 분리
//		userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		if(profile.getSize() > 0) {
			String filename= profile.getOriginalFilename();
			String ext = PartUtil.getExt(filename);
			
			String uploadPath = PartUtil.getUploadPath();
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVo.setPath(filePath);
			userVo.setFilename(filename);
			
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
		}
		int updateCnt = userService.updateDataUser(userVo);
		
		if(updateCnt == 1){
			session.setAttribute("msg", "등록되었습니다");
			
			redirectAttributes.addFlashAttribute("msg", "등록되었습니다");
			redirectAttributes.addAttribute("userId", userVo.getUserId()); 	//파라미터를 자동으로 붙여준다
//			return "redirect:/user/user?userId=" + userVo.getUserId();
			return "redirect:/user/user";
		}else {
			return userModify(userVo.getUserId(), model);
		}
	
	}
		
	
	
	
	
	
	

}











