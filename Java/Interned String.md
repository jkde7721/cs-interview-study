# Interned String

### 자바에서 스트링을 선언하는 2가지 방식

1.  리터럴

```java
String a = "hello"
String b = "hello"

print(a==b) // True

```

Java의 Heap 영역 안에 있는 **String Constant Pool**에 위치합니다.

String Constant Pool에 이미 존재하는 문자열이라면 같은 주소값을 공유합니다.

따라서 변수 a, b는 비교하였을 때, 문자열과 주소값이 모두 같은 것을 확인할 수 있습니다.

+**String Constant Pool**이란?

자바 메모리 중 Heap 영역 안에 위치하여 스트링을 별도로 관리하는 장소입니다.

Heap 안에 있기 때문에 모든 스레드가 공유할 수 있고 가비지 콜렉터의 대상이 됩니다.

2.  객체

```java
String c = new String("hello")
String d = new String("hello")

print(c==d) // False

```

Java의 Heap 영역에 할당됩니다.

새로운 인스턴스를 생성할 때마다 Heap 영역에 새로운 객체가 생성됩니다.

따라서 변수 c, d를 비교하였을 때 문자열은 같지만 Heap 영역에서 서로 다른 객체로 생성되었기 때문에 주소값은 다릅니다.

그림으로 표현하면 다음과 같습니다.
![image](https://github.com/jkde7721/cs-interview-study/assets/77728683/a381d011-70bb-4d73-8d70-2f5026013ec0)

### intern()

intern() 메서드는 String pool에서 리터럴 문자열이 이미 존재하는지 체크하고 존재하면 해당 문자열을 반환하고, 아니면 리터럴을 String pool에 넣어주고 그 주소값을 반환합니다.

intern()은 주로 ==연산자를 사용할 때 필요합니다.

```java
String a = "apple";
String b = new String("apple");
String c = b.intern()

System.out.println(a==b); // False
System.out.prrintln(a==c); // True

```

위 예제에서 c의 경우 string pool에 b의 값인 “apple”이미 존재하므로 해당 문자열을 반환합니다. 따라서 a와 c가 동일한 주소값을 가져 True가 나오게 됩니다.

----------

참고자료

[](https://velog.io/@kiiiyeon/%EC%9E%90%EB%B0%94-%EC%8A%A4%ED%8A%B8%EB%A7%81-%EA%B0%9D%EC%B2%B4-%EC%83%9D%EC%84%B1-%EB%B0%A9%EB%B2%95)[https://velog.io/@kiiiyeon/자바-스트링-객체-생성-방법](https://velog.io/@kiiiyeon/%EC%9E%90%EB%B0%94-%EC%8A%A4%ED%8A%B8%EB%A7%81-%EA%B0%9D%EC%B2%B4-%EC%83%9D%EC%84%B1-%EB%B0%A9%EB%B2%95)
