# Checked Exception vs Unchecked Exception

<br/>

## 에러와 예외

> 어떤 원인에 의해 오동작하거나 비정상적으로 종료되는 경우

### 심각도에 따른 분류

① 에러 (Error) : 시스템에 비정상적인 상황이 발생한 경우

- 메모리 부족, stack overflow와 같이 일단 발생하면 복구할 수 없는 상황
- 프로그램의 비정상적 종료를 막을 수 없음 &rarr; 디버깅 필요
  ex) OutOfMemoryError, ThreadDeath, StackOverflowError 등 (일반적으로 애플리케이션단에서 복구할 수 없는 상황)

② 예외 (Exception) : 입력값에 대한 처리가 불가능하거나, 프로그램 실행 중에 참조된 값이 잘못된 경우 등 정상적인 프로그램의 흐름을 어긋나는 경우

- 읽으려는 파일이 없거나 네트워크 연결이 안되는 등 수습될 수 있는 비교적 상태가 약한 것들
- 프로그램 코드에 의해 수습될 수 있는 상황 즉, 개발자가 직접 처리할 수 있기 때문에 예외 상황을 미리 예측하여 핸들링할 수 있음

<br/>

## 예외 구분

> 예외는 Checked Exception과 Unchecked Exception으로 구분
>
> ![enter image description here](https://joswlv.github.io/images/java-exception-handling-class-hierarchy-diagram.jpg)

### Checked Exception

- RuntimeException을 상속하지 않는 예외 클래스
- 컴파일 시점에 컴파일러에서 확인하는 예외
- 명시적인 예외 처리를 강제하기 때문에 Checked Exeption이라 함
- 반드시 try ~ catch로 예외를 잡거나, throw로 호출한 메소드에게 예외를 던져야 함

```java
public  class CheckedException {
	public  static  void  main(String[] args) {
		try {
			File file = new File("example.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println("line = " + line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading the file: " + e.getMessage());
		}
	}
}
```

&rarr; example.txt라는 파일을 읽는 코드
<br/>

:point_right: 실제로 example.txt라는 파일은 존재하지 않기 때문에 '**FileNotFoundException**' 예외 발생

```java
An error occurred while reading the file: example.txt (No such file  or directory)
```

:pushpin: 그 외의 예외

- IOException : 파일 또는 네트워크 연결에서 읽기 또는 쓰기와 같은 입출력 작업과 관련된 오류
- SQLException : 데이터베이스 액세스 및 쿼리와 관련된 오류
- ClassNotFoundException : 동적으로 클래스 로드와 관련된 오류

<br/>

### Unchecked Exception

- RuntimeException을 상속하는 예외 클래스
- 런타임 단계에서 확인 가능
- 명시적인 예외 처리를 강제하지 않기 때문에 Unchecked Exeption이라 함

```java
public  class  UncheckedException {
	public static void main(String[] args) {
		int[] numbers = {1, 2, 3};
		int index = 10;
		System.out.println("number = " + numbers[index]);
	}
}
```

&rarr; ArrayIndexOutOfBoundsException 발생하는 코드
<br/>

:point_right: try ~ catch 문을 사용해 에러가 발생하는 지점에 예외처리를 하여 해결할 수 있음

```java
public  class  UncheckedException {
	public static void main(String[] args) {
		int[] numbers = {1, 2, 3};
		int index = 10;
		try {
			int number = numbers[index];
			System.out.println("number = " + number);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("An error occurred: " + e.getMessage());
			// An error occurred: Index 10 out of bounds for length 3
		}
	}
}
```

:pushpin: 그 외의 예외

- NullPointerException : 실제 값이 아닌 null 값을 가지는 객체 참조를 사용하려고 할 때 발생, 객체를 선언만 하고 생성하지 않은 상태에서 접근한 경우
- ArithmeticException : 0으로 나눈 경우
- NegativeArraySizeException : 배열의 크기를 음수로 지정한 경우

<br/>

### 참고

[[Java] Checked Exception과 Unchecked Exception](https://seungjjun.tistory.com/250)

[[Java] Checked Exception vs Unchecked Exception](https://hahahoho5915.tistory.com/67)
