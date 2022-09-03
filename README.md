# Where-are-you-going-today
가게 온라인 웨이팅 예약 및 소규모 SNS를 구현한 프로젝트입니다. 모바일 어플 컨셉으로 제작하게 되었습니다!

# 개발 개요

평소 금요일이나 주말에 가고싶은 술집들은 인기가 많습니다.

인기가 많으니 빠르게 테이블이 꽉 차게 되고 어쩔수 없이 대기를 서거나 다른 가게를 찾아야하는데

대기를 서게 될때 술집 자체가 많이 바쁘기 때문에 대기를 하고 다른곳을 돌아다니면 자신의 차례가 되어도 따로 전화나 연락을 주지않는 가게들이 많습니다.

그렇기 때문에 현장 대기를 설수 밖에 없는데 기다리는 시간이 너무 오래 걸리기도하고 아깝습니다 또한 현장대기가 몇 팀있는지 알수가 없기 때문에 일일히 전화를 해서

물어보는것도 상당히 번거롭습니다. 그래서 저희는 이러한 불편함을 해결 하기 위해 온라인 웨이팅 프로젝트를 개발하게 되었습니다.

# 개발 환경

* 개발 기간 : 2022.08.08 ~ 2022.08.22

* 개발 인원 : 5명 ( 팀 프로젝트 )

* 사용 기술

   * Html5, CSS3, JavaScript
   * KAKAO API
   * Java 11
   * JSP / JSTL
   * Java Spring 3.9
   * MVC Pattern
   * Apache Tomcat 8.5
   * MySQL DataBase 8.0.29
   
* 사용 IDE
     * Eclipse
     * Atom
     
# 구현 내용

