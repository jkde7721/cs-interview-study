# Call by Value vs Call by Reference

> 메서드를 호출할 때 파라미터를 전달하는 방법

## Call by Value

> Call by Value는 메서드를 호출할 때 값을 넘겨주기 때문에 Pass by Value라고도 부름

- 메서드를 호출하는 호출자(Caller)의 변수와 호출 당하는 수신자(Callee)의 파라미터는 복사된 서로 다른 변수
- 값만 전달하기 때문에 수신자의 파라미터를 수정해도 호출자의 변수에는 아무런 영향이 없음

<br/>

## Call by Reference

> Call by Reference는 참조(주소)를 직접 전달하며 Pass by Reference라고도 부름

- 참조를 직접 넘기기 때문에 호출자의 변수와 수신자의 파라미터는 완전히 동일한 변수
- 메서드 내에서 파라미터를 수정하면 그대로 원본 변수에도 반영

<br/>

### Java에서의 파라미터 전달 방법

:bulb: **Java는 오직 Call by Value로만 동작**

<br/>

## JVM 메모리에 변수가 저장되는 위치

&rarr; Java의 Call by Value에 대해 이해하기 위해선 변수 생성 시 메모리에 어떤 식으로 저장되는지 알아야 함

- Java에서 변수를 선언하면 **Stack** 영역에 할당
- 원시 타입(Primitive type)은 Stack 영역에 변수와 함께 저장
- 참조 타입(Reference type) 객체는 Heap 영역에 저장되고 Stack 영역에 있는 변수가 객체의 주소값을 갖고 있음
  ![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_20_51_33.png)

<br/>

### 원시 타입 (Primitive type) 전달

> 원시 타입은 Stack 영역에 위치함

- 메서드 호출 시 넘겨받는 파라미터들도 원시 타입이라면 Stack 영역에 생성

```java
public  class PrimitiveTypeTest {
	@Test
	@DisplayName("Primitive Type 은 Stack 메모리에 저장되어서 변경해도 원본 변수에 영향이 없다")
	void test() {
		int a = 1;
		int b = 2;
		// Before
		assertEquals(a, 1);
		assertEquals(b, 2);
		modify(a, b);
		// After: modify(a, b) 호출 후에도 값이 변하지 않음
		assertEquals(a, 1);
		assertEquals(b, 2);
	}
	private void modify(int a, int b) {
		// 여기 있는 파라미터 a, b 는 이름만 같을 뿐 test() 에 있는 a, b 와 다른 변수
		a = 5;
		b = 10;
	}
}
```

&rarr; modify(a, b) 를 호출하는 순간 Stack 영역에 새로운 변수 a, b가 새로 생성되어 총 4개의 변수가 존재
![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_01_33.png)

- Stack 내부에 test()와 modify() 라는 영역이 나뉘어져 있고 거기에 동일한 이름을 가진 변수 a, b 존재 <br/>
  &rarr; modify() 영역의 값을 바꿔도 test() 영역의 변수는 변화가 없음

- **원시 타입의 전달**은 **값만 전달하는 Call by Value**로 동작

<br/>

### 참조 타입 (Reference type) 전달

> 변수 자체는 Stack 영역에 생성되지만 실제 객체는 Heap 영역에 위치

- Stack에 있는 변수가 Heap에 있는 객체를 바라보고 있는 형태

```java
class User {
	public  int age;
	public User(int age) {
		this.age = age;
	}
}
public  class ReferenceTypeTest {
	@Test
	@DisplayName("Reference Type 은 주소값을 넘겨 받아서 같은 객체를 바라본다" + "그래서 변경하면 원본 변수에도 영향이 있다")
	void test() {
		User a = new User(10);
		User b = new User(20);
		// Before
		assertEquals(a.age, 10);
		assertEquals(b.age, 20);
		modify(a, b);
		// After
		assertEquals(a.age, 11);
		assertEquals(b.age, 20);
	}
	private void modify(User a, User b) {
		// a, b 와 이름이 같고 같은 객체를 바라본다.
		// 하지만 test 에 있는 변수와 확실히 다른 변수다.
		// modify 의 a 와 test 의 a 는 같은 객체를 바라봐서 영향이 있음
		a.age++;
		// b 에 새로운 객체를 할당하면 가리키는 객체가 달라지고 원본에는 영향 없음
		b = new User(30);
		b.age++;
	}
}
```

&rarr; Reference 자체를 전달하는 것이 아니라 주소값만 전달하고 modify() 에서 생긴 변수들이 주소값을 보고 객체를 같이 참조하고 있는 것

<br/>

#### 처음 변수 선언 시 메모리 상태

:bulb: 원시 타입과는 다르게 변수만 Stack 영역에 생성되고 실제 객체는 Heap 영역에 생성 <br/>
:bulb: 각 변수는 Heap 영역에 있는 객체를 가리킴
![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_44_00.png)

<br/>

#### modify(a, b) 호출 시점의 메모리 상태

:bulb: 넘겨받은 파라미터는 Stack 영역에 생성되고 넘겨받은 주소값을 똑같이 가리킴
![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_50_06.png)

<br/>

#### modify(a, b) 수행 직후 메모리 상태

:bulb: test() 영역과 modify() 영역에 존재하는 a라는 변수들은 같은 객체인 User01을 가리키고 있기 때문에 객체를 공유<br/>
:bulb: b라는 변수는 서로 같은 객체인 User02를 가리키지만, modify(a, b) 내부에서 새로운 객체를 생성해서 할당했기 때문에 User03이라는 객체를 가리킴

&rarr; User03의 age 값을 변경해도 test()에 있는 b에는 아무런 변화 없음
![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_12_16.png)

<br/>

#### test() 끝난 후 최종 메모리 상태

:bulb: modify(a, b) 메서드를 빠져나오면 Stack 영역에 할당된 변수들은 사라짐
![enter image description here](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_15_36.png)

&rarr; 최종적으로 위와 같은 상태가 되며 User03은 어떤 곳에서도 참조하지 않고 있기에 나중에 Garbage Collector에 의해 제거될 것

<br/>

### 참고

[[Java] Java 의 Call by Value, Call by Reference](https://bcp0109.tistory.com/360)
