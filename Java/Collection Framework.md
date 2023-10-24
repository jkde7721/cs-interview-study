## Collection Framework

> 객체나 데이터들을 효율적으로 관리(추가, 삭제, 검색)할 수 있는 자료구조 라이브러리
> <br/>ex. C 언어에서는 연결 리스트 자료구조를 사용하려면 직접 구현해야 하지만 자바는 `LinkedList` 타입의 객체를 생성해서 사용

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/7b087ec8-9625-44b6-9445-e7ac31cf7549" width="85%"/><br/>

- 컬렉션 프레임워크에서 저장할 수 있는 데이터는 오직 객체(`Object`) 뿐, 즉 참조 타입만 저장 가능
- 컬렉션 프레임워크는 크게 `Collection` 인터페이스 / `Map` 인터페이스로 나뉨

  ❓`Map`이 `Collection` 인터페이스를 구현하지 않은 이유

  - `Collection` 인터페이스를 구현한 클래스들은 모두 단일 데이터를 처리하는 반면, `Map`은 Key-Value 쌍의 데이터를 처리
  - `Collection` 인터페이스가 상속한 `Iterable` 인터페이스를 만약 `Map` 인터페이스에서 상속하면 Key와 Value 중 어떤 것을 반복자로 반환할지 모호 → `Map`은 `Iterable` 인터페이스를 상속받지 않기 때문에 `Map` 컬렉션을 순회하기 위해서는 Stream을 사용하거나 `keySet()` 메소드의 반환값인 Key Collection을 통해 간접적으로 순회

    \***Iterable 인터페이스**: 컬렉션의 데이터들을 순회할 때 사용되는 `Iterator` 객체를 관리하는 인터페이스

  - 아주 정확히 말하면 `Map`은 `Collection`은 아니나, 공부를 위해 그냥 추가함

<br/>
<br/>

### List 인터페이스

- 대표적인 선형 자료구조로, 주로 순서가 있는 데이터를 관리하기 위한 인터페이스
- 저장 순서가 유지되면서 중복 객체 저장 가능
- 객체를 인덱스로 관리
- 자바 배열(ex. `Object[]`)과의 차이점: 크기가 고정되어 있지 않고 데이터 양에 따라 동적으로 변경

<br/>

**List 인터페이스에 선언된 대표 메소드**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/88539ccf-5251-4e7c-b486-53c7f5a0dcfc" width="60%"/><br/>

<br/>

**ArrayList 클래스**

- `Object[]` 배열을 이용하여 만든 리스트 (배열 크기 동적으로 관리)
- random access가 빠르지만, 삽입/삭제는 느림

<br/>

**LinkedList 클래스**

- `Node` 객체를 연결하여 만든 리스트

  ```java
  private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
  }
  ```

- 삽입/삭제는 빠르지만 random access는 느림
- 자바의 `LinkedList`는 이중 연결 리스트
- 여러가지 용도: `List`, `Deque`, `Queue`, `Stack`, `Tree` 등의 자료구조 구현체로 사용 가능

<br/>

**Vector 클래스**

- `ArrayList`의 구형 버전으로 내부적으로 `Object[]` 배열 관리
- `ArrayList`와의 차이는 모든 메소드가 동기화(synchronized) 되어있어 Thread-Safe → 여러 쓰레드가 동시에 데이터에 접근하면 순차적으로 처리
- 구버전 자바와의 호환성을 위해 남겨두었으며 잘 사용X

❗만약 컬렉션에 동기화가 필요하면 `Collections.synchronizedList(Collection<T> c)` 메소드를 통해 컬렉션을 동기화 처리하여 사용

<br/>

**Stack 클래스**

- 후입선출 LIFO(Last-In-First-Out) 자료구조
- `Vector`를 상속하기 때문에 문제점이 많아 잘 사용X → `ArrayDeque` 사용

<br/>

**객체 생성 방법**

```java
//방법 1
ArrayList<T> arraylist = new ArrayList<>();
LinkedList<T> linkedlist = new LinkedList<>();
Vector<T> vector = new Vector<>();
Stack<T> stack = new Stack<>();

//방법 2
List<T> arraylist = new ArrayList<>();
List<T> linkedlist = new LinkedList<>();
List<T> vector = new Vector<>();
List<T> stack = new Stack<>();
```

<br/>
<hr/>
<br/>

### Queue 인터페이스

- 선입선출 FIFO(First-In-First-Out) 자료구조
- 선형 자료구조로, 주로 순서가 있는 데이터를 기반으로 선입선출을 위해 만들어진 인터페이스

<br/>

**Queue, Deque 인터페이스에 선언된 대표 메소드**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/b80d4bcc-4f23-4a98-9c73-0c9910ab3b84" width="60%"/><br/>

