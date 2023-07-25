## 웹소켓이란?
서버와 클라이언트 간의 메시지 교환을 위한 **통신 규약(프로토콜)**

- 기존의 단방향 HTTP 프로토콜과 호환되어 양방향 통신을 제공하기 위해 개발
- 접속까지는 HTTP 프로토콜을 이용하고, 그 이후 통신은 자체적인 WebSocket 프로토콜로 통신 ([ws://~]로 시작)


## HTTP 통신 방식
- 웹소켓이 나오기 이전에 사용하던 실시간성 보장 기법들
1. `Polling`
	일정한 주기로 서버에 요청(Request)을 보내는 방법.
	- 불필요한 Request 와 Connection을 생성하여 서버에 부담
   - '일정 주기마다' 요청을 보내는 것이기 때문에 실시간이라고 보기에 애매
2. `Long-Polling`
	요청을 보냈을 때, 서버가 응답을 바로 보내지 않고 특정 이벤트나 타임아웃이 발생했을 때 응답을 전달하는 방식.
	- polling보다 서버의 부담 적음
	- 클라이언트로 보내는 이벤트들의 시간간격이 좁다면 polling과 별 차이 없음.
	- 다수의 클라이언트에게 동시에 이벤트가 발생될 경우 서버의 부담이 급증한다.
	
3. `Streaming`
이벤트가 발생했을 때 응답을 내려주되, 응답을 완료시키지 않고 계속 연결을 유지하는 방식.

  - Long Polling에 비해 응답마다 다시 요청을 하지 않아도 되므로 효율적이지만, 연결 시간이 길어질 수록 연결 유효성 관리의 부담이 발생  
 - HTTP 통신을 하기 때문에 Request, Response 헤더가 불필요하게 크다.


## 웹 소켓의 특징
1. 양방향 통신 (Full-Duplex)

   데이터 송수신을 동시에 처리할 수 있는 방법.  
통상적인 HTTP 통신은 client 가 요청을 보내는 경우에만 Server가 응답을 하는 단방향 통신이지만,  웹 소켓은 양방향 통신이 가능. 

2. 실시간 네트워킹 (Real Time Networking)
웹 환경에서 연속된 데이터를 빠르게 노출
ex. 채팅, 주식


## 웹소켓 동작과정
![](https://blog.kakaocdn.net/dn/ZNU2Y/btrpmgSbZSw/N2VCWDRjtSZito6ZZwRNt0/img.png)
1. **Opening Handshake**  
-   웹소켓 연결은 `HTTP프로토콜`을 통해 시작. 이때 일반적인 HTTP 요청과는 다른 웹소켓 연결 요청 헤더를 포함함
-   웹소켓 서버는 클라이언트의 연결 요청을 받으면 핸드쉐이크 과정을 통해 웹소켓 연결을 수립
- 핸드쉐이크가 성공하면 클라이언트와 서버 사이에 양방향 연결(TCP/IP기반) 설정
2. **Data transfer**  
-   클라이언트 또는 서버는 언제든지 데이터를 보낼 수 있으며, 상대방은 해당 데이터를 즉시 수신할 수 있습니다.
3. **Closing Handshake**
- 일정 시간이 지나면 HTTP연결은 자동으로 끊어짐

 
웹소켓 연결 시, HTTP Request를 그대로 사용하기 때문에 기존의 80, 443 포트로 접속을 하므로 추가 방화벽을 열지 않고도 양방향 통신이 가능하고, HTTP 규격인 CORS 적용이나 인증 등 과정을 기존과 동일하게 가져갈 수 있는 것이 장점

접속 요청은 HTTP 로 한 뒤, 웹소켓 프로토콜로 변경된다. (WS)
ex) `ws://localhost:8080/chat





<br>

[참고]
https://doozi0316.tistory.com/entry/WebSocket%EC%9D%B4%EB%9E%80-%EA%B0%9C%EB%85%90%EA%B3%BC-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95-socketio-Polling-Streaming
https://yuricoding.tistory.com/134