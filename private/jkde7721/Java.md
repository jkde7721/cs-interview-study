## 자바 면접 질문 리스트

<details>
    <summary>C/C++과 자바의 실행 방식에 있어서 가장 큰 차이점에 대해 설명해주세요.</summary>
    <br/><code>플랫폼 의존적/독립적</code>, <code>바이트 코드</code>, <code>JVM</code><br/><br/>

    C/C++은 소스 파일을 컴파일한 결과물이 운영체제별로 달라지기 때문에, 예를 들어 윈도우를 타겟으로 컴파일된 실행 파일은 리눅스에서 실행될 수 없는 반면,
    자바는 소스 파일을 컴파일하면 중간 단계인 바이트 코드가 생성되고 이 바이트 코드는 어떤 운영체제에서든 실행이 가능합니다. 그 이유는 JVM 즉 자바 가상 머신이 바이트 코드를 현재 운영체제에서 실행될 수 있는 기계어로 바꿔주는 역할을 하기 때문입니다. 즉 자바는 다른 언어들에 비해 플랫폼에 독립적이고 이식성이 높을 특징을 가집니다.

</details>

<details>
    <summary>자바 소스 파일이 컴파일되고 실행되는 과정을 간략히 설명해주세요.</summary>
    <br/><code>컴파일</code>, <code>클래스 로더</code>, <code>실행 엔진</code>, <code>런타임 데이터 영역</code><br/><br/>

    먼저 자바 컴파일러에 의해 자바 소스 파일이 컴파일되어 바이트 코드가 생성됩니다. 이 생성된 바이트 코드는 JVM에서 실행될 수 있는데요.
    이 바이트 코드는 클래스 로더의 동적 로딩을 통해 JVM의 메모리에 적재하는 과정을 거칩니다. 이후 적재된 바이트 코드를 JVM의 실행 엔진이 명령어 단위로 하나씩 가져와서 실행하게 됩니다.

</details>

<details>
    <summary>자바 8의 특징에 대해 3가지 이상 설명해주세요.</summary>
    <br/><code>Lambda</code>, <code>Stream</code>, <code>Interface Default Method</code>, <code>Optional</code>, <code>New Date and Time API</code><br/><br/>

    람다식은 간단히 말해 함수를 하나의 식으로 표현한 것이라고 할 수있는데요. 함수를 람다식으로 표현하면 메소드의 이름이 필요 없어 일종의 익명 함수라고 할 수 있습니다.
    따라서 자바 8 이전의 익명 클래스를 람다를 이용하여 더욱 간결하게 작성할 수 있게 되었습니다. 이를 통해 불필요한 코드를 줄이고 가독성을 높여주며 함수형 인터페이스의 인스턴스를 생성하여 함수를 변수처럼 선언 가능하기 때문에 예를 들어 메소드 파라미터로 함수를 전달하는 것도 가능해졌습니다.
    *함수형 인터페이스: 추상 메소드 1개만을 가진 인터페이스

    Stream API 특징
    - 원본 데이터를 변경하지 않음
    - Stream은 일회용
    - 내부 반복으로 작업을 처리
    → 스트림 API를 통해 컬렉션을 처리하면서 발생하는 모호함과 반복적인 코드 문제와 멀티코어 활용 어려움이라는 두 가지 문제를 모두 해결, 반복 문법을 내부에 숨기고 있기 때문에 보다 간결한 코드 작성 가능, for문 없이도 컬렉션의 요소 탐색이 가능해지고 병렬 처리가 용이

    Interface Default Method를 통해 인터페이스에 실제 코드 구현 로직 포함 가능

    Optional는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로 이를 통해 NPE를 방지하고 if문 없이도 null 체크 가능 → 반환 타입으로써 제한적으로 사용되어야 함

    참고: https://mangkyu.tistory.com/203

</details>

