## 1. 전송계층
`TCP`와  `UDP`는 TCP/IP의  `전송계층`에서 사용되는 프로토콜
*  **전송계층은 IP에 의해 전달되는 패킷의 오류를 검사하고 재전송 요구 등의 제어를 담당**하는 계층


## 2. TCP vs UDP
              TCP                 UDP
<img src = "https://madplay.github.io/img/post/2018-02-04-network-tcp-udp-tcpip-2.png" width = "200" ></img>   <img src = "https://madplay.github.io/img/post/2018-02-04-network-tcp-udp-tcpip-3.png" width = "200" ></img>

 TCP : 양방향 vs UDP : 단방향 (송신만 함)

- **신뢰성이 요구되는 애플리케이션에서는 TCP를 사용**
- **간단한 데이터를 빠른 속도로 전송하고자 하는 애플리케이션에서는 UDP를 사용**

![](https://image.slidesharecdn.com/tcp-150426214109-conversion-gate01/95/tcp-22-1024.jpg?cb=1430085440)

## 3. TCP vs UDP

**[ 공통점 ]**

1. 포트 번호를 이용하여 주소를 지정

2. 데이터 오류 검사를 위한 체크섬 존재

**[ 차이점 ]**

**TCP(Transfer Control Protocol)**

1. 연결이 성공해야 통신 가능(연결형 프로토콜)
2. 데이터의 경계를 구분하지 않음(Byte-Stream Service)
3. 신뢰성 있는 데이터 전송(데이터의 재전송 존재)
4. 일 대 일(Unicast) 통신

**UDP(User Datagram Protocol)**
1. 비연결형 프로토콜(연결 없이 통신이 가능)
2. 데이터의 경계를 구분함(Datagram Service)
3. 비신뢰성 있는 데이터 전송(데이터의 재전송 없음)
4. 일 대 일, 일 대 다(Broadcast), 다 대 다(Multicast) 통신


<br>

## [참고용] TCP (Transmission Control Protocol) 더 자세하게 알아보기 

장치들 사이에 논리적인 접속을 성립(establish)하기 위하여 연결을 설정하여  **신뢰성을 보장하는 연결형 서비스**  
연결된 컴퓨터에서 실행되는 프로그램 간에  **일련의 옥텟(데이터, 메세지, 세그먼트라는 블록 단위)를  `안정적`으로,  `순서대로`,  `에러없이`  교환**할 수 있게 함

**[ 특징 ]**
1. 연결형 서비스로 가상 회선 방식 제공
	
-   3-way handshaking -> 연결 설정
-  4-way handshaking -> 연결 해제.
2. 흐름제어(Flow control)

  - 데이터 처리 속도를 조절하여 수신자의 버퍼 오버플로우를 방지
	-   송신하는 곳에서 감당이 안되게 많은 데이터를 빠르게 보내 수신하는 곳에서 문제가 일어나는 것을 막음
	-   수신자가  `윈도우크기(Window Size)`  값을 통해 수신량을 정할 수 있음
3. 혼잡제어(Congestion control)
- 네트워크 내의 패킷 수가 넘치게 증가하지 않도록 방지
  -   정보의 소통량이 과다하면 패킷을 조금만 전송하여 혼잡 붕괴 현상이 일어나는 것을 막는다.
4. 신뢰성이 높은 전송(Reliable transmission)
-   Dupack-based retransmission
    -   정상적인 상황에서는 ACK 값이 연속적으로 전송되어야 함
    -   그러나 ACK값이 중복으로 올 경우 패킷 이상을 감지하고 재전송을 요청
-   Timeout-based retransmission
    -   일정시간동안 ACK 값이 수신을 못할 경우 재전송을 요청

5. 전이중, 점대점 방식
-   **전이중 (Full-Duplex)**  : 전송이 양방향으로 동시에 일어날 수 있음
-   **점대점 (Point to Point)** : 각 연결이 정확히 2개의 종단점을 가지고 있다.

=> 멀티캐스팅이나 브로드캐스팅을 지원하지 않음


**[ TCP Header 정보 ]**
![](https://nesoy.github.io/assets/posts/20181010/1.png)

응용 계층으로부터 데이터를 받은 TCP는 `헤더`를 추가한 후에 이를 IP로 보냄

1. `송수신자의 포트 번호`  (16bits)
- TCP로 연결되는 가상 회선 양단의 송수신 프로세스에 할당되는  **포트 주소** 
2. `시퀀스 번호(Sequence Number)` (32bits)
- 송신자가 지정하는 순서 번호,  **전송되는 바이트 수**를 기준으로 증가.  
- SYN = 1 : 초기 시퀀스 번호가 된다. ACK 번호는 이 값에 1을 더한 값.  
- SYN = 0 : 현재 세션의 이 세그먼트 데이터의 최초 바이트 값의 누적 시퀀스 번호 
3. `응답 번호(ACK Number)`  (32bits)
- 수신 프로세스가 제대로  **수신한 바이트의 수**를 응답하기 위해 사용
4. `데이터 오프셋(Data Offset)`  (4bits)
- TCP 세그먼트의 시작 위치를 기준으로  **데이터의 시작 위치**를 표현(TCP 헤더의 크기)
5. `예약 필드(Reserved)`  (6bits)
- 사용을 하지 않지만 나중을 위한 예약 필드이며 0으로 채워져야한다.
6. `제어 비트(Flag Bit)`  (6bits)
- SYN, ACK, FIN 등의 제어 번호 -> 아래 추가 설명 참조
7. `윈도우 크기(Window)` (16bits)
- **수신 윈도우의 버퍼 크기**를 지정할 때 사용. 0이면 송신 프로세스의 전송 중지
8. `체크섬(Checksum)` (16bits)
- TCP 세그먼트에 포함되는 프로토콜 헤더와  **데이터에 대한 오류 검출**  용도
9. `긴급 위치(Urgent Pointer)` (16bits)
- 긴급 데이터를 처리하기 위함, URG 플래그 비트가 지정된 경우에만 유효


**[ 제어 비트(Flag Bit) 정보 ]**

**1. ACK**
응답 번호 필드가 유효한지 설정. 
클라이언트가 보낸 최초의 SYN 패킷 이후에 전송되는 모든 패킷은 이 플래그가 설정되어야 함 
-   ACK는 송신측에 대하여  **수신측에서 긍정 응답**으로 보내지는 전송 제어용 캐릭터
    
-   ACK 번호를 사용하여 패킷이 도착했는지 확인한다.
    
    -> 송신한 패킷이 제대로 도착하지 않았으면  **재송신** 요구
    
**2. SYN**
연결 설정 요구. 동기화 시퀀스 번호. 양쪽이 보낸 최초의 패킷에만 이 플래그가 설정되어 있어야 한다.

**3. FIN**
더 이상 전송할 데이터가 없을 때 연결 종료 의사 표시

등등 

<br> 

## TCP의 연결 및 해제 과정

![](https://nesoy.github.io/assets/posts/20181010/2.png)


### TCP Connection (3-way handshake)

1.  먼저 open()을 실행한 클라이언트가  `SYN`을 보내고  `SYN_SENT`  상태로 대기한다.
2.  서버는  `SYN_RCVD`  상태로 바꾸고  `SYN`과 응답  `ACK`를 보낸다.
3.  `SYN`과 응답  `ACK`을 받은 클라이언트는  `ESTABLISHED`  상태로 변경하고 서버에게 응답  `ACK`를 보낸다.
4.  응답  `ACK`를 받은 서버는  `ESTABLISHED`  상태로 변경한다.

### TCP Disconnection (4-way handshake)

1.  먼저 close()를 실행한 클라이언트가 FIN을 보내고  `FIN_WAIT1`  상태로 대기한다.
2.  서버는  `CLOSE_WAIT`으로 바꾸고 응답 ACK를 전달한다. 동시에 해당 포트에 연결되어 있는 어플리케이션에게 close()를 요청한다.
3.  ACK를 받은 클라이언트는 상태를  `FIN_WAIT2`로 변경한다.
4.  close() 요청을 받은 서버 어플리케이션은 종료 프로세스를 진행하고  `FIN`을 클라이언트에 보내  `LAST_ACK`  상태로 바꾼다.
5.  FIN을 받은 클라이언트는 ACK를 서버에 다시 전송하고  `TIME_WAIT`으로 상태를 바꾼다.  `TIME_WAIT`에서 일정 시간이 지나면  `CLOSED`된다. ACK를 받은 서버도 포트를  `CLOSED`로 닫는다.

> #### 주의
> 
> -   반드시 서버만  `CLOSE_WAIT`  상태를 갖는 것은 아니다.
> -   서버가 먼저 종료하겠다고  `FIN`을 보낼 수 있고, 이런 경우 서버가  `FIN_WAIT1`  상태가 됩니다.
> -   누가 먼저  `close`를 요청하느냐에 따라 상태가 달라질 수 있다.

<br>

