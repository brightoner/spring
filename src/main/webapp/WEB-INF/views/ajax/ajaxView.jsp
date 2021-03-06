<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
$(document).ready(function() {
	console.log("ready");
	//requestData클릭시 이벤트 핸들러
	$("#requestData").on("click", function() {
		$.ajax({
			url : "/ajax/requestData",
			methoe : "post",
			success : function(data) {
				//pageVo.page, pageVo.pageSize
				$("#page").text(data.pageVo.page);
				$("#pageSize").text(data.pageVo.pageSize);

			}
		});
	});
	
	//requestDataResponseBody클릭시 이벤트 핸들러
	$("#requestDataResponseBody").on("click", function() {
		$.ajax({
			url : "/ajax/requestDataResponseBody",
			methoe : "post",
			success : function(data) {
				console.log(data);
				//pageVo.page, pageVo.pageSize
				$("#pageResponseBody").text(data.page);
				$("#pageSizeResponseBody").text(data.pageSize);

			}
		});
	});
	
	
	//유저 클릭시 이벤트 핸들러
	$("#user").on("click", function(){
		$.ajax({
			url : "/ajax/user",
			method : "post",
			data : "userId=" + $("#userId").val(),
			success : function(data){
// 				console.log(data);
			/*
			name: <input type="text" id="name" readonly/>
			allis : <input type="text" id="alias" readonly/>
			birth : <input type="text" id="birth" readonly/>
			
			$("#name").val(data.userVo.name);
			$("#alias").val(data.userVo.alias);
			$("#birth").val(data.userVo.birth);
			*/
			
			var html="";
			html += "name : <input type =\"text\" id=\"name\" readonly value=\"" + data.userVo.name + "\"/>";
			html += "alias : <input type =\"text\" id=\"alias\" readonly value=\"" + data.userVo.alias + "\"/>";
			html += "birth : <input type =\"text\" id=\"birth\" readonly value=\"" + data.userVo.birth+ "\"/>";
				
			$("#userJsonInfo").html(html);
			
			}
		});
	});
	
	//userHtml클레스 이벤트 핸들러
	$("#userHtml").on("click", function(){
		$.ajax({
			url : "/ajax/userHtml",
			method : "post", 
			data : $("#frm").serialize(),
			success : function(data){
// 				console.log(data);
				$("#userInfo").html(data);
			}
		});
	});
	
	//전송할 json객체를 준비
	/*
	public class UserVo(){
		private String userId;
		private String pass;
		public String userId(){........}
	}
	UserVo user+ new UserVo();
	user.setUserId("brown")
	user.setPass("brown1234")
	*/
	var user = {userId:"brown", pass:"brown1234"};
	//JSON.stringify():자바스크립트 객체를 json문자열로 생성
	//JSON.parse("json문자열") : json문자열을 다바 스크립트 객체로 변경
	$("#userFormString").text("userId=brown&pass=brown1234");
	$("#userJsonString").text(JSON.stringify(user));
	
	//@ResponseBody 데이터 전송
	$("#userJsonStringBtn").on("click", function(){
		$.ajax({
			url : "/ajax/requestBody",
			method : "post",
			contentType : "application/json",	//ajax를 통해서 데이터를 보내는 형식이 json임을 알려준다
// 			dataType : "xml",		//xml은 잘 사용X
			dataType : "json",		//server측으로 부터 받고자하는 데이터 타입(Accept 헤더)
			data : JSON.stringify(user),
			success : function(data){
				console.log(data);
				
				//xml
// 				$("#userJsonResult.userId").text(data.getElementsByTagName("userId")[0].childNodes[0].textContent);
// 				$("#userJsonResult.pass").text(data.getElementsByTagName("pass")[0].childNodes[0].textContent);
				
				//json
				$("#userJsonResult.userId").text(data.userId);
				$("#userJsonResult.pass").text(data.pass);
			}
		});
	});
	
	
});
</script>

<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a><br>
page :
<span id="page"></span>
<br>
pageSize :
<span id="pageSize"></span>


<h2>ajax json 데이터 요청(@responseBody)</h2>
<a id="requestDataResponseBody">데이터 가져오기</a><br>
page :
<span id="pageResponseBody"></span>
<br>
pageSize :
<span id="pageSizeResponseBody"></span>




<h2>ajax json 데이터 요청(user)</h2>
<a id="user">데이터 가저오기</a><br>
userId : <input type="text" id="userId" value="sally"/>
<div id="userJsonInfo"></div>


<h2>ajax html 데이터 요청(user)</h2>
<a id="userHtml">데이터 가저오기</a><br>
<form id="frm">
	userId : <input type="text" id="userIdHtml" name="userId" value="brown"/>
</form>
<div id="userInfo"></div>


<h2>ajax json 데이터 보내기</h2>
<a id="userJsonStringBtn">데이터 보내기</a><br>
요청보내는 데이터(기존) : <div id="userFormString"></div>
요청 보내는 데이터 :  <div id="userJsonString"></div>
받는데이터 :  
<div id="userJsonResult">
	userId : <span class="userId"></span><br>
	pass : <span class="pass"></span>
</div>











