# SQL(RDB) vs NoSQL
### SQL(RDB)
#### - SQL(RDB)란?
* = 관계형 데이터베이스 = Relational Database
* 테이블, 행, 열의 정보를 구조화하여 모든 데이터를 2차원 테이블 형태로 표현하는 데이터베이스
* 데이터 간의 상관관계에서 개체간의 관계를 표현한 것 : 상호관련성을 가진 테이블의 집합
<br/>

#### - RDB 특징
* 유연성 <br/>
  : 전체 DB구조를 변경하거나 기존 애플리케이션에 영향을 주지 않고 필요할 때마다 간편하게 테이블, 관계 추가 및 삭제와 데이터 변경 가능
* 대량의 구조화된 데이터 관리에서 사용多 <br/>
  : 직관적인 데이터 표현 방법 제공 & 관련 데이터 포인트에 쉬운 액세스
* 사용 편의성 및 공동작업 가능 <br/>
  : 손쉬운 생성 및 이용, SQL을 사용하여 복잡한 쿼리 쉽게 실행 가능, 여러 사용자 동시에 데이터 운영 및 액세스 가능
* ACID 규정 준수 <br/>
  : ACID(원자성, 일관성, 격리, 내구성) 성능을 지원하므로 오류, 실패, 기타 잠재적 오작동에 관계없이 데이터 유효성 검사 가능
* 높은 데이터 독립성
* 데이터 베이스 정규화 <br/>
  : 고수준의 데이터 조작언어(DML)을 사용하여 결합, 제약, 투영 등의 관계 조작으로 데이터 중복성 감소 및 데이터 무결성 개선표현
* 기본 키를 통해 RDB의 모든 테이블 행에서 고유한 식별 가능 (기본 키에는 동일 값을 가지지 않음)
* 외래 키를 이용한 테이블 간 JOIN을 통해 정보 사이의 관계 또는 링크 설정 가능 <br/>
&ensp; -> 여러 데이터 포인트 간의 관계를 쉽게 이해하고 정보 얻기O
<br/>

#### - RDBMS란?
* = Relational Database Management System
* RDB를 생성, 수정, 관리하는 소프트웨어(시스템)
* DBMS의 한 종류로, 여러 개의 테이블을 조합해 원하는 데이터를 찾을 수 있게 함
* ex) MySQL, MariaDB, Oracle ..
<br/>

#### - RDB 작동 방식
<img src="https://lh3.googleusercontent.com/_j-DAQG6tx5MwOwhdNFkHMou4fWHRuEbzYr3wEaRClkCnC3W2TR8CnMsAvmVX-rgOICpWX-wrBPc=e14-rj-sc0xffffff-h1000-w1000"><br/>
> * 고객 테이블에서의 기본 키 : 고객 ID (고객 식별) / 주문 테이블에서의 기본 키 : 주문 ID (주문 식별)<br/>
> &nbsp;-> 주문 테이블에서 외래 키를 사용하여 고객 테이블의 고객 ID를 연결하여 고객과 주문 연결
> * 이제 두 테이블은 외래 키(고객 ID)를 기반으로 JOIN됨. <br/>즉, 두 테이블을 쿼리하여 공식 보고서를 만들거나 다른 애플리케이션에 데이터 사용이 가능. 예를 들어 소매 지점 관리자는 특정 날짜에 구매한 모든 고객에 관한 보고서를 생성하거나 지난 달에 배송일이 지연된 주문을 받은 고객 파악ㅇㅇ
<br/>

---
### NoSQL
#### - NoSQL이란?
* = 비관계형 데이터베이스 = Not Only SQL
* RDB와 가장 큰 차이점 : 데이터 저장 및 구성 방식
* SQL을 사용하지 않는 데이터베이스 관리 시스템
* RDB 형태의 관계형 데이터베이스(규칙 기반의 테이블 형식)가 아닌 다른 형태로 데이터 저장
* ex) mongoDB, redis ..
<br/>

#### - NoSQL 특징
* RDBMS와 달리 테이블 간 관계 정의 X
* 구조화되지 않은(비정형, 반정형) 데이터 처리
* 데이터 테이블은 그저 하나의 테이블로, 테이블 간의 관계가 정의되어 있지 않아 보통 테이블 간 JOIN 불가
* 자주 변경되는 데이터 저장 or 다양한 유형의 데이터 처리하는 애플리케이션에 적합
* Goal : Scale-Out(= 데이터 일관성 대신 비용을 우선시하여 여러 대의 데이터에 분산하여 저장)
<br/>

---
### SQL(RDBMS) VS NoSQL 비교
<img src="https://blog.kakaocdn.net/dn/IIPb5/btrhdB46EKO/9jt6cEx0FvjQRC4Bd4uStK/img.png"><br/><br/>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FBsH2t%2Fbtrhh7BICI9%2FV7uaNidKXgUtMK7VDmKNc0%2Fimg.webp"><br/><br/>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F3e33E%2FbtrheZdlk1s%2FFcU3U0Kz7KI0mV5sk6NPL1%2Fimg.png"><br/>
<br/>

---
### 참고
[[ DataBase ] RDB, RDBMS, SQL, NOSQL 간단 개념정리](https://im-designloper.tistory.com/67)<br/>
[관계형 데이터베이스란 무엇인가요?](https://cloud.google.com/learn/what-is-a-relational-database?hl=ko)<br/>
