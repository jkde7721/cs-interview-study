## TCP 3-way handshake & 4-way handshake
### TCP와 UDP
#### - TCP란?
* = Transmission Control Protocol
* 인터넷 상에서 데이터를 메세지의 형태로 보내기 위해 IP와 함께 사용하는 프로토콜
* 주로 IP와 함께 사용되며 IP는 배달을, TCP는 패킷의 추적 및 관리
* 신뢰적, 연결지향성 서비스
  * 3-way handshaking 통해 연결을 설정하고 4-way handshaking 통해 연결 해제
  * 데이터의 흐름 제어와 혼잡 제어 수행
  * 높은 신뢰성의 전송 보장
  * UDP보다 느린 속도
  * 전이중(Full-Duplex), 점대점(Point to Point) 방식
  <img src="https://velog.velcdn.com/images%2Faverycode%2Fpost%2F9bb2bc45-52aa-4444-a9de-243189dede20%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-07-17%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.18.14.png"><br/>
<br/>

#### - UDP란?
* = User Datagram Protocol
* 데이터를 데이터그램(패킷) 단위로 처리하는 프로토콜
* 연결을 설정하고 해제하는 과정 존재 X : 패킷에 순서 부여X 재조립X 흐름제어X 혼잡제어X
* 데이터를 서로 다른 경로로 독립 처리하는 프로토콜 : 할당되는 논리적인 경로가 없고 각각의 패킷은 다른 경로로 전송되어 패킷들이 독립적인 관계를 지님
* 비연결형 서비스
  * 정보를 주고 받을 때, 정보를 보내거나 받는다는 신호절차X
  * UDP 헤더의 CheckSum 필드를 통해 최소한의 오류만 검출
  * 낮은 신뢰성의 데이터 전송
  * TCP보다 빠른 속도(3번째 특징 때문)
  * 네트워크 부하 적음(3번째 특징 때문)
  * 연속성이 중요한 실시간 서비스(스트리밍)에 적합
  <img src="https://velog.velcdn.com/images%2Faverycode%2Fpost%2F6d3a5978-24b6-48a7-bd20-ac1d8bd3fee0%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-07-17%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.19.12.png"><br/>
<br/>

#### - TCP vs UDP
* 별도의 포트 주소 공간을 관리하므로 같은 포트 번호 사용해도 무방 -> 두 프로토콜에서 동일한 포트 번호를 할당해도 서로 다른 포트로 간주함

<img src="https://velog.velcdn.com/images%2Faverycode%2Fpost%2F7ced087c-a2f1-4a0a-a00e-c1c65391e30c%2Fimage.png"><br/>
<br/>
<br/>

***
### TCP 접속 기초 지식
#### - 포트(Port) 상태 정보
* CLOSED : 포트가 닫힌 상태
* LISTEN : 포트가 열린 상태로 연결 요청 대기 중
* SYN_RCV : SYNC 요청을 받고 상대방의 응답을 기다리는 중
* ESTABLISHED : 포트 연결 상태
<br/>

#### - 플래그 정보
* TCP Header에는 CONTROL BIT(플래그 비트, 6bit)가 존재하며, 각각의 bit는 "URG-ACK-PSH-RST-SYN-FIN"의 의미를 가짐

  * 즉, 해당 위치의 bit가 1이면 해당 패킷이 어떠한 내용을 담고 있는 패킷인지를 나타냄
* SYN(Synchronize Sequence Number) / 000010

  * 연결 요청
  * 시퀀스 번호를 랜덤으로 설정하여 세션을 설정 및 연결하는 데 사용
  * 초기에 시퀀스 번호를 전송함
* ACK(Acknowledgement) / 010000

  * 응답 확인
  * 패킷을 받았다는 것을 의미
  * Acknowledgement Number 필드가 유효한지를 나타냄
  * 보낸 시퀀스 번호에 TCP 계층에서의 길이 또는 양을 더한 것과 같은 값을 ACK에 포함하여 전송
    * 동기화 요청에 대한 답변 : 클라이언트의 '시퀀스 번호+1'을 하여 ACK로 돌려줌
  * 양단 프로세스가 쉬지 않고 데이터를 전송한다고 가정하면, 최초 연결 설정 과정에서 전송되는 첫 번째 세그먼트(트랜스포트 계층의 패킷)를 제외한 모든 세그먼트의 ACK 비트는 1로 지정된다고 생각하면 댐!
