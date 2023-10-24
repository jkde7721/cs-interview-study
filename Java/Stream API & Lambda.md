## Stream API & Lambda

### Stream API

Java는 객체지향 언어이기 떄문에 기본적으로 함수형 프로그래밍이 불가능. JDK8부터 Stream API와 람다식, 함수형 인터페이스 등을 지원하면서 Java를 이용해 함수형으로 프로그래밍할 수 있는 API들을 제공

그 중 Stream API는 데이터를 추상화하고 처리하는데 자주 사용되는 함수들을 정의한 것으로, 여기서 데이터를 추상화하였다는 것은 데이터의 종류에 상관 없이 같은 방식으로 데이터를 처리할 수 있다는 것을 의미

<br/>

**배열이나 리스트의 데이터를 정렬된 상태로 출력 (Stream API 사용X)**

```java
//Stream 사용 전
String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"}
List<String> nameList = Arrays.asList(nameArr);

//원본의 데이터가 직접 정렬됨
Arrays.sort(nameArr);
Collections.sort(nameList);

for (String str: nameArr) {
    System.out.println(str);
}
for (String str : nameList) {
    System.out.println(str);
}
```

**Stream API 사용한 경우**

```java
//Stream 사용 후
String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"}
List<String> nameList = Arrays.asList(nameArr);

//원본의 데이터가 아닌 별도의 Stream을 생성함 (원본 데이터에 변경X)
Stream<String> nameStream = nameList.stream();
Stream<String> arrayStream = Arrays.stream(nameArr);

//복사된 데이터를 정렬하여 출력함
nameStream.sorted().forEach(System.out::println);
arrayStream.sorted().forEach(System.out::println);
```

<br/>
<br/>

### Stream API 특징

- 원본 데이터를 변경하지 않는다.
- 일회용이다.
- 내부 반복으로 작업을 처리한다.

<br/>

**1. 원본의 데이터를 변경하지 않는다.**

Steam API는 원본의 데이터를 읽어 별도의 Stream을 생성한다. 즉 원본 데이터는 읽기만 할 뿐 데이터의 변경은 별도의 Stream 요소들에 대해 이뤄진다.

```java
List<String> sortedList = nameList.stream() //별도의 Stream 생성
                                    .sorted().collect(Collections.toList());
```

<br/>

**2. Stream은 일회용이다.**

Stream API는 일회용이기 때문에 한 번 사용이 끝나면 재사용이 불가능하다. 만약 Stream이 또 필요한 경우에는 Stream을 다시 생성해주어야 한다. 만약 사용이 끝나 닫힌 Stream을 다시 사용하려고 하면 `IllegalStateException` 예외가 발생한다.

```java
userStream.sorted().forEach(System.out::println);

//스트림이 이미 사용되어 닫혔으므로 에러 발생
int count = userStream.count();

//IllegalStateException 발생
java.lang.IllegalStateException: stream has already been operated upon or closed
    at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:229)
    at java.util.stream.ReferencePipeline.noneMatch(ReferencePipeline.java:459)
```

<br/>

**3. 내부 반복으로 작업을 처리한다.**

Stream을 이용하면 코드가 간결해지는 이유 중 하나는 '내부 반복' 때문이다. 기존에는 반복문을 사용하기 위해 `for`나 `while` 등과 같은 문법을 사용해야 했지만, Stream에서는 이러한 반복 문법을 메소드 내부에 숨기고 있기 때문에 보다 간결한 코드 작성이 가능하다.

```java
//반복문이 forEach라는 메소드 내부에 숨겨져 있다.
nameStream.forEach(System.out::println);
```

<br/>
<br/>

### Stream API 연산 종류

**Stream API 연산의 3가지 단계**: 생성하기 → 가공하기 → 결과 만들기

**1. 생성하기**

- Stream 객체를 생성하는 단계로, 배열, 컬렉션, 임의의 수, 파일 등 거의 모든 것을 스트림으로 생성 가능하다.
- Stream은 재사용이 불가능하므로, 연산이 끝나 스트림이 닫히면 다시 생성해줘야 한다.

**2. 가공하기**

- 원본의 데이터를 별도의 데이터로 가공하기 위한 중간 연산
- 연산 결과를 Stream으로 다시 반환하기 때문에 연속해서 중간 연산을 이어갈 수 있다.
- Stream 연산들이 연결된 것을 연산 파이프라인이라고 한다.
- Stream 연산들은 매개변수로 함수형 인터페이스(Functional Interface)를 전달받는다.

**3. 결과 만들기**

- 가공된 데이터로부터 원하는 결과를 만들기 위한 최종 연산
- Stream의 요소들을 소모하면서 연산이 수행되기 때문에 1번만 처리 가능하다.

**Stream 연산 예시 코드**

```java
List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
myList
    .stream()						//생성하기
    .filter(s -> s.startsWith("c"))	//가공하기
    .map(String::toUpperCase)		//가공하기
    .sorted()						//가공하기
    .count();                       //결과 만들기
```

