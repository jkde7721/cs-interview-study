## Index (인덱스)
### Index란?
* 데이터베이스 테이블의 검색 속도를 향상시키기 위한 자료구조 (추가적인 쓰기 작업과 저장 공간 활용)
* = 두꺼운 책의 **'목차'** or **'색인'** 과 동일

  > + DB에서 테이블의 모든 데이터를 검색하면 시간이 너무 오래 걸리기 때문에 따로 데이터와 데이터의 위치를 포함한 자료구조를 생성하여 빠르게 조회할 수 있도록 함 : 테이블 전체를 비교하여 탐색하는 Full Scan 필요 X
  > + 인덱스를 활용 시 SELECT(데이터 조회) 외에도 UPDATE와 DELETE 성능도 함께 향상됨. UPDATE나 DELETE 연산을 수행하려면 해당 대상 조회인 SELECT가 선수행되어야하기 때문

<br/>

### Index 관리
DBMS는 index를 항상 최신 정렬 상태로 유지해야 원하는 값 빠르게 탐색 가능
* INSERT: 새로운 데이터에 대한 인덱스 추가
* DELETE: 삭제하려는 데이터의 인덱스에 사용하지 않음 처리
* UPDATE: 기존의 인덱스에 사용하지 않음 처리 후, 갱신된 데이터에 대한 인덱스 추가

<br/>

### Index 장단점
* 장점
  * 테이블 조회 속도와 그에 따른 성능 향상
  * 전반적인 시스템의 부하 감소   

* 단점
  * 인덱스 관리 위해 DB의 약 10%에 해당하는 저장공간 필요
  * 인덱스 관리 위한 추가 작업 필요
  * 인덱스 잘못 사용 시, 역효과가 발생<br/>:  CREATE, DELETE, UPDATE가 빈번한 속성에 인덱스를 걸게 되면 인덱스의 크기가 비대해져서 **성능 오히려 저하**<br/> UPDATE와 DELETE는 기존 인덱스를 삭제하지 않고 '사용하지 않음' 처리를 하기 때문에 실제 데이터에 비해 인덱스는 훨씬 많이 존재하게 됨

<br/>

### Index 알고리즘
#### 1. 해시 테이블(Hash Table)<br/>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRpMoO%2FbtqKMzdg9TX%2FXYkGt2kqE0hr9rqhHx3o3K%2Fimg.png"><br/>
* Key, Value로 데이터 저장하는 자료구조
* 컬럼의 값으로 생성된 해시를 통해 인덱스 구현하는 알고리즘 -> 매우 빠른 검색 지원
* but, 값을 변형해서 인덱싱하기 떄문에 부등호 연산(>,<)이 주된 DB 검색에선 부적합 (일부 검색 불가)
<br/>

#### 2. B-Tree <br/><br/>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdjhlni%2FbtrXnJAFNh0%2FmdqodQkO6khsmi3Z8seFQ1%2Fimg.png"><br/>
* 인덱스에서 가장 일반적으로 사용되는 자료구조
* 자식 2개 만을 갖는 이진 트리(Binary Tree)를 확장하여 N개의 자식을 가질 수 있도록 고안된 항상 균형을 맞추는 균형 트리(Balanced Tree)
* 모든 노드에 데이터(Value) 저장<br/>
#### 2-1. B-Tree 인덱스 구조 <br/>
  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FejxY4N%2FbtrXnwhiPKR%2FQ2XAYLfbiKgqKeYccH0oCK%2Fimg.png"><br/>
* 칼럼의 값을 변형하지 않고 원래의 값을 이용해 인덱싱하는 알고리즘
* 인덱스를 페이지 단위로 저장 && 인덱스 키를 바탕으로 항상 정렬된 상태 유지

  * 페이지(Page)란?
    
    >  디스크와 메모리(버퍼풀)에 데이터를 읽고 쓰는 최소 작업 단위<br/>일반적인 인덱스를 포함해 PK(클러스터 인덱스)와 테이블 등은 모두 페이지 단위로 관리
* 정렬된 인덱스 키를 따라 리프 노드에 도달하면 (인덱스 키, PK) 쌍으로 저장되어 있음
* 범위 탐색 가능 (like와 같은 구문 실행 시에도 사용 O)
<br/>

#### 3. B+Tree <br/>
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fd78iJ0%2FbtqKRYbLdM9%2FnIvz1M4gffMl4YHS77JSfK%2Fimg.png"><br/>
* DB의 인덱스 위해 자식 노드가 2개 이상인 B-Tree를 개선한 자료구조
* 리프노드(데이터노드)만 인덱스와 데이터(Value)를 함께 가지고 있고, 나머지 노드(인덱스노드)들은 데이터를 위한 인덱스(Key)만 가짐
* 리프노드들은 LinkedList로 연결되어 있음 -> 순차검색 용이하게!
* 데이터 노드 크기는 인덱스 노드 크기와 같지 않아도 됨
<br/>

### 참고
[[Database] 인덱스(index)란?](https://mangkyu.tistory.com/96)<br/>
[[MySQL] B-Tree로 인덱스(Index)에 대해 쉽고 완벽하게 이해하기](https://mangkyu.tistory.com/286)<br/>
[B트리,B+트리, B*트리 개념 정리](https://velog.io/@seanlion/btree)<br/>
[데이터베이스의 인덱스 알고리즘](https://ledpear.tistory.com/58?category=932656)<br/>
