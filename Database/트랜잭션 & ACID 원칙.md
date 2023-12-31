## 트랜잭션(Transaction)

> 하나의 작업을 수행하는데 필요한 데이터베이스 연산(SQL문)들의 집합, 즉 데이터베이스의 논리적 작업 단위

- 예시. 계좌이체 작업을 위한 트랜잭션
  - A → B 10,000원을 송금하는 경우
  - `(A의 잔액 - 10,000원) SQL` + `(B의 잔액 + 10,000원) SQL` 2개의 `UPDATE`문이 필요
  - 두 연산의 처리 순서는 중요X, but 정상적으로 모두 실행되어야 함
  - 만약 첫번째 SQL 실행 후 시스템에 장애가 발생하여 두번째 SQL이 실행되지 않는다면 A는 돈을 보냈지만 B는 돈을 받지 못하는 모순된 상황 발생
- 트랜잭션의 모든 연산들은 **모두 처리**되거나 **하나도 처리되지 않아야** 데이터베이스가 모순 없는 일관된 상태 유지
- 데이터베이스의 무결성과 일관성을 보장하기 위해 하나의 작업을 수행하는데 필요한 연산들을 하나의 트랜잭션으로 제대로 정의하고 관리해줘야 함
- 일반적으로 데이터베이스를 변경하는 `INSERT`, `DELETE`, `UPDATE`문의 실행을 트랜잭션으로 관리

<br/>

## 트랜잭션의 ACID 원칙

> 테이터베이스의 무결성과 일관성을 보장하기 위한 트랜잭션의 4가지 특징

### 원자성(**A**tomicity)

> 트랜잭션을 구성하는 연산들은 모두 정상적으로 실행되거나 하나도 실행되지 않아야 함 (all-or-nothing)

- 트랜잭션은 부분적으로 실행될 수 없고, 모두 실행되거나 모두 실패해야 함을 의미
- 트랜잭션은 커밋되든지 롤백되든지 둘 중 하나만 가능

  _커밋(commit): 트랜잭션이 성공적으로 수행되었음을 선언(작업 완료), commit 연산 후 트랜잭션의 수행 결과를 실제 데이터베이스에 반영_

  _롤백(rollback): 트랜잭션을 수행하는데 실패했음을 선언(작업 취소), 트랜잭션이 지금까지 실행한 연산 결과가 모두 취소되고 트랜잭션이 수행되기 전의 상태로 돌아감_

- 즉 트랜잭션을 구성하는 연산 중 일부만 처리한 결과를 데이터베이스에 반영해서는 안됨

<details>
  <summary>보상 트랜잭션(Compensating Transaction)</summary>

- 커밋된 트랜잭션이 실수였다는 것이 밝혀졌을 때 결과를 바로잡기 위해 실행하는 트랜잭션 ex. 입금 트랜잭션이 실수였다면, 출금 트랜잭션으로 결과를 바로잡음

- 어플리케이션을 잘 설계하기 위해서는 모든 트랜잭션에 대해 보상 트랜잭션을 고려해야 함
</details>

### 일관성(**C**onsistency)

> 트랜잭션이 성공적으로 수행된 후에도 데이터베이스가 일관된 상태를 유지해야 함

<details>
<summary>일관된 상태?</summary>

트랜잭션이 성공적으로 수행된 후

1. 데이터베이스의 제약조건(개체 무결성, 참조 무결성, <code>CHECK</code>문으로 정의한 제약조건, <code>NOT NULL</code> 등)을 위반하지 않음을 보장 → DBMS에 의해 보장 가능

2. 어플리케이션 관점에서의 데이터의 유효성을 보장, 즉 '어떤 데이터가 유효하다'라는 정의에 따라 일관성을 만족할 수도, 만족하지 않을 수도 있음 → DBMS에 의해 보장 불가능<br/>ex. 데이터베이스의 재고 수량과 실제 세계에서의 창고 내 재고 수량이 일치해야 함

</details>

<br/>

