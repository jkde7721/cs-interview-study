# JWT(JSON Web Token)
### JWT란?
* = JSON Web Token, 전자 서명된 URL-Safe의 JSON
  
  > JSON = Key, Value가 한 쌍을 이루는 객체 <br/>
    전자 서명은 JSON의 변조 여부 체크 가능 <br/>
    URL-Safe = URL로 이용할 수 있는 문자로만 구성된 문자열
* 인터넷 표준 인증 방식
* JSON 데이터 구조로 표현한 Token에 인증에 필요한 정보들을 담은 후 암호화하여 사용하는 서명된 토큰
* 웹 표준을 따르기 때문에 대부분의 언어가 이를 지원
* 필요한 모든 정보를 하나의 JSON 객체에 담아 전달하기에 JWT 하나로 인증 완료 가능
  * 서버와 클라이언트 간 정보를 주고 받을 때, HTTP 리퀘스트 헤더에 JSON 토큰을 넣은 후에 서버는 별도의 인증 과정 없이 헤더에 포함되어 있는 JWT 정보를 통해 인증함
  * 이때 사용되는 JSON 데이터는 URL-Safe하도록 URL에 포함할 수 있는 문자로만 구성
* HMAC 알고리즘을 사용하며 비밀키 또는 RSA를 이용한 공개키-개인키 쌍으로 서명 가능
* 핵심 목표 : 사용자에 대한, 토큰에 대한 표현을 압축하는 것
<br/>
<br/>

### JWT 구조
<img src="https://velog.velcdn.com/images%2Fvamos_eon%2Fpost%2Feadb5e93-f581-4f54-9453-5b6d4bd7ea59%2Fjwt%EA%B5%AC%EC%A1%B0.png"> <br/>
총 3개의 요소로 구성되어 있으며 각각의 구성 요소는 점(.)으로 구분함 !
1. Header (헤더)
  ```sql
  {
    "typ": "JWT",
    "alg": "HS512",
  }
  ```
  * 토큰의 타입과 서명 생성에 사용된 해시 알고리즘을 저장
  * 위와 같은 경우 현재 토큰 타입이 JWT이며, 개인키로 HS512 알고리즘이 적용되어 암호화가 되어있음을 의미
<br/>

2. Payload
  ```sql
  {
    "sub": "1",
    "name": "John Doe",
    "iat": 1516239022,
    "exp": 1516239022
  }
  ```
  * 전달하는 데이터를 저장 
  * 이때 데이터 각각의 Key를 Claim이라고 부르며, Claim은 property를 사용자가 원하는 Key-Value 형태로 구성
  * **Claim**
    : 말 그대로 토큰에서 사용할 정보의 조각 <br/>
    * 민감한 정보를 절대! 담으면 안됨!
      
      > Payload는 서명된 파트가 아니므로 안전한 공간이 아님. password 같은 결정적인 아이들 절대 X
      > header와 payload는 JSON 인코딩이 되어있을 뿐, 암호화X -> 누구나 JWT를 이용해 디코딩하여 값(데이터)에 접근 가능<br/>
      
    ① Registered Claim (등록된 클레임)
      * 미리 정의되어 있는 표준 스펙으로 Key 이름이 3글자로 되어있으며 사용 권장
      * iss (Issuer) : 토큰 발급자
      * sub (Subject) : 토큰 제목 - 토큰에서 사용자에 대한 식별값
      * aud (Audience) : 토큰 대상자
      * iat (Issued At) : 토큰 발급 시간
      * exp (Expiration Time) : 토큰 만료 시간
      * nbf (Not Before) : 토큰 활성 날짜 - 이 날짜 이전의 토큰은 활성화되지 않음을 보장
      * jti (JWT Id) : JWT 토큰 식별자 - issuer가 여러 명일 때 이를 구분하기 위한 값
      * 꼭 이 7가지를 모두 포함해야 하는 것이 아니며 상황에 맞게 사용하면 됨<br/>
      
      ② Public Claim (공개 클레임)
      * 사용자가 자유로이 정의 가능
      * 단, 충돌 방지 위해 IANA JSON 웹 토큰 레지스트리에 정의되어 있거나 충돌이 방지된 네임스페이스를 포함하는 URI정의<br/>
      
      ③ Private Claim (비공개 클레임)
      * 등록 또는 공개된 클레임이 아닌 클레임으로, 정보를 공유하기 위해 만들어진 커스터마이징된 클레임
