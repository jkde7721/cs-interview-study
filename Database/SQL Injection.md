## SQL Injection

> 악의적인 사용자가 보안상의 취약점을 이용하여, 임의의 SQL 문을 주입하고 실행되게 하여 데이터베이스가 비정상적인 동작을 하도록 조작하는 행위

<br/>

### SQL Injection의 영향

- SQL Injection 공격은 인증을 우회하는 등의 웹 애플리케이션의 로직을 파괴하거나 조직의 데이터베이스 정보나 개인 정보, 또는 의도적으로 숨긴 데이터의 유출 및 데이터의 손상, 손실을 초래할 수 있다.
- MSSQL과 같은 일부 데이터베이스에서 제공되는 저장 프로시저(Stored Procedure)를 이용하면 원격 명령 실행 등에 노출되어 시스템의 손상까지 초래할 수 있다.

<br/>

### SQL Injection 공격 종류 및 방법

1. **Error based SQL Injection** : 논리적 에러를 이용한 SQL Injection
   > 잘못된 문법이나 자료형 불일치 등에 의해 웹 브라우저에 표시되는 데이터베이스 오류를 기반으로 수행되는 가장 대중적인 공격 기법

- 공격자는 의도적인 오류를 유발시키고 해당 오류 정보를 바탕으로 데이터베이스명, 테이블, 컬럼 정보 등을 파악할 수 있게 됨

  ![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/9958373C5C8890FA03)

  -> 로그인 SQL 구문, 해당 구문에서 입력값에 대한 검증이 없음을 확인하고, 악의적인 사용자가 임의의 SQL 구문을 주입 <br/>
  -> Users 테이블에 있는 모든 정보를 조회하게 됨으로 써 가장 먼저 만들어진 계정으로 로그인에 성공하게 된다. 보통은 관리자 계정을 맨 처음 만들기 때문에 관리자 계정에 로그인 할 수 있게 되어 관리자의 권한을 이용해 또 다른 2차피해를 발생할 수 있다.

<br/>

2. **Union based SQL Injection** : Union 명령어를 이용한 SQL Injection
   > UNION 키워드를 사용하여 원래의 요청에 추가 정보를 얻는 공격 기법

- UNION Injection을 성공하기 위해서는 UNION하는 두 테이블의 컬럼수가 같아야 하고, 데이터 형이 같아야 함

  ![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/99BD4C3C5C8890FA0A)

  -> 입력값에 대한 검증이 없기 때문에 발생

<br/>

3. **Blind SQL Injection**
   > 데이터베이스로부터 특정한 값이나 데이터를 전달받지 않고, 단순히 참과 거짓의 정보만 알 수 있을 때 사용하는 공격 기법

- 로그인 폼에 SQL Injection이 가능하다고 가정 했을 때, 서버가 응답하는 로그인 성공과 로그인 실패 메시지를 이용하여, DB의 테이블 정보 등을 추출해 낼 수 있음

① Boolean based SQL

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/99525F3C5C8890F90E)

② Time based SQL

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/99CAFB395C88914513)

-> 사용되는 함수는 MySQL 기준으로 SLEEP과 BENCHMARK이다.

<br/>

4. **Stored Procedure SQL Injection** : 저장된 프로시저에서의 SQL Injection
   > 공격자가 시스템 권한을 획득해야 하므로 공격 난이도가 높으나 성공 시 직접적인 피해를 입힐 수 있는 공격 기법
   > 저장 프로시저(Stored Procedure) : 일련의 쿼리들을 모아 하나의 함수처럼 사용하기 위한 것

- 공격에 사용되는 대표적인 저장 프로시저 : MSSQL에 있는 xp_cmdshell로 윈도우 명령어 사용 가능

<br/>

5. **Mass SQL Injection** : 다량의 SQL Injection 공격
   > 기존 SQL Injection과 달리 한 번의 공격으로 다량의 데이터베이스가 조작되어 큰 피해를 입히는 공격 기법

- 보통 MSSQL을 사용하는 ASP 기반 웹 애플리케이션에서 많이 사용되며 쿼리문은 HEX 인코딩 방식으로 인코딩하여 공격
- 데이터베이스 값을 변조하여 데이터베이스에 악성스크립트를 삽입하고 사용자들이 변조된 사이트에 접속 시 좀비PC로 감염되게 함

<br/>

### SQL Injection 대응 방안

1. **입력값에 대한 검증**
   > 사용자의 입력을 받을 때 검증 로직을 추가하여 값이 유효한지 검증

- ', ", #, --, = 등 특수문자와 명령어 필터링
- 데이터 길이 제한
- 서버 단에서 화이트리스트 기반으로 검증해야 함. 블랙리스트 기반으로 검증하게 되면 수많은 차단리스트를 등록해야 하고, 하나라도 빠지면 공격에 성공하게 되기 때문

2. **Prepared Statement 구문사용**

   > 사용자의 입력값이 데이터베이스의 파라미터로 들어가기 전에 DBMS가 미리 컴파일하여 실행하지 않고 대기, 그 후 사용자의 입력값을 문자열로 인식하여 공격 쿼리가 들어간다고 하더라도, 사용자의 입력은 이미 의미없는 단순 문자열이기 때문에 전체 쿼리문도 공격자의 의도대로 작동하지 않는다.

3. **Error Message 노출 금지**

   > 에러 메시지를 통해 테이블명 및 컬럼명 그리고 쿼리문이 노출될 수 있기 때문에 데이터베이스에 대한 에러 발생 시 사용자에게 보여줄 수 있는 페이지 및 메시지박스 띄우도록 해야 함

4. **웹 방화벽 사용**
   > 웹 공격 방어에 특화되어있는 웹 방화벽을 사용하는 것

< 웹 방화벽 종류 >

1. 소프트웨어형 : 서버 내에 직접 설치하는 방법
2. 하드웨어형 : 네트워크 상에서 서버 앞 단에 직접 하드웨어 장비로 구성하는 것
3. 프록시형 : DNS 서버 주소를 웹 방화벽으로 바꾸고 서버로 가는 트래픽이 웹 방화벽을 먼저 거치도록 하는 방법