- 트랜잭션 수행 전 데이터베이스가 **일관된 상태**였다면 트랜잭션 커밋 후의 데이터베이스도 **또 다른 일관된 상태**가 되어야 함
- 트랜잭션 수행 중에는 데이터베이스가 일시적으로 일관된 상태가 아닐 수 있지만, 트랜잭션 수행이 성공적으로 완료된 후에는 데이터베이스가 일관된 상태를 유지해야 함
- 일반적으로 애플리케이션에서 데이터가 유효한지 아닌지를 정의하고 데이터베이스는 데이터를 저장할 뿐 → 즉 일관성을 유지하도록 트랜잭션을 올바르게 정의하는 것은 애플리케이션의 책임이지 데이터베이스가 일관성을 완전히 보장할 수는 없음
- 예시. A의 잔액은 30,000원, B의 잔액은 20,000원인 초기 상태에서 A → B 10,000원을 송금하는 경우

  - 초기 상태에서 두 사람의 잔액 합계는 50,000원
  - 성공적으로 송금 후 A의 잔액은 20,000원, B의 잔액은 30,000원으로 여전히 둘의 잔액 합계는 50,000원으로 일관되게 유지
  - but 송금 시 수수료 비용은 500원이라는 정책이 있다면, 위 트랜잭션은 일관성을 지키지 않음 → 일관성을 보장하기 위해 계좌 이체 트랜잭션을 재정의해야 함

_원자성, 격리성, 지속성은 데이터베이스의 속성인 반면 일관성은 애플리케이션의 속성으로, 데이터베이스가 아닌 사용자(애플리케이션)가 제어할 수 있는 유일한 속성 (실제로 ACID의 C는 약어를 만들기 위해 추가했다는 이야기가 있음)_

### 격리성(**I**solation, 고립성)

> 현재 수행 중인 트랜잭션이 완료될 때까지 해당 트랜잭션이 생성한 중간 연산 결과에 다른 트랜잭션들은 접근할 수 없음, 즉 트랜잭션 수행 시 다른 트랜잭션이 끼어들 수 없음

- 일반적으로 데이터베이스에서 여러 트랜잭션이 동시에 수행되지만, 각 트랜잭션의 독립적인 실행을 보장하기 위해 다른 트랜잭션의 중간 연산 결과에 서로 접근 불가
- 직렬성으로 정의되기도 함

  _직렬성(Serializability): 여러 트랜잭션을 수행한 결과가 각 트랜잭션이 순차적으로 실행됐을 때의 결과와 동일한 경우 가지는 특성_

- 여러 트랜잭션을 병렬로 수행하기 위해 각 트랜잭션이 엑세스하는 데이터에 잠금(Lock)을 설정하여 다른 트랜잭션이 간섭하지 못하도록 함 → 여러 트랜잭션의 병렬 실행이 직렬로(순서대로) 일어나는 것처럼 보이게 함

- 예시. A의 잔액은 30,000원, B의 잔액은 20,000원인 초기 상태에서 `A → B 10,000원을 송금하는 트랜잭션` + `B의 계좌에 5,000원을 입금하는 트랜잭션`이 동시에 수행되는 상황
  - 첫번째 트랜잭션의 첫번째 연산(`A의 잔액 - 10,000원`) 실행 후 두번째 트랜잭션(`B의 잔액 + 5,000원`)이 끼어들어 첫번째 트랜잭션의 두번째 연산(`B의 잔액 + 10,000원`)과 동시에 실행되면 첫번째 트랜잭션 입장에서 B의 잔액은 30,000원인 반면 두번째 트랜잭션 입장에서 B의 잔액은 25,000원으로 가능한 B의 잔액 상태가 2개인 모순 발생
  - 즉 송금 트랜잭션이 커밋되어 B의 잔액에 대한 Lock이 풀릴 때까지 기다렸다가 입금 트랜잭션을 수행해야 함

_고립성을 엄격하게 유지하는 것은 데이터베이스 시스템 성능에 부정적인 영향을 미치기 때문에 동시성을 위해 고립성을 지키지 않을 수 있음_

### 지속성(**D**urability, 영속성)

> 트랜잭션이 성공적으로 완료된 후 데이터베이스에 반영한 수행 결과는 어떠한 경우에도 손실되지 않고 영구적이어야 함

- 트랜잭션이 성공적으로 커밋됐다면 하드웨어 결함이 발생하거나 데이터베이스가 죽더라도 트랜잭션에서 기록한 모든 데이터는 손실되지 않음을 보장
- **로깅(Logging)**: 영속성을 보장하기 위한 기법, 모든 트랜잭션의 수정 사항을 로그 파일에 남김 → 트랜잭션이 commit된 후 데이터베이스를 수정하기 전에 시스템 장애가 발생하면 시스템 복구 후 데이터베이스 복구 필요 → 데이터베이스 복구 작업을 위해 시스템은 로그 파일에서 commit된 트랜잭션의 수정 사항 중 실제 데이터베이스에 적용되지 않은 것들을 찾아서 데이터베이스에 반영 → 시스템 정상 운영 시작

<br/>

### 참고

[데이터베이스 개론(3판)](https://www.hanbit.co.kr/store/books/look.php?p_code=B6505632990)

[트랜잭션 ACID](https://johngrib.github.io/wiki/database/acid/)
