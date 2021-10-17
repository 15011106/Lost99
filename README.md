# Lost-99

참여인원

Front-end : 장원배, 홍유미, 임동건

Back-end : 권민혁, 김가민

개발기간 : 2021.10.11 ~ 2021.10.16

개발언어:
-   Frontend : React
-   Backend : Spring


**목차**

[1. 프로젝트 설명](#프로젝트-설명)

[2. 시연 영상](#시연-영상)

[3. What I learned ](#what-i-learned)

[4. 주요 기능 구현 (API) ](#주요-기능-구현)


## 프로젝트 설명

미니프로젝트2 / 분실물 찾기 게시판으로 잃어버린 물건들을 게시판에 자유롭게 등록할 수 있습니다.


## 🎥시연 영상

 [Click](https://www.youtube.com/watch?v=qvm-y-gtcnk)
 
 

## 🔎What I learned

1. JWT 토큰을 이용한 로그인 (OAuth + JWT)

2. 프론트와 백엔드 서버 배포를 통한 협업 절차

3. 서로 다른 Origin의 API 요청에 따른 CORS 설정


## 🛠주요 기능 구현

|   기능             |Method                |URL              |  설명         |
|----------------|-------------------------------|-----------|-----------   |
|로그인   |`POST`             |/api/login          |  로그인 내용 전송          |
|회원가입      |`POST`            |/api/signup        |   회원가입 내용 전송      |
|게시글 작성 |	`POST` 	     |/api/article			|  리뷰 업로드   |
|댓글 작성|`POST`|/api/comment| 댓글 등록하기|
|게시글 조회(모든게시글)|`GET`           |/api/contents      |등록된 모든 게시글 조회|
|게시글 조회(로그인 회원)|`GET`          |/api/mypage|    '로그인 회원'의 게시글 조회  |
|게시글 조회(특정 게시글)|`GET`          |/api/detail|    '특정 게시글'의 상세정보 조회  |
|게시글 조회(로그인 회원)|`GET`          |/api/comment|    게시글의 댓글 조회  |
|게시글 삭제|`delete`|/api/article{id}|게시글 삭제|
|댓글 삭제|`POST`|/api/comment{id}|댓글 삭제|
|게시글 수정|`PUT`|/api/article{id}|게시글 수정|
|게시글 삭제|`PUT`|/api/comment{id}|댓글 수정|
