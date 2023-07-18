## Join이란?
두 개 이상의 테이블을 연결해 데이터 검색할 때 사용하는 방법

    SELECT A.column1, B.column2
    FROM A, B
    WHERE 조건 


## Join 종류
-   Inner Join
	<img src="https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_INNER-JOIN.png" width = "300" height = "250"> </img>
	
-   Outer Join
    -   Left Join
    -   Right Join
    <img src = "https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_OUTER-JOIN.png" width = "270" height = "300"> </img> <img src = "https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/OUTER-JOIN_%EB%8D%94%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0.png" width = "270" height = "300"> </img>
    
-   Cross Join
	<img src = "https://hongong.hanbit.co.kr/wp-content/uploads/2021/11/%ED%98%BC%EC%9E%90-%EA%B3%B5%EB%B6%80%ED%95%98%EB%8A%94-SQL_CROSS-JOIN.png" width = "300" height = "250"> </img>

![JOIN 종류](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/b8YNAm/btrbCuoZPrI/lEIcImHWTIZs3nApw0Bqr0/img.png)


## 내부 조인 (Inner Join)

    1) 
	    SELECT 조회할 컬럼 
	    FROM 테이블1, 테이블2 
	    [WHERE 조건문] 
    2) 
	    SELECT 조회할 컬럼  
	    FROM 테이블1 
	    (INNER) JOIN 테이블2  
	    ON 테이블1.컬럼 = 테이블2.컬럼 
	    [WHERE 추가조건]

기준 테이블과 조인 테이블 모두에 조인 컬럼 데이터가 존재해야 조회 (교집합)
<br>

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/c7kLBI/btrbquYTQ0x/M4kso0nOk1n1kplQmyjHXk/img.png)

    SELECT  Sales.*,  Countries.Country  
	FROM  Sales  
	JOIN  Countries  
	ON  Sales.CountryID  =  Countries.ID

<br>

##  전체 외부 조인 (Full Outer Join)

    SELECT 조회할 컬럼 
    FROM 테이블1  
    FULL  OUTER  JOIN 테이블2  
    ON 조건문 
    [WHERE 추가조건문]

-   공통된 부분만 골라 결합하는 Inner Join 과 다르게 공통되지 않은 행도 유지(합집합)
- 이때 두 테이블 모두의 값을 유지하면 `Full Outer Join`  
    왼쪽 테이블 값만 유지하면 `Left Outer Join`  
    오른쪽 테이블 값만 유지하면 `Right Outer Join`
-   MySQL에서는 FULL OUTER JOIN을 지원하지 않으므로 LEFT OUTER JOIN 결과와 RIGHT OUTER JOIN결과를 UNION 하여 사용해야 함

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/PNNRB/btrbCtcAR3w/oXmkrdq6IHzv6NggOR0kl0/img.png)

    SELECT *  
	FROM instructor  
	FULL OUTER JOIN teaches  
	ON  instructor.id = teaches.id

<br>

## Left Outer Join

    SELECT 조회할 컬럼 
    FROM 기준테이블1  
    LEFT  OUTER  JOIN 테이블2  
    ON 조건문 
    [WHERE 추가조건문]


-   Left Join = Left Outer Join
-   왼쪽 테이블을 기준으로 일치하는 행만 결합되고, 일치하지 않는 부분은 null 값으로 채워짐.

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/x7aO7/btrbwg59qDG/CJu4asfXeRFAX7o0fm7NdK/img.png)

    SELECT *  
	FROM instructor  
	LEFT OUTER JOIN teaches  
	ON  instructor.id = teaches.id

<br>

## Right Join

    SELECT 조회할 컬럼 
    FROM 테이블1  
    RIGHT  OUTER  JOIN 기준테이블2  
    ON 조건문 
    [WHERE 추가조건문]

-   Right Join = Right Outer Join
-   오른쪽 테이블을 기준으로 일치하는 행만 결합되고, 일치하지 않는 부분은 null 값으로 채워짐.

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/kBoPJ/btrbpWOcIGj/ECN1TsGyFcMSOOVLE1C4ok/img.png)

    SELECT *  
	FROM instructor  
	RIGHT OUTER JOIN teaches  
	ON  instructor.id = teaches.id

<br>

## Cross Join

    1) 
	    SELECT 조회할컬럼 
	    FROM 테이블1, 테이블2  
    2) 
	    SELECT 조회할컬럼 
	    FROM 테이블1  
	    JOIN 테이블2  
    3) 
	    SELECT 조회할컬럼 
	    FROM 테이블1  
	    CROSS JOIN 테이블2

-   곱집합
-   두 테이블 데이터의 모든 조합
-   테이블1의 row * 테이블2의 row 개수만큼의 row를 가진 테이블 생성

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/lBWZk/btrbHcvQ7lK/ziZIwcRD2XedQRGznmbmUk/img.png)


### 요약 

-   조인은 두 개의 테이블을 서로 묶어서 하나의 결과를 만들어 내는 것을 말한다.
-   **INNER JOIN(내부 조인)**은 두 테이블을 조인할 때, 두 테이블에 모두 지정한 열의 데이터가 있어야 한다.
-   **OUTER JOIN(외부 조인)**은 두 테이블을 조인할 때, 1개의 테이블에만 데이터가 있어도 결과가 나온다.
-   **CROSS JOIN(상호 조인)**은 한쪽 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인하는 기능이다.
-   **SELF JOIN(자체 조인)**은 자신이 자신과 조인한다는 의미로, 1개의 테이블을 사용한다.