*FIN(Finish) / 000001

  * 연결 해제
  * 세션 연결을 종료시킬 때 사용되며, 더 이상 전송할 데이터가 없음을 의미
<br/>
<br/>

***
### 3-Way Handshake
#### - 3-Way Handshake란?
* = TCP 통신을 통해 데이터를 전송하기 위해 네트워크 연결을 설정하는 과정 (Connection Establish)
* 응용 프로그램이 데이터 전송 전, 정확한 전송을 보장하기 위해 상대 컴퓨터와 사전에 세션을 수립하는 과정
* 양쪽 모두 데이터 전송 준비가 되었음을 보장 -> 실제로 데이터 전달을 시작하기 전에 한 쪽이 다른 쪽이 준비되었다는 것을 알 수 있게 함
* 양쪽 모두 상대편에 대한 초기 순차일련변호를 얻을 수 있도록 함
<br/>

#### - 3-Way Handshake 기본 매커니즘
* TCP 통신은 PAR을 통해 신뢰적인 통신 제공 : PAR을 사용하는 기기는 ACK를 받을 때까지 데이터 유닛 재전송함!
  > PAR = Positive Acknowledgement with Re-transmission
* 수신자가 세그먼트(=데이터 유닛) 손상을 확인하면(트랜스퍼트 계층의 checkSum 활용) 해당 세그먼트 제거.<br/>
  송신자는 Positive ACK가 오지 않은 세그먼트를 다시 보내야함 <br/>
  -> 이 과정에서 클라이언트와 서버 사이에 아래와 같이 3개의 Segment가 교환됨 : 3-way handshake 기본 매커니즘

  > <img src="https://velog.velcdn.com/images%2Faverycode%2Fpost%2Fcd53e336-a624-4f8a-b7e5-20fe62eb6648%2Fimage.png"><br/>
  > * Client > Server : TCP SYN
  > * Server > Client : TCP SYN ACK
  > * Client > Server : TCP ACK
<br/>

#### - 3-Way Handshake 작동 방식
  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F225A964D52F1BB6917"><br/>
1. STEP 1 (SYN)
* = 클라이언트는 서버에 커넥션(접속)을 요청하는 SYN 패킷 보냄
* 송신자가 최초로 데이터를 전송할 때, 시퀀스 번호를 임의의 랜덤 숫자로 지정하고, SYN 플래그 비트를 1로 설정한 세그먼트를 전송
* PORT 상태
  * Client : (CLOSED ->)SYN_SENT (SYN/ACK 응답을 기다리는 상태)로 변함
  * Server : LISTEN
<br/>

2. STEP 2 (SYN + ACK)
* = 서버가 SYN 요청을 받고 클라이언트에게 ACK와 SYN flag (요청을 수락한다) 패킷을 발송
* 패킷 발송 후엔 클라이언트가 다시 ACK으로 응답하기를 기다림
* 접속 요청을 받은 서버가 요청을 수락했으며 접속 요청 프로세스인 클라이언트도 포트를 열어달라는 메세지를 전송(SYN-ACK singal bits set)
* ACK Numger 필드를 시퀀스번호+1 로 지정하고 SYN과 ACK 플래그 비트를 1로 설정한 세그먼트 전송
* PORT 상태
  * Client : CLOSED
  * Server : SYN_RCV
<br/>

3. STEP 3 (ACK)
* = 클라이언트는 서버의 응답(ACK+SYN 패킷)을 받고, 서버에게 ACK를 보냄
* 이후엔, 연결이 성사되고 데이터가 오가게 되는 것
* PORT 상태
  * Client : ESTABLISED
  * Server : (SYN_RCV => ACK =>)ESTABLISED
<br/>
<br/>

***
### 4-Way Handshake
#### - 4-Way Handshake란?
* = FIN 플래그를 이용하여 연결을 해제하는 과정 (Connection Termination)

  > FIN(finish) = 세션을 종료시키는 데 사용되며, 더 이상 보낸 데이터가 없음을 의미
