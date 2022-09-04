<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>게시판 - 글 수정</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../resources/board/write.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/home.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/banner.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>마이페이지</title>
</head>
<body>

	<a class="userInformation"> <span></span></a>
	<nav id=gnb>
		<ul>
			<!-- userId를 세션으로 받아서 사용하세요  -->
			<li class="sub1"><span>${userId}님<br> 반가워요 !
			</span></li>
			<hr
				style="border: none; background-color: #DA0037; margin-bottom: 20px; height: 2px; width: 200px;">
			<li class="sub2">
				<form id="submitID" action="go_get_waiting" method="post">
					<a onclick="submit_form()">나의 웨이팅</a>
				</form>
			</li>
			<li class="sub3"><a onclick="location.href='/wgt/userInfo';">회원정보
					보기</a></li>
			<li class="sub4"><a onclick="location.href='/wgt/logOut';">로그아웃</a>
			</li>
		</ul>
	</nav>

	<div class="header_form">
		<header class="headerContainer">
			<!--상단 탭 만들기 뼈대구조-->
			<form action="home" method="post">
				<div class="headerContents">
					<!--상단 탭 내용물 감싼구조-->
					<div class="bannerTag">
						<!--상단 좌측 내용물-->
						<a href="home"><i class="fa-solid fa-fork-knife"></i> <!--  |  -->
							<span class="shopname">${shop}</span></a>
					</div>
					<div class="headerSearchBar" style="border-radius: 5px;">
						<!--상단 중앙 내용물-->
						<i class="fas fa-search"></i> <input name="title" type="text"
							placeholder=" 게시글 검색" style="border: none; outline: none;">
						<input type="submit" value="검색" style="display: none;">
					</div>
				</div>
			</form>
		</header>

		<div class="click">
			<div class="mypage">
				<a href="mypage"><img id="user"
					src="../resources/board/img/user.png" width="30px" height="30px"
					style="cursor: pointer;"></a>
			</div>
			<h3
				style="text-align: center; margin-top: 30px; margin-bottom: -15px;">게시글
				수정</h3>
			<div class="write_area">
				<form action="update" method="post" enctype="multipart/form-data"
					style="text-align: center; display: grid; justify-content: center; margin-top: 40px;">
					<input class="update" name="number" value="${board.number}"
						style="display: none;"> <span>제목</span>
					<textarea class="title_area" name="title" maxlength="50" required>${board.title}</textarea>
					<!-- input은 <>안에, textarea는 <>밖에 -->
					<span>내용</span>
					<textarea class="content_area" name="content" maxlength="150"
						required>${board.content}</textarea>
					사진 선택 : <span><input type="file" accept=".jpg" name="file"><br></span>
					<br>
					<img id="profileImg"
						src="../resources/board/img/upload/${board.imgname}.jpg"
						style="width: 50px; height: 50px; margin: 0 auto;">이미지 <input
						type="submit" class="update" value="수정하기" required="required"><br>
				</form>
				<button class="go_home" onclick="location.href='mypage'">목록으로</button>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	// 이미지 파일 수정
	// input 태그 (name이 file)를 가져옴 
	let fileTag = document.querySelector("input[name=file]");

	// 파일태그에 변화가 있을 때 실행될 함수 작성 
	fileTag.onchange = function() {

		let imgTag = document.querySelector("#profileImg");

		// 파일이 있는지 확인
		if (fileTag.files.length > 0) {
			// 파일을 선택한 경우 미리보기 생성 (이미지 태그 src에 데이터를 넣어주면 됨)
			let reader = new FileReader();

			// reader 읽어들이는 작업(onload)를 끝냈을 때 함수 실행, 읽어온 데이터를 함수의 파라미터로 줄 수 있음
			reader.onload = function(data) {
				console.log(data);
				imgTag.src = data.target.result;
			}

			reader.readAsDataURL(fileTag.files[0]);
		} else {
			// 취소 버튼 누를 경우
			imgTag.src = "";
		}
	}
</script>
</html>