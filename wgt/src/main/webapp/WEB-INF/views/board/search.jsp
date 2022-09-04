<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*, java.text.*, java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page isELIgnored="false"%>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="../resources/board/home.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/mypage.css">
<link rel="stylesheet" type="text/css"
	href="../resources/board/banner.css">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">

<title>Board Search Home</title>
</head>
<body>

<c:if test="${ownerchk != true}">
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
	</c:if>

	<div class="header_form">
		<header class="headerContainer">
			<!--상단 탭 만들기 뼈대구조-->
			<form action="home" method="post">
				<div class="headerContents">
					<!--상단 탭 내용물 감싼구조-->
					<div class="bannerTag">
						<!--상단 좌측 내용물-->
						<a href="home"><i class="fa-solid fa-fork-knife"></i>
						<!--  |  -->
							<span class="shopname">${shop}</span></a>
					</div>
					<div class="headerSearchBar" style="border-radius: 5px;">
						<!--상단 중앙 내용물-->
						<i class="fas fa-search"></i> <input name="title" type="text"
							placeholder="검색" style="border: none; outline: none;"> <input
							type="submit" value="검색" style="display: none;">
					</div>
				</div>
			</form>
		</header>

		<div class="mypage">
			<c:if test="${ownerchk != true}">
				<a href="mypage"><img id="user"
					src="../resources/board/img/user.png" width="30px" height="30px"
					style="cursor: pointer;"> </a>
			</c:if>
			<c:if test="${ownerchk == true}">
				<a href="mypage">
					<!-- <img id="user"
			src="../resources/board/img/user.png" width="30px" height="30px"
			style="cursor: pointer; display:none;"> -->
				</a>
			</c:if>
			<c:if test="${ownerchk != true}">
				<a href="../map/map;"><img id="gohome"
					src="../resources/board/img/back.png" width="30px" height="30px"
					style="cursor: pointer;"> </a>
			</c:if>
			<c:if test="${ownerchk == true}">
				<a href="../shop/viewMyShop;"><img id="gohome"
					src="../resources/board/img/back.png" width="30px" height="30px"
					style="cursor: pointer;"> </a>
			</c:if>
		</div>

		<c:if test="${empty list}">
			<p class="searchresult">조회된 게시글이 없습니다.</p>
		</c:if>

		<div class="click">
			<div class="body1">
				<div class="feedReactionButton">
					<!-- <button class="heartbtn" onclick="addLike()"><i class="far fa-heart"></i></button> -->
					<div class="boardarea" style="margin-top: 20px;">
						<c:forEach var="item" items="${list}">
							<div class="write" style="margin-bottom: 30px;">
								<p
									style="position: relative; margin-bottom: 7px; display: none;">[No.${item.number}]
									${item.title}</p>
								<img src="../resources/board/img/upload/${item.imgname}.jpg"
									style="width: 370px; height: 330px; border-radius: 5px;"><br>
								<div class="likearea">
									<c:if test="${ownerchk != true}">
										<button>
											<img id="likeCSS"
												src="../resources/board/img/${item.likeImg}.png"
												width="30px" height="30px" style="cursor: pointer;">
										</button>
									</c:if>
									<c:if test="${ownerchk == true}">
										<button type="button">
											<img id="likeCSS" src="../resources/board/img/likeheart.png"
												width="30px" height="30px" style="cursor: pointer;">
										</button>
									</c:if>
									<div class="feedReaction">
										<span class="liketext">좋아요 <span class="likesresult"><input
												type="text" id="result" value="${item.likecount}"
												name="likecount"
												style="border: none; width: 7px; background: none;"
												onfocus="this.blur()"></span>개
										</span>
									</div>
								</div>
								<p class="searchregdate">
									<fmt:formatDate pattern="yy년MM월dd일 a hh:mm"
										value="${item.regDate}" />
								</p>
								<h4>${item.title}</h4>
								<br> ${item.content}<br>
							</div>
							<c:if test="${ownerchk == true}">
								<div class="update_delete_area"
									style="display: flex; justify-content: space-evenly; height: 30px; border-bottom: 2px solid #f1f1f1;">
									<div class="deletearea">
										<button class="deletebtn" type="button" value="삭제"
											onclick="delCheck(${item.number})"
											style="position: relative; left: 1%; margin: 0 auto; cursor: pointer;">삭제</button>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
	let like = document.querySelectorAll(".likebtn")
	
	for(let i = 0; i < like.length; i++){
		like[i].addEventListener('click', ()=> {
			like[i].classList.toggle('open')
		})
	}
	
	function delCheck(num) {
		const link = "delete?number=" + num;
		if(confirm("정말 삭제하시겠습니까?")){
			/*console.log(num);*/
 			alert("삭제를 완료하였습니다.");
			location.href=link;
		}else{
			alert("삭제를 취소하였습니다.")
		}
	};
</script>
<script>	
	const bodytoggle = document.querySelector(".header_form")
	const bodyClick = document.querySelector(".click")
	const toggleBtn = document.querySelector(".userInformation")
	const gnbBtn = document.querySelector("#gnb")
	
	function toggleHandler() {
			toggleBtn.classList.toggle("open")
			gnbBtn.classList.toggle("on")
			bodytoggle.classList.toggle("on")
		}
		
	function removeOn() {
			bodytoggle.classList.remove("on")
			toggleBtn.classList.remove("open")
			gnbBtn.classList.remove("on")
	 }
	
		toggleBtn.addEventListener("click", toggleHandler);
		bodyClick.addEventListener("click", removeOn);
</script>

</body>
</html>