* 해제(Termination) 종류
  * Graceful connection release (정상적인 연결 해제)
    
    * 정상적인 연결 해제는 서로 모두 커넥션을 닫을 때까지 양쪽 커넥션이 연결되어있음
    * 일반적으로 Client와 Server 모두 서로 연결 요청을 먼저 할 수 있기 때문에, 연결 요청을 먼저한 요청자를 Client로, 연결 요청을 받은 수신자를 Server쪽으로 생각하면 됨
  * Abrupt connection release (갑작스런 연결 해제)
    
    * 갑자기 한 TCP 엔티티가 연결을 강제로 닫는 경우
    * 한 사용자가 두 데이터 전송 방향을 모두 닫는 경우
    * RST(TCP reset) 세그먼트가 전송될 때
      
       * 존재하지 않는 TCP 연결에 대해 비SYN 세그먼트가 수신된 경우
       * 열린 커넥션에서 일부 TCP 구현은 잘못된 헤더가 있는 세그먼트가 수신된 경우
       * 일부 구현에서 기존 TCP 연결을 종료해야 하는 경우
    * 연결을 지원하는 리소스 부족할때
    * 원격 호스트에 연결할 수 없고 응답이 중지되었을때
<br/>

#### - 4-Way Handshake 작동 방식
  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2152353F52F1C02835"><br/>
1. STEP 1 (Client → Server : FIN(+ACK))
* = 서버와 클라이언트가 연결된 상태에서 클라이언트가 close() 호출하여 접속을 끊으려 서버에게 연결을 종료하겠다는 FIN 플래그를 전송
* FIN 패킷엔 실질적으로 ACK도 포함되어있음
<br/>

2. STEP 2 (Server → Client : ACK)
* = 서버는 FIN을 받고 ACK(확인메시지)를 보내고 자신의 통신이 끝날 때까지 기다림 (TIME_WAIT 상태)
  * 서버는 ACK Number 필드를 시퀀스넘버+1로 지정하고, ACK 플래그 비트를 1로 설정한 세그먼트를 전송
* 서버는 클라이언트에게 응답을 보내고 CLOSE_WAIT 상태로! 이 때, 아직 남은 데이터 전송을 마저 마친 후에 close() 호출
* 클라이언트는 서버에서 ACK를 받은 후 서버가 남은 데이터 처리를 끝내고 FIN 패킷을 보낼 때까지 대기 (FIN_WAIT_2)
<br/>

3. STEP 3 (Server → Client : FIN)
* = 데이터 처리 완료 후, 서버는 연결 종료에 합의한다는 의미로 클라이언트에게 FIN 플래그를 전송
* 이 후, 클라이언트가 승인 번호를 보내줄 때까지 대기 (LAST_ACK)
<br/>

4. STEP 4 (Client → Server : ACK)
* = 클라이언트는 FIN을 받고 ACK(확인했다는 메시지)를 서버에게 보냄
* 이 때, 아직 서버로부터 받지 못한 데이터가 있을 수 있으므로 대기 (TIME_WAIT)
  * 실질적인 종료 과정인 CLOSED에 들어감
  * TIME_WAIT는 의도치 않은 에러로 인해 연결이 데드락으로 빠지는 것을 방지
  * 만약 에러로 인해 종료가 지연되다가 타임이 초과되면 CLOSED로 들어감
<br/>

5. 서버는 ACK를 받은 후 소켓 닫음 (CLOSED)
<br/>

6. TIME_WAIT 시간이 끝나면 클라이언트도 닫음 (CLOSED)
<br/>
  <img src="https://velog.velcdn.com/images%2Faverycode%2Fpost%2F27fe1c27-49cc-40a0-acc0-bc1eef4dd6e4%2F%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-07-18%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%202.04.20.png"><br/>
<br/>

***
### 참고
[[ 네트워크 쉽게 이해하기 22편 ] TCP 3 Way-Handshake & 4 Way-Handshake](https://mindnet.tistory.com/entry/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-22%ED%8E%B8-TCP-3-WayHandshake-4-WayHandshake)<br/>
[[네트워크] TCP/UDP와 3 -Way Handshake & 4 -Way Handshake](https://velog.io/@averycode/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-TCPUDP%EC%99%80-3-Way-Handshake4-Way-Handshake)