<br/>

3. Signature (서명)

  ```sql
  HMACSHA512(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret
  )
  ```

  * JWT의 변조 여부를 확인하기 위해 존재하는 부분
  * JWT 발급 시 <code>Base64Url 인코딩된 Header<b>.</b>Base64Url 인코딩된 Payload</code>를 secret(서버만 가지고 있는 비밀키)을 이용해 Header에서 지정한 알고리즘으로 해싱한 뒤 다시 Base64Url 인코딩하여 생성
    
    _해싱: 일종의 단방향 암호화로 해싱된 데이터는 원본 데이터로 되돌리는 복호화 불가_

  * JWT 사용 시 서버는 클라이언트로부터 전달받은 JWT의 Header, Payload를 이용해 위와 동일한 프로세스로 직접 Signature를 생성해보고 이 값이 전달받은 JWT의 Signature와 동일하다면 유효한 JWT, 즉 변조되지 않은 JWT임이 보장

<br/>

### JWT 사용과정
**JWT 토큰 기반의 인증 시스템 사용 프로세스**<br/>
: 거의 로그인에서 사용됨. 토큰으로 해당 요청이 유효한지 확인
<br/><img src="https://i1.wp.com/www.opennaru.com/wp-content/uploads/2018/08/jwt_process_image_v2.png?zoom=1.25&fit=1920%2C1080"><br/>
1. 사용자가 id와 password를 입력하여 로그인 시도
2. 서버는 요청을 확인하고 secret key를 통해 Access token 발급
3. JWT 토큰을 클라이언트에게 전달
4. 클라이언트에서 API를 요청할 때, 클라이언트가 Authourization header에 Access token을 담아서 보냄
5. 서버는 JWT Signature를 체크하고 Payload로부터 사용자 정보를 확인해 데이터 반환
6. 클라이언트의 로그인 정보를 서버 메모리에 저장하지 않기에 토큰 기반 인증 메커니즘을 제공<br/>
   인증이 필요한 경로에 접근할 때 서버 측은 Authorization 헤더에 유효한 JWT 또는 존재하는 지를 확인<br/>
<br/>

```sql
로그인 성공 시, JWT 인증 성공 시입니다.
(실패 시에는 실패 응답이 반환됩니다.)
사용자 | ===== ID / PWD ====> 계정 관리 및 REST-API 서버 (로그인)
      | <======= JWT ======= 로그인 성공 시, JWT 발급
      | ==== 요청 + JWT ====> JWT 인증 성공 시, REST-API 요청 
```
<br/>
<로그인 Workflow>
<br/><br/><img src="https://velog.velcdn.com/images%2Fvamos_eon%2Fpost%2Fb43158f7-51a6-4815-8448-b96106b9ede4%2FJWT%EB%A1%9C%EC%A7%81.png">

* JWT에는 필요한 모든 정보를 토큰에 포함하기 때문에 데이터베이스와 같은 서버와의 커뮤니케이션 오버 헤드 최소화 가능
* CORS(Cross-Origin Resource Sharing)는 쿠키를 사용하지 않기 때문에 JWT를 사용한 인증 메커니즘은 두 도메인에서 API를 제공하더라도 문제X
* 일반적으로 처음 사용자를 등록할 때, Access token과 Refresh token 모두 발급되어야 함
* Access Token
  * API를 사용하기 위한 인증용 토큰
  * 이 토큰 만료되면 access token이 없는 것과 마찬가지이므로 접근을 막을 수 있음
