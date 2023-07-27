# REST API & RESTFul API

## REST

### REST란?

- = REpresentational State Transfer
- 자원을 이름(표현)으로 구분해 해당 자원의 상태 및 정보를 주고 받는 모든 것
- **자원**의 **표현**에 의한 **상태 전달**
  - 자원 : 해당 소프트웨어가 관리하는 모든 것 (ex. 문서, 그림, 데이터, 해당 소프트웨어 자체 등)
  - 표현 : 그 자원을 표현하기 위한 이름 (ex. 자원:학생 정보 -> 표현:students)
  - 상태 전달 : 데이터가 요청되는 시점에서의 자원의 상태를 전달
- 네트워크 상 Client와 Server 간의 통신 방식 中 1
- 웹의 기존 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 웹의 장점을 최대로 활용할 수 있는 소프트웨어 아키텍쳐 스타일

<br/>

### REST 개념

- HTTP URI로 HTTP Method(POST, GET, PUT, DELETE)를 통해 요청을 보내며 해당 자원에 대한 CRUD 연산을 수행 + 요청을 위한 자원은 특정 형태(Representation of Resoure)로 명시됨

  > CRUD 연산<br/>
  > : Create생성(POST) / Read조회(GET) / Update수정(PUT) / Delete삭제(DELETE) / HEAD헤더정보조회(HEAD)

  - **URI란?**
    - URI : Uniform Resource Identifier로 인터넷 상의 자원을 식별하기 위한 문자열의 구성
    - URL : Uniform Resource Locator로 인터넷 상 자원의 위치
    - URI가 URL을 포함

- REST는 자원 기반의 구조(ROA) 설계의 중심엔 자원이 있고 HTTP Method를 통해 자원을 처리하도록 설계된 아키텍쳐
- 웹 사이트의 이미지, 텍스트, DB 내용 등 모든 자원에 고유한 ID인 HTTP URI부여

<br/>

### REST 구성요소

- **자원(Resource) : URI**
  - 모든 자원에는 고유한 ID가 존재하고, 이 자원은 Server에 존재함
  - 자원을 구별하는 ID는 '/exgroups/1'와 같은 HTTP URI 임
  - Client는 URI를 이용해 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청
- **행위(Verb) : Method**
  - HTTP 프로토콜의 Method(GET, POST, PUT, PATCH, DELETE)를 사용
  - GET : Read, 정보 요청, URI가 가진 정보를 검색하기 위해 요청
  - POST : Create, 정보 입력, 클라이언트가 서버로 정보를 전달
  - PUT : Update, 정보 업데이트, 데이터 전체를 바꿀때
  - PATCH : Update, 정보 업데이트, 데이터 일부를 바꿀때
  - DELETE : Delete, 정보 삭제, 안정성 문제로 서버에서 대부분 비활성화함
- **표현(Representation of Resource)**
  - Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답(Representation)을 보냄
  - Client와 Server가 데이터를 주고받는 형태로 JSON, XML, TEXT, RSS 등 여러 형태의 Respresentation가 있음
  - JSON 혹은 XML을 통해 데이터를 주고 받는 것이 일반적

<br/>

### REST 설계 규칙 및 특징

**Server-Client (서버-클라이언트 구조)**

- 자원이 있는 쪽이 Server, 자원을 요청하는 쪽이 Client
  - REST Server : API를 제공하고 비즈니스 로직 처리 및 저장을 책임짐
  - Client : 사용자 인증이나 context(세션, 로그인 정보) 등을 직접 관리하고 책임짐
- 서로 간 의존성 감소

**Stateless (무상태)**

- HTTP 프로토콜이 Stateless Protocol이므로 REST 역시 Stateless 무상태성을 가짐
- Client의 context를 Server에 저장X : context 정보(세션 및 쿠키)를 신경쓰지 않아도 되어 구현 단순
- Server는 각 요청들을 완전히 별개의 것으로 인식하고 처리
  - 각 API 서버는 Client의 요청만을 단순 처리
  - 이전 요청이 다음 요청의 처리에 연관되면 X, but 이전 요청이 DB를 수정하여 DB에 의한 변경은 허용
  - Server의 처리 방식에 일관성을 부여하여 부담 ▼ 서비스의 자유도 ▲

**Cacheable (캐시 처리 가능)**

- 웹 표준 HTTP 프로토콜을 그대로 사용하므로 웹에서 사용하는 기존 인프라를 그대로 활용 가능
- HTTP 프로토콜 표준에서 사용하는 Last-Modified 태그나 E-Tag 이용 시, 캐싱(HTTP가 가진 가장 강력한 특징 중 하나) 구현 가능
- 대량의 요청을 효율적으로 처리하기 위해 캐시 요구됨
- 캐시 사용을 통해 응답시간 감소 및 REST Server 트랜잭션이 발생하지 않아 전체 응답시간, 성능, 서버의 자원 이용률 향상 가능

