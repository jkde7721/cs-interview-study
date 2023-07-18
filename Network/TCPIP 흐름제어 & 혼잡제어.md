## TCP/IP 모델

OSI 참조 모델은 말그대로 참조 모델일 뿐, 인터넷 프로토콜 스택(Internet Protocol Stack)은 현재 대부분 `TCP/IP`를 따른다.

TCP/IP는 인터넷 프로토콜 중 가장 중요한 역할을 하는  `TCP`와  `IP`의 합성어로 데이터의 흐름 관리, 정확성 확인, 패킷의 목적지 보장을 담당한다.  

 - TCP : 데이터의 정확성 확인
 - IP : 패킷을 목적지까지 전송하는 일

<br> 

## TCP (Transmission Control Protocol)의 특징

패킷 교환 방식 네트워크에서  **패킷들이 안전하게 이동할 수 있도록 보장**해주는 프로토콜

 - 흐름제어 수행
 - 혼잡제어 수행 

<br>

## 흐름제어란?

-   **송신측과 수신측의 데이터 처리 속도 차이를 해결하기 위한 기법**
- **수신측이 너무 많은 패킷을 수신받지 않도록 하기 위함** 

ex)  패킷을 수신받는 버퍼의 크기가 정해져있음.
-> 송신측의 전송 속도가 너무 빨라 수많은 패킷을 수신 받는 경우, **버퍼가 가득차 손실되는 패킷들이 발생**

따라서, CP 에서는  **흐름제어 기법을 사용**하여, 송신측의 처리 속도가 더 빠를 경우를 제어

<br>

> 흐름제어의 기본 개념은, **수신측이 송신측에게 자신의 상태를 계속하여 알리는 것**이다. 즉, 데이터를 더 받을 준비가 되어있다는 피드백이 이루어졌을 때 송신측에서 패킷을 이어서 보내도록 하는 것이다.

<br> 

## 패킷 전송 과정

1.  **Application**  Layer : 송신측 Application Layer 가 소켓에 데이터를 입력
2.  **Transport**  Layer : 데이터를 세그먼트로 감싸고 Network Layer 에 전달
    
3.  **수신측 노드**로 세그먼트가  **전송**됨. 동시에  **송신측의 Send Buffer 와 수신측의 Receive Buffer 각각에 데이터가 저장**됨.
    
4.  **수신측 Application Layer**  에서 준비가 되면, R**eceive Buffer 에 있는 데이터를 읽기**  시작함
    
5.  **따라서, Receive Buffer 가 넘쳐나지 않도록 하는 것이 흐름 제어의 핵심!**
    
    → 이를 위해 RWND(Receive Window, Receive Buffer 의 남은 공간) 을 송신측에 계속하여 피드백함

<br>

## 흐름제어 기법 종류

### 1) Stop-And-Wait

**매번 전송한 패킷에 대한 확인 응답을 받아야만 그 다음 패킷을 전송**하는 기법
-   이런한 구조 때문에 비효율적이다. (단점)
-   Give & Take

![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2Fd0d48e5e-55df-4936-8328-796fa8b53f56%2F263B7D4E5715ECEB32.png)

### 2) Sliding Window (Go-Back-n ARQ)

-   (송신측 = 전송측)
-   수신측에서 설정한 윈도우 크기만큼 송신측에서 확인 응답 없이 세그먼트를 전송할 수 있게 하여 데이터 흐름을 동적으로 조절하는 기법이다.
-   **`윈도우`** : 송신, 수신 스테이션 양쪽에서 만들어진 버퍼의 크기
-   Stop and Wait의 비효율성을 개선한 기법
-   송신측에서는 Ack 프레임을 수신하지 않더라도 여러 개의 프레임을 연속적으로 전송할 수 있다
-   송신측에서 0,1,2,3,4,5,6을 보낼 수 있는 프레임을 가지고 있고 데이터 0,1을 전송했다고 가정하면 슬라이딩 윈도우 구조는 2,3,4,5,6처럼 변하게 된다.
-   이때, 만약 수신측으로부터 ACK라는 프레임을 받게 된다면 송신측은 이전에 보낸 데이터 0,1을 수신측에서 정상적으로 받았음을 알게 되고 송신측의 슬라이딩 윈도우는 ACK 프레임에 따른 프레임의 수만큼 오른쪽으로 경계가 확장된다.
![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2F193e1e14-c3bb-4049-a07f-df0a2c9de843%2F253F7E485715ED5F27.png)

