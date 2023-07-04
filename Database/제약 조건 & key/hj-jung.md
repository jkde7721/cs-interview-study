## 테이블(Table)
### 테이블이란?
* 데이터베이스의 기본적인 데이터 저장 단위 => 행과 열로 구성된 정보 모음
* 사용자가 접근 가능한 모든 데이터 보유
* 시스템 내에서 독립적으로 사용되길 원하는 엔티티 표현 가능<br/>
ex) 회사에서의 고용자, 제품에 대한 주문
* 두 엔티티간의 관계 표현<br/>
ex) 회사에서의 고용자 & 그들의 숙련도 / 제품 & 주문의 관계


### 테이블 구조
* **행(row)**
  * = 관계된 데이터들의 묶음 (가로 칸)
  * 레코드(record), 튜플(tuple) <br/>
   : 릴레이션이 나타내는 엔티티(entity)의 특정 인스터스에 관한 값들의 모임. 튜플로 통용!
  
    > 엔티티 : DB에서 표현하려는 객체
  
    > 릴레이션 : 각 엔티티 간의 관계
    
    > 값(value) : 테이블에서 각각의 행과 열에 대응하는 값. 열의 데이터 형에 맞아야함
  * 카디날리티(cardinality) <br/>
   : 릴레이션 튜플의 개수
  * **-> 튜플(tuple) = 레코드(record) = 행(row)**
<br/>

* **열(Column)**
  * = 데이터들의 타입 (세로 칸)
  * 각각의 열은 데이터 형(intm string ..) 가짐
  * 속성(attribute) <br/>
    : 어떤 엔티티를 표현하고 저장하는데 사용
  * 필드(field) <br/>
    : 한 열 사이의 교차로 존재하는 단일 항목 특정할 때 언급
  * 차수(degree) <br/>
    : 한 릴레이션에 들어있는 속성의 수
  * **-> 속성(attribute) = 필드(field) = 열(column)**
<br/>

* 도메인
  *  = 하나의 속성이 취할 수 있는 같은 타입의 원자값들의 집합
  *  실제 속성 값이 나타날 때 시스템이 그 값의 합법 여부를 검사하는 데 이용
<br/>

* 릴레이션 인스턴스
  * = 속성들의 데이터 타입이 정의되어 구체적인 데이터 값을 가지고 있는 것
<br/>

* 관계형 데이터베이스 구조<br/>

  <img src="http://wiki.hash.kr/images/a/a3/%EB%A6%B4%EB%A0%88%EC%9D%B4%EC%85%98_%EA%B5%AC%EC%A1%B0.png">

