<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<title>LPROD리스트</title>

<!-- scc, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>

</head>

<body>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp" %>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">LPROD리스트</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>LPROD ID</th>
									<th>LPROD GU</th>
									<th>LPROD NAME</th>
								</tr>
								
								<c:forEach items="${lprodList }" var="vo" varStatus="status">
									<tr>
										<td>${vo.lprod_id }</td>
										<td>${vo.lprod_gu}</td>
										<td>${vo.lprod_nm }</td>
									</tr>
								</c:forEach>
															
							</table>
						</div>

						<a class="btn btn-default pull-right">사용자 등록</a>

						<!-- 사용자 수 : 105건 // 페이지 네이션 : 11건 -->
						<div class="text-center">
							<ul class="pagination">
							
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp }/lprod/pagingList?page=${pageVo.page -1 }&pageSize=${pageVo.pageSize }">«</a></li>
									</c:otherwise>
								
								</c:choose>
								
								<c:forEach begin="1" end="${paginationSize }" step="1" var="i">
								<c:choose>
									<c:when test="${pageVo.page ==i }">
										<li class="active">
											<span>${i }</span>
										</li>
									</c:when>
									<c:otherwise>
										<li>
									 		<a href="${cp}/lprod/pagingList?page=${i }&pageSize=${pageVo.pageSize }">${i }</a>
									 	<li>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								
								<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp}/lprod/pagingList?page=${pageVo.page +1 }&pageSize=${pageVo.pageSize }">»</a></li>
									</c:otherwise>
								</c:choose>
							
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
