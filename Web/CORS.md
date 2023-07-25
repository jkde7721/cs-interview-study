# CORS (Cross-Origin Resource Sharing)

> 출처가 다른 자원들을 공유한다는 뜻<br/>
> 한 출처에 있는 자원에서 다른 출처에 있는 자원에 접근하도록 하는 것

> **교차 출처 리소스 공유(CORS)** 는 추가 HTTP 헤더를 사용하여, 한 출처에서 실행 중인 웹 애플리케이션이 다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 체제 <br/>
> 웹 애플리케이션은 리소스가 자신의 출처(도메인, 프로토콜, 포트)와 다를 때 교차 출처 HTTP 요청을 실행

<br/>

## 요청 방식에 따라 다른 CORS 발생 여부

1. \<img>, \<video>, \<script>, \<link> 태그 등
   > 기본적으로 Cross-Origin 정책을 지원함

- \<link> 태그의 href에서 다른 사이트의 .css 리소스에 접근하는 것이 가능
- \<img> 태그의 src에서 다른 사이트의 .png, .jpg 리소스에 접근하는 것이 가능
- \<script> 태그의 src에서 다른 사이트의 .js 리소스에 접근하는 것이 가능
  <br/>

2. XMLHttpRequest, Fetch API 스크립트
   > 기본적으로 Same-Origin 정책을 따름

&rarr; Same Origin / Cross Origin 정책의 정보 부족으로 인해 자신도 모르게 정책을 위반하는 행동을 하게 되어 CORS 에러가 나타나는 것

<br/>

### 출처란?

> Protocol + Host + Port까지 모두 합친 URL을 의미

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/E2KY5/btrI1hxsSYD/ZJrCDW0gdeOsWgnKvsYn51/img.png)

<br/>

## 동일 출처 정책 (Same-Origin Policy)

> 다른 출처로부터 조회된 자원들의 읽기 접근을 막아 다른 출처 공격을 예방

- '동일한 출처에서만 리소스를 공유할 수 있다.'라는 법률을 가지고 있다.

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/StWqf/btrIYZKTbKr/Qo5bsGbEVJ0kd95ak5lRwk/img.png)

- 동일 출처 서버에 있는 리소스는 자유로이 가져올 수 있지만, 다른 출처 서버에 있는 이미지나 유튜브 영상 같은 리소스는 상호작용 불가능
  &rarr; 다른 출처 리소스에 접근성을 높이기 위해서 CORS가 등장

<br/>

### 동일 출처 정책 (Same-Origin Policy) 이 필요한 이유

- 만약 제약이 없이 출처가 다른 두 어플리케이션이 자유로이 소통할 수 있는 환환경이라면 해커가 CSRF(Cross-Site Request Forgery)나 XSS(Cross-Site Scripting) 등의 방법을 이용해 만들어 놓은 어플리케이션에서 해커가 심어놓은 코드가 실행하여 개인 정보를 가로챌 수 있다.

<br/>

### 동일 출처와 다른 출처 구분 기준

- 두 URL의 구성 요소 중 **Protocol(Scheme), Host, Port 이 3가지만 동일**하다면 동일 출처로 판단

#### 동일 출처 예시

http://example.com/app1/index.html
http://example.com/app2/index.html

#### 다른 출처 예시

http://example.com/app1
https://example.com/app2
&rarr; Protocol이 다름!

<br/>

## 교차 출처 리소스 공유 (Cross-Origin Resource Sharing, CORS)

> 다른 출처의 리소스 공유에 대한 허용/비허용 정책
> 앞서 말한 다른 출처 정책을 의미

- SOP(동일 출처 정책)을 위반해도 CORS에 따르면 다른 출처의 리소스라도 허용

### 브라우저의 CORS 기본 동작

① 클라이언트에서 HTTP 요청의 헤더에 Origin을 담아 전달