<details>
    <summary>오버라이딩과 오버로딩에 대해 각각 설명해주세요.</summary>
    <br/><code>상속 관계</code>, <code>같은 클래스</code><br/><br/>

    오버라이딩은 부모 클래스로부터 상속받은 메소드를 자식 클래스에서 재정의하는 것으로, 메소드 시그니처가 모두 일치해야 합니다.
    부모 클래스의 메소드가 자식 클래스에서는 다르게 동작하기를 원할 때 오버라이딩을 통해 메소드를 재정의할 수 있습니다.

    오버로딩은 하나의 클래스 내에서 메소드 이름은 같고 매개변수의 개수나 타입은 다른 메소드를 여러 개 정의하는 것을 말합니다.
    오버로딩을 통해 같은(비슷한) 기능을 하는 메소드를 하나의 이름으로 정의할 수 있어 가독성이 높아진다는 장점이 있습니다.

    → 오버라이딩은 실제 실행되는 메소드가 런타임에 결정되는 반면, 오버로딩은 메소드 시그니처에 따라 컴파일 시점에 결정

</details>

<details>
    <summary>가비지 컬렉션(Garbage Collection)에 대해 설명해주시고 장단점에 대해 간략히 말해주세요.</summary>
    <br/><code>힙 메모리</code>, <code>Stop The World</code><br/><br/>

    가비지 컬렉션은 JVM의 Heap 메모리가 일정 수준으로 꽉 찼을 때 사용되지 않는 메모리 영역을 해제하여 메모리의 여유 공간을 확보하는 과정입니다.
    이때 사용되지 않는 메모리 영역이란 힙 메모리에 생성된 객체들 중 참조되지 않는 객체 즉 unreachable 객체를 말하며 이러한 객체들을 찾아 JVM이 알아서 해당 메모리를 해제해줍니다.

    가바지 컬렉션은 개발자가 직접 수행하는 것이 아닌 JVM이 알아서 적정한 시기에 수행하기 때문에 개발자가 메모리를 관리해야 하는 책임이 줄어든다는 장점이 있지만 (+ 메모리 누수 발생 가능성↓),
    일반적으로 이 가비지 컬렉션이 발생할 때 GC를 수행하는 쓰레드를 제외한 모든 쓰레드의 실행이 중지되는 Stop The World가 발생하여 어플리케이션의 전체 성능이 떨어진다는 단점 또한 존재합니다. 따라서 GC의 최적화는 이 Stop The World의 지속 시간을 줄이는 것이 관건입니다. (+ 언제, 어떻게 실행될지 개발자가 제어하기가 힘듦)

    *메모리 누수(memory leak): 컴퓨터 프로그램이 필요하지 않은 메모리를 계속 점유하고 있는 현상

</details>

<details>
    <summary>가비지 컬렉션 과정에 대해 설명해주세요.</summary>
    <br/><code>Minor GC & Major GC</code>, <code>Stop The World</code>, <code>Mark & Sweep</code><br/><br/>

    가비지 컬렉션은 크게 Stop The World와 Mark & Sweep의 2가지 단계로 진행됩니다.
    먼저 GC를 실행하는 쓰레드를 제외한 모든 쓰레드들의 작업이 중단되기 때문에, GC를 실행하기 위해 JVM이 애플리케이션의 실행을 멈추는 작업인 Stop The World가 발생합니다.
    Stop The World를 통해 모든 작업을 중단시키면, GC는 스택의 모든 변수 또는 Reachable 객체를 스캔하면서 각각이 어떤 객체를 참조하고 있는지 탐색하는 Mark & Sweep 과정이 진행됩니다.
    먼저 Mark에서는 사용되는 메모리와 사용되지 않는 메모리를 식별하는 작업이 이뤄지며, 다음 Sweep에서는 Mark 단계에서 사용되지 않음으로 식별된 메모리를 해제하는 작업이 수행됩니다.

    GC는 효율적인 작업을 위해 힙 메모리 영역을 물리적으로 분리하여 관리하는데요. 크게 Young 영역과 Old 영역으로 분리됩니다. 각 영역에서 발생하는 GC에 따라 Minor GC, Major GC로 구분됩니다.

    Eden 영역: 새로 생성된 객체가 할당되는 영역 → Eden 영역이 꽉 차면 Minor GC 발생하여 Survivor 영역으로 이동
    Survivor 영역: 최소 1번의 GC에서 살아남은 객체가 존재하는 영역 (총 2개이지만 반드시 1개의 영역에만 데이터가 존재) → 계속해서 살아남은 객체는 Old 영역으로 이동(Promotion)
    객체들이 계속 Promotion되어 Old 영역의 메모리가 부족해지면 Major GC 발생
    *객체의 생존 횟수를 카운트하기 위해 Minor GC에서 객체가 살아남은 횟수를 의미하는 age를 Object Header에 기록 → Minor GC 시 Object Header에 기록된 age를 보고 Promotion 여부 결정

    JVM의 Heap 영역 설계 시 2가지 전제 (Weak Generational Hypothesis)
    - 대부분의 객체는 금방 접근 불가능한 상태(Unreachable)가 된다.
    - 오래된 객체에서 새로운 객체로의 참조는 아주 적게 존재한다.
    → 객체는 대부분 일회성이며, 메모리에 오랫동안 남아있는 경우는 드묾 → 객체의 생존 기간에 따라 물리적으로 Heap 영역을 나눔 (Young / Old)

    - Young 영역에 대한 GC는 Minor GC
    - Old 영역에 대한 GC는 Major GC

