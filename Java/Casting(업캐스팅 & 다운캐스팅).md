## Casting(업캐스팅 & 다운캐스팅)

### Casting(형변환) 이란?

> 하나의 데이터 타입을 다른 타입으로 바꾸는 것

자바의 데이터형은 크게 2가지로 나뉨

- **기본형**(primitive type): Boolean Type(`boolean`), Numeric Type(`short`, `int`, `long`, `float`, `double`, `char`)
- **참조형**(reference type): Class Type, Interface Type, Array Type, Enum Type, 그 외 다른 것들

❗기본적으로 자바에선 대입 연산자 `=` 에서 양쪽 간 타입이 일치하지 않으면 할당 불가능

❗자료형이 정해진 변수에 값을 넣을 때는 변수가 원하는 정보를 하나도 빠짐 없이 다 넣어줘야 성립

<br/>

`int a = 1.0;` 컴파일 에러 발생

- 1.0 이라는 실제 데이터는 int 정보를 이미 가지고 있는, 소수점을 표현하는 double 형 데이터
- 변수 a는 원하는 정보인 int 정보가 실제 데이터에 있음에도 불구하고 왜 컴파일 에러 발생?

  실제 데이터 1.0이라는 것이 a에 들어가면 1로 바뀌어 실제 데이터가 손실되는 것을 막고자 에러 발생

`int a = (int) 1.0;` 기본형 형변환

- `(Type)` 캐스팅 연산자를 통해 강제적으로 타입을 지정하여 변수에 대입하도록 설정 가능
- 즉 코드를 작성하는 개발자가 데이터가 손실된다는 것을 이미 인지하고 명시적으로 지정하여 캐스팅 가능
- a에는 1.0 대신 캐스팅된 1이 대입

`double f = 1;` 기본형 형변환

- 변수가 원하는 정보는 double 자료형이지만, 실제 데이터는 1이라는 int 자료형을 대입
- 원칙적으로는 변수가 원하는 정보를 충족하지 않으면 컴파일 에러가 발생해야 하나 기본형 간의 캐스팅이기 때문에 에러 발생X
- 기본 자료형은 이미 컴파일러가 알고 있는 자료형으로 double형 데이터의 형태를 추측으로 알아냄, 즉 컴파일러가 자동적으로 바꿔주는 것이지 원칙적으로 불가능
- 반면 참조형 간의 형변환에서는 컴파일러는 참조형 데이터를 추리하지 못함

<br/>
<hr/>
<br/>

> 기본적으로 캐스팅은 서로 관련없는 데이터 간에는 변환 불가능 ex. `int` - `boolean` 간
> <br/>참조형 형변환에서도 마찬가지로, 즉 참조형 데이터 간 관련이 있어야 형변환이 가능
> <br/>관련이 있다는 것은? 상속 관계, 인터페이스 구현 관계

✅ 자바의 상속 관계에 있는 부모, 자식 클래스 간에는 서로 형변환이 가능하며, 클래스는 참조 타입으로 분류되기 때문에 이를 **참조형 캐스팅**(업캐스팅 / 다운캐스팅)이라고 지칭

- 자식 클래스의 객체는 부모 클래스를 상속하고 있기 때문에 부모의 멤버를 모두 가지고 있으나, 부모 클래스의 객체는 자식 클래스의 멤버를 모두 가지고 있지 않음
- 따라서 참조 변수의 형변환은 사용할 수 있는 멤버의 갯수를 조절하는 것이라 할 수 있음
- 다만 같은 부모 클래스를 상속받고 있더라도 형제 클래스 간에는 타입이 서로 다르기 때문에 참조 형변환 불가능

```java
class Parent {
    String name;
    int age;
}

class Child extends Parent {
    /*
    String name;
    int age;
    */
    int number;
}

Parent p = new Parent();
Child c = new Child();

Parent p2 = (Parent) c; //업캐스팅 - 자식에서 부모로
Child c2 = (Child) p2; //다운캐스팅 - 부모에서 자식으로
```

<br/>
<br/>

### 업캐스팅(UpCasting)

> 자식 클래스가 부모 클래스 타입으로 캐스팅되는 것

- 캐스팅 연산자 괄호를 생략 가능
- 부모 클래스로 캐스팅된다는 것은 멤버 개수 감소를 의미, 즉 자식 클래스에만 정의된 속성과 메서드는 실행 불가
- 업캐스팅 후 메소드를 실행할 때, 만약 자식 클래스에서 오버라이딩한 메소드이면 부모 클래스의 메소드가 아닌 오버라이딩된 메소드가 실행

```java
class Unit {
    public void attack() {
        System.out.println("유닛 공격");
    }
}

class Zealot extends Unit {
    public void attack() {
        System.out.println("찌르기");
    }

    public void teleportation() {
        System.out.println("프로토스 워프");
    }
}

public class Main {
    public static void main(String[] args) {
        Unit unit_up;
        Zealot zealot = new Zealot();

        //업캐스팅(upcasting)
        unit_up = (Unit) zealot;
        unit_up = zealot; //업캐스팅은 형변환 괄호 생략 가능
    }
}
```

<br/>

❗**업캐스팅 멤버 제한**

- 멤버 개수 감소로 인한 멤버 접근 제한 존재
- 실행할 수 있는 속성과 메소드가 제한됨
- 위 `unit_up` 객체를 통해서는 `Unit` 클래스에 정의된 멤버에만 접근이 가능
- ex. `Zealot` 클래스에만 정의된 `teleportation()` 메소드 실행 시, 컴파일 오류 발생

→ 객체를 업캐스팅을 하게 되면 자식과 부모의 공통된 것만 사용할 수 있고 자식 클래스에서 새로 정의된 것은 사용 불가

