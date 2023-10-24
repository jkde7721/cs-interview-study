# 가변객체 vs 불변객체

## 가변 객체

-   Java에서 Class의 인스턴스가 생성된 이후에 내부 상태가 변경 가능한 객체이다.
-   대표적인 가변 객체로 ArrayList, HashMap, StringBuilder, StringBuffer 등이 존재한다. 이외에도 프로그래머가 커스텀 객체를 생성하여 내부 상태를 변경할 수 있게 만든다면, 그것도 가변 객체가 된다.
-   가변 객체는 멀티 스레드 환경에서 사용하려면 별도의 동기화 처리가 필요하다.

## 불변 객체

-   가변 객체와 반대로 Java에서 Class의 인스턴스가 생성된 이후에 내부 상태를 변경할 수 없는 객체이다.
-   대표적인 불변 객체로 String 등이 존재한다. 이외에도 프로그래머가 커스텀 객체를 생성하여 내부 상태가 변경되지 않게 만들면, 그것도 불변 객체가 된다.

### 불변 객체의 장점

-   멀티 스레드 환경에서도 안전하게 사용할 수 있다.

    공유 자원 불변 객체라면, 항상 동일한 값만 반환하므로 동기화를 고려할 필요가 없다. 이는 안정성을 보장할 뿐만 아니라 동기화를 하지 않음으로써 성능 상의 이점도 가져다 준다.

-   내부상태가 변경되지 않으므로 Cache, Map, Set 등의 요소로 활용하기에 적합하다.

    만약 캐시나 Map 또는 Set 등으로 사용되는 객체가 변경되었다면 이를 갱신하는 등의 작업이 필요하다. 하지만 객체가 불변이라면 한 번 데이터가 저장된 이후에 다른 부가 작업을 고려하지 않아도 될 것이고, 이는 캐시나 다른 자료 구조를 사용하는데 용이하게 작용한다.

-   실패 원자적인(Failure Atomic) 메소드를 만들 수 있다.

    -   가변 객체를 통해 어떠한 작업을 하는 도중 예외가 발생하면 해당 객체가 불안정한 상태에 빠질 수 있다. 그리고 불안정한 상태를 갖는 객체는 변경된 상태로 인해 새로운 에러를 유발할 수 있다. 하지만 불변 객체라면 어떠한 예외가 발생하여도 메소드 호출 전의 상태를 유지할 수 있으므로 예외가 발생하여도 변경된 상태로 인한 추가 에러를 막을 수 있다.
-   불변 객체를 한번 메모리에 할당하게 되면 같은 객체를 계속 호출하여도, 새롭게 할당하지 않아도 되므로 GC 의 성능을 높일 수 있다.


### 불변 객체를 만드는 방법

불변 객체는 read-only 메소드만 제공하며, 객체의 내부 상태를 알려주는 메소드를 제공하지 않거나 제공할 경우 방어적 복사 혹은 Unmodified 라이브러리를 통해 제공한다.

-   setter 메소드를 사용하지 않는다.
-   모든 필드에 대해 final을 설정한다.
-   필드에 참조 타입이 있을 경우, 해당 객체도 불변성을 보장해야 한다.
-   필드에 컬렉션이 존재할 경우, 생성자 및 getter에 대해 **방어적 복사**를 수행해야 한다.

**방어적 복사란?**

생성자의 인자로 받은 객체의 복사본을 만들어 내부 필드를 초기화하거나,

`getter`메서드에서 내부의 객체를 반환할 때, 객체의 복사본을 만들어 반환하는 것이다.

방어적 복사를 사용할 경우, 외부에서 객체를 변경해도 내부의 객체는 변경되지 않는다.

----------

위 불변 객체를 만드는 방법에 대해 아래 상황 별로 살펴보겠다.

-   필드가 모두 원시타입인 경우
-   필드에 참조타입이 있는 경우
-   필드에 불변 참조타입 컬렌션이 있는 경우
-   필드에 가변 참조타입 컬렉션이 있는 경우

> **필드가 모두 원시타입인 경우**

```java
public class Car {

    private final String name;

    public Car(String name) {
        this.name = name;
    }
}

```

원시 타입은 참조 값이 존재하지 않기 때문에 값을 그대로 외부로 내보내는 경우에도 내부 객체는 불변이므로 setter가 없고, 원시 타입 필드에 대해 final을 설정하였다면 해당 객체인 Car는 불변 객체가 된다.
<br>

> **필드에 참조타입이 있는 경우**