</details>

<details>
    <summary>OOP의 4가지 특징에 대해 각각 설명해주세요.</summary>
    <br/><code>Abstraction</code>, <code>Encapsulation</code>, <code>Inheritance</code>, <code>Polymorphism</code><br/><br/>

    - 추상화: 객체의 공통적인 속성과 행위를 하나로 묶는 것

    - 캡슐화: 객체의 속성과 행위를 하나로 묶고 구현 코드를 외부에 감춰 은닉하는 것,
    이를 통해 객체의 응집도와 독립성을 높여 객체의 모듈화를 지향하게 되고 이러한 모듈화로 인해 모듈 단위의 코드 재사용성이 증가,
    객체 간 결합도는 낮아지기 때문에 코드 유지 보수에도 도움

    - 상속: 상위 클래스에서 정의된 기능을 가져와 재사용하거나 새로운 기능을 추가할 수 있기 떄문에 코드 중복을 줄이고 재사용성도 증가,
    but 상속은 클래스 간 결합도가 과도하게 높아져 유지보수가 어려워질 수 있다는 단점

    - 다형성: 객체가 상속을 통해 기능을 확장, 변경하여 여러 형태의 객체로 재구성되는 것을 의미,
    즉 메소드가 상황에 따라 다른 방식으로 동작하는 것, Overriding 또는 Overloading을 통해 다형성 확보 가능

</details>

<details>
    <summary>OOP의 5가지 설계 원칙 중 의존 역전 원칙에 대해 설명해주세요. (밀접한 관련이 있는 다른 설계 원칙과 의존성 역전 시점 포함하여)</summary>
    <br/><code>SRP</code>, <code>OCP</code>, <code>LSP</code>, <code>ISP</code>, <code>DIP</code><br/><br/>

    의존 역전 원칙인 DIP는 구체가 아닌 추상에 의존하라는 의미로, 쉽게 말해 구현 클래스에 의존하지 말고 인터페이스에 의존하라는 의미로 해석될 수 있습니다.
    DIP 적용 시 비즈니스 관련 부분이 세부 구현에는 의존하지 않는 설계로, 기존 서비스 코드의 변경 없이 서비스의 확장과 변경이 용이해지기 때문에 확장에 대해서는 열려있고 수정에는 닫혀있는 OCP 원칙에도 부합합니다. (OCP를 달성하기 위한 방법)
    그러나 이 의존성이 역전되는 시점은 컴파일 시점으로 실행 시점에는 실제 구현 객체에 의존하게 되는데요. 이때 DI(의존성 주입)를 통해 서비스가 의존하고 있는 인터페이스의 실제 구현 객체를 외부에서 주입해줌으로써 DIP 원칙에 더욱 부합하는 설계를 할 수 있습니다.

</details>

<hr/>