- 기본적으로 웹은 HTTP 프로토콜을 이용하여 서버에 요청을 보내게 되는데 이때 브라우저는 요청 헤더에 Origin이라는 필드에 출처를 함께 담아 보내게 된다.
  ![enter image description here](https://velog.velcdn.com/images/dae_eun2/post/17d9738c-6b9a-41f2-a3af-059fbc736417/image.png)

② 서버는 응답 헤더에 Access-Control-Allow-Origin을 담아 클라이언트로 전달

- 이후 서버가 이 요청에 대한 응답을 할 때 응답 헤더에 'Access-Control-Allow-Origin'이라는 필드를 추가하고 값으로 '이 리소스를 접근하는 것이 허용된 출처 URL'을 내려보낸다.
  ![enter image description here](https://velog.velcdn.com/images/dae_eun2/post/059b901d-5885-4917-b9df-3335249b8dc0/image.png)

③ 클라이언트에서 Origin과 서버가 보내준 Access-Control-Allow-Origin을 비교한다.

- 이후 응답을 받은 브라우저는 자신이 보냈던 요청의 Origin과 서버가 보내준 응답의 Access-Control-Allow-Origin을 비교해본 후 차단할지 말지를 결정한다.
- 만약 유효하지 않다면 그 응답을 사용하지 않고 버린다.(CORS 에러!)
- 위의 경우에는 둘다 http://localhost:3000이기 때문에 유효하니 다른 출처의 리소스를 문제없이 가져오게 된다.

&rarr; 결국 CORS 해결책은 서버의 허용이 필요!
&rarr; 서버에서 Access-Control-Allow-Origin 헤더에 허용할 출처를 기재해서 클라이언트에 응답하면 되는 것

<br/>

### CORS 작동 방식 3가지 시나리오

❶ **예비 요청 (Preflight Request)**

- 브라우저는 요청을 보낼 때 한 번에 바로 보내지 않고 먼저 예비 요청을 보내 서버와 잘 통신되는지 확인한 후 본 요청을 보낸다.
- 예비 요청의 역할은 본 요청을 보내기 전에 브라우저 스스로 안전한 요청인지 미리 확인하는 것
- 이때 브라우저가 예비요청을 보내는 것을 Preflight라고 부르며 예비요청의 HTTP 메소드를 GET이나 POST가 아닌 OPTIONS라는 요청이 사용된다는 것이 특징

**예비 요청의 문제점과 캐싱**

- 실제 요청에 걸리는 시간이 늘어나게 되어 어플리케이션 성능에 영향을 미치는 단점이 있음
- 수행하는 API 호출 수가 많으면 많을 수록 예비 요청으로 인해 서버 요청을 배로 보내게되니 비용적 측면에서도 부담

&rarr; 브라우저 캐시를 이용해 Access-Control-Max-Age 헤더에 캐시될 시간을 명시해주면 이 예비 요청을 캐싱시켜 최적화시켜줄 수 있다.

<br/>

❷ **단순 요청 (Simple Request)**

- 예비 요청을 생략하고 바로 서버에 직행으로 본 요청을 보낸 후 서버가 이에 대한 응답의 헤더에 Access-Control-Allow-Origin 헤더를 보내주면 브라우저가 CORS 정책 위반 여부를 검사하는 방식
- 심플한 만큼 특정 조건을 만족하는 경우에만 예비 요청을 생략할 수 있다.

< 3가지 경우를 만족할 때 가능 >

1. 요청의 메소드는 GET, HEAD, POST 중 하나여야 한다
2. Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width 헤더일 경우에만 적용된다.
3. Content-Type 헤더가 application/x-www-form-urlencoded, multipart/form-data, text/plain중 하나여야 한다. 아닐 경우 예비 요청으로 동작된다.

&rarr; 위의 조건들을 모두 만족되어야 하므로 단순 요청이 일어나는 상황은 드물다. 따라서 **대부분의 API 요청은 그냥 예비 요청(preflight)으로 이루어진다.**

<br/>

❸ **인증된 요청 (Credentialed Request)**

- 클라이언트에서 서버에게 \*\*자격 인증 정보(Credential)를 실어 요청할 때 사용되는 요청
- 자격 인증 정보 : 세션 ID가 저장되어 있는 쿠키(Cookie) 혹은 Authorization 헤더에 설정하는 토큰 값 등을 일컫는다.

1. 클라이언트에서 인증 정보를 보내도록 설정하기

- 기본적으로 브라우저가 제공하는 요청 API들은 별도의 옵션 없이 브라우저의 쿠키와 같은 인증과 관련된 데이터를 함부로 요청 데이터에 담지 않도록 되어있다.
- 이때 요청에 인증과 관련된 정보를 담을 수 있게 해주는 옵션이 credentials 옵션
  < 옵션에 3가지의 값 사용 가능 >
  ① 옵션 값 : same-origin(기본값) -> 같은 출처 간 요청에만 인증 정보를 담을 수 있다.
  ② 옵션 값 : include -> 모든 요청에 인증 정보를 담을 수 있다.
  ③ 옵션 값 : omit -> 모든 요청에 인증 정보를 담지 않는다.

2. 서버에서 인증된 요청에 대한 헤더 설정하기

- 서버도 마찬가지로 이러한 인증된 요청에 대해 일반적인 CORS 요청과는 다르게 대응해야 한다.
  ① 응답 헤더의 Access-Control-Allow-Credentials 항목을 true로 설정해야 한다.
  ② 응답 헤더의 Access-Control-Allow-Origin 의 값에 와일드카드 문자("_")는 사용할 수 없다.
  ③ 응답 헤더의 Access-Control-Allow-Methods 의 값에 와일드카드 문자("_")는 사용할 수 없다.
  ④ 응답 헤더의 Access-Control-Allow-Headers 의 값에 와일드카드 문자("\*")는 사용할 수 없다.

<br/>

### CORS 해결 방법

① Chrome 확장 프로그램 이용

- Chrome에서는 CORS 문제를 해결하기 위한 확장 프로그램을 제공해준다

② 프록시 사이트 이용

- 프록시(Proxy)란 클라이언트와 서버 사이의 중계 대리점
- 프론트에서 직접 서버에 리소스를 요청했을 때 서버에서 따로 설정을 안해줘서 CORS 에러가 뜬다면 모든 출처를 허용한 서버 대리점을 통해 요청을하면 되는 것

&rarr; 하지만 악용 사례로 인해 API 요청 횟수 제한을 두어 실전에서는 사용하기 어렵다.

③ 서버에서 Access-Control-Allow-Origin 헤더 세팅

- 직접 서버에서 HTTP 헤더 설정을 통해 출처를 허용하게 설정하는 가장 정석적인 해결책
- 각 서버의 문법에 맞게 위의 HTTP 헤더를 추가해주면 된다.
  <br/>

### 참고

[[Network] CORS란 무엇인가?](https://escapefromcoding.tistory.com/724)

[[Network] 악명 높은 CORS 개념 & 해결법](https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-CORS-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95-%F0%9F%91%8F)
