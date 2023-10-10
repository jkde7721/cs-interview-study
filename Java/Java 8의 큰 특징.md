## Java 버전

![Java 버전](https://miro.medium.com/v2/resize:fit:1400/format:webp/1*xsX4mZ6Nk4JNHika5ue8Sg.png)

### LTS(Long Term Support)  버전이란?
Java에는 다양한 버전이 존재한다. 그중 가장 많이 쓰이는 버전은 Java 8, 11, 17이다. 이 세 가지 버전이 많이 사용되는 이유는 이 버전들이 LTS(Long Term Support) 버전이기 때문이다.

LTS란 말 그대로 장기간에 걸쳐 지원을 해주겠다는 뜻이다. LTS 지원 버전은 출시 이후 8년간 보안 업데이트와 버그 수정을 지원해준다. 그 외에 6개월 간격으로 non-LTS 버전들이 출시되는데, 이러한 버전들은 짧은 기간만 해당 버전을 지원해준다.

따라서, LTS 버전인 Java 8, 11, 17이 가장 많이 사용되고 있다.



## Java 8
-   32bit를 지원하는 마지막 공식 Java 버전
-   Oracle JDK(Oracle사에서 지원하는 버전으로 유료) , Open JDK(오픈소스 기반의 무료)로 나뉨

[기능]
-   Lambda
-   stream
-   interface default method
-   Optional
-   new Date and Time API(LocalDateTime, …)

<br>

**1. Lambda**

Java 8 이전 익명 클래스의 사용을 람다를 이용하여 더욱 간결하고 직관적으로 구현 가능

```java
Runnable runnable = new Runnable(){
   @Override
   public void run(){
     System.out.println(*"Hello world !"*);
   }
 };
```

```java
Runnable runnable = () -> System.out.println(*"Hello world two!"*);
```

[Lambda식 관련 설명](https://mangkyu.tistory.com/113)

<br>

**2. Stream**

자바 8은 스트림 API를 통해 컬렉션을 처리하면서 발생하는 `모호함과 반복적인 코드 문제`와 `멀티코어 활용 어려움`이라는 두 가지 문제를 모두 해결

```java
List<String> list = Arrays.asList(*"franz"*, *"ferdinand"*, *"fiel"*, *"vom"*, *"pferd"*);
list.stream()
    .filter(name -> name.startsWith(*"f"*))
    .map(String::toUpperCase)
    .sorted()
    .forEach(System.out::println);
```

<br>

 **3. Interface의 Default method**
 
인터페이스는 기능에 대한 선언만 가능하기 때문에, 실제 코드를 구현한 로직은 포함될 수 없음
하지만 자바8에서 이러한 룰을 깨트리는 기능이 나오게 되었는 데 그것이 Default Method(디펄트 메소드)

메소드 선언 시에 default를 명시하게 되면 인터페이스 내부에서도 로직이 포함된 메소드를 선언할 수 있음 
```java   
    interface  MyInterface { 
	    default  void  printHello() { 	
		    System.out.println("Hello World"); 
	    } 
    } 
    
    class  MyClass  implements  MyInterface {} 
    
    public  class  DefaultMethod { 
	    public  static  void  main(String[] args){ 
		    MyClass myClass = new MyClass(); 	
		    myClass.printHello(); //실행결과 Hello World 출력 
		} 
    }
```

<br>

 **4. Optional**

Java8에서는 Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와줌
Optional<T>는  null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE(Null Point Exception)가 발생하지 않도록 도와줌
Optional 클래스는 아래와 같은 value에 값을 저장하기 때문에 값이 null이더라도 바로 NPE가 발생하지 않으며, 클래스이기 때문에 각종 메소드를 제공

```java 
public  final  class Optional<T> { 
	// If non-null, the value; if null, indicates no value is present  
	private  final T value; ... 
}
```

[Optional 관련 설명](https://mangkyu.tistory.com/70)


<br>

## Java 11 

-   Oracle JDK와 Open JDK 통합
-   Oracle JDK가 구독형 유료 모델로 전환 

**1. Strings & Files**

Strings and Files에는 몇 가지 새로운 메서드 추가

```java
*"Marco"*.isBlank();
*"Mar\nco"*.lines();
*"Marco  "*.strip();

Path path = Files.writeString(Files.createTempFile(*"helloworld"*, *".txt"*), *"Hi, my name is!"*);
String s = Files.readString(path);
```

**2. Run Source Files**

Java 10부터 Java 소스 파일 을 먼저 컴파일 하지 않고도 실행할 수 있다. 스크립팅을 향한 한 걸음

```bash
ubuntu@DESKTOP-168M0IF:~$ java MyScript.java
```

**3. 람다 매개변수에 대한 지역 변수 유형 추론(var)**

람다 표현식에 var 사용 가능

```java
(var firstName, var lastName) -> firstName + lastName
```

<br>

## Java 17

Java 17은 Java 11 이후의 새로운 Java LTS(장기 지원) 릴리스

-   Pattern Matching for switch (Preview)
-   Sealed Classes (Finalized)
-   Foreign Function & Memory API (Incubator)
-   Deprecating the Security Manager

  

**Pattern Matching for switch (Preview)**

이제 객체를 전달하여 기능을 전환하고 특정 유형을 확인할 수 있다.

```java
public String test(Object obj) {

    return switch(obj) {

    case Integer i -> *"An integer"*;

    case String s -> *"A string"*;

    case Cat c -> *"A Cat"*;

    default -> *"I don't know what it is"*;

    };

}
```

**Sealed Classes (Finalized)**

Java 15에서 preview 제공되었던 기능 완료

**Foreign Function & Memory API (Incubator)**

Java Native Interface(JNI)를 대체한다. 기본 함수를 호출하고 JVM 외부의 메모리에 액세스할 수 있다. 지금은 C가 C++, 포트란과 같은 추가 언어 지원 계획을 가지고 있다고 생각

**Deprecating the Security Manager**

자바 1.0 이후로 보안 관리자가 존재해 왔었지만 현재는 더 이상 사용되지 않으며 향후 버전에서는 제거될 예정



<br>

[Java 세부 버전별 변경사항](https://velog.io/@ljo_0920/java-%EB%B2%84%EC%A0%84%EB%B3%84-%EC%B0%A8%EC%9D%B4-%ED%8A%B9%EC%A7%95)
[Java 17 관련](https://techblog.gccompany.co.kr/%EC%9A%B0%EB%A6%AC%ED%8C%80%EC%9D%B4-jdk-17%EC%9D%84-%EB%8F%84%EC%9E%85%ED%95%9C-%EC%9D%B4%EC%9C%A0-ced2b754cd7)