<br/>

**Deque 인터페이스**

- Deque(Double-Ended Queue)는 양방향 enqueue, dequeue가 가능한 큐
- 스택과 큐를 하나로 합쳐놓은 것과 같기 때문에 스택 또는 큐로 사용 가능
- `Queue` 인터페이스를 상속하며 구현체는 `ArrayDeque`, `LinkedList`

<br/>

**ArrayDeque 클래스**

- 내부적으로 `Object[]` 배열 관리
- 스택으로 사용 시 `Stack` 클래스보다 빠름
- null 저장 불가

<br/>

**PriorityQueue 클래스**

- 데이터에 우선순위(priority)를 부여하여 우선순위가 높은 순으로 정렬
- 우선순위가 높은 데이터가 먼저 dequeue되며, 정렬 방식을 별도로 지정하지 않으면 낮은 숫자가 높은 우선순위를 가짐
- 우선순위 큐에 저장할 객체는 필수적으로 `Comparable` 인터페이스를 구현해야 함 (`compareTo()` 메소드를 통해 객체의 우선순위를 결정하기 때문) → 이러한 이유로 null 저장 불가능
- 저장공간으로 `Object[]` 배열을 사용하며, 각 요소를 힙(heap) 형태로 관리
- 주로 주어진 데이터들 중 최댓값 또는 최솟값을 조회할 때 사용

<br/>

**객체 생성 방법**

```java
ArrayDeque<T> arraydeque = new ArrayDeque<>();
PriorityQueue<T> priorityqueue = new PriorityQueue<>();

Deque<T> arraydeque = new ArrayDeque<>();
Deque<T> linkedlistdeque = new LinkedList<>();

Queue<T> arraydeque = new ArrayDeque<>(); //Object[] 배열로 관리되는 큐
Queue<T> linkedlistdeque = new LinkedList<>(); //Node 객체를 연결해 관리되는 큐
//큐는 맨 앞 요소가 삭제되는 dequeue 연산으로 당연히 배열(ArrayDeque)보다는 연결 리스트(LinkedList)가 적합
Queue<T> priorityqueue = new PriorityQueue<>();
```

<br/>
<hr/>
<br/>

### Set 인터페이스

1. 데이터를 중복해서 저장할 수 없음
2. 입력 순서대로의 저장 순서를 보장하지 않음 (`LinkedHashSet`은 제외)

- 순서 자체가 없으므로 객체를 인덱스로 관리하지 않음
- 중복 저장이 불가능하기 때문에 null 값도 하나만 저장 가능

<br/>

**Set 인터페이스에 선언된 대표 메소드**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/0e4add8d-5b74-47fb-aebf-91f6329d586e" width="60%"/><br/>

<br/>

**HashSet 클래스**

- 주로 어떤 경우에 사용? 아이디, 닉네임 등의 값 중복 여부 확인 시 (데이터가 정렬되어 있을 필요 없이 빠르게 중복 값인지만 확인하는 경우)
- Hashing으로 데이터의 위치를 계산하여 해당 데이터를 빠르게 검색
- Hash 기능 + `Set` 컬렉션의 조합으로 삽입, 삭제, 탐색에 O(1)의 시간복잡도

<br/>

**LinkedHashSet 클래스**

- 일반적인 `Set`과 달리 순서가 보장
- 중복은 허용하지 않으면서 순서를 보장받고 싶은 경우에 사용

<br/>

**TreeSet 클래스**

- `SortedSet` 인터페이스의 구현체로, `HashSet`과 마찬가지로 입력 순서대로의 저장 순서를 보장하지 않으며 중복 데이터 저장 불가
- 데이터의 '가중치에 따른 순서'대로 정렬, 즉 이진 검색 트리(Binary Search Tree) 자료구조의 형태로 데이터 저장
- 중복되지 않으면서 특정 규칙에 의해 정렬된 형태의 집합을 사용하고 싶은 경우에 적합 (데이터를 정렬하여 저장하는 자료구조인 **Tree** + 고유한 데이터만을 저장하는 자료구조인 **Set**의 조합)
- 검색, 범위 검색에 높은 성능

<br/>

**객체 생성 방법**

```java
HashSet<T> hashset = new HashSet<>();
LinkedHashSet<T> linkedhashset = new LinkedHashSet<>();
TreeSet<T> treeset = new TreeSet<>();
SortedSet<T> treeset = new TreeSet<>();

Set<T> hashset = new HashSet<>();
Set<T> linkedhashset = new LinkedHashSet<>();
Set<T> treeset = new TreeSet<>();
```

<br/>
<hr/>
<br/>