**Layered System (계층화)**

- Client는 REST API Server만 호출
- 다중 계층으로 REST Server 구성할 수 있음
  - API Server는 순수 비즈니스 로직을 수행하고 그 앞단에 보안, 로드밸런싱, 암호화, 사용자 인증 등을 추가해 구조상의 유연성 부여 가능
  - 로드밸런싱, 공유 캐시 등을 통해 확장성&보안성 향상 가능
- PROXY, 게이트웨이 같은 네트워크 기반의 중간 매체 사용 가능

**Uniform Interface (인터페이스 일관성)**

- 통일되고 한정적인 인터페이스로 URI를 통해 지정한 자원에 대한 조작을 수행
- HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용 가능 -> 특정 언어나 기술에 종속X

**Self-Descriptiveness (자체 표현)**

- 요청 메시지만 보고도 쉽게 이해할 수 있는 자체 표현 구조

**Code-On-Demand (optional)**

- Server로부터 스크립트를 받아서 Client에서 실행
- 서버의 응답으로 정적 리소스(JSON, HTML 등)를 전송하는 일반적인 경우가 아닌 실행 코드(Java 애플릿 등)를 전달하여 클라이언트에서 실행 가능
- 반드시 충족할 필요X

<br/>

### REST 장단점

**장점**

- HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용 가능 + HTTP 표준 프로토콜을 최대한 활용하여 여러 장점들 GET
- HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라 구축 필요 X
- Hypermedia API의 기본을 충실히 지키며 범용성 보장
- REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도 쉽게 파악 가능
- 여러가지 서비스 디자인에서 생길 수 있는 문제 최소화
- 명확한 서버와 클라이언트의 역할 분리

**단점**

- 표준 존재 X
- 제한적인 HTTP Method 형태

**필요한 이유**

- 애플리케이션 분리 및 통합 + 다양한 클라이언트의 등장
- 최근의 서버는 다양한 브라우저와 모바일 디바이스, 즉 멀티 플랫폼에서 통신이 가능해야 함. 이를 위해 서비스 자원에 대한 아키텍쳐를 세우고 이용하는 방법으로 REST가 선택

<br/>
<hr/>

## REST API

> API(Application Programming Interface)란, 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램 간의 상호 작용을 촉진해서, **서로 정보를 교환 가능하도록 하는 것**

### REST API란?

- REST 기반으로 서비스 API를 구현한 것
- 최근 OpenAPI, 마이크로 서비스 등은 모두 REST API 형태로 제공됨

  - OpenAPI : 누구나 사용할 수 있는 공개된 API (ex. 구글맵, 공공데이터 ..)
  - 마이크로 서비스 : 하나의 큰 애플리케이션을 여러 개의 작은 애플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍처

<br/>

### REST API 특징

- REST는 HTTP표준을 기반으로 구현하므로 HTTP를 지원하는 프로그램 언어로 클라이언트와 서버 구현 가능
- 사내 시스템들도 REST 기반으로 한 시스템 분산을 통해 확장성과 재사용성을 높임 -> 편리한 유지보수 및 운용
- REST API를 제작하면 델파이 클라이언트뿐만 아니라 자바, C#, 웹 등을 이용해 클라이언트 제작 가능

<br/>

### REST API 설계 기본 규칙

> 참고 리소스 원형
>
> - 도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념
> - 컬렉션 : 서버에서 관리하는 디렉터리라는 Resource
> - 스토어 : 클라이언트에서 관리하는 리소스 저장소

1.  URI는 정보의 자원을 표현해야 한다.

    - resource는 동사보다는 명사를, 대문자보다는 소문자를 사용한다.
    - resource의 도큐먼트 이름으로는 단수 명사를 사용해야 한다.
    - resource의 컬렉션 이름으로는 복수 명사를 사용해야 한다.
    - resource의 스토어 이름으로는 복수 명사를 사용해야 한다.

      ex) `GET /Member/1` → `GET /members/1`

2.  자원에 대한 행위는 HTTP Method(GET, PUT, POST, DELETE 등)로 표현한다.

    - URI에 HTTP Method가 들어가면 안된다.

      ex) `GET /members/delete/1` → `DELETE /members/1`

    - URI에 행위에 대한 동사 표현이 들어가면 안된다.(즉, CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.)

      ex) `GET /members/show/1` → `GET /members/1`

      ex) `GET /members/insert/2` → `POST /members/2` \* 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.(즉, id는 하나의 특정 resource를 나타내는 고유값)

      ex) student를 생성하는 요청: `POST /students`

      ex) id=12인 student를 삭제하는 요청: `DELETE /students/12`

<br/>

### REST API 설계 기본 규칙

1. 슬래시 구분자(/ )는 계층 관계를 나타내는데 사용한다.

   ex) http://restapi.example.com/houses/apartments

