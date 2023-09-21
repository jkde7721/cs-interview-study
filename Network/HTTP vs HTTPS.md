# HTTP vs HTTPS
## HTTP
### - HTTP란?
* = Hyper Text Transfer Protocol
* 클라이언트와 서버 간 통신을 위한 통신 규칙 세트 또는 프로토콜
* 텍스트 기반의 통신 규약으로 인터넷에서 데이터를 주고 받을 수 있는 프로토콜
* OSI 네트워크 통신 모델의 애플리케이션 계층 프로토콜
<br/>
  
### - HTTP 동작 과정
* 여러 유형의 요청과 응답을 정의함
* 서버와 클라이언트는 텍스트 형태로 데이터 교환 -> 네트워크 통신을 작동하게 하는 기본 기술
  
  > server : 어떠한 자료에 대한 접근을 관리하는 네트워크 상의 시스템 <br/>client : 그 자료에 접근할 수 있는 프로그램(ex. 웹 브라우저, 핸드폰 어플리케이션 ..)
* 사용자가 브라우저를 통해 서버에 HTTP 요청(request)을 전송하면 서버는 해당 요청사항에 맞는 결과를 찾아 사용자에게 HTTP 응답(response)
  * 요청 (Request) : client -> server
    
    * GET : 자료를 요청할 때 사용
    * POST: 자료의 생성을 요청할 때 사용
    * PUT : 자료의 수정을 요청할 때 사용
    * DELETE : 자료의 삭제를 요청할 때 사용
    * Request HTTP 메시지 구조
      
      ```
      GET https://velog.io/@surim014 HTTP/1.1				 // 시작줄
      User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) ...	 // 헤더
      Upgrade-Insecure-Requests: 1

      ... 어저쿵 저저쿵 데이터들 ...                                   // 본문
      ```  
      1. 시작줄 (첫 줄) : 'HTTP Method/사이트 주소/HTTP 버전'으로 구성
      2. 헤더 (두번째 줄~) : 요청에 대한 정보를 담고 있는 부분. User-Agent, Upgrade-Insecure-Requests 등이 해당되며 헤더의 종류는 매우 많음
      3. 본문 (헤더에서 한 줄 띄고~) : 요청 시, 함께 보낼 데이터를 담고 있는 부분
  * 응답 (Response) : server -> client

    * 1XX (조건부 응답) : 요청을 받았으며 작업을 계속함
    * 2XX (성공) : 클라이언트가 요청한 동작을 수신받아 이해했고 승낙하여 성공적으로 처리했음
    * 3XX (리다이렉션 완료) : 클라이언트는 요청을 마치기 위해 추가 동작을 취해야 함
    * 4XX (요청 오류) : 클라이언트에 오류가 있음
    * 5XX (서버 오류) : 서버가 유효한 요청을 명백하게 수행하지 못했음
    * Response HTTP 메시지 구조

      ```
      HTTP/1.1 200 OK														// 시작줄
      Connection: keep-alive												 // 헤더
      Content-Encoding: gzip												 
      Content-Length: 35653
      Content-Type: text/html;

      <!DOCTYPE html><html lang="ko" data-reactroot=""><head><title...  // 본문
      ```
      1. 시작줄 (첫 줄) : '버전/상태코드/상태메시지'로 구성. 200은 성공적인 요청이라는 뜻.
      2. 헤더 (두번째 줄~) : 응답에 대한 정보를 담고 있는 부분
      3. 본문 (헤더에서 한 줄 띄고~) : 요청한 데이터를 담고 있는 부분. HTML이 담아서 보냄.
<br/>

### - HTTP 특징
* HTTP 메시지는 HTTP서버와 HTTP클라이언트에 의해 해석됨
* TCP/IP를 이용하는 응용 프로토콜
* 비연결성 프로토콜 - 연결 상태를 유지하지 않는 단점 해결 위해 Cookie와 Session 등장
* 연결 상태를 유지하지 않기 때문에 요청/응답 방식으로 동작
<br/>
<br/>

---
## HTTPS
### - HTTPS란?
* = Hyper Text Transfer Protocol Secure
* HTTP에 데이터 암호화가 추가된 프로토콜 - 네트워크 상에서 제3자가 중간에서 정보를 볼 수 없음
<br/>

