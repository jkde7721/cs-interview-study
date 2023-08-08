# PCB (Process Control Block) & Context Switching

<br/>

## 프로세스 스케줄링

> 운영체제에서 CPU를 사용할 수 있는 프로세스를 선택하고, CPU를 할당하는 작업

- 프로세스의 우선순위, 작업량 등을 고려하여 효율적으로 배치하여, 이를 통해 운영체제는 CPU를 효율적으로 사용하며 시스템 전반적인 성능을 향상시킴<br/>
  &rarr; 스케줄링은 멀티 태스킹 작업을 만들어내는 데에 있어서 핵심적인 부분

- 스케줄링은 운영체제의 특징과 시스템 요구사항에 따라 다양한 알고리즘 방식으로 동작

    <br/>

  **< 알고리즘 종류 >**
  :arrow_right: FCFS(First-Come, First-Served), SJF(Shortest-Job-First), Priority, RR(Round-Robin), Multilevel Queue 등

<br/>

## 프로세스 상태

> 프로세스가 실행되는 동안 변경되는 고유 상태를 의미

| 프로세스 상태     | 설명                                                                                                                                                |
| ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- |
| 생성 (new)        | 프로세스가 생성되고 아직 준비가 되지 않은 상태                                                                                                      |
| 준비 (ready)      | 프로세스가 실행을 위해 기다리는 상태<br/>CPU를 할당받을 수 있는 상태이며, 언제든지 실행될 준비가 되어있음                                           |
| 실행 (running)    | 프로세스가 CPU를 할당받아 실행되는 상태                                                                                                             |
| 대기 (waiting)    | 프로세스가 특정 이벤트(입출력 요청 등)가 발생하여 대기하는 상태<br/>CPU를 할당받지 못하며, 이벤트가 발생하여 다시 ready 상태로 전환될 때까지 대기함 |
| 종료 (terminated) | 프로세스가 실행을 완료하고 종료된 상태<br/>더 이상 실행될 수 없으며, 메모리에서 제거되게 됨                                                         |

<br/>

## 프로세스 상태 전이

> 프로세스가 실행되는 동안 상태가 OS에 의해 변경되는 것을 의미

- 운영체제는 프로세스의 상태를 감시하고, 프로세스 상태를 기반으로 프로세스 스케줄링을 통해 프로세스를 관리하고 제어함

![enter image description here](https://dejavuhyo.github.io/assets/img/2021-07-05-process/process-state-transitions.png)

① **admit (new &rarr; ready)** : 프로세스 생성을 승인 받음<br/>
② **dispatch (ready &rarr; running)** : 준비 상태에 있는 여러 프로세스들 중 하나가 스케줄러에 의해 실행됨<br/>
③ **interrupt (running &rarr; ready)** : Timeout, 예기치 않은 이벤트가 발생하여 현재 실행 중인 프로세스를 준비 상태로 전환하고 해당 작업을 먼저 처리<br/>
④ **I/O or event wait (running &rarr; waiting)** : 실행 중인 프로세스가 입출력이나 이벤트를 처리해야 하는 경우, 입출력이나 이벤트가 끝날 때까지 대기 상태로 전환<br/>
⑤ **I/O or event completion (waiting &rarr; ready)** : 입출력이나 이벤트가 모두 끝난 프로세스를 다시 준비 상태로 만들어 스케줄러에 의해 선택될 수 있는 상태로 전환

<br/>

## PCB (Process Control Block)

> 운영체제가 프로세스를 제어하기 위해 정보를 저장해 놓는 곳으로 프로세스의 상태 정보를 저장하는 구조체

- 프로세스의 상태 관리와 문맥 교환 (Context Switching)을 위해서 필요

:point_right: 프로세스를 context switching할 때 기존 프로세스의 상태를 어딘가에 저장해둬야 다음에 똑같은 작업을 이어서 할 수 있을 것이고, 새로 해야 할 작업의 상태 또한 알아야 어디서부터 다시 작업을 시작할지 결정할 수 있을 것

:bulb: PCB는 프로세스 스케줄링을 위해 프로세스에 관한 모든 정보를 저장하는 **임시 저장소**

- 프로세스가 생성될 때마다 고유의 PCB가 생성되며, 프로세스가 완료되면 PCB는 제거

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/VXBmD/btrtADpaHoj/vDizu05LeysOIGF06Sjd1K/img.png)