<br>

- TCP/IP 를 사용하는 모든 호스트들은 **송신 그리고 수신을 위한 2개의 Window** 를 가지고 있다. 
- 호스트들은 실제 데이터를 보내기 전에 3-Way Handshake 를 통해 연결 설정을 해줄 때 **수신 호스트의 Receive Window 크기에 자신의 Send Window 크기를 맞춰 설정**한다.

<br> 

### [ Sliding Window 세부동작 ]
1.  **송신 버퍼**

![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2F71089f91-675e-43dd-8874-0521a0a3c211%2F22532F485715EDF218.png)

-   200 이전 바이트는 전송 성공했고, 확인 응답을 받은 상태
-   그런데  **200~202 바이트는 아직 확인 응답을 받지 못한 상태**
-   203~211 바이트는 아직 전송이 되지 않은 상태

2.  **수신 윈도우**

![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2F239f4dbf-7e74-4b55-bf23-1a92e9abcf36%2F25403A485715EE362B.png)

  

3.  **송신 윈도우**

![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2Fb24a934c-a837-4ea3-aee5-f7165e871af5%2F2520244B5715EE6A14.png)

-   **수신 윈도우보다 작거나 같은 크기로 송신 윈도우를 지정**하여 흐름제어
4.  **송신 윈도우 슬라이딩 (이동)**