<br/>

2. URI 마지막 문자로 슬래시(/ )를 포함하지 않는다.

- URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며 URI가 다르다는 것은 리소스가 다르다는 것이고, 역으로 리소스가 다르면 URI도 달라져야 한다.

- REST API는 분명한 URI를 만들어 통신을 해야 하기 때문에 혼동을 주지 않도록 URI 경로의 마지막에는 슬래시(/)를 사용하지 않는다.

  ex) http://restapi.example.com/houses/apartments/ (X)

<br/>

3. 하이픈(- )은 URI 가독성을 높이는데 사용

- 불가피하게 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높인다.

<br/>

4. 밑줄(\_ )은 URI에 사용하지 않는다.

- 밑줄은 보기 어렵거나 밑줄 때문에 문자가 가려지기도 하므로 가독성을 위해 밑줄은 사용하지 않는다.

<br/>

5. URI 경로는 소문자로만 구성한다.

- URI 경로에 대문자 사용은 피하도록 한다.
- RFC 3986(URI 문법 형식)은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정하기 때문

<br/>

6. 파일확장자는 URI에 포함하지 않는다.

- REST API에서는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다.
- Accept header를 사용한다. <br/>

  ex) `http://restapi.example.com/members/soccer/345/photo.jpg` (X)

  ex) <code>GET /members/soccer/345/photo HTTP/1.1 <br/>Host: restapi.example.com <br/>Accept: image/jpg</code> (O)

<br/>

7. 리소스 간에는 연관 관계가 있는 경우

- /리소스명/리소스 ID/관계가 있는 다른 리소스명

  ex) `GET /users/{userid}/devices` (일반적으로 소유 ‘has’의 관계를 표현할 때)

<br/>

8. HTTP 응답 상태 코드 사용할 것

- 클라이언트는 해당 요청에 대한 실패 처리완료, 잘못된 요청 등에 대한 피드백 필요

  \*2xx은 성공 / 4xx은 클라이언트 실패 / 5xx은 서버 실패

<br/>

9. URI는 명사를 사용해야한다. \* 동사를 사용하지 말라는 얘기 <br/>

   ex) `/getUsers`, `/createNewUser`

<br/>
<hr/>

## RESTful API

### RESTful API란?

- RESTful = 일반적으로 REST라는 아키텍처를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어
- 즉 ‘REST API’를 제공하는 웹 서비스를 ‘RESTful’하다고 할 수 있다.
- RESTful은 REST를 REST답게 쓰기 위한 방법으로, 누군가가 공식적으로 발표한 것이 아님
- **REST API와 RESTful API의 차이**
  - REST 원리를 따르는 시스템이 RESTful이란 용어로 지칭되는 것
  - **결론! RESTful API = REST의 6가지 설계 규칙을 잘 지켜서 설계된 API**

<br/>

### RESTful API 목적

- 이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것
- 근본적인 목적이 성능 향상이 아닌 일관적인 컨벤션을 통해 API의 이해도 및 호환성을 높이는 것임 -> 성능이 중요할 땐 굳이 RESTful API 구현할 필요X
- RESTful 하지 못한 경우

  ex1) CRUD 기능을 모두 POST로만 처리하는 API

  ex2) URI에 resource, id 외의 정보가 들어가는 경우`/students/updateName`

<br/>

### RESTful API 설계 규칙 및

1. URI는 정보의 자원를 표현해야 한다. (리소스명은 동사가 아닌 명사를 사용한다)
2. 자원의 행위는 HTTP 메소드(GET, POST, PUT, DELETE)로 표현한다.
3. 슬래시(/)로 계층 관계를 표현해야 한다.
4. 소문자로만 구성해야 한다.
5. 밑줄(\_)은 사용하지 않고 하이픈(-)을 사용한다.
6. 확장자(.txt, .png 등)를 사용하지 않는다.
7. URI의 마지막에 슬래시(/)를 포함하지 않는다.
   ..
8. 자원에 대한 행위로는 HTTP Method로 표현해야하며 HTTP 응답 코드로 클라이언트에게 피드백을 해줘야 한다.

<br/>
<hr/>

## 참고

[REST API란?](https://www.ibm.com/kr-ko/topics/rest-apis)<br/>
[REST란? REST API 와 RESTful API의 차이점](https://dev-coco.tistory.com/97)<br/>
[REST란? REST API / RESTful API차이점](https://velog.io/@somfist/REST%EB%9E%80-REST-API-RESTful-API%EC%B0%A8%EC%9D%B4%EC%A0%90)<br/>
[[간단정리] REST, REST API, RESTful 특징](https://hahahoho5915.tistory.com/54#google_vignette)<br/>
[[ Network ] REST란? / Rest API와 Restful API의 차이점 / REST 규칙](https://jy-tblog.tistory.com/24)<br/>
