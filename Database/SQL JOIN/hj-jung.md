# SQL JOIN
### SQL문 기본 정의
#### - SQL이란?
* SQL = 관계형 데이터베이스에 정보를 저장하고 처리하는 표준화된 프로그래밍 언어
  * 구조적 쿼리 언어
  * 정보를 테이블 형식으로 저장 : 행과 열은 다양한 데이터 속성과 데이터 값 간의 다양한 관계 명시
<br/>
        
#### - SQL문이란?
* SQL문 = DBMS에서 이해하는 유효한 명령
  * SQL문 사용하여 DB에 정보 저장, 업데이트, 제거, 검색 가능
  * 구성 요소 : 식별자, 변수, 검색 조건 ..
  * 데이터베이스 SQL문
    ```sql
    -- 모든 데이터베이스 조회
    SHOW DATABASES;

    -- 데이터베이스 생성
    CREATE DATABASE practice DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

    -- 데이터베이스 삭제
    DROP DATABASE IF EXISTS practice;
    ```
  * 테이블 SQL문
    ```sql
    -- 테이블 조회하기
    use mysql;
    SHOW TABLES;

    -- 테이블 구조 확인하기
    DESC [테이블명];

    -- 테이블 생성하기
    CREATE DATABASE practice DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

    -- 조회 결과로 테이블 생성하기
    CREATE TABLE [생성할 테이블명] AS (SELECT * FROM [기존 테이블명]);

    -- 테이블 삭제하기
    DROP DATABASE IF EXISTS practice;
    ```
  * 데이터 SQL문
    ```sql
    -- 기본 데이터 조회하기
    SELECT * FROM [테이블명];
        
    -- 별칭 사용하기 (ORDER BY에 사용가능, WHERE에 사용불가)
    SELECT CustomerName AS 고객, Address AS 주소, PostalCode AS 우편번호 FROM Customers;
        
    -- 특정 행 특정 열 조회하기
    SELECT [열1, 열2, ...] FROM [테이블명] WHERE [행 선택 조건식]
    SELECT * FROM Customers WHERE Country = 'Germany';
    SELECT * FROM Orders WHERE ShipperID <> 2;
    SELECT * FROM OrderDetails WHERE Quantity > 100;
    SELECT * FROM Employees WHERE FirstName >= 'O';
    SELECT * FROM Employees WHERE BirthDate <= '1950-01-01';
        
    -- 특정 패턴 조회하기
    SELECT * FROM [테이블명] WHERE text LIKE '%우아한%';
    SELECT * FROM [테이블명] WHERE number BETWEEN 1 and 3;
    SELECT * FROM [테이블명] WHERE text IN (1, 2, 3);
    SELECT * FROM [테이블명] WHERE text IS NULL;
        
    -- 검색결과 일부 출력 후 정렬하기 (내림차순 DESC)
    SELECT [열1, 열2, ...] FROM [테이블명] ORDER BY [열1, 열2, ...] ASC LIMIT 5;
        
    -- 데이터 가공하기
    SELECT 1 - 2 + 2 * 3;
    SELECT MOD(10, 3)
    SELECT ROUND(30.60, 1)
    SELECT CONCAT('우아한', '형제들')  
    SELECT SUBSTRING('20190422', 1, 4)
    SELECT CURDATE();
    SELECT CURTIME();
        
    -- 데이터 집계하기
    SELECT COUNT(*) FROM [테이블명]
    SELECT DISTINCT [열명] FROM [테이블명]
    SELECT SUM([열명]) FROM [테이블명]

    -- 테이블 삭제하기
    DROP DATABASE IF EXISTS practice;
    ```
<br/>

---
### SQL JOIN
#### - JOIN이란?
* = 2개 이상의 테이블을 연결(결합)하여 원하는 데이터를 검색하는 방법
* 2개의 테이블을 마치 1개의 테이블인 것처럼 보여줌
* JOIN 위해서는 기본 키와 외래 키 관계로 맺어져아함 = 일대다 관계
* 여기서 SQL은 테이블 SQL문
<br/>