![](https://velog.velcdn.com/images%2Fhaero_kim%2Fpost%2Fb25f6bcf-0668-4ae0-af95-b6b951a3f9af%2F227DC8505715EEBA0A.png)

-   Before 에서  **203~204를 전송**하면 수신측에서는  **확인응답 203**을 보내고, 송신측은 이를 받아 After 상태와 같이 송신  **윈도우를 203~209로 이동**함
-   After 는  **205~209가 전송 가능한 상태**

<br>

## 혼잡제어 
 **`혼잡`** : 네트워크 내에 패킷의 수가 과도하게 증가하는 현상

혼잡 제어는  **혼잡 현상을 방지하고 제거하기 위한 기능**이다.

흐름 제어는 송신 측과 수신 측의 전송 속도를 다루고, 혼잡 제어는 라우터를 포함한 넓은 범위의 전송 문제를 다룬다.

![](https://blog.kakaocdn.net/dn/R86dS/btrgB57vx3w/AC4NZXEfNaNRQkYQEHA0nK/img.png)
  


#### **1) AIMD (Additive Increase / Multiplicative Decrease)**
 
-   합 증가 / 곱 감소 알고리즘이라고 한다
-   처음에 패킷 하나를 보내는 것으로 시작
-  전송한 패킷이 문제 없이 도착한다면 Window Size를 1씩 증가시키며 전송

  **< 혼잡 현상 발생 시 >** <br>
- 만약, 패킷 전송을 실패하거나 TIME_OUT이 발생하면 Window Size를 절반으로 감소
-   이 방식은 공평하다. *여러 호스트가 한 네트워크를 공유하고 있으면 나중에 진입하는 쪽이 처음에는 불리하지만, 시간이 흐르면 평형 상태로 수렴함
-   문제점은 초기 네트워크의 높은 대역폭을 사용하지 못하고 네트워크가 혼잡해지는 상황을 미리 감지하지 못하여 혼잡해지고 나서야 대역폭을 줄이는 방식 

#### **2) Slow Start (느린 시작)**

-   AIMD가 네트워크의 수용량 주변에서는 효율적으로 동작하지만, 처음에 전송 속도를 올리는 데 시간이 너무 길다는 단점 존재 
-   Slow Start는 AIMD와 마찬가지로 패킷을 하나씩 보내는 것부터 시작한
- 이 방식은 패킷이 문제 없이 도착하여 ACK 패킷마다 Window Size를 1씩 늘린다. 즉, 한 주기가 지나면 Window Size는 2배가 된다
-   따라서 그래프의 모양은 지수 함수 꼴

  **< 혼잡 현상 발생 시 >** <br>
-   혼잡 현상이 발생하면 Window Size를 1로 떨어뜨린다
-   처음에는 네트워크 수용량을 예상할 수 있는 정보가 없지만, 한 번 혼잡 현상이 발생한 후에는 네트워크의 수용량을 어느 정도 예상할 수 있다.
-   그래서 혼잡 현상이 발생하는 Window Size의 절반가지는 지수 함수 꼴로 Window Size를 증가시키고 그 이후에는 완만하게 1씩 증가시킨다.
  
 **2-1 )  2배씩 증가시키는 과정** 
  
  ![](https://blog.kakaocdn.net/dn/3HTVj/btqJlsUPCno/uKH303wx8XNrmCzPOmrtqk/img.png)
  

-   미리 정해진 임계값(threshold)에 도달할 때까지 Window Size를 2배씩 증가시킨다.
-   Slow Start라는 이름을 사용하지만, 매 전송마다 2배씩 증가하기 때문에 전송되어지는 데이터의 크기는 지수함수적으로 증가한다
-   전송되는 데이터의 크기가 임계 값에 도달하면 혼잡 회피 단계로 넘어간다

![](https://blog.kakaocdn.net/dn/blF3bg/btqJrxU3OD0/qvOhv5cJNsLuiuglXuuoo1/img.png)

**2-2) 혼잡 회피(Congestion Avoidance) 과정 **

-   Window Size가 임계 값에 도달한 이후에는 데이터의 손실이 발생할 확률이 높다
-   따라서 이를 회피하기 위해 **Window Size를 선형적으로 1씩 증가시키는 방법**이다.
-   수신측으로부터 일정 시간 동안까지 ACK를 수신하지 못하는 경우  
    * 타임 아웃의 발생 : 네트워크 혼잡이 발생했다고 인식  
    * 혼잡 상태로 인식된 경우  
    --> 윈도우의 크기 세그먼트의 수를 1로 감소시킨다.  
    --> 동시에 임계값을 패킷 손실이 발생했을 때의 윈도우 크기의 절반으로 줄인다

  
#### **3) Fast Retransmit (빠른 재전송)**

-   TCP는 지금가지 받은 데이터 중 연속되는 패킷의 마지막 순번 이후를 ACK 패킷에 실어서 보낸다.
-   그래서 송신 측이 아래처럼 3, 4번을 보내더라도 ACK 2 를 중복해서 받는다.
-   그러면 timeout이 발생하기 전이라도 송신 측은 문제가 되는 2번 패킷을 재전송한다.
-   그리고 혼잡한 상황이라고 판단해서 Window Size를 줄인다.
-   3 ACK Duplicated : 송신 측이 3번 이상 중복된 ACK 번호를 받은 상황

![](https://blog.kakaocdn.net/dn/cccQCT/btrgOtediVl/5EJfTnDqk5RWx1uiSgM8IK/img.png)

#### **4) Fast Recovery (빠른 회복)**

-   혼잡한 상태가 되면 Window Size를 1이 아니라 반으로 줄이고, 선형 증가시킨다.
-   혼잡 상황을 한번 겪은 이후로는 AIMD 방식으로 동작한다.




<br>

### 레퍼런스 
[https://rok93.tistory.com/entry/네트워크-TCP-흐름제어혼잡제어](https://rok93.tistory.com/entry/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-TCP-%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4%ED%98%BC%EC%9E%A1%EC%A0%9C%EC%96%B4)

https://velog.io/@haero_kim/TCP-%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4-%EA%B8%B0%EB%B2%95-%EC%82%B4%ED%8E%B4%EB%B3%B4%EA%B8%B0