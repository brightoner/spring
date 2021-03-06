<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
$(document).ready(function(){
	
	//컨트롤러 변경없이 스크립트에서만 "ko"기본설정
// 	if("${param.lang}" == ""){
// 		$("#lang").val("ko");
// 		$("#frm").submit();
// 	}else{
// 	//select box option 설정
// 	$("#lang").val("${param.lang}");
// 	}
// 	$("#lang").on("change", function(){
// 		$("#frm").submit();
// 	});

	//컨트롤러 변경 후 스크립트에서만 "ko"기본설정
	$("#lang").val("${lang}");
	$("#lang").on("change", function(){
		$("#frm").submit();
	});

	
});
</script>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">english</option>
		<option value="ja">日本語</option>
	</select>
</form>

GREETING : <spring:message code="GREETING"/><br>
VISITOR :  <spring:message code="VISITOR">
				<spring:argument value="brown"/>
		   </spring:message>
