<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>게시판 - 글 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css" href="../resources/board/write.css">
<link rel="stylesheet" type="text/css" href="../resources/board/home.css">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
</head>
<body>
	<header class="headerContainer">
		<!--상단 탭 만들기 뼈대구조-->
		<form action="home" method="post">
			<div class="headerContents">
				<!--상단 탭 내용물 감싼구조-->
				<div class="WestagramTag">
					<!--상단 좌측 내용물-->
					<a href="home"><i class="fab fa-instagram"></i> | <span>Wgt</span>agram</a>
				</div>
				<div class="headerSearchBar" style="border-radius: 5px;">
					<!--상단 중앙 내용물-->
					<i class="fas fa-search"></i> <input name="title" type="text"
						placeholder=" 게시글 검색" style="border: none; outline: none;"> <input
						type="submit" value="검색" style="display: none;">
				</div>
			</div>
		</form>
	</header>
	
	<div class="mypage">
		<a href="mypage"><img id="user"
			src="../resources/board/img/user.png" width="30px" height="30px"
			style="cursor: pointer;"></a>
	</div>
	<h3 style="text-align: center; margin-top: 35px;">게시글 수정</h3>
	<div class="write_area">
		<form action="update" method="get" enctype="multipart/form-data"
			style="text-align: center; display: grid; justify-content: center; margin-top: 40px;">
			<span>제목</span>
			<c:forEach var="item" items="${board}" varStatus="status">
			<input type="text" class="title_area" name="title" required value="${item.title}"
				maxlength="50"></input>
			<span>내용</span>
			<input type="text" class="content_area" name="content" required value="${item.content}"
				maxlength="150"></input>
			사진 선택 : <span><input type="file" accept=".jpg" name="file" value="${item.imgname}"
				required="required"><br></span> <input type="submit"
				class="update" value="수정하기" required="required"><br>
			</c:forEach>
		</form>
		<button class="go_home" onclick="location.href='mypage'">목록으로</button>
	</div>

</body>
</html>