### 테이블 생성
```sql
CREATE TABLE 테이블 이름 (    
    컬럼명1 DATATYPE [DEFAULT 형식],
    컬럼명2 DATATYPE [DEFAULT 형식],
    컬럼명3 DATATYPE [DEFAULT 형식]
    );
```
* 테이블 생성 시, 대소문자 구분 X
* 테이블 이름 및 컬럼명은 항상 알파벳 문자로 시작(0-9, A-Z, $, #, _ 사용 가능)
* 컬럼 이름은 30자 초과 X, 예약어 X
* 한 테이블 안에 동일 컬럼명 사용 불가 but 다른 테이블과의 컬럼명 일치는 가능
* DATE 유형은 별도로 크기 지정 X
* 문자 데이터 유형은 가질 수 있는 최대 길이 반드시 표시
* 콤마로 컬럼간의 구분하되 마지막 컬럼은 콤마 X
* CONSTRAINT를 이용하여 컬럼에 대한 제약조건 추가

### 테이블 수정
```sql
ALTER TABLE 테이블명;
```
### 테이블 삭제
```sql
DROP TABLE 테이블명 [CASCADE CONSTRAINT];
```
### 테이블 목록 조회
```sql
SHOW TABLES;
```
### 테이블 조회
```sql
SELECT * FROM 테이블명;
```
### 컬럼 추가
```sql
ALTER TABLE 테이블명
ADD 추가할 컬럼명 데이터 유형;
```
### 컬럼 수정
```sql
ALTER TABLE 테이블명
MODIFY COLUMN 수정할 컬럼명;
```
* 해당 컬럼의 크기를 늘릴 수 있지만 줄이지는 못함
* 해당 컬럼이 NULL 값만 가지고 있으면 데이터 유형 변경 가능
  
### 컬럼명 수정
```sql
ALTER TABLE 테이블명
RENAME COLUMN 변경해야할 컬럼명 TO 새로운 컬럼명;
```
### 컬럼 삭제
```sql
ALTER TABLE 테이블명
DROP COLUMN 삭제할 컬럼명;
```
<br/>
<img src="https://velog.velcdn.com/images%2Fm1njae%2Fpost%2Ff565de4f-4ba6-4d98-ab01-d470d6027afe%2Fimage.png">
<br/>

---

## 제약조건
### 제약조건이란?
* 데이터의 무결성을 지키기 위해 입력받은 데이터에 대해 사용자가 제한을 두는 것
* 제약 조건을 바탕으로 검사를 진행하여 특정 조건을 만족했을 때만 입력이 되도록 제약<br/>
= SQL 테이블에 부적절한 자료가 입력되는 것을 방지하기 위한 컬럼별 여러가지 규칙들
<br/>

## Key(키)
### Key란?
* 테이블에서 행의 식별자로 이용되는 열
* = 테이블에 저장된 행을 고유하게 식별하는 후보 키 중 DB설계자가 지정한 속성
<br/>

## 제약조건 & key SQL
### 1. NOT NULL 조건
```sql
Create Table 테이블(
필드명 INT NOT NULL   --해당 필드는 Null값 저장x
);
```
* 해당필드는 NULL 값을 저장할 수 없게 하는 제약조건 : 컬럼 필수 필드화 시 사용
* NOT NULL 제약조건 설정 시 해당 컬럼에는 꼭 데이터를 입력해야 함
<br/>

### 2. UNIQUE 조건
```sql
--기본적인 Unique
Create Table 테이블(
      필드명 INT UNIQUE  
); 

--제약조건에 이름부여(constraint)
Create Table 테이블(
      필드명 INT ,
      ...
	    CONSTRAINT 제약조건 UNIQUE 필드명  
);
    
--테이블을 생성 후, ALTER 를 이용해서 제약조건 추가 (제약조건명 : EMP2_UK_DEPTNO)
ALTER TABLE EMP2
ADD CONSTRAINT EMP2_UK_DEPTNO UNIQUE(deptno);

--제약조건 삭제
ALTER TABLE EMP2
DROP CONSTRAINT EMP2_UK_DEPTNO;
```
* 중복된 값을 저장할 수 없게 하는 제약조건 : 데이터의 유일성 보장
* NULL끼리는 중복으로 간주 X
* 자동으로 인덱스 생성됨
* 제약조건 추가&삭제 시 둘 다 ALTER 이용
<br/>

### 3. PRIMARY KEY (기본키)
```sql
--기본적인 Primary
Create Table 테이블(
      필드명 INT PRIMARY KEY   
);

--제약조건에 이름부여(constraint)
Create Table 테이블(
      필드명 INT ,
      ...
      CONSTRAINT 제약조건 PRIMARY KEY 필드명  
);
```
* 기본키를 지정하는 제약조건 : NOT NULL + UNIQUE 결합
* 그 데이터 행을 대표하는 컬럼으로 다른 테이블에서 외래키들이 참조할 수 있는 키로서의 자격
* 지정한 열은 반드시 유일한 값을 가져야 함
* 테이블 당 1개만 지정 가능
* 기본키 정의 시, 자동으로 기본 키 제약조건의 이름과 동일한 이름의 인덱스 자동 생성
  > 인덱스(INDEX) : 검색 키로서 검색 속도 향상
<br/>

### 4. FOREIGN KEY (외래키)
```sql
Create Table 테이블(
	    필드명 INT ,
	    ...
	    CONSTRAINT 제약조건 FOREIGN KEY (필드명) 
    	REFERENCES 테이블명 (필드명)
);
```
* 한 테이블을 다른 테이블과 연결하여 외래키를 지정하는 제약조건 : 기본키를 참조하는 컬럼들의 집합
* 해당 테이블은 다른 테이블에 의존 : 다른 테이블 열 참조해 해당 테이블에 존재하는 값만 입력 가능
* 외래키를 가지는 컬럼의 데이터 형은 외래키가 참조하는 기본키의 컬럼과 데이터 형 일치해야함
* 외래키에 의해 참조되고 있는 기본키는 삭제 불가
* ↓ 특정 테이블의 삭제 및 수정 진행 시, 연관된 필드가 어떤 동작을 수행할 지 지정 가능 ↓ 
```sql
--삭제 및 수정 시 연관되어있는 필드 같이 삭제
ON DELETE CASCADE
ON UPDATE CASCADE

--삭제 및 수정 시 연관되어있는 필드 null
ON DELETE SET NULL
ON UPDATE SET NULL

--삭제 및 수정 시 연관되어있는 필드 기본값으로 설정
ON DELETE SET DEFAULT
ON UPDATE SET DEFAULT

--참조하는 테이블에 데이터 남아있으면 삭제 불가
ON DELETE RESTRICT
ON UPDATE RESTRICT
```
<br/>

### 5. CHECK 조건
```sql
--테이블의 comm 컬럼이 1~100 까지의 값만 가질 수 있도록 체크 제약조건 생성
ALTER TABLE EMP2
ADD CONSTRAINT EMP2_CK_COMM CHECK (comm >= 1 AND comm <= 100);
```
* 컬럼의 값을 특정 범위로 제한
* 조건 만족하지 않는 데이터는 입력 거부
<br/>

### 6. DEFAULT
```sql
Create Table 테이블(
	    필드명 INT DEFAULT '기본값'
);
```
* 해당 필드의 디폴트 값을 설정하는 제약조건 : 데이터 입력하지 않아도 지정 값이 기본으로 입력됨
<br/>


## 참고
[[DB] 테이블 개념](https://lhh3520.tistory.com/38) <br/>
[관계형 데이터베이스 1) 릴레이션이란?](https://nolja98.tistory.com/12)<br/>
[[DAY18] SQL, 테이블의 개념](https://velog.io/@m1njae/DAY18-SQL-%ED%85%8C%EC%9D%B4%EB%B8%94%EC%9D%98-%EA%B0%9C%EB%85%90)<br/>
[[DB] 테이블 & 제약조건](https://velog.io/@sezzzini/DB)<br/>
[[MySQL] 데이터베이스 제약조건이란?](https://liveloper-jay.tistory.com/22)<br/>
[테이블 (데이터베이스)](http://wiki.hash.kr/index.php/%ED%85%8C%EC%9D%B4%EB%B8%94_(%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4))<br/>
