<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.io.*, java.text.*, java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<link rel="stylesheet" href="resources/user/modify.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no,  maximum-scale=1.0, minimum-scale=1.0">
<title>회원정보 보기</title>
</head>

<body>
	<a class="back" href="<c:url value='/map/map'/>"><img src="resources/mapcss/img/backicon.png"></a>
	<form action="userInfo" method="post" enctype="multipart/form-data">
		<c:forEach var="user" items="${userList}">
		<div style="font-size: 100px; text-align: center; padding-top: 40px;">회원정보</div>
		<!-- required는 입력을 안할시 자동으로 입력하라고 알림창을 띄움 -->
		<div style="text-align: center; padding-top: 20px; font-size: 30px;">아이디 
		<input type="text" name="userId" value="${user.userId}" readonly></div><br> 
		
		<div style="margin-left: 55px; padding-top: 20px; font-size: 30px;">비밀번호 
		<input type="password" name="passwd" value="${user.passwd}" required></div><br>
		
		<div style="margin-left: 95px; padding-top: 20px; font-size: 30px;">이름 
		<input type="text" name="name" value="${user.name}" required></div><br>
		
		<div style="margin-left: 55px; padding-top: 20px; font-size: 30px;">주민번호 
		<input type="text" name="ssn" value="${user.ssn}" readonly></div><br>
		
		<div style="margin-left: 47px; padding-top: 20px; font-size: 30px;">전화번호 
		<input type="text" name="phone" value="${user.phone}" required></div><br>
		
		<div style="text-align: center; padding-bottom: 10px; font-size: 30px;">주소</div>
        <div><input type="text" name="addr1" value="${user.addr1}" id="sample6_postcode" placeholder="우편번호" style="margin-left: 55px;width: 280px;height: 20px;text-align: center;"></div><br>
        <div><input type="text" name="addr2" value="${user.addr2}" id="sample6_address" placeholder="주소" style="margin-left: 55px;width: 280px;height: 20px;text-align: center;"></div><br>
        <div><input type="text" name="addr3" value="${user.addr3}" id="sample6_detailAddress" placeholder="상세주소" style="margin-left: 55px;width: 280px;height: 20px;text-align: center;"></div><br>
        <input type="text" name="addr4" value="${user.addr4}" id="sample6_extraAddress" placeholder="참고항목" style="margin-left: 55px;width: 280px;height: 20px;text-align: center;">
		
		<div style="text-align: center; font-size: 30px; padding-top: 10px;">프로필 사진<br>
		<img src="resources/user/img/${user.imgName}.jpg" id="profileImg" name="imgName" style="width: 100px; height: 100px;" /><br></div>  
		
		</c:forEach>
	</form>
		<button type="submit" class="sBtn" onclick="location.href='modifyUser';">수정하기</button>
</body>

	<!-- 스크립트 영역 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript">
		// input 태그 (name이 file)를 가져옴 
		let fileTag = document.querySelector("input[name=file]");
		
		// 파일태그에 변화가 있을 때 실행될 함수 작성 
		fileTag.onchange = function () {
			
			let imgTag = document.querySelector("#profileImg");
			
			// 파일이 있는지 확인
			if(fileTag.files.length > 0) {
				// 파일을 선택한 경우 미리보기 생성 (이미지 태그 src에 데이터를 넣어주면 됨)
				let reader = new FileReader();
				
				// reader 읽어들이는 작업(onload)를 끝냈을 때 함수 실행, 읽어온 데이터를 함수의 파라미터로 줄 수 있음
				reader.onload = function (data) {
					console.log(data);
					imgTag.src = data.target.result;
				}
				
				reader.readAsDataURL(fileTag.files[0]);
			} else {
				// 취소 버튼 누를 경우
				imgTag.src = "";
			}
		}
		
		   function sample6_execDaumPostcode() {
		        new daum.Postcode({	
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수

		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }

		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    document.getElementById("sample6_extraAddress").value = extraAddr;
		                
		                } else {
		                    document.getElementById("sample6_extraAddress").value = '';
		                }

		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('sample6_postcode').value = data.zonecode;
		                document.getElementById("sample6_address").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("sample6_detailAddress").focus();
		            }
		        }).open();
		    }

	</script> 
</html>