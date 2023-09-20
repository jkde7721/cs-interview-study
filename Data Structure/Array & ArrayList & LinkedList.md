## Array & ArrayList & LinkedList

### Array

> 흔히 알고 있는 배열로, 가장 기본적인 순차 자료구조

- 배열의 **크기**와 **데이터 타입**을 함께 선언 ex. `Integer[] array = new Integer[10];` `int[] array2 = new int[10];`
- 논리적인 저장 순서와 물리적인 저장 순서가 일치
- 인덱스로 임의의 원소에 접근 가능 → 탐색 연산에 O(1)의 시간복잡도
- `int[] arr = new int[5];` 배열에서 인덱스 2에 데이터 삽입: `arr[2]`, `arr[3]` → `arr[3]`, `arr[4]`로 복사 후 `arr[2]`에 새로운 값 삽입 → 삽입 연산에 O(N)의 시간복잡도
- `int[] arr = new int[5];` 배열에서 인덱스 2에서 데이터 삭제: `arr[3]`, `arr[4]` → `arr[2]`, `arr[3]`으로 복사하면 기존 `arr[2]` 값이 삭제 → 삭제 연산에 O(N)의 시간복잡도

<br/>

### ArrayList

> Array와 비슷하지만 Array는 배열의 크기가 고정되어 있는 반면, ArrayList는 배열의 크기를 동적으로 변경할 수 있음

- 배열과 동일하게 인덱스로 원소들을 관리하여 탐색 연산에 O(1)의 시간복잡도
- 삽입 연산 시 배열 용량이 꽉 찼을 경우 더 큰 용량의 배열을 만들어 옮기는 작업 수행

**실제 ArrayList 클래스**

```java
//일부 코드 변경
public class ArrayList<E> {

  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
  transient Object[] elementData; //내부 배열
  private int size;

  public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; //빈 배열
  }

  public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity]; //지정한 크기의 새로운 배열 생성
    } else if (initialCapacity == 0) {
      this.elementData = EMPTY_ELEMENTDATA; //빈 배열
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  //삽입 연산
  public boolean add(E e) {
    if (size == elementData.length) elementData = grow(size + 1); //용량이 꽉 찬 경우
    elementData[size] = e;
    size += 1;
    return true;
  }

  private Object[] grow(int minCapacity) {
    int oldCapacity = elementData.length;
    if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1); //새로운 배열의 용량은 보통 (oldCapacity + (oldCapacity >> 1))
      return elementData = Arrays.copyOf(elementData, newCapacity); //기존 배열 새로운 배열에 복사
    } else {
      return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
  }
}
```

- 실제 `ArrayList` 내부에 `Object[] elementData` 배열 필드를 가지고 있음
- 삽입 연산 시, 기존 용량을 초과한다면 (기존 용량 + 기존 용량 / 2)의 크기를 가진 새로운 배열을 생성하고 기존 배열을 복사한 후 삽입 → 대량의 원소를 삽입하는 경우 빈번한 복사 연산으로 성능 저하 발생
- 초기 용량을 지정하지 않으면 배열의 크기는 `DEFAULT_CAPACITY = 10`로 설정되며, 많은 원소가 삽입되는 상황이라면 배열의 복사가 빈번하게 발생할 수 있기 때문에 `ArrayList` 객체 생성 시 초기 용량을 지정해주는 것이 좋음

**ArrayList API**

- `add(E element)`: 원소를 마지막에 추가하기

  배열 마지막에 원소를 추가하기 때문에 빠르게 추가 가능, but 용량 초과 시 복사 연산 발생하기 때문에 O(N)의 시간복잡도

- `add(int index, E element)`: 원소를 지정된 위치에 추가하기

  배열의 처음 또는 중간에 데이터를 추가하는 경우, 예를 들어 5번 인덱스까지 있는 배열에서 2번 인덱스에 원소를 추가하면 2번 인덱스부터 원소들을 뒤로 한칸씩 밀어서 중간에 공간을 만드는 추가적인 작업이 필요하기 때문에 O(N)의 시간복잡도

- `remove(int index)`: 원소의 인덱스로 삭제하기

  마지막 원소를 삭제한다면 빠르게 삭제 가능, but 처음 또는 중간의 원소를 삭제하게 되면 뒤의 원소들을 앞으로 한칸씩 땡겨 빈 공간을 채우는 추가적인 작업이 필요하기 때문에 O(N)의 시간복잡도

- `get(int index)`: 인덱스에 해당하는 원소 찾아오기

  배열은 인덱스에 해당하는 원소를 O(1)의 시간복잡도로 탐색 가능

➡️ 정리하면 ArrayList는 탐색은 빠르게 할 수 있지만 삽입, 삭제는 비교적 느림

