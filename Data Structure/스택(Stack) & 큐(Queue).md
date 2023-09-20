## 스택(Stack) & 큐(Queue)

> 스택은 마지막에 들어간 데이터를 먼저 꺼내는 LIFO 구조, 큐는 먼저 들어간 데이터를 먼저 꺼내는 FIFO 구조

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/14341b49-5a54-4ffc-9167-2c42363efa24" width="55%"/>

<br/>

### 스택(Stack)

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/72aced27-ca9b-4fc9-be37-f982cd5b24b4" width="50%"/>

- 데이터를 일시적으로 저장하기 위한 자료구조로, **후입선출(LIFO, Last In First Out)** 방식으로 데이터 입출력
- 스택의 가장 윗부분은 `top`, 가장 아랫부분은 `bottom`이라 지칭하며 `top`에서만 데이터 삽입, 삭제 연산 발생
- 스택의 데이터 삽입 연산은 `push`, 삭제 연산은 `pop`이라 지칭
- 스택에는 배열을 사용하는 것이 효율적: 스택의 `top`(마지막 인덱스)에서만 삽입, 삭제 연산이 발생하기 때문

  \*실제로 자바의 `Stack` 클래스는 `Vector` 클래스를 상속하여 내부적으로 `Object[] elementData` 배열을 관리

- 스택은 문서 작업 시 undo, redo 기능을 구현하거나 웹 브라우저의 방문 기록을 관리하기 위해 활용됨
- 자바에서는 스택을 사용하기 위해 `Stack` 클래스를 사용 ex. `Stack<Integer> stack = new Stack<>();`

**Stack API**

| API                        | 설명                                                                                                                                                                                                                                                      |
| -------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Object push(Object item)` | 스택의 top에 요소 추가                                                                                                                                                                                                                                    |
| `Object pop()`             | - 스택의 top에서 요소를 꺼내 반환 <br/> - 스택이 비어있으면 `EmptyStackException` 발생                                                                                                                                                                    |
| `Object peek()`            | - 스택의 top에서 삭제 없이 요소를 읽어서 반환 <br/> - `pop()`과 달리 스택에서 요소를 삭제하지 않고 단순 조회 <br/> - 스택이 비어있으면 `EmptyStackException` 발생                                                                                         |
| `boolean empty()`          | 스택이 비어있는지 확인                                                                                                                                                                                                                                    |
| `int search(Object o)`     | - 스택에서 주어진 요소를 찾아서 그 위치를 반환, 찾지 못하면 -1 반환 (요소 비교 시에는 `equals()` 메소드 사용) <br/> - 요소의 위치는 스택의 top을 시작점으로 정의, 즉 주어진 요소가 스택의 top에 위치한다면 1 반환하고 스택의 top 아래에 위치한다면 2 반환 |

<br/>

### 큐(Queue)

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/86e7af79-c0a3-4efc-8889-0fd9d49b5a1b" width="50%"/>

- 데이터를 일시적으로 저장하기 위한 자료구조로, **선입선출(FIFO, First In First Out)** 방식으로 데이터 입출력
- 큐의 가장 앞 부분은 `front`, 가장 뒷 부분은 `rear`라 지칭하며 `front`에서는 데이터 삭제 연산만, `rear`에서는 데이터 삽입 연산만 발생
- 큐의 데이터 삽입 연산은 `enqueue`, 삭제 연산은 `dequeue`라 지칭
- 큐에는 연결 리스트를 사용하는 것이 효율적: 큐의 `front`(맨 앞 인덱스)에서 삭제 연산이 발생하기 때문 (배열 사용 시에는 전체 데이터 자리 이동이 불가피)
- 큐를 통해 데이터가 줄을 지어 순서대로 처리되기 때문에, 그래프의 넓이 우선 탐색(BFS) 또는 컴퓨터 버퍼(처리되지 못한 입력을 순서대로 큐에 저장)에서 주로 활용
- 자바에서는 큐를 사용하기 위해 `Queue` 인터페이스를 사용 ex. `Queue<Integer> queue = new LinkedList<>();`

  자바에서 큐는 `Queue` 인터페이스만 존재하기 때문에 `Queue` 인터페이스를 구현한 클래스를 사용

**Queue API**

| API                       | 설명                                                                                                          |
| ------------------------- | ------------------------------------------------------------------------------------------------------------- |
| `boolean add(Object o)`   | - 큐의 rear에 요소 추가 <br/> - 성공 시 `true` 반환 <br/> - 저장 공간이 부족하면 `IllegalStateException` 발생 |
| `boolean offer(Object o)` | - 큐의 rear에 요소 추가 <br/> - 성공하면 `true`, 실패하면 `false` 반환                                        |
| `Obejct remove()`         | - 큐의 front에서 요소를 꺼내 반환 <br/> - 큐가 비어있으면 `NoSuchElementException` 발생                       |
| `Object poll()`           | - 큐의 front에서 요소를 꺼내 반환 <br/> - 큐가 비어있으면 `null` 반환                                         |
| `Object element()`        | - 큐의 front에서 삭제 없이 요소를 읽어서 반환 <br/> - 큐가 비어있으면 `NoSuchElementException` 발생           |
| `Object peek()`           | - 큐의 front에서 삭제 없이 요소를 읽어서 반환 <br/> - 큐가 비어있으면 `null` 반환                             |

<br/>

### PriorityQueue

- 자바의 `Queue` 인터페이스 구현체 중 하나로, 데이터를 넣은 순서에 관계없이 우선순위가 높은 것부터 꺼냄
- 우선순위는 숫자가 작을 수록 높아짐, 숫자가 아닌 일반 객체를 요소로 저장할 때에는 객체 간 크기 비교를 위한 `Comparator` 구현체를 `PriorityQueue` 생성자로 전달
- 저장 공간으로 배열을 사용하며 각 요소를 힙(heap) 자료구조 형태로 관리

<br/>

### Deque(Double-Ended Queue)

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/b43c4f3a-c412-4c3e-a23d-fe260f64f17b" width="55%"/>

- `Queue`의 하위 인터페이스로, 구현체로는 `ArrayDeque`, `LinkedList` 클래스 등이 존재
- 한쪽 끝에서만 삽입/삭제할 수 있는 큐와 달리, 양쪽 끝에서 삽입/삭제 가능
- `Deque`는 `Stack`과 `Queue`를 하나로 합쳐놓은 것과 같아서 `Stack`으로 사용할 수도, `Queue`로 사용할 수도 있음

| Deque         | Stack    | Queue     |
| ------------- | -------- | --------- |
| `offerLast()` | `push()` | `offer()` |
| `pollFirst()` |          | `poll()`  |
| `pollLast()`  | `pop()`  |           |
| `peekFirst()` |          | `peek()`  |
| `peekLast()`  | `peek()` |           |

<br/>

### 참고

[스택(Stack) vs 큐(Queue) 1](https://pridiot.tistory.com/68)

[스택(Stack) vs 큐(Queue) 2](https://commencer-y.tistory.com/19)

[큐(Queue)](https://coding-factory.tistory.com/602)