:pencil: **포인터 (pointer)** : 프로세스의 현재 위치를 저장하는 포인터 정보<br/>
:pencil: **프로세스 상태 (process state)** : 프로세스의 각 상태 - 생성(new), 준비(ready), 실행(running), 대기(waiting), 종료(terminated) 를 저장<br/>
:pencil: **프로세스 아이디 (process ID, PID, process number)** : 프로세스 식별자를 지정하는 고유한 ID<br/>
:pencil: **프로그램 카운터 (program counter)** : 프로세스를 위해 실행될 다음 명령어의 주소를 포함하는 카운터를 저장<br/>
:pencil: **레지스터 (register)** : 누산기, 베이스, 레지스터 및 범용 레지스터를 포함하는 CPU 레지스터에 있는 정보<br/>
:pencil: **메모리 제한 (memory limits)** : 운영 체제에서 사용하는 메모리 관리 시스템에 대한 정보<br/>
:pencil: **열린 파일 목록 (list of open file)** : 프로세스를 위해 열린 파일 목록<br/>

<br/>

## 문맥 교환 (Context Switching)

> CPU가 현재 작업중인 프로세스에서 다른 프로세스로 넘어갈 때, 이전의 프로세스 정보를 PCB에 저장하고 새롭게 실행할 프로세스의 정보를 PCB에서 읽어와 레지스터에 적재하는 과정

**Context**란? : CPU가 프로세스를 실행시키기 위해 필요한 정보들

- 프로세스의 데이터, CPU 레지스터 값

<br/>

### Context Switching이 필요한 이유

:arrow_right: CPU는 한 번에 하나의 프로세스만 수행할 수 있지만 실생활에서 우리는 여러 개의 프로세스를 동시에 수행하려고 한다.<br/>
:arrow_right: CPU는 동시에 수행하는 것처럼 보이게 하기 위해 여러 개의 프로세스를 번갈아가며 수행하고 이렇게 CPU가 프로세스를 바꿔가며 실행하기 위해서는 문맥 교환이 필요하게 되었다.

<br/>

### Context Switching 과정

![enter image description here](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/bGhXBX/btrtzagzfEU/01ovKkEQaozwLgEQUjTyx1/img.png)

① CPU는 Process P1을 실행 (Executing) <br/>
② 일정 시간이 지나 Interrupt 또는 system call이 발생 (CPU는 idle 상태)<br/>
③ 현재 실행 중인 Process P1의 상태를 PCB1에 저장 (Save state into PCB1)<br/>
④ 다음으로 실행할 Process P2를 선택 (CPU 스케줄링)<br/>
⑤ Process P2의 상태를 PCB2에서 불러옴 (Reload state from PCB2)<br/>
⑥ CPU는 Process P2를 실행 (Executing)<br/>
⑦ 일정 시간이 지나 Interrupt 또는 system call이 발생 (CPU는 idle 상태)<br/>
⑧ 현재 실행 중인 Process P2의 상태를 PCB2에 저장 (Save state into PCB2)<br/>
⑨ 다시 Process P1을 실행할 차례가 됨 (CPU 스케줄링)<br/>
⑩ Process P1의 상태를 PCB1에서 불러옴 (Reload state from PCB1) <br/>
⑪ CPU는 Process P1을 중간 시점부터 실행 (Executing)

<br/>

### Context Switching은 언제 발생?

- CPU 스케줄링에 의해 할당된 작업 시간이 끝나 timeout이 발생했을 때 발생
- 프로세스의 작업이 끝났을 때 발생
- 실행 중이던 프로세스가 입출력 요청을 할 때 발생

<br/>

### Context Switching Overhead

- Context Switching 과정은 사용자로금 빠른 반응성과 동시성을 제공하지만, 실행되는 프로세스의 변경 과정에서 프로세스의 상태, 레지스터 값 등이 저장되고 불러오는 등의 작업이 수행되기 때문에 시스템에 많은 부담을 줌

#### Context Switching Overhead 발생

:bookmark_tabs: PCB 저장 및 복원 비용<br/>
:bookmark_tabs: CPU 캐시 메모리 무효화에 따른 비용<br/>
:bookmark_tabs: 프로세스 스케줄링 비용<br/>

<br/>

### 참고

[[OS] 프로세스 스케줄링](https://inpa.tistory.com/357#%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4__%EC%8A%A4%EB%A0%88%EB%93%9C%EC%9D%98_%EC%83%9D%EB%AA%85_%EC%A3%BC%EA%B8%B0)

[[OS] PCB와 Context Switching](https://m.blog.naver.com/adamdoha/222019884898)

[[OS] PCB(Process Control Block)와 문맥 교환(Context Switching)](https://junsangkwon.tistory.com/45)
