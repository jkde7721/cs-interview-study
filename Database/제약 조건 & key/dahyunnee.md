
## 테이블 제약 조건
- 테이블에 조건에 맞는 자료만 입력될 수 있게, 제약 조건을 설정하는 것. 
- 즉, 데이터의 성격을 정의

1. NOT NULL
	- 해당 컬럼을 필수 필드화. 즉, 무조건 해당 컬럼에는 데이터 입력이 필요. 
2. UNIQUE
    - 데이터의 유일성 보장. 즉, 중복되는 데이터 존재 불가능 및 자동 인덱스 생성 
    - unique -> null 허용 / primary key -> null 불가능 
    - unique -> 여러 컬럼 가능 / primary key -> 하나의 컬럼만 가능
3. CHECK
	- 컬럼의 값을 특정 범위로 제한
4. DEFAULT 
	- 컬럼의 기본값 지정 
	- 데이터 입력하지 않아도 지정된 값이 기본으로 입력됨.
5. PRIMARY KEY (기본키)
	- `UNIQUE + NOT NULL = PRIMARY KEY`
	- 행을 대표하는 컬럼. 다른 테이블에서 FOREIGN KEY들이 참조 가능함 
	- 자동 인덱스 생성 
	- 유니크 값이 없다면, 필드 두 개 묶어서 PRIMARY KEY 지정 가능함.  

> INDEX : UNIQUE, PRIMARY KEY 생성시 자동적으로 생성

6. FOREIGN KEY (외래키)
   - 기본키 참조 컬럼 집합 
   - 외래키를 가지는 컬럼의 데이터 형은  외래키가 참조하는 기본키의 컬럼과 데이터 형이 일치해야함.
   - 외래키에 의해 참조되고 있는 기본키는 삭제 불가능하다. 
   - `on update cascade` : 기본키 수정 시, 외래키 함께 수정
   - `on delete cascade` : 기본키 삭제 시, 외래키 함께 삭제 


## 무결성 제약 조건
- 데이터의 정확성 / 유효성 

 1. **도메인 무결성(Domain Constraint)**
: 특정 속성의 값이 그 속성이 정의된 도메인에 속한 값이어야 함
2. **키 무결성(Key Constraint)**
: 하나의 릴레이션(테이블)에는 최소 하나의 키가 존재해야 함
3. **개체 무결성(Entity Integrity Constraint)**
: 기본키 속성은 NULL이나 중복값이 불가능
4. **참조 무결성(Referential Integrity Constraint)**
: 외래키 값은 NULL이거나 참조 릴레이션의 기본키 값과 동일해야 함


## Key
-   **슈퍼 키(Super Key)**:  **유일성**을 만족하는 키. 예를 들면, {학번 + 이름}, {주민등록번호 + 학번}
-   **복합 키(Composite Key)**:  **2개 이상**의 속성(attribute)를 사용한 키.
-   **후보 키(Candidate key)**:  **유일성**과  **최소성**을 만족하는 키. 기본키가 될 수 있는 후보이기 때문에 후보키라고 불린다. 예를 들면, 주민등록번호, 학번 등
-   **기본 키(Primary key)**: 후보 키에서 선택된 키. NULL값이 들어갈 수 없으며, 기본키로 선택된 속성(Attribute)은 동일한 값이 들어갈 수가 없다.
-   **대체 키(Surrogate key)**: 후보 키 중에 기본 키로 선택되지 않은 키.
-   **외래 키(Foreign Key):**  어떤 테이블(Relation) 간의 기본 키(Primary key)를 참조하는 속성이다. 테이블(Relation)들 간의 관계를 나타내기 위해서 사용된다.

![키 관계](https://drive.google.com/file/d/17QQyOXqcjhDdsQO8ENuJxNsrXiNk7yyc/view?usp=sharing)