### - HTTPS 동작 과정
* HTTPS 연결 과정(Hand-Shaking)
  1. 클라이언트(브라우저)가 서버로 최초 연결 시도. 서버의 SSL 인증서를 요청하여 사이트의 신뢰성 검증 시도
  2. 서버는 공개키(SSL 인증서)를 클라이언트에게 넘겨줌
  3. 클라이언트는 인증서의 유효성을 검사하고 세션키 발급
  4. 클라이언트는 세션키 보관 + 세션키를 서버의 공개키로 암호화하여 서버로 전송
  5. 서버는 암호화된 세션키를 개인키로 복호화하여 세션키를 얻음
  6. 클라이언트와 서버는 동일한 세션키를 공유하므로 데이터 전달 시, 세션키로 암호화&복호화 진행
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcCodLU%2FbtrqRZnoOFq%2Fe6kFHjADoVby70466Jkq51%2Fimg.png"><br>
* HTTPS 연결 과정(Hand-Shaking)에서 대칭키&비대칭키 암호화 모두 사용
  * **대칭키 암호화**
    
    * 클라이언트와 서버가 동일한 키를 사용하여 암호화와 복호화 진행
    * 연산 속도 빠름
    * 키가 노출되면 매우 위험
  * **비대칭키 암호화**

    * 1개의 쌍으로 구성된 공개키와 개인키를 사용하여 암호화와 복호화 진행
    * 공개키 : 모두에게 공개 가능한 키 <br/>
      공개키 암호화 : 공개키로 암호화 시, 개인키로만 복호화 가능 -> 개인키는 나만 보기 가능
    * 개인키 : 나만 가지고 알고 있는 키 <br/>
      개인키 암호화 : 개인키로 암호화 시, 공개키로만 복호화 가능 -> 공개키는 모두에게 공개되어 있어, 내가 인증한 정보임을 알려 신뢰성 보장 <br/>
    * 키가 노출되어도 비교적 안전
    * 연산 속도 느림
    * <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOKcog%2FbtqK71fM8a4%2Fg1HmcDOR7MVRRz7pSKKJWk%2Fimg.png"><br/>
  * 먼저 서버와 클라이언트 간에 세션키(= 주고 받는 데이터 암호화에 사용되는 대칭키) 교환 <br/>
    비대칭키 : 처음 연결을 성립한 후에 안전하게 세션키를 공유하기 위해 비대칭키 사용됨 <br/>
    대칭키 : 이후, 데이터를 교환하는 과정에서의 빠른 연산 속도를 위해 대칭키 사용됨
<br/>


### - HTTPS 발급 과정
* 서버는 클라이언트와 세션키를 공유하기 위한 공개키(비대칭키)를 생성해야 함
* 발급 과정
  * A기업은 HTTP 기반의 애플리케이션에 HTTPS를 적용하기 위해 공개키/개인키를 발급
  * CA기업에게 돈을 지불하고, 공개키를 저장하는 인증서 발급을 요청
  * CA기업은 CA기업의 이름, 서버의 공개키, 서버의 정보 등을 기반으로 인증서를 생성하고, CA기업의 개인키로 암호화하여 A기업에게 이를 제공
  * A기업은 클라이언트에게 암호화된 인증서를 제공
  * 브라우저는 CA기업의 공개키를 미리 다운받아 갖고 있어, 암호화된 인증서를 복호화함
  * 암호화된 인증서를 복호화하여 얻은 A기업의 공개키로 세션키를 공유

    > 인증서는 신뢰성 확보 (CA기업의 개인키로 암호화되었기에) <br/>
      A기업만 복호화를 통해 원본 데이터 얻을 수 있음 (클라이언트는 A기업의 공개키로 데이터를 암호화였기 때문) <br/>
      인증서 == A기업의 공개키
<br/>

---
## HTTP vs HTTPS
### - 차이점
* **HTTP**
1. 데이터 보안 취약 : 일반 텍스트 형태로 데이터 교환
2. 검색 엔진에서의 낮은 신뢰성
3. 오늘날에는 큰 차이 없지만 비교적 빠른 속도
4. WHEN ? 노출되어도 괜찮은 단순한 정보 조회 등 만을 처리하는 경우

* **HTTPS**
1. 데이터 보안 강화 : 암호화된 형태로 모든 데이터 교환
2. 암호화와 복호화 과정 필요
3. 인증서 발급 및 유지 위한 추가 비용 발생 - 요즘은 무료 SSL인증서를 획득할 수 있는 출저 다양
4. WHEN ? 개인 및 금융 정보와 같은 민감한 데이터를 주고 받는 경우

* **HTTPS 선택 이유**
1. 보안 : 사용자가 민감한 데이터를 제출할 때, 제3자가 네트워크를 통해 해당 데이터 가로채기 불가능
2. 권위 : 검색 엔진에서 신뢰성이 더 높아 웹사이트 콘텐츠 순위가 더 높으며 사용자도 더 선호(URL옆 자물쇠 아이콘)
3. 성능 및 분석 : 더 빠른 로드 속도 / 우월한 참조 링크 추적 능력 / 정확한 트래픽 소스 식별 가능
<br/>

---
## 참고
[HTTP와 HTTPS의 차이점은 무엇인가요?](https://aws.amazon.com/ko/compare/the-difference-between-https-and-http/)<br/>
[[Web] HTTP와 HTTPS의 개념 및 차이점](https://mangkyu.tistory.com/98)<br/>
[HTTP vs HTTPS의 차이점을 알아보자](https://devjem.tistory.com/3)<br/>
[HTTP란 무엇인가?](https://velog.io/@surim014/HTTP%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)<br/>