<br/>

### LinkedList

> 배열의 문제점을 해결하기 위한 자료구조로, 내부적으로 양방향의 연결 리스트로 구성되어 있어 참조하려는 원소에 따라 순방향 또는 역방향으로 순회 가능

- 배열의 문제점

  - **배열 크기 변경 불가능**: 크기를 변경할 수 없어 항상 새로운 배열을 생성해서 복사해야 함, 실행 속도를 향상시키기 위해 충분히 큰 용량을 미리 확보한다면 메모리 낭비 발생
  - **비순차적인 데이터 삽입, 삭제에 많은 시간 소요**: 순서대로 데이터를 삽입하거나 마지막에서부터 데이터를 삭제하는 것은 빠른 반면, 배열의 중간에 데이터를 삽입하거나 삭제하면 빈 공간을 만들기 위한 데이터 이동과 빈 공간을 채우기 위한 데이터 이동이 빈번하게 발생

- LinkedList의 각 원소들은 데이터 뿐만 아니라 자신의 앞뒤에 있는 원소의 물리적 위치 또한 저장, 배열과 달리 각 원소를 인덱스로 관리하지 않음
- 삽입, 삭제 연산은 O(1)의 시간복잡도인 반면, 논리적인 저장 순서와 물리적인 저장 순서가 다르기 때문에 탐색 연산은 O(N)의 시간복잡도

**실제 LinkedList 클래스**

```java
//일부 코드 변경
public class LinkedList<E> {
  transient int size = 0;
  transient Node<E> first; //Pointer to first node.
  transient Node<E> last; //Pointer to last node.
  //...
  private static class Node<E> {
    E item; //데이터
    Node<E> next; //다음 노드 참조
    Node<E> prev; //이전 노드 참조

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}
```

**LinkedList API**

- `add(E element)`: 원소를 마지막에 추가하기

  리스트의 마지막 원소에 대한 참조 `last`를 통해 O(1)의 시간복잡도로 원소를 마지막에 추가 가능

- `add(int index, E element)`: 원소를 지정된 위치에 추가하기

  인덱스를 지정해서 추가하는 경우, 해당 위치로 가기 위해 `first`부터 탐색해야 하기 때문에 O(N)의 시간복잡도

- `remove(int index)`: 지정된 위치의 원소를 삭제하기

  배열의 경우 중간의 원소를 삭제하면 빈 공간을 다시 채워주는 작업이 필요한 반면, `LinkedList`는 삭제하려는 원소 앞 or 뒤 원소의 `next`, `prev` 값을 조정해주면 됨

- `get(int index)`: 인덱스에 해당하는 원소 찾아오기

  `LinkedList`는 `ArrayList`와 달리 인덱스를 통해 검색하는 것이 아닌 `first`에서부터 해당 원소까지 탐색해야 하기 때문에 O(N)의 시간복잡도

<br/>

### ArrayList vs LinkedList 성능 차이

```java
public class ArrayListLinkedListTest {

  public static void main(String[] args) {
    ArrayList<Integer> arrayList = new ArrayList<>(2000000);
    LinkedList<Integer> linkedList = new LinkedList<>();

    System.out.println("=== 순차적으로 추가하기 ===");
    System.out.println("ArrayList: " + addSeq(arrayList));
    System.out.println("LinkedList: " + addSeq(linkedList));
    System.out.println();

    System.out.println("=== 중간에 추가하기 ===");
    System.out.println("ArrayList: " + addMid(arrayList));
    System.out.println("LinkedList: " + addMid(linkedList));
    System.out.println();

    System.out.println("=== 순차적으로 삭제하기 ===");
    System.out.println("ArrayList: " + removeSeq(arrayList));
    System.out.println("LinkedList: " + removeSeq(linkedList));
    System.out.println();

    System.out.println("=== 중간에서 삭제하기 ===");
    System.out.println("ArrayList: " + removeMid(arrayList));
    System.out.println("LinkedList: " + removeMid(linkedList));
  }

  public static long addSeq(List<Integer> list) {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) list.add(i);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static long addMid(List<Integer> list) {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) list.add(500, i);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static long removeSeq(List<Integer> list) {
    long startTime = System.currentTimeMillis();
    for (int i = list.size() - 1; i >= 0; i--) list.remove(i);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static long removeMid(List<Integer> list) {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) list.remove(i);
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }
}
```

```
실행 결과

=== 순차적으로 추가하기 ===
ArrayList: 21
LinkedList: 119

=== 중간에 추가하기 ===
ArrayList: 2946
LinkedList: 13

=== 순차적으로 삭제하기 ===
ArrayList: 9
LinkedList: 26

=== 중간에서 삭제하기 ===
ArrayList: 2127
LinkedList: 133
```

