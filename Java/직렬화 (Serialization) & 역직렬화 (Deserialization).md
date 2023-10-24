# 직렬화 (Serialization) & 역직렬화 (Deserialization)

![enter image description here](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2016/01/serialize-deserialize-java.png)

- Java나 Kotlin, Swift 같은 객체 지향 언어에서는 모든 데이터들을 객체로 표현한다.
- 기본적으로 Java의 I/O 처리는 정수, 문자열, 바이트 단위의 처리만 지원하기 때문에 Java에서 만든 객체의 내용을 저장하거나 복원하기 위해선 I/O가 처리할 수 있는 형태로 객체를 변환해야 한다.

&rarr; 이를 바탕으로 직렬화 (Serialization)와 역직렬화 (Deserialization)가 필요해짐

<br/>

## 직렬화 (Serialization)

> 객체 직렬화는 Java의 객체를 외부로 저장/복원하거나 네트워크 상으로 전송할 수 있도록 바이트 형태로 변환하는 기술을 의미

&rarr; 객체가 아무리 복잡하여도 직렬화를 통해 객체를 바이트 형태로 변환하여 외부로 전송할 수 있는 것

<br/>

### 직렬화의 특징

- 프로그램이 종료되어도 객체의 데이터는 파일로 변환하여 저장되어 있기 때문에, 언제든지 불러서 다시 객체로 변환할 수 있으며 외부로 보내서 데이터를 공유할 수 있음
- Java의 기본 라이브러리를 사용하지 않더라도 여러 형태 (CSV, JSON, 일반 파일 등)로 수행이 가능
- Java에서 제공하는 직렬화 기능은 오직 Java 프로그램끼리만 공유가 가능한 데이터이며, 코드 수정을 하거나 자바 버전이 달라 클래스 속성이 바뀌게 되면 사용할 수 없음
- Java의 JVM에서 자동으로 처리해주기 때문에 수신부와 송신부의 운영체제가 달라도 아무런 상관이 없음

<br/>

## 역직렬화 (Deserialization)

> 직렬화를 통해 변환된 바이트 형태를 다시 원상태인 객체로 변환시키는 기술을 의미

&rarr; 객체가 아무리 복잡하여도 직렬화를 통해 객체를 바이트 형태로 변환하여 외부로 전송할 수 있는 것

<br/>

## Java에서 직렬화 및 역직렬하는 방법

### 직렬화

1. 기본적으로 클래스의 객체를 직렬화시키기 위해선 Serializable 인터페이스를 implements 해줘야 한다.

```java
class Person implements Serializable {
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
```

&rarr; **Serializable 인터페이스는 현재 클래스의 객체가 직렬화가 제공되어야 함을 JVM에게 알려주는 역할만 수행**

<br/>

2. Person 클래스의 객체를 직렬화를 통해 파일로 변환하여 저장

**:heavy_check_mark: 객체 직렬화는 직렬화하고자 하는 객체에 직렬화를 수행해주는 ObjectOutputStream과 직렬화한 내용을 저장할 .txt 파일을 생성해주는 FileOutputStream을 통해 수행**

```java
public  class Main {
	public static void main(String[] args) throws IOException {
		Person person = new Person("Libi", 26);
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Serialization.txt"))){
			out.writeObject(person);
		} catch (Exception e) { }
	}
}
```

&rarr; Serialization.txt 파일을 열어서 person 객체의 정보들을 직렬화시켜서 변환된 바이트 형태의 내용을 저장해준다.

<br/>

### 역직렬화

**:heavy_check_mark: 객체 역직렬화는 불러온 파일에 역직렬화를 수행해주는 ObjectInputStream과 .txt 파일을 불러오는 FileInputStream을 통해 수행**

```java
public  class Main {
	public static void main(String[] args) throws IOException {
		Person person = null;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Serialization.txt"))){
			person = (Person) in.readObject();
		} catch (Exception e) { }
		System.out.println(person.toString());
	}
}
```

<br/>

:bulb: 특정 정보를 저장하고 싶지 않다면 저장하고 싶지 않은 멤버에 Transient 나 Static 키워드를 붙이면 제외할 수 있다.

```java
class Person implements Serializable {
	static String name;
	transient  int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return  "Person [name=" + name + ", age=" + age + "]";
	}
}
```

<br/>

## 역직렬화 수행 시 주의해야 할 점

:heavy_check_mark: 역직렬화를 수행하기 위해선 **직렬화한 클래스의 속성과 역직렬화를 통해 생성할 클래스의 속성**이 일치해야 함<br/>
:point_right: Java는 이를 **serialVersionUID** 필드를 통해 판단<br/>
:point_right: 이 필드는 **Serializble** 인터페이스에 구현된 것으로, 직렬화를 선언한 클래스를 컴파일할 때 컴파일러가 클래스 구조 정보를 해시값으로 변환한 값을 자동으로 넣어줌

```java
private  static  final  long serialVersionUID = 1L;
```

<br/>
:bookmark_tabs: serialVersionUID 는 기본적으로 클래스의 해시값을 가짐<br/>

- 클래스에 수동으로 ID를 부여함으로써 내용에 관계없이 동일 클래스로 인식함
- 직렬화를 수행할 클래스의 필드값이나 함수는 추가하거나 삭제할 수 있음. 그러나 기존 필드의 이름을 변경시켜서는 안됨

<br/>

## 직렬화 단점

- 간단한 객체의 내용도 직렬화를 수행하면 데이터뿐만 아니라 다른 구조 데이터 등의 정보도 포함되기 때문에 **데이터의 크기가 많이 커짐**
- **타입에 대해 상당히 엄격함**
  :arrow_right: String &rarr; StringBuilder, int &rarr; long 등 잘못된 타입으로 받아들이면 Exception이 발생

:exclamation: 직렬화는 내용 변경이 없을 만한 클래스에 사용하는 것이 좋으며, 사용하더라도 장기 보관용으로는 적합하지 않다.
<br/>
:exclamation: 데이터 크기가 많이 차이나기 때문에 긴 만료 시간을 가지는 데이터는 JSON 등 다른 포맷을 사용하여 저장하는 것이 낫다.

<br/>

### 참고

[[Java] 직렬화 (Serialization) & 역직렬화 (Deserialization)](https://sorjfkrh5078.tistory.com/89)

[[Java] 직렬화 (Serialization)와 역직렬화 (Deserialization)](https://forstudy.tistory.com/11)
