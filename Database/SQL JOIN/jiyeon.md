
#  JOIN

복수의 테이블을 결합하는 것으로, 데이터 조회 시 다른 테이블의 데이터를 함께 조회할 때 이용한다.


 ### INNER JOIN(내부 조인)
두 테이블을 조인할 때, 두 테이블에 모두 지정한 열의 데이터가 있어야 한다.


### OUTER JOIN(외부 조인)
두 테이블을 조인할 때, 1개의 테이블에만 데이터가 있어도 결과가 나온다.

외부 조인은 3가지가 있다.
1. LEFT OUTER JOIN : 왼쪽 테이블의 모든 값이 출력되고 일치하지 않은 부분은 NULL로 표시
2. RIGHT OUTER JOIN : 오른쪽 테이블의 모든 값이 출력되고 일치하지 않은 부분은 NULL로 표시
3. FULL OUTER JOIN : LEFT OUTER JOIN과 RIGHT OUTER JOIN의 UNION