<details>
    <summary><code>Child child = (Child) new Parent();</code> (실제 부모 객체를 자식 클래스 타입으로 캐스팅하는 경우)에서 왜 예외가 발생하는지, 그렇다면 유효한 다운 캐스팅은 무엇인지 또는 언제 다운 캐스팅을 사용하는지에 대해 설명해주세요.</summary>
    <br/><code>객체 지향의 참조 다형성 위반</code>, <code>업캐스팅 객체의 멤버 복구</code><br/><br/>

    객체 지향의 참조 다형성에 따르면 부모 객체를 자식 객체로 캐스팅하는 것은 불가능합니다.

    또한 참조 변수의 형변환은 사용할 수 있는 멤버의 개수를 조절하는 것입니다.
    여기서는 부모 클래스 타입이 가지고 있는 정보가 자식 클래스 타입이 가지고 있는 정보 보다 더 적은데, 이때 나머지 정보를 JVM이 추측하지 못하기 때문에 런타임 에러가 발생하게 됩니다.

    자식 클래스를 부모 클래스 타입으로 업캐스팅하게 되면 자식 클래스에서만 정의한 멤버에는 접근이 불가능합니다. 이때 업캐스팅한 객체를 통해 자식 클래스에서만 정의한 멤버에 접근하기 위해 다시 자식 클래스로 캐스팅해야 하는데, 이와 같이 업캐스팅한 객체의 멤버를 복구하기 위한 경우가 유효한 다운 캐스팅이라고 할 수 있습니다.

    *참조 다형성: 다형성이란 여러 가지 형태를 가질 수 있는 능력을 의미한다. 자바에서는 한 타입의 참조 변수로 여러 타입의 객체를 참조할 수 있도록 함으로써 다형성을 프로그램적으로 구현하였다. 부모 클래스 타입의 참조 변수로 자식 클래스의 인스턴스를 참조할 수 있도록 하였다는 것이다.

</details>

<details>
    <summary>추상 클래스, 인터페이스의 공통점 및 차이점과 각각을 어느 상황에 사용하는 것이 좋을지에 대해 설명해주세요.</summary>
    <br/><code>추상 메소드 포함</code>, <code>멤버 필드</code>, <code>다중 상속</code><br/><br/>

    추상 클래스와 인터페이스의 가장 큰 공통점은 추상 메소드를 1개 이상 포함한다는 것입니다. 이로 인해 둘다 인스턴스화할 수 없으며, 둘을 상속 혹은 구현한 클래스는 반드시 해당 추상 메소드를 구현해야 합니다.

    반면 차이점은 추상 클래스는 내부에 멤버 필드를 정의할 수 있어 이를 하위 클래스에게 상속해줄 수 있는 반면 인터페이스는 static final 즉 상수만을 가질 수 있습니다. 추상 클래스는 말그대로 클래스의 특징을 가지기 때문에 다중 상속이 불가능하지만 인터페이스는 클래스에서는 다중 구현이, 인터페이스 간에는 다중 상속이 가능합니다.

    추상 클래스는 상속 기반이기 때문에 추상 클래스와 이를 상속 받는 클래스 간 밀접한 연관 관계가 생성됩니다. 따라서 클래스 간 논리적 연관 관계가 강한 경우나 또는 자식 클래스 간 공통된 멤버가 많아 이 공통된 기능들을 상위 클래스에서 추상화할 필요가 있을 때 추상 클래스를 사용할 수 있습니다.

    인터페이스는 구현 기반으로, 상속과 같은 밀접한 연관 관계를 맺지 않고 다형성을 적용하고자 하는 경우나 구현 세부사항과 관련 없이 여러 클래스들의 공통된 동작을 정의하고자 하는 경우 또는 다중 상속이 필요한 경우 인터페이스를 사용할 수 있습니다.

</details>

<details>
    <summary>Call by Value와 Call by Reference의 차이점에 대해 설명해주시고, Java에서는 어떤 전달 방식을 사용하는지 말씀해주세요.</summary>
    <br/><code>값 복사/참조</code>, <code>참조값(주소값)</code><br/><br/>

    Call by Value는 메소드 호출 시 값을 넘겨주는 방식으로, 메소드를 호출하는 호출자의 변수와 호출되는 수신자의 파라미터는 값이 복사된 서로 다른 변수입니다.

    반면 Call by Reference는 메소드 호출 시 참조를 직접 전달하는 방식으로, 참조를 직접 넘기기 때문에 호출자의 변수와 수신자의 파라미터는 완전히 동일한 변수입니다.

    따라서 Call by Value에서는 메소드 바디에서 수신자의 파라미터 값을 수정해도 호출자의 변수에는 영향이 없는 반면, Call by Reference에서는 수신자의 파라미터 값을 수정하는 것은 호출자의 변수를 수정하는 것과 동일한 것으로 판단됩니다.

    자바에서는 참조 자체를 전달하는 것이 아닌 참조값 즉 객체의 주소값을 전달하기 때문에 수신자의 파라미터에는 해당 객체의 주소값이 복사되어 Call by Value 방식으로 동작합니다.