* 로그인 관련
   * [유저/점주 회원가입](#회원-가입)
   * [유저/점주 로그인/로그아웃](#로그인/로그아웃)
   * [회원정보 수정](#회원정보-수정)
   * [회원 탈퇴](#회원-탈퇴)
   
* 웨이팅 관련
  * [가게 웨이팅 예약](#가게-웨이팅-예약)
  * [가게 웨이팅 취소](#가게-웨이팅-취소)
  * [현재 가게 웨이팅 내역 조회](#가게-웨이팅-조회)
  * [현재 실시간 대기 인원 조회](#가게-대기인원-조회)
  * [점주측에서 웨이팅 일괄 삭제](#웨이팅-삭제)
  * [체크인 미확인시 자동 예약 취소](#자동-예약취소)
  
* 가게 관련 ( 담당 업무 )
  * [가게 등록](#가게-등록)
  * [자신의 가게 조회](#가게-조회)
  * [가게 정보 수정](#가게-정보-수정)
  * [메뉴 등록](#메뉴-등록)
  * [메뉴 수정](#메뉴-수정)
  * [메뉴 삭제](#메뉴-삭제)
  
* SNS 관련
  * 게시글 작성(#게시글-작성)
  * 게시글 수정(#게시글-수정)
  * 게시글 삭제(#게시글-삭제)
  * 게시글 검색(#게시글-검색)

* 지도(메인페이지)관련
  * 카카오API를 사용하여 지도 구현
  * 지도상의 키워드 검색으로 가게 검색
  * 키워드 검색시 가게정보,위치 및 메뉴정보 조회
  * 관리자 페이지 구현 ( 위도 , 경도 설정 )
  

# 가게 등록

제가 담당하게된 업무 입니다.

* 첫번째 폼

점주가 회원 가입을 한 후 가게를 등록하지 않았다면 바로 가게 등록 페이지로 이동하게 됩니다.

필요한 정보들을 입력 후 다음 버튼을 통해 정보들을 입력해 나가면 됩니다.


![image](https://user-images.githubusercontent.com/100820039/187572309-33f29c90-ee04-40ac-8e9b-91aa447ecb57.png)

주소 입력시 조금 더 편리하게 사용할 수 있게끔 카카오 주소 API를 사용 하였습니다.

![image](https://user-images.githubusercontent.com/100820039/187576513-accfb466-42f8-43ef-9ec3-91ce1ff5911c.png)

![image](https://user-images.githubusercontent.com/100820039/187577458-138925be-2a87-4c57-b90f-c6c561f23960.png)

* 두번째 폼

가게 영업시간, 가게 테이블 수 등 각자 포맷이 일정한 값들은 select box로 구현하여 사용성을 높혔습니다.

![image](https://user-images.githubusercontent.com/100820039/187577678-19bbf0ef-f082-43f8-827b-5f0b09e3cd71.png)

![image](https://user-images.githubusercontent.com/100820039/187577723-5b7d163e-2396-4a04-b0e0-25df7e095cb3.png)

![image](https://user-images.githubusercontent.com/100820039/187577731-dae66465-c63b-4d2e-8f41-c49b8b333081.png)

![image](https://user-images.githubusercontent.com/100820039/187577744-e948a2cc-f9bd-497c-ab5d-87e9ee805dc9.png)

* 세번째 폼

이 폼에서는 가게의 이미지를 점주가 업로드하게 됩니다. 이때 점주가 이미지 등록도중 취소를 할 수 도 있기 때문에 우선 서버의 임시 폴더로 이미지를 업로드하게 됩니다. 

업로드 할때 이미지 명이 겹칠 수 있는것을 생각해 UUID를 사용하여 랜덤 고유 식별값으로 이름을 지정해주어 업로드 하게 됩니다. 

최종적으로 가게 등록까지 완료 할 시 서버의 임시 폴더에서 실제 상점 이미지들이 보관되는 폴더로 이미지가 이동이 되게 구현을 하였습니다.

업로드를 하고 구현해놓은 취소 버튼을 누르면 로그인페이지로 이동하며 업로드했던 이미지들은 temp에서 모두 지워지게 구현하였습니다.

서버temp 누르지않고 사용자가 임의로 인터넷창을 꺼버리는등의 동작을 하였을때 서버 임시폴더안에 있는 이미지들이 그대로 남게되는 문제가 발생하였는데 아직

그것에 대한 데이터 처리를 하지 못한게 아쉬운 기능입니다.

![image](https://user-images.githubusercontent.com/100820039/187578180-5f8e9f06-646e-4342-98a5-f6946f1beec2.png)

이미지 업로드시 모든 확장자로 원래는 올릴 수 있게 됐는데 input의 accept 기능을 활용하여 .jpg파일만 업로드 할 수 있게 구현하였습니다.

![image](https://user-images.githubusercontent.com/100820039/188173222-882e1041-b7dc-4416-a024-aff6fa2b136f.png)

![image](https://user-images.githubusercontent.com/100820039/188173242-fbe95d91-3354-4e97-955e-483bdcf04695.png)

# 메뉴 등록

가게 이미지를 업로드 후에 메뉴 등록을 할 수 있게 구현 하였습니다. 메뉴의 정보를 간단히 받고 다음 버튼을 누르면 계속해서 메뉴를 등록 할 수 있으며 

최대 10개까지 등록 할 수 있게 구현했습니다.

![image](https://user-images.githubusercontent.com/100820039/188173258-94847106-04a4-49ca-aa64-52b387986954.png)

컨트롤러에서 올린 메뉴를 리스트에 저장하여 현재 올린 메뉴를 리스트에 담고 그 길이를 보여주어 현재까지 등록한 메뉴가 몇개인지 알려주게끔 구현하였습니다.

또한 여기서 메뉴를 등록한다고 바로 DB에 저장되는것이 아닌 메뉴를 10개 등록하거나 '여기까지만 등록하기' 버튼을 누르면 최종적으로 가게 등록이 완료되어 이때

DB에 값을 저장하게 하여 필요없는 쿼리전송을 제한하였습니다.

![image](https://user-images.githubusercontent.com/100820039/188173270-a69a381e-6992-4857-aa6f-05a63cfebd8a.png)

![image](https://user-images.githubusercontent.com/100820039/188173288-9dceed1b-faab-4afa-b12f-42c22ae63c24.png)

가게를 성공적으로 등록하게 되면 간단히 자신의 가게 정보를 보여주고 홈으로 돌아가기를 누르면 가게의 정보를 조회,수정 메뉴 추가, 수정, 삭제 할 수 있는

점주의 메인페이지로 가게 됩니다.

![image](https://user-images.githubusercontent.com/100820039/188173303-fcd242a2-123f-4481-9fa7-547473789fe4.png)

![image](https://user-images.githubusercontent.com/100820039/188173320-19561ce9-137c-4a7a-97db-3d0727d7ae39.png)

# 가게 조회

점주로 로그인했을 때 자신의 가게가 등록되어있으면 오게되는 메인 페이지입니다. 로그인시 점주의 ID를 session으로 받아와 점주의 ID로 Controller에서

그 ID의 점주의 가게가 있는지 검색 후 (DB와 통신) 가게가 있다면 그 정보를 모두 가져와 이 페이지로 정보를 보내주게 구현했습니다.

![image](https://user-images.githubusercontent.com/100820039/188173336-d871729b-2fcd-4b39-ba44-6be47c7ec087.png)

![image](https://user-images.githubusercontent.com/100820039/188173354-a6ef423f-3805-4aa6-9675-a3adca81f1c5.png)

# 가게 정보 수정

![image](https://user-images.githubusercontent.com/100820039/188173364-081e95be-dd24-4215-8dd7-8d1905d34fd1.png)

![image](https://user-images.githubusercontent.com/100820039/188173389-20980e1f-6b1c-4d75-b5bf-4b75fc45c19d.png)

![image](https://user-images.githubusercontent.com/100820039/188173402-def92837-6544-4886-b87d-d356876ad456.png)

![image](https://user-images.githubusercontent.com/100820039/188173415-08111075-c210-4280-9918-75968e262725.png)








