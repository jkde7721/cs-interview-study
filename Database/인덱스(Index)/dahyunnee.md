## Index란?
 - RDBMS에서 검색 속도를 높이기 위한 기술
 - 추가적인 쓰기 작업과 저장 공간을 활용 
       &nbsp; &nbsp; -> 데이터베이스 테이블의 **검색 속도를 향상**시키기 위한 `자료구조`
 - 조회 성능을 개선하는 용도
       - **TABLE의 컬럼을 색인화**

<br>

### [ 풀 스캔(Full-Scan) vs 인덱스(Index) ]
SELECT 할 때, 성능에 차이가 존재

 1. 풀 스캔(Full Scan) / 테이블 스캔(Table Scan)
SQL서버에서 데이터의 레코드는 `Heap` 영역에 **아무런 순서없이 저장**됨. 

	인덱스 없는 테이블의 경우, 

	`Heap`에서는 전체 데이터 페이지의 처음 레코드부터 끝 페이지의 마지막 레코드까지 모두 조회하여 검색조건과 비교
	
	->  *레코드가 많은 테이블의 경우, 성능이 떨어짐* 
2. 인덱스(Index)
검색 시 레코드를 모두 스캔하지 않고, **색인화된 Index 파일**을 검색해 속도를 빠르게 

<br>

### [ 인덱스 특징 ]  
-   장점
    -   적절한 인덱스를 생성하고 사용해 조회 성능 향상 
    -    테이블의 기본 키는 자동으로 인덱스로 설정 
-   단점
    -   **인덱스 페이지** -> 인덱스는 대략 테이블 크기의  **10% 공간이 추가로 필요**  
    -   **페이지 정렬 작업**  ->  Index 된 Field에서 SELECT 가 아닌 데이터의 변경 작업 (INSERT, UPDATE, DELETE)이 자주 일어나면 오히려 성능에 악영향 (Index를 재작성).

<br>

### [ 인덱스 사용 이유 ]

-   WHERE 구문과 일치하는 열을  **빨리 찾기 위해.**
    
-   특정 열을 고려 대상에서  **빨리 없애 버리기 위해.**
    
-   **조인 (join)을 실행**할 때  **다른 테이블에서 열을 추출하기 위해.**
    
-   **특정하게 인덱스된 컬럼**을 위한  **MIN() 또는 MAX() 값을 찾기 위해.**
    
-   사용할 수 있는 키의 최 좌측 접두사(leftmost prefix)를 가지고  **정렬 및 그룹화를 하기 위해.**
    
-   데이터 열을 참조하지 않는 상태로 값을 추출하기 위해서  **쿼리를 최적화 하는 경우.**

<br>

### [ 인덱스의 자료구조 ]

-   해시 테이블
    -   컬럼의 값으로 생성된 해시를 기반으로 인덱스를 구현한다.
    -   시간복잡도가 O(1)이라 검색이 매우 빠르다.
    -   키-값 쌍을 빠르게 검색하기 위한 자료구조이기 때문에, 증가하는 숫자나 날짜 같은 연속적인 데이터를 위한 순차 검색이 불가능하다.
    - 범위 검색이나 정렬 작업에는 효율적이지 않을 수 있다. 또한 해시 충돌이 발생할 경우 성능이 저하
-   B+Tree
    -   자식 노드가 2개 이상인 B-Tree를 개선시킨 자료구조이다.
    -   BTree의 리프노드들을 LinkedList로 연결하여 순차 검색을 용이하게 하였다.
    -   해시 테이블보다 나쁜 O(𝑙𝑜𝑔2𝑛) 의 시간복잡도를 갖지만 해시테이블보다 흔하게 사용된다.
    - 데이터를 정렬된 순서로 유지하기 때문에 범위 검색이나 정렬 작업에 대한 연산을 빠르게 수행

<br>

### [ 인덱스의 동작원리 ]

 - Index는 논리적/물리적으로 테이블과 독립적
     - `테이블`은 컬럼에 데이터가 정렬되지 않고 입력된 순서대로 
     - `Index`는 KEY 컬럼과 ROWID 컬럼 두개로 이루어져 있고 오름차순, 내림차순으로 정렬이 가능
  - _Key_ : 인덱스를 생성하라고 지정한 컬럼의 값
  <br>
  
  MySQL에서 테이블 생성 시, 아래와 같은 3가지 파일 생성
> FRM : 테이블 구조 저장 파일
>  MYD : 실제 데이터 파일
>  MYI : Index 정보 파일 (Index 사용 시 생성)

MYI 파일의 내용을 활용해서 Index 사용 칼럼 검색 
인덱스는 KEY-ROWID만 가지고 있고,  테이블의 세부항목들은 가지고 있지 않음.

 1.  `SELECT` 
	
	   ```null
	   SELECT *
	   FROM EMP
	   WHERE empno=7902;
	   ```

 - **empno가 Index로 설정** 
    인덱스에서 7902의 ROWID 확인 후, **`하드 디스크 파일`에서 7902정보를 가진 블록을 복사**해서 `DB buffer cache`로 복사
 - **empno가 Index로 설정 되지 않음**
	 7902정보가 어떤 블록에 들어 있는지 모르므로 **10만개 전부  `db buffer cache`로 복사한 후 하나하나 찾음 

 2.  `INSERT`
 - **추가된 레코드의 인덱스 키 값을 인덱스에 삽입**-> 인덱스 트리의 조정이나 노드의 분할 필요 
 - **기존 데이터 블록 여유 없는 경우** -> 새로운 데이터 블록이 할당, 데이터의 물리적인 배치가 재조정(인덱스의 레코드가 재배치)
- **인덱스의 블록 크기가 초과** -> 인덱스의 분할 작업(인덱스 트리를 재구성, 새로운 블록을 생성) 필요. 인덱스의 블로킹과 대기 이벤트가 발생 가능

 3.  `DELETE`
	 
 - **Table에서 Data가 delete 되는 경우**  
Data가 지워지고, 다른 Data가 그 공간을 사용 가능하다.

- **Index에서 Data가 delete 되는 경우**  
Data가 지워지지 않고, 사용 안 됨 표시만 해둔다.  
_Table의 Data 수와 Index의 Data 수가 다를 수 있다._

	 
 4. `UPDATE`
	 Table에서 update가 발생 → Index는 Update 불가능
Index에서는 Delete가 발생한 후, 새로운 작업의 Insert 작업 / 2배의 작업이 소요
 

<br>

[참고 레퍼런스 1](https://rachel0115.tistory.com/entry/MySQL-%EC%9D%B8%EB%8D%B1%EC%8A%A4-INDEX-%EC%A0%95%EB%A6%AC-%EB%8F%99%EC%9E%91-%EB%B0%A9%EC%8B%9D-%EC%83%9D%EC%84%B1-%EC%82%AD%EC%A0%9C-%EC%84%A4%EA%B3%84)
[참고 레퍼런스 2](https://velog.io/@gillog/SQL-Index%EC%9D%B8%EB%8D%B1%EC%8A%A4)
[B-Tree vs B+Tree](https://zorba91.tistory.com/293)