</details>

<details>
    <summary>Primitive type에 대해 설명해주시고 Wrapper Class는 무엇인지, 왜 사용하는지에 대해서 말씀해주세요.</summary>
    <br/><code>스택/힙</code>, <code>Collection Generic</code>, <code>NULL</code><br/><br/>

    Primitive Type은 기본 자료형으로 JVM의 스택 메모리에 저장됩니다. 이 Primitive Type은 비객체 타입으로 NULL 값을 가질 수 없는데 만약 NULL 값을 저장하고 싶다면 Wrapper Class를 사용할 수 있습니다. 또한 컬렉션의 제네릭 타입은 참조형만 가능하기 때문에 이 경우 Wrapper Class를 사용할 수 있으며 메소드의 반환값으로 NULL을 반환해야 하는 경우에도 반환형을 Wrapper Class 타입으로 지정할 수 있습니다.
    즉 Wrapper Class는 기본 자료형을 객체로 변환하기 위해 사용하는 클래스입니다.

    *왜 Primitive Type이 별도로 존재? 스택 영역에 저장되기 때문에 GC가 발생하지 않으며, 변수값에 더 빠르게 접근 가능 (힙 영역에 저장되는 객체는 스택을 거쳐 가기 때문에 더 느림)

</details>

<details>
    <summary>String과 String Builder의 차이점에 대해 설명해주세요.</summary>
    <br/><code>불변/가변</code>, <code>버퍼 존재 여부</code><br/><br/>

    String과 StringBuilder의 가장 큰 차이점은 불변, 가변 여부입니다. 먼저 String은 클래스 내부에 상수 필드를 가지고 있어, String 객체에 한번 할당한 문자열은 바꿀 수 없기 때문에 불변입니다. 따라서 기존 String 문자열에 대해 + 연산이나 변경 연산을 수행하면 매번 새로운 객체를 생성하게 되고 이에 따라 메모리 공간이 낭비될 뿐만 아니라 속도도 매우 느려진다는 단점이 존재합니다. 그러나 이러한 불변의 특징으로 멀티 쓰레드에서도 안정적으로 동작할 수 있다는 장점이 있습니다.

    반면 StringBuilder는 가변적인 특징으로, String과 달리 내부에 크기 조절이 가능한 buffer를 두고 이 임시 공간에 문자열을 저장해 그 안에서 추가, 수정, 삭제 작업이 가능하기 때문에 동일 객체 내에서 문자열을 변경하는 것이 가능합니다. 따라서 값이 변경될 때마다 새롭게 객체를 만드는 String 보다 훨씬 빠르기 때문에 문자열의 추가, 수정, 삭제가 빈번한 경우에는 StringBuilder를 사용하는 것이 좋습니다.

</details>

<details>
    <summary>String을 new로 생성하는 것과 literal로 생성하는 것의 차이점에 대해 설명해주세요.</summary>
    <br/><code>String Constant Pool</code><br/><br/>

    두 객체 모두 JVM의 힙 영역에 저장되지만 정확히 말해 literal은 힙 영역 내부의 String Constant Pool에 위치하게 됩니다. String Constant Pool은 스트링을 별도로 관리하는 공간으로, 힙 내부에 있기 때문에 모든 스레드가 공유할 수 있으며 GC의 대상이 됩니다. 따라서 literal은 String Constant Pool에 해당 문자열이 이미 존재한다면 새로운 객체를 생성하지 않고 기존 주소값을 공유합니다.

    반면 new 생성자를 사용하면 매번 힙 영역에 새로운 객체가 생성됩니다. 따라서 같은 문자열에 대해 new 생성자로 생성한 각 객체의 주소값은 모두 다르게 됩니다. 결론적으로 literal로 String 객체를 생성하는 것이 메모리의 효율적 사용 측면에서 더 좋습니다.

    *String Constant Pool: 내부적으로 HashTable 구조, String Constant를 hashing하고 해당 데이터를 key로 value인 주소값을 찾음

</details>