* Refresh Token
  * Access Token의 유효 기간 연장을 위한 토큰
  * Access token 만료 시, Refresh token으로 Access token 새로 발급 가능
  * Refresh token 또한 사용자가 로그인하며 발급받은 토큰이므로 요청의 Refresh token이 유효하면 해당 사용자임을 간주 가능
* JWT 저장 DB : Redis
  * JWT는 간단하고 빠른 조회 위해 no-sql, Non-Relational DB 사용
  * Redis에 Key:Value 형태의 Dictionary로 저장하면 값 서칭도 빠름
  * JWT 저장 시 Key, Value, TTL(Time to live=expiry) 필요 - Redis는 expiry가 지나면 자동으로 해당 Key:Value 제거
* Token 유효 여부 판단 기준
  * token을 서버로 보내면 서버는 JWT 라이브러리 복호화 로직을 사용하여 Signature의 Serect 확인
  * 복호화한 데이터가 서버 DB에 저장된 token 정보와 일치하는 지 비교하여 판단
* 1가지의 Token만 사용 가능?
  * YES! but, expiry가 지나면 token은 사라짐
  * -> 따라서 사용자가 서비스 이용하던 도중 token expiry에 도달하면 문제 발생ㅇㅇ..
  * -> 그래서 위와 같이 Access Token이 expired되면 Refresh Token으로 Access Token을 재발급 받아 별도의 로그인 없이 다시 서비스 정상 이용 가능
  * -> Soooo 대게 처음 사용자 등록할 때, 2가지의 Token 모두 발급받아야하며 access token expiry < refresh token expiry를 만족하도록 설정!
<br/>
<br/>

### JWT 장점
* 이미 토큰 자체가 인증된 정보이기에 세션 저장소와 같은 별도의 인증 저장소 필요 X
* 분산 마이크로 서비스 환경에서 중앙 집중식 인증 서버 + 데이터베이스에 의존하지 않는 쉬운 인증 및 인가 방법 제공
* 서버가 클라이언트의 상태를 저장해두지 않아도 됨
* Signature를 공통키&개인키 암호화를 통해 막아두었기에 데이터 보완성 ↑
* 타서비스에서 이용할 수 있는 공통적인 스펙 사용 가능
* URL 파라미터와 헤더로 사용
* 최신 웹 서버 하드웨어에서의 쉬운 확장
* 트래픽에 대한 부담 ↓
* 용이한 디버깅 및 관리
* REST 서비스로 제공 가능
* 만료 시점이 내장되어있음
* 독립적인 JWT <br/>
**=> stateful해야 하는 세션의 단점을 보완하기 위해 만들어진 JWT는 별도의 세션 저장소를 강요하지 않기 때문에 stateless하여 확장성이 뛰어나고, signature를 통한 보안성까지 갖추고 있음**
<br/>
<br/>

### JWT 단점
* Statelss 애플리케이션에서 토큰은 대다수의 요청에 대해 전송되므로 데이터 트래픽 크기에 영향
  * base64 인코딩을 통한 정보 전달이므로 전달량이 많아 네트워크 전달 시, 많은 데이터양으로 부하 발생 가능
* JWT의 3가지 구성 요소인 Header, Payload, Signature에서 Signature를 제외한 부분은 누구나 열람 가능
  * Payload에 암호화가 되어있지 않아 민감 정보 저장 불가