**순차적으로 추가하기**

- `ArrayList`: 순차적으로 추가하면 배열 원소들의 이동이 없이 추가만 하면 되기 때문에 빠르게 가능
- `LinkedList`: 내부적으로 리스트의 마지막 원소에 대한 참조를 관리하기 때문에 전체 리스트 탐색 없이 빠르게 가능 (`ArrayList`와 큰 차이X)

**중간에 추가하기**

- `ArrayList`: 중간에 추가하게 되면 빈 공간을 만들어야 하기 때문에 원소들의 이동이 발생하므로 시간이 오래 걸림
- `LinkedList`: 중간에 추가할 때는 추가하고자 하는 원소 앞 or 뒤 원소의 `next`, `prev` 참조값을 변경해주면 되기 때문에 빠르게 가능

**순차적으로 삭제하기**

- `ArrayList`: 순차적으로 마지막 원소를 삭제할 때는 원소들의 이동이 필요 없기 때문에 빠르게 가능
- `LinkedList`: 마지막 원소의 앞 원소의 `next` 참조값과 리스트의 마지막 원소에 대한 참조값인 `last`를 변경해주면 되기 때문에 빠르게 가능 (`ArrayList`와 큰 차이X)

**중간에서 삭제하기**

- `ArrayList`: 중간에서 삭제하는 것도 마찬가지로 중간에 빈 공간이 생기기 때문에 채우기 위해 원소들의 이동이 발생하므로 시간이 오래 걸림
- `LinkedList`: 중간에서 삭제하는 것도 추가하는 것과 마찬가지로 삭제하고자 하는 원소 앞 or 뒤 원소의 `next`, `prev` 참조값을 변경해주면 되기 때문에 빠르게 가능

> **순차적으로 삽입/삭제하는 경우에는 ArrayList가 LinkedList보다 빠름** (배열 용량 초과로 인한 복사 연산은 고려X)

> **중간 데이터를 삽입/삭제하는 경우에는 LinkedList가 ArrayList보다 빠름**
> LinkedList는 각 요소 간 연결만 변경해주면 되기 때문에 처리 속도가 빠른 반면, ArrayList는 각 요소들을 재배치하여 추가할 공간을 확보하거나 빈 공간을 채워야하기 때문에 처리 속도가 느림

| 컬렉션     | 탐색 | 삽입/삭제 | 설명                                                                             |
| ---------- | ---- | --------- | -------------------------------------------------------------------------------- |
| ArrayList  | 빠름 | 느림      | - 순차적인 삽입/삭제는 더 빠름 <br/> - 빈번한 배열 복사로 비효율적인 메모리 사용 |
| LinkedList | 느림 | 빠름      | - 데이터가 많아질 수록 접근성이 떨어짐                                           |

<br/>

### ArrayList vs LinkedList 시간복잡도

| 메소드              | ArrayList | LinkedList |
| ------------------- | --------- | ---------- |
| `add()`             | O(1)      | O(1)       |
| `add(index, value)` | O(N)      | O(1)       |
| `remove(index)`     | O(N)      | O(1)       |
| `remove(value)`     | O(N)      | O(1)       |
| `get(index)`        | O(1)      | O(N)       |
| `indexOf(value)`    | O(N)      | O(N)       |

\*삽입, 삭제 시 데이터 접근 과정은 고려X

- 배열의 경우 연속적으로 메모리 상에 존재하여 `배열의 시작 주소 + index * 데이터 타입의 크기`를 통해 원하는 원소에 빠르게 접근 가능
- 연결 리스트는 불연속적으로 위치한 각 원소들이 서로 연결된 것이기 때문에 처음부터 순서대로 따라가야만 원하는 원소에 접근 가능, 즉 LinkedList는 저장한 데이터의 개수가 많아질수록 데이터를 읽어 오는 시간, 접근 시간(access time)이 매우 길어짐

<br/>

### 참고

[Array vs ArrayList vs LinkedList 1](https://gwang920.github.io/datastructure/Array-ArrayList-LinkedList-%EB%B3%B5%EC%82%AC%EB%B3%B8/)

[Array vs ArrayList vs LinkedList 2](https://m.blog.naver.com/tjsdk2769/221781313533)

[Array vs ArrayList vs LinkedList 3](https://prosaist0131.tistory.com/entry/Array-%EC%99%80-ArrayList-%EC%99%80-LinkedList-%EC%9D%98-%EC%B0%A8%EC%9D%B4)

[ArrayList vs LinkedList](https://devlog-wjdrbs96.tistory.com/64)