### Map 인터페이스

- Key와 Value의 쌍으로 구성된 데이터의 집합
- Value는 중복 저장 가능, Key는 해당 `Map`에서 고유
- 만약 중복된 Key 값으로 Value를 저장하면 기존 값을 덮어씀
- 저장 순서 유지X

<br/>

**Map 인터페이스에 선언된 대표 메소드**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/373bf79b-9aa7-43de-a9bf-5377db08928f" width="60%"/><br/>

<br/>

**Map.Entry 인터페이스**

- `Map` 인터페이스의 내부 인터페이스
- `HashMap`에 저장되는 데이터인 `Node` 클래스가 이를 구현

  ```java
  public interface Map<K, V> {
    //...
    interface Entry<K, V> {
      K getKey();
      V getValue();
      V setValue(V value);
    }
    //...
  }

  public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    //...
    transient Node<K,V>[] table;
    static class Node<K,V> implements Map.Entry<K,V> {
      final int hash;
      final K key;
      V value;
      Node<K,V> next;
    }
    //...
  }
  ```

<br/>

**HashMap 클래스**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/51ab86a7-8845-4ca8-9d8d-8c475896f65a" width="45%"><br/>

- `Hashtable`을 보완한 자료구조
- 배열과 연결 리스트가 결합된 해시 테이블 형태로, Key-Value 쌍을 하나의 데이터로 저장
- 중복 허용하지 않고 순서 보장X
- Key, Value로 null 허용
- 추가, 삭제, 검색 성능이 좋음
- 비동기로 작동하기 때문에 멀티 쓰레드 환경에는 적합X (대신 `ConcurrentHashMap` 사용)

<br/>

**LinkedHashMap 클래스**

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/9c29fff9-c847-49c2-8630-55c7085362e3" width="50%"><br/>

- `HashMap`을 상속하여 비슷하지만, `Entry`들이 연결 리스트를 구성하여 데이터의 순서를 보장 (`before`, `after` 필드가 각각 이전, 다음 `Entry`를 참조)
- 일반적인 `Map` 자료구조와 달리, 입력 순서대로 저장

<br/>

**TreeMap 클래스**

- 이진 검색 트리의 형태로 Key-Value 쌍의 데이터를 저장
- `SortedMap` 인터페이스를 구현하고 있어 Key 값을 기준으로 정렬됨
- 빠른 검색(특히 범위 검색)이 가능하나, Key와 Value를 저장하는 동시에 정렬을 수행하기 때문에 저장 시간이 다소 오래 걸림
- 정렬 순서: 숫자 → 알파벳 대문자 → 알파벳 소문자 → 한글 순

<br/>

**Hashtable 클래스**

- 자바 초기 버전에 나온 레거시 클래스
- Key를 특정 해시 함수를 통해 해싱한 후 나온 값을 배열의 인덱스로 사용하여 Value를 저장
- `HashMap` 보다는 느리지만 동기화 기본 지원
- Key, Value로 null 허용X

<br/>

**Properties 클래스**

- `Hashtable` 클래스를 상속
- `Properties(String, String)`의 형태로 데이터를 저장하는 단순화된 `Map`
- 주로 애플리케이션의 환경 설정과 관련된 속성 파일인 .properties를 설정하는데 사용

  ```java
  Properties AppProps = new Properties();

  //Properties 객체에 String : String 구조의 데이터 추가
  AppProps.setProperty("Backcolor", "White");
  AppProps.setProperty("Forecolor", "Blue");
  AppProps.setProperty("FontSize", "12");

  //test.properties 파일에 Properties 자료들을 저장
  Path PropertyFile = Paths.get("test.properties");
  try (Writer propWriter = Files.newBufferedWriter(PropertyFile)) {
    AppProps.store(propWriter, "Property File Test");
  } catch (IOException e) {
    e.printStackTrace();
  }
  ```

  **저장 결과 파일 내용**

  ```
  #Property File Test
  #현재 날짜
  Backcolor=White
  FontSize=12
  Forecolor=Blue
  ```

<br/>
<hr/>
<br/>

### 적절한 자료구조 사용하기

<img src="https://github.com/jkde7721/cs-interview-study/assets/65665065/faab5892-eced-44ee-a967-2688fca4ce93" width="70%"/>

<br/>
<br/>

### 참고

[참고 1](https://st-lab.tistory.com/142)

[참고 2](https://inpa.tistory.com/entry/JCF-%F0%9F%A7%B1-Collections-Framework-%EC%A2%85%EB%A5%98-%EC%B4%9D%EC%A0%95%EB%A6%AC#hashset_%ED%81%B4%EB%9E%98%EC%8A%A4)
