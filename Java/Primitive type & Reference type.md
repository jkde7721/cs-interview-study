# Primitive type & Reference type

> Java에는 기본형 (Primitive type)과 참조형 (Referece type) 이 존재

```java
Java Data Type
	ㄴ Primitive Type
		ㄴ Boolean Type (boolean)
		ㄴ Numeric Type
			ㄴ Integral Type
				ㄴ Integer Type (short, int, long)
				ㄴ Floating Point Type (float, double)
			ㄴ Character Type (char)
	ㄴ Reference Type
		ㄴ Class Type
		ㄴ Interface Type
		ㄴ Array Type
		ㄴ Enum Type
		ㄴ etc.
```

<br/>

## Primitive type

> 기본형 타입

:bulb: **Java에서는 총 8가지의 Primitive type을 미리 정의하고 제공**

- Java에서는 기본 자료형은 반드시 사용하기 전에 선언되어야 함
- OS에 따라 자료형의 길이가 변하지 않음
- **스택(Stack)** 메모리에 저장됨
- **비객체** 타입으로, NULL 값을 가질 수 없음

&rarr; 만약 Primitive type에 NULL 값을 넣고 싶다면 Wrapper Class를 활용해야 함

![enter image description here](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-type-001.png?raw=true)

#### boolean

- 논리형인 boolean의 기본값은 false이며 참과 거짓을 저장하는 타입
- 두 가지 값만 표현하므로 가장 크기가 작음
- boolean은 실제로 1bit면 충분하지만, 데이터를 다루는 최소 단위가 1byte이므로 메모리 크기 1byte

#### byte

- byte는 주로 이진데이터를 다루는데 사용되는 타입

#### short

- short는 C언어와의 호환을 위해 사용되는 타입으로 잘 사용되지 않음

#### int

- int형은 자바에서 정수 연산을 하기 위한 기본 타입
- byte 혹은 short의 변수가 연산을 하면 연산의 결과는 int형이 됨

#### long

- 수치가 큰 데이터를 다루는 프로그램에서 주로 사용
- long 타입의 변수를 초기화할 때는 정수값 뒤에 알파벳 L을 붙여서 long 타입(8byte)의 정수 데이터임을 알려줘야 함

#### float, double

- 실수를 가수와 지수 형식으로 저장하는 부동소수점 방식으로 저장
- 가수를 표현하는데 있어 double형이 float 형보다 표현 가능 범위가 더 크므로 보다 정밀하게 표현 가능
- 자바에서 실수의 기본 타입은 double형이므로 float형에는 F를 붙여서 float형임을 명시해줘야 함

<br/>

### Wrapper Class

> 기본 자료 타입 (Primitive type)을 객체로 다루기 위해서 사용하는 클래스

| 기본타입(primitive type) | 래퍼클래스(wrapper class) |
| ------------------------ | ------------------------- |
| byte                     | Byte                      |
| char                     | Character                 |
| int                      | Integer                   |
| float                    | Float                     |
| double                   | Double                    |
| boolean                  | Boolean                   |
| long                     | Long                      |
| short                    | Short                     |

:question: 이렇게 기본 타입을 래퍼클래스 객체로 감싸는 이유?

&rarr; 컬렉션에서 제네릭을 사용하기 위해서는 Wrapper Class를 사용해야 함<br/>
&rarr; NULL 값을 반환해야만 하는 경우에는 return type을 Wrapper Class로 지정하여 NULL을 반환할 수 있음

:point_right: 값을 비교할 때, Primitive type인 경우에는 ==로 바로 비교할 수 있지만 Wrapper Class인 경우에는 .intValue() 메소를 통해 해당 Warpper Class의 값을 가져와 비교해줘야 함

<br/>

## Reference type

> 참조형 타입

:bulb: **Java에서 Primitive type을 제외한 타입들이 모두 Reference type**

:bulb: **Reference type은 Java에서 최상인 java.lang.object 클래스를 상속하는 모든 클래스**

&rarr; new로 인하여 생성하는 것들은 메모리 영역인 Heap 영역에 생성을 하게 되고, Garbage Collector가 돌면서 메모리를 해제

&rarr; 클래스 타입 (class type), 인터페이스 타입 (interface type), 배열 타입 (array type), 열거 타입 (enum type)이 있음

- 빈 객체를 의미하는 NULL이 존재
- 문법상으로는 에러가 없지만 실행시켰을 때 에러가 나는 런타임 에러가 발생
  ex) 객체나 배열을 NULL 값으로 받으면 NullPointException(NPE)이 발생하므로 변수값을 넣어야 함
- **Heap** 메모리에 생성된 인스턴스는 메소드나 각종 인터페이스에서 접근하기 위해 JVM의 **Stack** 영역에 존재하는 Frame에 일종의 포인터인 참조값을 가지고 있어 이를 통해 인스턴스를 핸들링함

![enter image description here](https://github.com/GimunLee/tech-refrigerator/blob/master/Language/JAVA/resources/java-type-002.png?raw=true)

<br/>

### String Class

> 참조형에 속하지만, 기본적인 사용은 기본형처럼 사용

> 불변(immutable)하는 객체

- String 클래스에는 값을 변경해주는 메소드들이 존재하지만 해당 메소드를 통해 데이터를 바꾼다 해도 새로운 String Class 객체를 만들어내는 것
- 일반적으로 기본형 비교는 == 연산자를 사용하지만 String 객체간의 비교는 **.equals()**메소드를 사용해야 함

<br/>

#### String Constant Pool

:bulb: **동일하다(==)** 는 두 개의 실제 인스턴스가 완전히 같을 경우(메모리 주소값이 같음)이고 **동등하다(equals)** 는 두 개의 값이 같다라는 의미

```java
String s1 = new String("aaa");
String s2 = new String("aaa");
System.out.println(s1 == s2);       // false
System.out.println(s1.equals(s2));  // true
```

:heavy_exclamation_mark: Java에서는 String에게 new 연산자가 아니라 primitive 타입 변수를 선언하듯이 하는 것을 문법적으로 허락하고 있음

```java
String s1 = "aaa";
String s2 = "aaa";
System.out.println(s1 == s2);       // true
System.out.println(s1.equals(s2));  // true
```

&rarr; 이처럼 문자열 상수에 대해서 문자열이 동일한 경우 하나의 인스턴스만 생성하고 이를 공유하도록 함

&rarr; 이때 문자열이 저장되는 곳이 바로 String Constant Pool
&rarr; 이렇게 생성된 String 값은 Heap 영역 내에 있는 String Constant Pool에 저장되어서 재사용

:heavy_check_mark: String 객체를 new 연산자로 생성하면 같은 값이라 할지라도 Heap 영역에 매번 새로운 객체가 생성됨. 따라서 String이 갖는 불변성이라는 장점을 누리지 못함

:heavy_check_mark: 따라서 메모리를 효율적으로 사용하기 위해서는 **String literal (큰 따옴표)** 로 생성하는 것이 좋음

<br/>

### 참고

[[Java] Java의 자료형(Primitive type & Reference type)](https://coder-in-war.tistory.com/entry/Java-13-Java%EC%9D%98-%EC%9E%90%EB%A3%8C%ED%98%95-Primitive-type-Reference-type)

[[Java] 래퍼 클래스 (Wrapper Class)란 무엇인가?](https://coding-factory.tistory.com/547)
