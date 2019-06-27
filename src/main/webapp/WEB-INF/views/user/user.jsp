<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 날자 formet 변화 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<title>사용자 상세조회</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<script>
$(document).ready(function() {
   var msg = '${msg}';
   if(msg !='')
      alert(msg);
});
</script>


</head>



<body>
   <!-- header -->
   <%@include file="/WEB-INF/views/common/header.jsp"%>
   <div class="container-fluid">
      <div class="row">

         <!-- left -->
         <%@include file="/WEB-INF/views/common/left.jsp"%>

         <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
               <div class="col-sm-8 blog-main">
                  <h2 class="sub-header">사용자 상세</h2>
                  <form class="form-horizontal" role="form" action="${cp }/user/modify" method="get">
                           <input type="hidden" class="form-control" id="userId" name="userId" placeholder="사용자 아이디" value="${userVo.userId }">
					<div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
                        <div class="col-sm-10">
                           <img src="${cp}/profile?userId=${userVo.userId}" ></img>
                        </div>
                     </div>
      
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label" >사용자 아이디</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.userId }</label>
                           
                        </div>
                     </div>
                     
      
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.name }</label>
                        </div>
                     </div>
                     
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">별명</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.alias }</label>
                        </div>
                     </div>
                     
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">주소</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.addr1 }</label>
                        </div>
                     </div>
                     
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">상세주소</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.addr2 }</label>
                        </div>
                     </div>
                     
 						<div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">우편번호</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.zipcd }</label>
                        </div>
                     </div>
                     
                     <div class="form-group">
                        <label for="userNm" class="col-sm-2 control-label">생일</label>
                        <div class="col-sm-10">
                           <label class="control-label">${userVo.birthStr }</label>
<%--                            <label class="control-label"><fmt:formetDate value="${userVo.birth }" pattern="yyyy-MM-dd"></label> --%>
                        </div>
                     </div>
      
                     <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                           <button type="submit" class="btn btn-default">사용자 수정</button>
                        </div>
                     </div>
                  </form>   

               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</html>






<%-- 


<% 
                        PageVo pageVo= (PageVo)request.getAttribute("pageVo");
                        int paginationSize = (Integer)request.getAttribute("paginationSize");
                        if(pageVo.getPage()==1){%>
                           
                        <li class="prev disabled"><span>«</span></li>
                        <% }else{%>
                        <li class="prev"><a href="${cp}/userPagingList?page=<%=pageVo.getPage()-1 %>&pageSize=<%= pageVo.getPageSize()%>">«</a></li>
                        <%   
                        }
                        
                        
                        
                        //내가 현재 몇번째 페이지에 있는가?
//                         PageVo pageVo= (PageVo)request.getAttribute("pageVo");
                        for(int i=1; i<=paginationSize; i++){
                           if(i == pageVo.getPage()){
                           %>
                              <li class="active"><span><%=i %></span></li>
                           <% }else{%>
                              
                           <li><a href="${cp}/userPagingList?page=<%=i %>&pageSize=<%= pageVo.getPageSize()%>"><%=i%></a></li>
      
                           <% }
                        }
                        
                        if(pageVo.getPage()==paginationSize){%>
                        
                        <li class="next disabled"><span>»</span></li>
                        <% }else{%>
                        <li class="next"><a href="${cp}/userPagingList?page=<%=pageVo.getPage()+1 %>&pageSize=<%= pageVo.getPageSize()%>">»</a></li>
                        <%   
                        }
                        
                     %>
                     
--%>