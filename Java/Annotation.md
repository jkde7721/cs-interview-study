# Annotation

어노테이션(Annotation)은 메타데이터(metadata)로서 프로그램 그 자체의 일부분은 아니지만 프로그램에 대한 데이터를 제공한다. 그래서 어노테이션 자체는 어노테이션을 붙은 코드 동작에 영향을 미치지는 않는다. 어노테이션은 다음과 같은 상황에 사용된다.

-   컴파일러에게 필요한 정보를 제공
    -   컴파일러가 에러를 감지하거나, 경고를 띄우지 않게 하기 위함.
-   컴파일/배포 시에 필요한 처리 기능
    -   SW 개발 툴에서 어노테이션의 정보를 통해 특정 코드를 추가할 수 있음.
-   런타임 처리 제공
    -   런타임에도 어노테이션의 정보를 통해 필요한 처리를 할 수 있음. (Java Reflection)

요약하자면, 어노테이션은 다음과 같이 정의가 가능하다.

어노테이션은 작성한 코드에 대해 추가적인 정보를 제공하면서 컴파일 타임 혹은 런타임 시점에서 해당 코드에 필요한 추가적인 처리를 해 주는 역할을 한다.

<br>


## 자바 표준 어노테이션

자바에서 기본적으로 제공하는 어노테이션이다.

**@Override**

컴파일러에게 오버라이드하는 메소드라는 것을 알린다.

부모 클래스나 구현해야 할 인터페이스에서 해당 메소드를 찾을 수 없을 경우 오류를 발생시킨다.

**@Deprecated**

앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.

만약 위 어노테이션이 붙은 대상을 사용할 경우 컴파일 시 경고를 발생시킨다.

**@SuppressWarnings**

컴파일러의 특정 경고 메시지가 나타나지 않게 해 준다.

**@SafeVarargs**

제네릭 타입의 가변 인자에 사용할 때 경고를 무시한다.

**@FunctionalInterface**

함수형 인터페이스라는 것을 알린다.

함수형 인터페이스의 "하나의 추상메서드만 가져야 한다는 제약"을 확인해준다.

<br>

## 메타 어노테이션

사용자 커스텀 어노테이션을 정의하기 위한 어노테이션이다.

**@Target**

어노테이션이 적용 가능한 대상을 지정한다.

-   ElementType.TYPE : 클래스, 인터페이스, 열거 타입
-   ElementType.ANNOTATION_TYPE : 어노테이션
-   ElementType.FIELD : 필드
-   ElementType.CONSTRUCTOR : 생성자
-   ElementType.METHOD : 메소드

**@Retention**

어노테이션이 유지되는 기간을 지정한다.

-   RetentionPolicy.SOURCE: 소스 코드(.java)까지 남아있는다.
    -   컴파일 전까지만 유효하다.
-   RetentionPolicy.CLASS: 클래스 파일(.class)까지 남아있는다. (=바이트 코드)
    -   클래스 로더가 클래스를 로딩하기 전까지만 유효하다.
-   RetentionPolicy.RUNTIME: 런타임까지 남아 있는다. (사실상 사라지지 않는다.)
    -   클래스를 로딩한 이후에도 유효하다.
    -   Java 리플렉션 API를 사용하여 어노테이션 정보를 알 수 있다.

**@Documented**

해당 어노테이션을 javadoc에 포함시킨다.

**@Inherited**

어노테이션의 상속을 가능하게 한다.

**@Repeatable**

어노테이션을 반복해서 적용할 수 있게 한다.

```java
@ToDo("delete test codes.")
@ToDo("override inherited methods")
class MyClass{
	~~
}

```
<br>

## 커스텀 어노테이션 정의 방법

어노테이션 타입 선언은 @interface를 사용해야 하고, 그 옆에 어노테이션의 이름을 적으면 된다. 메타 어노테이션은 어노테이션 정의 앞쪽에 붙여준다.

```java
@interface 이름{
	타입 요소 이름(); // 어노테이션의 요소를 선언
	    ...
}

```

**타입 요소 규칙**

1.  요소의 타입은 기본형, String, enum, 어노테이션, Class만 허용된다.
2.  추상메서드의 괄호() 안에 매개변수를 선언할 수 없다.
3.  예외를 선언할 수 없다.
4.  요소를 타입 매개변수로 정의할 수 없다. (<T>)

-   잘못 사용한 예시

    ```java
    @interface AnnoTest{
     int id = 100;
     String major(int i, int j); // 매개변수 사용 불가
     String minor() throws Exception; // 예외 선언 불가
     ArrayList<T> list(); // 타입 매개변수 정의 불가
    }

    ```

---

(참고자료)

[어노테이션1](https://steady-coding.tistory.com/614)

(추가로 읽어보면 좋을 듯한 자료)

[어노테이션을 언제 어떻게 사용할 것인가에 대한 자료](https://www.nextree.co.kr/p5864/)