* 토큰은 클라이언트에 저장되어있어 데이터베이스에서 사용자 정보를 조작하더라도 토큰에 직접 적용 불가
* 더 많은 필드 추가 시, 토큰이 커질 수 있음
* **토큰을 탈취당하는 경우, 만료될 때까지 대처 불가능**
  * 해결법?? : exp(Expiration Time=만료시간)를 짧게 가져가자 !
  * 토큰 탈취 시, 서버가 이를 강제로 끊지 못하기 때문에 유효 시간을 짧게 두어 최소한의 보안성과 안정성을 보장
  * but, 서비스 사용 주체인 사용자 입장에서는 짧은 시간마다 로그인을 다시 해야하기 때문에 불편이 발생함으로 아래와 같은 방법들로 보완함!<br/>
    > Sliding Session
    > * 특정 서비스를 계속 사용하고 있는 특정 유저에 대해 만료 시간을 연장시키는 방법
    > * 유저가 특정 action(글쓰기, 결제..)을 하였을 때, 새롭게 exp를 늘린 JWT를 다시 제공함으로써 만료시간을 연장하여 보완
    > * but, 단발성 접속 시에는 Sliding Session으로 연장 불가+너무 긴 Access token을 발급시킨 경우엔 Sliding Session 때문에 무한정 사용하는 상황 발생<br/>
  
    > Refresh Token
    > * JWT를 처음 발급할 때 Access Token과 함께 Refresh Token을 발급하여 짧은 만료 시간 해결
    > * 가장 많이 사용하는 방법
    > * 비교적 긴 시간(7일, 30일 등)의 만료 시간을 가지고 있어 Access Token을 Refresh해주는 것을 보장하는 토큰
    > * 클라이언트가 스스로 혹은 서버로부터 Access Token가 만료됨을 알게되었을 때, Refresh Token으로 서버에서 새로운 Access Token을 발급하도록 요청하여 발급받는 방식
<br/>
<br/>

### JWT 활용
* 회원 인증
  * JWT가 사용되는 가장 흔한 경우
  * 사용자가 로그인 시, 서버는 사용자의 정보를 기반으로한 토큰 발급<br/>
    그 후, 사용자는 서버에 요청을 할 때마다 JWT를 포함하여 전달<br/>
    서버는 클라이언트에서 요청을 받을 때마다 해당 토큰이 유효하고 인증됐는지 검증하고, 사용자가 요청한 작업에 권한이 있는지 확인하여 작업을 처리
  * 서버에서는 사용자에 대한 세션을 유지 및 관리할 필요 X
  * 사용자의 로그인 여부 상관X -> 사용자가 요청을 했을 때 토큰만 확인하면 되므로 서버의 자원과 비용 절감 가능
* 정보 교류
  * JWT는 두 개체 사이에서 안정성있게 정보를 교환하기에 적합한 방법
  * 정보가 서명이 되어있기 때문에 정보를 보낸 이가 바뀌진 않았는지, 정보가 도중에 조작되지는 않았는지를 검증 가능
* JWT 예제
  * [In iris](https://velog.io/@vamos_eon/JWT%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-%EC%98%88%EC%A0%9C4)<br/>
  * [In Spring](https://brunch.co.kr/@jinyoungchoi95/1)<br/>
<br/>
<br/>

## 참고
[JWT ( JSON WEB TOKEN ) 이란?](http://www.opennaru.com/opennaru-blog/jwt-json-web-token/)<br/>
[JWT란 무엇인가? 그리고 어떻게 사용하는가? (1) - 개념](https://velog.io/@vamos_eon/JWT%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-1)<br/>
[JWT란 무엇인가? 그리고 어떻게 사용하는가? (2) - 사용처](https://velog.io/@vamos_eon/JWT%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-%EC%82%AC%EC%9A%A9%EC%B2%982)<br/>
[JWT란 무엇인가? 그리고 어떻게 사용하는가? (3) - 보안](https://velog.io/@vamos_eon/JWT%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-%EB%B3%B4%EC%95%883)<br/>
[JWT란 무엇인가? 그리고 어떻게 사용하는가? (4) - 예제](https://velog.io/@vamos_eon/JWT%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-%EC%98%88%EC%A0%9C4)<br/>
[JWT(JSON WEB TOKEN) 알아가기](https://brunch.co.kr/@jinyoungchoi95/1)<br/>

