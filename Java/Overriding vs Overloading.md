## 오버라이딩(Overriding) vs 오버로딩(Overloading)

**오버라이딩(Overriding)** - 상속받은 메소드를 재정의 하는 것
**오버로딩(Overloading)** - 기존에 없는 새로운 메소드를 추가하는 것 

<br>

| 구분 | 오버라이딩  |  오버로딩 |
|--|--|--|
|접근제어자  | 부모 클래스의 메소드의 접근 제어자보다 **더 넓은 범위의 접근 제어자**를 자식 클래스의 메소드에서 설정할 수 있다. |**모든 접근 제어자**를 사용할 수 있다.   |
|리턴형  |동일해야 한다.  |  달라도 된다. |
|메소드명  |동일해야 한다.  |동일해야 한다.   |
|매개변수  | 동일해야 한다. | 달라야만 한다.  |
|적용범위  |**상속관계**에서 적용된다.  |  **같은 클래스** 내에서 적용된다. |

<br>

## 오버로딩 (Overloading)

**자바의 한 클래스 내에 이미 사용하려는 이름과 같은 이름을 가진 메소드**가 있더라도 매개변수의 개수 또는 타입이 다르면, 같은 이름을 사용해서 메소드를 정의할 수 있음

 <br>

**[오버로딩의 조건]**

**메소드의 이름이 같고, 매개변수의 개수나 타입이 달라야 함** 
주의할 점은 **'리턴 값만'** 다른 것은 오버로딩을 할 수 없음

또한 **접근 제어자도 자유롭게 지정**해 줄 수 있음. 각 메소드의 접근 제어자를 public, default, protected, private으로 다르게 지정해줘도 상관없음. 단,  접근 제어자만 다르게한다고 오버로딩이 가능하지 않음

<br>

[예시]
```java
public double computeArea(Circle c) { ... }
public double computeArea(Circle c1, Circle c2) { ... }
public double computeArea(Square c) { ... }
```

<br>

**오버로딩을 사용하는 이유**

**1. 같은 기능을 하는 메소드를 하나의 이름으로 사용할 수 있다.**

우리가 흔히 콘솔창에 텍스트를 출력할 때 사용하는 println라는 메소드를 대표적인 예로 들어볼 수 있다. 실은 이 함수가 오버로딩의 결정체이다. 우리는 println의 인자 값으로 int, double, boolean, String 등의 아주 다양한 타입의 매개변수들을 집어넣어도 우리는 그 함수들이 어떻게 실행되지는 모르지만 콘솔창에 아주 잘 출력해주는 것을 볼 수 있다.

이렇게 '출력하다.'라는 같은 기능을 가진 메소드들를 println이라는 하나의 이름으로 정의가 가능한 것이다.

  
**2. 메소드의 이름을 절약할 수 있다.**

이 위에서 예로 들었던 println을 생각해보자. 이 메소드를 매개변수의 종류에 따라서 다르게 지정한다고 생각해보자. printlnInt, printlnDouble, printlnBoolean 등 수많은 메소드들의 이름을 정해줘야 할 것이다. 이는 프로그래머의 입장에서는 메소드의 네이밍에 고민을 가중시킨다. 그리고 이런 이름들은 다른 곳에 사용할 가능성도 생기게 된다.

<br>

## 오버라이딩 (Overriding)

**부모 클래스로부터 상속받은 메소드를 자식 클래스에서 재정의**하는 것을 오버라이딩이라고 함. 
상속받은 메소드를 그대로 사용할 수도 있지만, 자식 클래스에서 상황에 맞게 변경해야하는 경우 오버라이딩할 필요가 생김


 <br>

**[오버라이딩의 조건]**

오버라이딩은 부모 클래스의 메소드를 재정의하는 것이므로, 자식 클래스에서는  **오버라이딩하고자 하는 메소드의 이름, 매개변수, 리턴 값이 모두 같아야 함** 

<br>
[예시]

```java
public abstract class Shape {
  public void printMe() { System.out.println("Shape"); }
  public abstract double computeArea();
}

public class Circle extends Shape {
  private double rad = 5;
  @Override
  public void printMe() { System.out.println("Circle"); }
  public double computeArea() { return rad * rad * 3.15; }
}

public class Ambiguous extends Shape {
  private double area = 10;
  public double computeArea() { return area; }
}
```
``` java
public class Main {
  public static void main(String[] args) {
    Shape[] shapes = new Shape[2];
    Circle circle = new Circle();
    Ambiguous ambiguous = new Ambiguous();

    shapes[0] = circle;
    shapes[1] = ambiguous;

    for(Shape s : shapes) {
      s.printMe();
      System.out.println(s.computeArea());
    }
  }
}
```
출력결과 
``` java
Circle
78.75
Shape
10
```
<br>

부모 클래스의 메소드를 오버라이딩하는 것은 내용만을 새로 정의하는 것이므로 **선언부는 부모의 것과 완벽히 동일**해야함

<br>

[오버라이딩에서 접근 제어자를 설정하는 규칙]

**1. 자식 클래스에서 오버라이딩하는 메소드의 접근 제어자는 부모 클래스보다 더 좁게 설정할 수 없다.**

위에서 볼 수 있듯이 부모클래스의 접근제어자는 default로 설정되어 있다. 여기서 자식 클래스들은 default보다 같거나 더 넓은 범위의 접근제어자만 설정할 수 있으므로 default, protected, public 이 세 개의 접근 제어자는 사용이 가능하다.

**2. 예외(Exception)는 부모 클래스의 메소드 보다 많이 선언할 수 없다.**

부모 클래스에서 어떤 예외를 throws 한다고 하면, 자식 클래스에서는 그 예외보다 더 큰 범위의 예외를 throws할 수 없다는 것이다.

**3. static메소드를 인스턴스의 메소드로 또는 그 반대로 바꿀 수 없다.**

부모 클래스의 static메소드를 자식에서 같은 이름으로 정의할 수 있지만 이것은 다시 정의하는 것이 아니라 같은 이름의 static메소드를 새로 정의하는 것이다.



<br>

[레퍼런스1](https://hyoje420.tistory.com/14)

[레퍼런스2](https://gmlwjd9405.github.io/2018/08/09/java-overloading-vs-overriding.html)