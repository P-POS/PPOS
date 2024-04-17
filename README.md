# PPOS

## 프로젝트 소개

> 신한투자증권 프로 디지털 아카데미 JAVA 과정 미니 프로젝트
 <br/><br/>
> 주제: 마트 포스기 서비스 제작
 <br/><br/>
> 개발기간: 4일
 <br/><br/>
> 개발인원: 6명
 <br/><br/>


<br/>

## R&R

|                                 김현수                                  |                                   신의진                                    |                                   박유                                    |                                  정우성                                   |                                  이한슬                                   |                                  박예린                                   |
| :---------------------------------------------------------------------: | :-------------------------------------------------------------------------: | :-------------------------------------------------------------------------: | :-----------------------------------------------------------------------: | :-----------------------------------------------------------------------: | :-----------------------------------------------------------------------: |
| <img src = "https://avatars.githubusercontent.com/jkl0124" width=150px> | <img src = "https://avatars.githubusercontent.com/Tomk2d" width=150px> | <img src = "https://avatars.githubusercontent.com/yjp8842" width=150px> | <img src = "https://avatars.githubusercontent.com/NOEL-code" width=150px> | <img src = "https://avatars.githubusercontent.com/eehanseul" width=150px> | <img src = "https://avatars.githubusercontent.com/ye-s-rin" width=150px> |
|                 [@jkl0124](https://github.com/jkl0124)                  |               [@Tomk2d](https://github.com/Tomk2d)                |               [@yjp8842](https://github.com/yjp8842)                |                [@NOEL-code](https://github.com/NOEL-code)                 |                [@NOEL-code](https://github.com/eehanseul)                 |                [@NOEL-code](https://github.com/ye-s-rin)                 |


### 김현수

PM

`Backend` `판매`

> 판매 백엔드 구현

### 신의진

`View` `판매`

> 판매 프론트 구현


### 박유진

`View` `매출내역` `상품`

> 매출내역, 상품, 프론트 구현

### 정우성

BE Leader

`Backend` `매출내역` `상품`

> 매출내역, 상품, 백엔드 구현

### 이한슬

FE Leader

`View` `메인` `회원`

> 메인화면, 회원, 프론트 구현

 ### 박예린

 PL

`Backend` `메인` `회원`

> 메인화면, 회원, 백엔드 구현

## 주요 서비스

#### 거래서비스
#### 회원관리
#### 매출관리
#### 상품관리




<br/>
<br/>

## 프로젝트 MVC 구조 설명

#### MemberController
MemberView와 MemberService를 조작합니다. 클라이언트의 요청을 받고 적절한 서비스를 호출하여 비즈니스 로직을 수행한 후, 그 결과를 MemberView에 전달합니다. 

#### MemberService
MemberModel을 매개변수로 받고, 비즈니스 로직을 처리합니다. 데이터베이스나 다른 외부 시스템과의 상호작용을 담당하는 MemberRepository를 호출하여 데이터 액세스를 수행합니다. 

#### MemberModel
비즈니스 객체를 나타냅니다. 데이터를 저장하고 조작하는데 사용됩니다. 

#### MemberDAO(Data Access Object)
데이터베이스나 다른 영구 저장소에 접근하는 데 사용됩니다. 

#### MemberRepository 
패턴에서 데이터 액세스 작업을 추상화하고 캡슐화합니다. 

#### DTO(Data Transfer Object)
데이터 전송 객체입니다. 서비스 계층과 데이터 액세스 계층 간에 데이터를 전송할 때 사용됩니다. 

#### MemberRepository
MemberDAO의 구현체로서, 실제 데이터베이스 조작을 담당합니다. 데이터베이스와의 통신을 수행하고 데이터 액세스 작업을 구현합니다.

##### MemberServiceTest
JUnit을 활용하여 MemberService를 테스트합니다.

## MemberPopup

#### MemberPopupController
MemberPopupView와 MemberService를 조작합니다.