<br/>

✅ **업캐스팅 오버라이딩 메소드**

- 업캐스팅된 객체의 메소드 호출 시 해당 메소드가 자식 클래스에서 재정의되었다면, 자식 클래스에 정의된 메소드가 호출

→ 업캐스팅 했지만 오버라이딩된 메서드는 자식 클래스의 메서드로 실행

<br/>

📌 **업캐스팅을 하는 이유**

공통적으로 할 수 있는 부분을 만들어 간단하게 다루기 위해, 즉 상속 관계에서 상속 받은 서브 클래스가 몇 개이든 하나의 클래스로 묶어서 관리할 수 있기 때문

**여러 서브 클래스를 하나의 클래스로 묶어서 관리하는 예제**

```java
//Shape은 부모 클래스, Rectangle, Triangle, Circle는 자식 클래스
//하나의 타입으로 묶어 배열 구성 가능
Shape[] s = new Shape[3];
s[0] = new Rectangle();
s[1] = new Triangle();
s[2] = new Circle();
```

<br/>

**자식 클래스에만 있는 고유한 메소드를 실행하려면?**
기본적으로 오버라이딩한 메소드가 아닌 이상 업케스팅한 부모 클래스 타입에서 자식 클래스의 고유 메소드는 실행할 수 없음. 따라서 업캐스팅한 객체를 다시 자식 클래스 타입으로 되돌리는 **다운 캐스팅**(down casting)이 필요

<br/>
<br/>

### 다운 캐스팅(Down Casting)

> 부모 클래스가 자식 클래스 타입으로 캐스팅되는 것

- 캐스팅 연산자 괄호 생략 불가능
- 다운 캐스팅의 목적은 **업캐스팅한 객체를 다시 자식 클래스 타입의 객체로 되돌리는 것** (복구), 즉 부모 클래스를 자식 클래스로 캐스팅하는 단순히 업캐스팅의 반대 개념X
- 부모 클래스로 업캐스팅된 자식 클래스를 복구하여 자식 클래스의 필드와 메소드를 회복하기 위해

```java
class Unit {
    public void attack() {
        System.out.println("유닛 공격");
    }
}

class Zealot extends Unit {
    public void attack() {
        System.out.println("찌르기");
    }

    public void teleportation() {
        System.out.println("프로토스 워프");
    }
}

public class Main {
    public static void main(String[] args) {
        Unit unit_up = new Zealot(); //업캐스팅

        //다운 캐스팅(down casting) - 자식 전용 멤버를 이용하기 위해, 이미 업캐스팅한 객체를 되돌릴 때 사용
        Zealot unit_down = (Zealot) unit_up; //캐스팅 연산자는 생략 불가능
        unit_down.attack(); //"찌르기" 출력
        unit_down.teleportation(); //"프로토스 워프" 출력

        //=> 업캐스팅된 객체 unit_up에서 만약 자식 클래스에만 있는 teleportation() 메소드를 실행해야 한다면, 다운 캐스팅을 통해 자식 클래스 타입으로 복구시킨 뒤 메소드 실행
    }
}
```

<br/>

❗**다운 캐스팅 주의점**

```java
Unit unit = new Unit();

//다운 캐스팅(down casting) 예외 - 다운 캐스팅은 업캐스팅한 객체를 되돌릴 때 적용 되는 것이지, 실제 부모 객체를 자식 객체로 강제 형변환은 불가능
Zealot unit_down = (Zealot) unit; //RUNTIME ERROR - Unit cannot be cast to Zealot
```

- 업캐스팅되지 않은 실제 부모 객체를 다운 캐스팅하면 `ClassCaseException` 발생, 즉 컴파일 에러가 아닌 런타임 에러가 발생하기 때문에 더더욱 주의!
- 애초에 부모 타입 → 자식 타입으로의 형변환은 객체지향의 참조 다형성에서 불가능하기 때문에 예외 발생
- 다운 캐스팅할 객체가 실제 부모 객체인지, 업캐스팅된 부모 객체인지 확인해주어야 함 → 자바의 `instanceof` 연산자를 사용하여 판별

<br/>
<br/>

### instanceof 연산자

> 해당 객체 변수가 어느 클래스 타입인지 판별해 true / false 반환

- 클래스(참조형) 타입에만 사용 가능
- `int`, `double` 같은 primitive 타입에는 사용 불가능

```java
public class Main {
    public static void main(String[] args) {
        //업캐스팅 유무
        Zealot zealot = new Zealot();
        if (zealot instanceof Unit) {
            System.out.println("업캐스팅 가능"); //실행
            Unit u = zealot; //업캐스팅
        } else {
            System.out.println("업캐스팅 불가능");
        }

        //다운캐스팅 유무
        Unit unit = new Unit();
        if (unit instanceof Zealot) {
            System.out.println("다운캐스팅 가능");
        } else {
            System.out.println("다운캐스팅 불가능"); //실행
        }

        Unit unit2 = new Zealot(); //unit2는 업캐스팅된 객체
        if (unit2 instanceof Zealot) {
            System.out.println("다운캐스팅 가능"); //실행
            Zealot z = (Zealot) unit2; // 다운캐스팅
        } else {
            System.out.println("다운캐스팅 불가능");
        }
    }
}
```

<br/>

### 참고

[참고 1](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%85%EC%BA%90%EC%8A%A4%ED%8C%85-%EB%8B%A4%EC%9A%B4%EC%BA%90%EC%8A%A4%ED%8C%85-%ED%95%9C%EB%B0%A9-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)

[참고 2](https://mommoo.tistory.com/40)