#### - JOIN 종류
* **INNER JOIN (내부 조인)**
  
  * 가장 많이 사용되는 JOIN, 보통 JOIN은 INNER JOIN 의미
  * 기준 테이블과 조인 테이블 모두에 조인 컬럼 데이터가 존재해야 조회됨 (ON절)
    
    ```sql
    SELECT <열 목록>
    FROM <첫 번째 테이블>
        INNER JOIN <두 번째 테이블>
        ON <조인 조건>
    [WHERE 검색 조건]

    #INNER JOIN을 JOIN이라고만 써도 INNER JOIN으로 인식
    ```
    <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_INNER-JOIN-768x768.png">
* **OUTER JOIN (외부 조인)**

  * 공통 부분만 결합하는 INNER JOIN과 다르게 공통되지 않은 행도 유지
  * INNER JOIN은 두 테이블 모두 테이터가 있어야만 결과가 나오지만 OUTER JOIN은 한쪽에만 데이터가 있어도 결과 도출
  * 1. **FULL** OUTER JOIN : 왼쪽 또는 오른쪽 테이블 모두의 모든 값이 출력되는 조인
    2. **LEFT** OUTER JOIN: 왼쪽 테이블의 모든 값만 출력되는 조인
    3. **RIGHT** OUTER JOIN: 오른쪽 테이블의 모든 값만 출력되는 조인
  * LEFT와 RIGHT OUTER JOIN은 해당 방향 테이블 기준으로 일치하는 행만 결합되고 일치하지 않는 부분은 null값으로..!
  * MySQL은 FULL OUTER JOIN X -> LEFT와 RIGHT의 결과를 UNION하여 사용해야 함

    ```sql
    SELECT <열 목록>
    FROM <첫 번째 테이블(LEFT 테이블)>
        <LEFT | RIGHT | FULL> OUTER JOIN <두 번째 테이블(RIGHT 테이블)>
          ON <조인 조건>
    [WHERE 검색 조건]
    ```
    <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/OUTER-JOIN_%EB%8D%94%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0-768x768.png">
    
* **CROSS JOIN (상호 조인)**

    * 한쪽 테이블의 모든 행과 다른쪽 테이블의 모든 행을 JOIN
    * 두 테이블 데이터의 모든 조합 = 곱집합
    * CROSS JOIN 결과의 전체 행의 개수 = 테이블1 행의 개수*테이블2 행의 개수(카티션 곱)

      ```sql
      SELECT *
      FROM <첫 번째 테이블>
          CROSS JOIN <두 번째 테이블>
      ```
      <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_CROSS-JOIN-768x768.png">
* **SELF JOIN (자체 조인)**

    * 자기 자신과 JOIN하는 것으로 1개의 테이블만 사용
    * 별도의 문법 X, 1개로 JOIN하면 SELF JOIN이 됨!
    * 다른 JOIN들과 달리 별칭을 필수로 입력해야 함

      ```sql
      SELECT <열 목록>
      FROM <테이블> 별칭A
          INNER JOIN <테이블> 별칭B
      [WHERE 검색 조건]
      ```
      <img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_SELF-JOIN-768x768.png">

---
### 참고
[SQL이란 무엇인가요?](https://aws.amazon.com/ko/what-is/sql/)<br/>
[SQL 기초 & 자주쓰는 쿼리문 정리](https://365kim.tistory.com/102)<br/>
[[MS SQL Server] #12_조인(JOIN)이란 무엇일까?, 기초적인 조인들!](https://doorbw.tistory.com/223)<br/>
[[DB] SQL - JOIN문, JOIN 종류(Inner Join,Natural Join,Outer Join,Croos Join)](https://doh-an.tistory.com/30)<br/>
[SQL 기본 문법: JOIN(INNER, OUTER, CROSS, SELF JOIN)](https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/)
