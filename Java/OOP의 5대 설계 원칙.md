# OOP의 5가지 설계 원칙, SOLID

> 객체 지향 프로그래밍을 하면서 지켜야하는 5대 원칙으로 각각 SRP(단일 책임 원칙), OCP(개방-폐쇄 원칙), LSP(리스코프 치환 원칙), ISP(인터페이스 분리 원칙), DIP(의존 역전 원칙)의 앞글자를 따서 만들어졌음

> 해당 원칙을 철저히 지키면 시간이 지나도 변경이 용이하고, 유지보수와 확장이 쉬운 소프트웨어를 개발하는데 도움이 되는 것으로 알려져있음

<br/>

## 단일 책임의 원칙 (SRP, Single Responsibility Principle)

> 한 클래스(객체)는 하나의 책임만 가져야 함

&rarr; 해당 모듈이 여러 대상 또는 액터들에 대해 책임을 가져서는 안되고, 오직 하나의 액터에 대해서만 책임을 져야 한다
<br/>

```java
@Component
public  class SimplePasswordEncoder {
	public void encryptPassword(final String pw) {
		final StringBuilder sb = new StringBuilder();
		for(byte b : pw.getBytes(StandardCharsets.UTF_8)) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
@Service
@RequiredArgsConstructor
public  class UserService {
	private  final UserRepository userRepository;
	private  final SimplePasswordEncoder passwordEncoder;
	public void addUser(final String email, final String pw) {
		final String encryptedPassword = passwordEncoder.encryptPassword(pw);
		final User user = User.builder()
							  .email(email)
							  .pw(encryptedPassword).build();
		userRepository.save(user);
	}
}
```

&rarr; 비밀번호 암호화를 책임지는 별도의 클래스를 만들어 UserService로부터 이를 추상화하고,
해당 클래스를 합성하여 접근 및 사용하면 UserService로부터 비밀번호 암호화 방식을 개선해달라는 변경을 분리할 수 있음
<br/>

### 장점

:point_right: 변경이 필요할 때 수정할 대상이 명확해짐

&rarr; 시스템이 커질수록 해당 장점은 극대화됨.

&rarr; 시스템이 커지면서 서로 많은 의존성을 갖게되는 상황에서 변경 요청이 오면 딱 1가지만 수정하면 되기 때문

<br/>

## 개방-폐쇄 원칙 (OCP, Open-Closed Principle)

> 확장에 대해 열려있고 수정에 대해서는 닫혀있어야 한다는 원칙

- 확장에 대해 열려 있다
  :point_right: 요구사항이 변경될 때 새로운 동작을 추가하여 애플리케이션의 기능을 확장할 수 있다.

- 수정에 대해 닫혀 있다
  :point_right: 기존의 코드를 수정하지 않고 애플리케이션의 동작을 추가하거나 변경할 수 있다.
  <br/>

```java
public  interface PasswordEncoder {
	String encryptPassword(final String pw);
}
@Component
public  class SHA256PasswordEncoder implements PasswordEncoder {
	@Override
	public String encryptPassword(final String pw) { ... }
}
@Service
@RequiredArgsConstructor
public  class UserService {
	private  final UserRepository userRepository;
	private  final PasswordEncoder passwordEncoder;
	public void addUser(final String email, final String pw) {
		final String encryptedPassword = passwordEncoder.encryptPassword(pw);
		final User user = User.builder()
							  .email(email)
							  .pw(encryptedPassword).build();
		userRepository.save(user);
	}
}
```

&rarr; UserService가 구체적인 암호화 클래스에 의존하지 않고 PasswordEncoder라는 인터페이스에 의존하도록 추상화하면 개방 폐쇄의 원칙이 충족되는 코드를 작성할 수 있음

&rarr; 개방 폐쇄 원칙을 지키기 위해서는 추상화에 의존해야 함 (변하는 것들은 숨기고 변하지 않는 것들에 의존)

⇒ 추상화란? 핵심적인 부분만 남기고, 불필요한 부분은 제거함으로써 복잡한 것을 간단히 하는 것

<br/>

## 리스코프 치환 원칙 (LSP, Liskov Substitution Principle)

> 자식 타입은 언제나 부모 타입으로 교체할 수 있어야 함

> 해당 객체를 사용하는 클라이언트는 상위 타입이 하위 타입으로 변경되어도, 차이점을 인식하지 못한 채 상위 타입의 퍼블릭 인터페이스를 통해 서브 클래스를 사용할 수 있어야 한다는 것

:bulb: 자식 클래스가 부모 클래스를 대체하기 위해서는 부모 클래스에 대한 클라이언트의 가정을 준수해야 한다는 것을 강조

<br/>

## 인터페이스 분리 원칙 (ISP, Interface Segregation Principle)

> 클라이언트의 목적과 용도에 적합한 인터페이스만을 제공

:point_right: 모든 클라이언트가 자신의 관심에 맞는 퍼블릭 인터페이스(외부에서 접근 가능한 메세지)만을 접근하여 불필요한 간섭을 최소화

&rarr; 인터페이스 분리 원칙을 지킨다는 것은 어떤 구현체에 부가 기능이 필요하다면 이 인터페이스를 구현하는 다른 인터페이스를 만들어서 해결할 수 있는 것

```java
public  interface PasswordChecker {
	String isCorrectPassword(final String rawPw, final String pw);
}
@Component
public  class SHA256PasswordEncoder implements PasswordEncoder, PasswordChecker {
	@Override
	public String encryptPassword(final String pw) { ... }

	@Override
	public String isCorrectPassword(final String rawPw, final String pw) {
		final String encryptedPw = encryptPassword(rawPw);
		return encryptedPw.equals(pw);
	}
}
```

&rarr; 클라이언트에 따라 인터페이스를 분리하면 변경에 대한 영향을 더욱 세밀하게 제어할 수 있음

<br/>

## 의존 역전 원칙 (DIP, Dependency Inversion Principle)

> 고수준 모듈은 저수준 모듈의 구현에 의존해서는 안 되며, 저수준 모듈이 고수준 모듈에 의존해야 한다는 것

- 고수준 모듈
  :point_right: 입력과 출력으로부터 먼(비즈니스와 관련된) 추상화된 모듈

- 저수준 모듈
  :point_right: 입력과 출력으로부터 가까운(HTTP, 데이터베이스, 캐시 등과 관련된) 구현 모듈

&rarr; 비즈니스와 관련된 부분이 세부 사항에는 의존하지 않는 설계 원칙

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/cgWOqH/btrjHUA2RyR/AvHKl9eXnkvppp8EPHdyrk/img.png)
&rarr; 의존 역전 원칙은 개방 폐쇄 원칙과 밀접한 관련이 있으며, 의존 역전 원칙이 위배되면 개방 폐쇄 원칙 역시 위배될 가능성이 높음

&rarr; 의존 역전 원칙에서 의존성이 역전되는 시점은 컴파일 시점

<br/>

### 참고

[[Java] 객체지향 프로그래밍의 5가지 설계 원칙, 실무 코드로 살펴보는 SOLID](https://mangkyu.tistory.com/194)
