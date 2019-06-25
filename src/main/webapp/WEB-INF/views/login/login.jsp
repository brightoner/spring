<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${cp}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
	
	<!-- 05.28 -->
	<!-- jQery CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${cp}/js/js.cookie.js"></script>
	
	<script>
		//******개념중요!!!
// 		//rememberme (checkbox)가 밑에 있어서 나오기전에  먼저 호출됨 -->  출력이 제대로 안된다!!
// 		console.log("remember me" + $("#rememberme").html());
// 		//해결방안 - (document).ready 는 모든 함수가 실행된 후 마지막에 실행
// 		$(document).ready(function(){
// 			console.log("remember me" + $("#rememberme").html());
// 		});
		
	
		//(document).ready 는 모든 함수가 실행된 후 마지막에 실행 - ***중요!!!!!!
		$(document).ready(function(){
			//문서로 로딩이 완료되고 나서 실행되는 부분
			//rememberme checkbox
			//1. rememberme cookie가 있는지?? 있으면 값이 true인지 확인
			//1-1. rememberme 가 true이면 input id="rememberme" 체크박스를 체크
			
			var rememberme = Cookies.get("rememberme");   //getCookie("rememberme");
			if(rememberme == "true"){
				$("#rememberme").prop("checked", true);
				$("#userId").val(Cookies.get("userId"));
				$("#password").focus();
			}
			
			//signin button 클릭시 실행되는 핸들러
			$("#signinBtn").on("click", function(){
				//만약, rememberme 체크 박스가 체크 되어 있는경우
				//  사용자 아이디 값을 userId쿠키로 저장
				//  true값을 rememberme cookie 값으로 저장
// 				if($("#rememberme").is(":checked")){
// 					Cookies.set("userId", $("#userId").val(),{expires : 30} );
// 					Cookies.set("rememberme", "true", {expires : 30});
				
				//만약, 체크 박스가 해제 되어 있는경우
				//  userId, rememberme cookie 값을 삭제
// 				}else{
// 					Cookies.remove("userId");
// 					Cookies.remove("rememberme");
// 				}
				
				//실제 로그인 요청을 서버로 전송
				$("#frm").submit();
				
				
			});
			
		});
		
		//쿠키 저장
		//expires : 현재 날자부터 몇일동안 유효한지 일자(정수)
		function setCookie(cookieName, cookieValue, expires){
			var dt = new Date();
			dt.setDate(dt.getDate() + parseInt(expires));
			
			document.cookie = cookieName + "=" + cookieValue + "; path=/; expires=" 
				+ dt.toGMTString();
		}
		
		//쿠키 삭제 - expires 에서 -값을 넣어주면 된다(현재시간에서 - 값을 넣어주면 값이 없어지므로)
		function deleteCookie(cookieName){
			setCookie(cookieName, "", -5);
		}
		
		//쿠키 이름에 해당하는 쿠키값을 조회
		function getCookie(cookieName){
			//String[] cookieArray = CookieUtil.cookieString.split("; ");
			var cookieArray = document.cookie.split("; ");
			
			//String cookieValue="";
			var cookieValue = "";
			
			//for(String str : cookieArray){
			for(var i = 0; i < cookieArray.length; i++){
				//if(str.startsWith(cookie+"=")){
					var str = cookieArray[i];
					if(str.startsWith(cookieName+"=")){
						//String[] cookieStr = str.split("=");
						var cookieStr = str.split("=");
					
						//cookieValue = cookieStr[1];
						cookieValue = cookieStr[1];
					
						break;
				}
			}
			return cookieValue;
			
		}
	</script>

  </head>

  <body>

    <div class="container">
    
      <form id="frm" class="form-signin" action="${cp}/login" method="post">
	<%-- <form id="frm" class="form-signin" action="${cp}"> --%>
       
        <h2 class="form-signin-heading">Please sign in</h2>
         
        <label for="userId" class="sr-only" >userId</label>
        <input type="text" id="userId" class="form-control" placeholder="userId" name="userId" required value="${param.userId }">
        
        <label for="inputPassword" class="sr-only" >Password</label>
        
        <input type="password" id="password" class="form-control" placeholder="Password" required name="password"  value="brown1234">
       
        <div class="checkbox">
          <label>
            <input id="rememberme" name="rememberme" type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id="signinBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
    