```java
public class Car {

    private final String name;

    private final Position position;

    public Car(String name, Position position) {
        this.name = name;
        this.position = position;
    }
}

```

```java
public class Position {

    private int value;

    public Position(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

```

Car 클래스의 필드에 Position이라는 참조 타입이 있다. final로 선언하여 불변 객체처럼 보이지만 만약 Car의 `getPosition()` 메소드를 통해 Position을 가져 오고 Position 객체의 `setValue()` 메소드를 사용하면 Position의 상태를 바꿀 수 있다. Position의 상태를 바꿀 수 있다는 말은 즉, Car 객체의 상태를 바꿀 수 있다는 의미가 된다. 따라서 필드에 참조 타입이 있는 경우, 해당 참조 타입 객체도 불변 객체로 만들어야 한다.
<br>

> **필드에 불변 참조타입 컬렉션이 있는 경우**

```java
public class Car {

    private final String name;

    private final List<Integer> monthlyMileages;

    public Car(String name, List<Integer> monthlyMileages) {
        this.name = name;
        this.monthlyMileages = monthlyMileages;
    }
}

```

List<Integer> monthlyMileages라는 불변 참조 타입 컬렉션을 추가하였다. final로 선언해 놨지만 2가지 경우에 문제점이 있다.

```java
public class Main {

    public static void main(String[] args) {
        List<Integer> monthlyMileages = new ArrayList<>(Arrays.asList(10, 15));
        Car car = new Car("jayon", monthlyMileages);

        monthlyMileages.add(1000);
    }
}

```

만약 monthlyMileages를 사용하여 Car객체를 만들고, monthlyMileages를 조작한다고 하자. 생성자로 넘겨줄 때, 주소 값을 공유하기 때문에 Car 객체의 내부가 변경될 것이다. 따라서 생성자로 monthlyMileages를 그냥 넘겨 주면 안 되고 방어적 복사를 거치고 넘겨야 한다.

아래와 같이 생성자의 인자로 받은 객체의 복사본을 만들어 내부 필드를 초기화해야 한다.

```java
public class Car {

    private final String name;

    private final List<Integer> monthlyMileages;

    public Car(String name, List<Integer> monthlyMileages) {
        this.name = name;
        this.monthlyMileages = new ArrayList<>(monthlyMileages);
    }
}

```

```java
public class Car {

    private final String name;

    private final List<Integer> monthlyMileages;

    public Car(String name, List<Integer> monthlyMileages) {
        this.name = name;
        this.monthlyMileages = new ArrayList<>(monthlyMileages);
    }

		public List<Integer> getMonthlyMileages() {
        return monthlyMileages;
    }
}

```

이번에는 getter를 만들어 monthlyMileages값을 넘겨준다고 하자. 이 경우도 주소값을 넘겨주기 때문에 외부에서 monthlyMileages를 변경할 경우 Car객체도 변경된다. 따라서 넘겨줄 때도 방어적 복사를 거치고 넘겨야 한다.

```java
public class Car {

    private final String name;

    private final List<Integer> monthlyMileages;

    public Car(String name, List<Integer> monthlyMileages) {
        this.name = name;
        this.monthlyMileages = new ArrayList<>(monthlyMileages);
    }

		public List<Integer> getMonthlyMileages() {
        return new ArrayList<>(monthlyMileages);
}


```

<br>

> **필드에 가변 참조타입 컬렉션이 있는 경우**

```java
public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}

```

```java
public class Car {

    private final String name;

    public int position;

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }
}

```

위는 생성자와 getter에서 방어적 복사를 수행하였다. 하지만 1가지 문제점이 있다.

```java
public class Main {

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>(Arrays.asList(new Car("car1", 1), new Car("car2", 2)));
        Cars cars = new Cars(carList);

        //초기 주입한 carList 내의 요소의 상태 변경
        carList.get(0).position = 10000;
        System.out.println(cars);
    }
}

```

방어적 복사를 통해 main의 carList와 Cars객체의 carList는 서로 다른 배열을 가리키지만, 그 배열 내부에 들어있는 주소 값은 똑같을 것이다. 따라서 main에서 carList 내의 요소의 상태 변경한다면 Cars객체도 변경된다.

따라서 참조 타입 컬렉션을 필드로 사용할 때는 방어적 복사를 맹신하지 말고, 참조 타입 자체를 불변 객체로 보장해 주어야 한다.

----------

참고자료

[https://steady-coding.tistory.com/559](https://steady-coding.tistory.com/559)
