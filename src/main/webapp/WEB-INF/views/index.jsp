<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : pageContext.request.session.getAttribute('id')}" />
<c:set var="loginOutLink" value="${sessionScope.id == null ? '/login/login' : '/login/logout'}" />
<c:set var="loginOut" value="${sessionScope.id == null ? '로그인' : '로그아웃'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>홈</title>

	<link rel="stylesheet" href="<c:url value='/css/menu.css' />">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

</head>
<body>

<div id="menu">
	<ul>
		<li id="logo">빠른 게시판</li>
		<li><a href="<c:url value='/' />">홈</a></li>
		<li><a href="<c:url value='/board/list' />">게시판</a></li>
		<li><a href="<c:url value='${loginOutLink}' />">${loginOut}</a></li>
		<li><a href="<c:url value='/register/add' />">회원가입</a></li>
		<li><a><i class="fas fa-search"></i></a></li>
	</ul>
</div>

<div style="text-align:center">
	<h1>여기는 홈입니다.</h1>
	<h1>여기는 홈입니다.</h1>
	<h1>여기는 홈입니다.</h1>
</div>
</body>
</html>
