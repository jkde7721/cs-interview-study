# String vs String Buffer vs String Builder

자바에서는 문자열을 다루는 자료형 클래스로 `String` vs `String Buffer` vs `String Builder` 이 3가지 자료형을 지원한다.

우선 크게 나누자면 `String`과 `String Buffer/String Builder`로 나눌 수 있을 것 같다. 둘의 가장 큰 차이점은 String은 불변, String Buffer, String Builder은 가변이라는 점이다.

# String

String 클래스를 살펴보면 아래와 같다.

```java
public final class String implements java.io.Serializable, Comparable {
	private final byte[] value;
}

```

인스턴스 생성 시 생성자의 매개변수로 입력 받는 문자열은 value라는 인스턴스 변수에 저장된다.

value는 final로 선언되어 있어 값을 바꿀 수 없다. 즉, 한번 할당한 String 객체는 변하지 않으므로 불변 자료형이다.

불변 자료형이기 때문에 다음과 같은 이점이 있다.

-   동시에 실행되는 여러 스레드에서 안정적이게 공유 가능
-   String pool에 각 리터럴 문자열의 하나만 저장하며 다시 사용하거나 캐싱에 이용 가능하며 이로 인해 힙 공간을 절약할 수 있다는 장점이 있다.

### String의 문자열 연산(추가하거나 변경)

```java
String result = "hello";
result += " ";
result += "jump to java";
System.out.println(result); // hello jump to java

```

1.  “hello” 문자열이 heap에 생성, result가 이를 가리키고 있다.

2.  “hello”에 “ “를 더한 “hello “가 heap에 생성되고 result는 새로 생성된 “hello “를 참조한다.

3.  이 때 “hello”는 자신을 가리키는 곳이 없어지므로 GC의 대상이 된다.


이처럼 덧셈(+) 연산자를 이용해 String 인스턴스의 문자열을 결합하면, 내용이 합쳐진 새로운 String 인스턴스를 생성한다. 따라서 문자열을 많이 결합한다면 매번 문자열이 업데이트 될 때마다 메모리 블럭이 추가되고, 일회용으로 사용된 이 메모리들은 추후에 GC의 제거 대상이 되어 빈번하게 Minor GC를 일으켜 Full GC(Major Gc)를 일으킬 수 있는 원인이 된다.

즉, String으로 문자열 연산을 많이 한다면 공간의 낭비 뿐만 아니라 속도 또한 매우 느려지게 된다는 단점이 있다.

# ****StringBuffer / StringBuilder****

StringBuffer나 StringBuilder의 경우 문자열 데이터를 다룬다는 점에서 String 객체와 같지만, 객체의 공간이 부족해지는 경우 버퍼의 크기를 유연하게 늘려주어 가변(mutable)적이라는 차이점이 있다.

두 클래스는 내부 Buffer(데이터를 임시로 저장하는 메모리)에 문자열을 저장해두고 그 안에서 추가, 수정, 삭제 작업을 할 수 있도록 설계되어 있다. .append() .delete() 등의 API를 이용하여 동일 객체 내에서 문자열 크기를 변경하는 것이 가능하다.

```java
StringBuffer sb = new StringBuffer();  // StringBuffer 객체 sb 생성
sb.append("hello");
sb.append(" ");
sb.append("jump to java");
String result = sb.toString();
System.out.println(result); // hello jump to java

```

따라서 값이 변경될 때마다 새롭게 객체를 만드는 String 보다 훨씬 빠르기 때문에, 문자열의 추가, 수정, 삭제가 빈번하게 발생할 경우라면 String 클래스가 아닌 StringBuffer / StringBuilder를 사용하는 것이 이상적이라 말할 수 있다.

## ****StringBuffer / StringBuilder 차이점****

둘 다 크기가 유연하게 변하는 가변적인 특성을 가지고 있으며, 제공하는 메서드도 똑같고 사용하는 방법도 동일하다. 딱 한 가지 차이점은 멀티스레드에서 안전한지의 여부이다.

StringBuffer는 메서드에서 synchronized 키워드를 사용하기 때문에 동기화를 지원하며 멀티 스레드 환경에서도 안전하게 동작할 수 있다. 하지만 StringBuilder는 동기화를 지원하지 않는다.

그래서 web이나 소켓환경과 같이 **비동기로 동작하는 경우가 많을 때는 StringBuffer를 사용**하는 것이 안전하다는 것을 알수가 있다.

----------

참고자료

[](https://inpa.tistory.com/entry/JAVA-%E2%98%95-String-StringBuffer-StringBuilder-%EC%B0%A8%EC%9D%B4%EC%A0%90-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90)[https://inpa.tistory.com/entry/JAVA-☕-String-StringBuffer-StringBuilder-차이점-성능-비교](https://inpa.tistory.com/entry/JAVA-%E2%98%95-String-StringBuffer-StringBuilder-%EC%B0%A8%EC%9D%B4%EC%A0%90-%EC%84%B1%EB%8A%A5-%EB%B9%84%EA%B5%90)