➡️ 각 연산의 자세한 API는 여기 [링크](https://mangkyu.tistory.com/114) 참고하기!

<br/>
<hr/>
<br/>

### 람다식(Lambda Expression)

함수를 **하나의 식(expression)으로 표현한 것**으로, 함수를 람다식으로 표현하면 **메소드의 이름이 필요 없기 때문**에 람다식은 **익명 함수(Anonymous Function)의 한 종류**라고 볼 수 있다.

익명 함수(Anonymous Function)란 함수의 이름이 없는 함수로, 익명 함수들은 모두 일급 객체이다. 일급 객체인 함수는 변수처럼 사용 가능하여 매개변수로 전달 가능하다는 등의 특징이 있다.

람다식이 등장하게 된 이유는 **불필요한 코드를 줄이고, 가독성을 높이기 위함**이다. **함수형 인터페이스의 인스턴스를 생성하여 함수를 변수처럼 선언하는 람다식**에서는 메소드 이름이 불필요하다고 여겨 이를 사용하지 않는다. 대신 컴파일러가 문맥을 살펴 타입을 추론한다. 또한 람다식으로 선언된 함수는 1급 객체이기 때문에 **Stream API의 매개변수로 전달 가능**하다.

```java
//기존 방식의 함수 정의
반환티입 메소드명 (매개변수, ...) {
	실행문
}
// 예시
public String hello() {
    return "Hello World!";
}

//람다 방식의 함수 정의
(매개변수, ... ) -> { 실행문 ... } //메소드명 불필요
// 예시
() -> "Hello World!";
```

<br/>
<br/>

### 람다식(Lambda Expression)의 특징

**람다식(Lambda Expression)의 특징**

- 람다식 내에서 사용되는 지역변수는 `final`이 붙지 않아도 상수로 간주된다.
- 람다식으로 선언된 변수명은 다른 변수명과 중복될 수 없다.

  ```java
  //String s = "hello"; //아래 람다식과 변수명 중복으로 불가능
  Consumer<String> consumer = s -> System.out.println("s = " + s);
  String s = "hello"; //람다식 선언 후에는 가능
  ```

<br/>

**람다식(Lambda Expression)의 장점**

1. 코드를 간결하게 만들 수 있다.
2. 식에 개발자의 의도가 명확히 드러나 가독성이 높아진다.
3. 함수를 만드는 과정없이 한번에 처리할 수 있어 생산성이 높아진다.
4. 병렬 프로그래밍이 용이하다.

<br/>

**람다식(Lambda Expression)의 단점**

1. 람다를 사용하면서 만든 무명함수는 재사용이 불가능하다.
2. 디버깅이 어렵다.
3. 람다를 남발하면 비슷한 함수가 중복 생성되어 코드가 지저분해질 수 있다.
4. 재귀로 만들 경우에는 부적합하다.

<br/>
<hr/>
<br/>

### 함수형 인터페이스(Functional Interface)

**단 하나의 추상 메소드만을 가지는 인터페이스**로, 람다식은 함수형 인터페이스의 인스턴스를 반환한다. 인터페이스에 `@FunctionalInterface` 어노테이션을 선언하여 함수형 인터페이스의 조건을 만족하는지 컴파일 시점에 검사할 수 있다. 기존에는 익명 클래스를 통해 함수형 인터페이스의 인스턴스를 생성하였으나 람다식을 이용해 더 간결한 코드로 생성 가능하다.

```java
@FunctionalInterface
public interface Calculator {
    int add(int a, int b);
}

public static void main(String[] args) {
    //익명 클래스로 생성
    Calculator calculator = new Calculator() {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
    };
    //람다식으로 생성
    Calculator calculator = (a, b) -> a + b;
}
```

<br/>

**Java에서 제공하는 대표적인 함수형 인터페이스**: `Supplier<T>`, `Consumer<T>`, `Function<T>`, `Predicate<T>`

**1. Supplier**

- 매개변수 없이 반환값 만을 갖는 함수형 인터페이스
- `T get()`을 추상 메소드로 가짐

```java
//정의
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

//사용 예시
Supplier<String> supplier = () -> "Hello World!";
System.out.println(supplier.get());
```

<br/>

**2. Consumer**

- `T` 타입의 객체를 매개변수로 받아 사용하며 반환값은 없는 함수형 인터페이스
- `void accept(T t)`를 추상 메소드로 가짐
- `andThen(Consumer<? super T> after)`라는 디폴트 메소드를 제공하는데, 이를 통해 하나의 함수 호출이 끝난 후 다음 Consumer를 연쇄적으로 호출할 수 있다.

```java
//정의
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

//사용 예시
Consumer<StringBuilder> consumer = sb -> sb.append(" World!");
consumer.andThen(System.out::println).accept(new StringBuilder("Hello")); //Hello World! 출력
```

<br/>

**3. Function**

- `T` 타입의 객체를 매개변수로 받아서 처리한 후 `R` 타입의 객체를 반환하는 함수형 인터페이스
- `R apply(T t)`를 추상 메소드로 가짐
- Consumer와 마찬가지로 `andThen` 디폴트 메소드를 제공하고 있으며, 추가적으로 `compose(Function<? super V, ? extends T> before)`라는 디폴트 메소드를 제공하는데, 이는 첫 번째 함수 실행 이전에 먼저 함수를 실행하여 연쇄적으로 호출해준다.
- `identity()`는 자기 자신을 반환하는 static 메소드

```java
//정의
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}

//사용 예시
Function<String, Integer> function = str -> str.length();
//또는 Function<String, Integer> function = String::length; //메소드 참조
function.apply("Hello World"); //11 반환
```

<br/>

**4. Predicate**

- `T` 타입의 객체를 매개변수로 받아 처리한 후 `boolean`을 반환하는 함수형 인터페이스
- `boolean test(T t)`을 추상 메소드로 가짐

```java
//정의
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }

    @SuppressWarnings("unchecked")
    static <T> Predicate<T> not(Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return (Predicate<T>)target.negate();
    }
}

//사용 예시
Predicate<String> predicate = (str) -> str.equals("Hello World");
predicate.test("Hello World"); //true 반환
```

<br/>
<hr/>
<br/>

### 메소드 참조(Method Reference)

**함수형 인터페이스를 람다식이 아닌 일반 메소드를 참조시켜 선언하는 방법**이다.
람다식은 종종 기존 메소드를 단순히 호출만 하는 경우가 많은데, 메소드 참조는 말 그대로 메소드를 참조해서 매개변수의 정보 및 리턴 타입을 추론하여 람다식에서 불필요한 매개변수를 제거하는 것이 목적이다.

참조 가능한 메소드에는 **일반 메소드, static 메소드, 생성자**가 있으며 `클래스 이름::메소드 이름` 형식으로 참조할 수 있다. 메소드 참조도 람다식과 마찬가지로 함수형 인터페이스의 인스턴스를 반환하므로 해당 함수형 인터페이스의 추상 메소드가 어떤 매개 변수를 가지고, 반환 타입이 무엇인가에 따라 달라진다.

<br/>

**정적 메소드와 인스턴스 메소드 참조**

- 정적(static) 메소드를 참조하는 경우에는 `클래스 이름::정적 메소드 이름`으로 작성
- 인스턴스(instance) 메소드를 참조하는 경우에는 먼저 객체를 생성한 뒤 `참조 변수 이름::인스턴스 메소드 이름`으로 작성

```java
//IntBinaryOperator는 int applyAsInt(int left, int right); 추상 메소드를 가진 자바의 함수형 인터페이스
//정적 메소드 참조
IntBinaryOperator operator = (a, b) -> Math.addExact(a, b);
IntBinaryOperator operator = Math::addExact; //메소드 참조

//인스턴스 메소드 참조
Consumer<String> consumer = str -> System.out.println(str);
Consumer<String> consumer = System.out::println; //이때 System.out은 참조 변수
```

<br/>

**매개변수의 메소드 참조**

- 람다식의 인자로 전달된 변수의 메소드를 호출하는 경우
- `(a, b) -> a.instanceMethod(b);`와 같이 **a 매개변수의 메소드를 호출**해서 b 매개변수를 인자로 전달하는 경우 등 → `클래스 이름::메소드 이름`으로 작성

```java
Function<String, Integer> function = str -> str.length();
Function<String, Integer> function = String::length; //매개변수 str의 메소드를 호출
```

<br/>

**생성자 참조**

- `(a, b) -> new 클래스(a, b);`와 같이 단순히 객체를 생성하고 반환하는 람다식은 생성자 참조로 대체 가능 → `클래스 이름::new`으로 작성
- 생성자가 오버로딩되어 여러 개 있을 경우, 컴파일러는 함수형 인터페이스의 추상 메소드와 동일한 매개 변수 타입과 개수를 가지고 있는 생성자를 찾아 실행한다.

```java
Function<Long, Member> function = id -> new Member(id);
Function<Long, Member> function = Member::new; //메소드 참조

//BiFunction<T, U, R>은 R apply(T t, U u);를 추상 메소드로 가진 자바의 함수형 인터페이스
BiFunction<Long, String, Member> biFunction = (id, name) -> new Member(id, name);
BiFunction<Long, String, Member> biFunction = Member::new; //메소드 참조

public class Member {
    private Long id;
    private String name;

    public Member(Long id) {
        this.id = id;
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

<br/>
<br/>

### 참고

[Stream API에 대한 이해](https://mangkyu.tistory.com/112)

[람다식과 함수형 인터페이스](https://mangkyu.tistory.com/113)

[람다식에서 메소드 참조](https://colinch4.github.io/2021-06-11/%EB%9E%8C%EB%8B%A4%EC%8B%9D_2/)

[Stream API의 고급 활용 및 사용 시 주의사항](https://mangkyu.tistory.com/115) → 읽어보기 추천
