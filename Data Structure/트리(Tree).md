# 트리(Tree)
### 트리란?
* 방향 그래프의 일종
* 아래와 같은 제약 존재
  * 루트 정점을 제외한 모든 정점은 반드시 하나의 부모 정점을 가짐
  * 정점이 N개인 트리는 반드시 N-1개의 간선을 가짐
  * 하나의 부모 정점을 가지기 때문에, 루트에서 특정 정점으로 가는 경로는 반드시 하나만 존재
* 트리 용어
  * Root(루트) : 가장 상위의 정점
  * Leaf Node(리프노드) : 더 이상 자식이 없는 정점
  * Level(레벨) : 루트로부터 몇 번째 깊이인지 표현
  * Degree : 한 정점에서 뻗어나가는 정점의 수 <br/>
  <img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F5817e4c3-0c5c-4254-8655-cb5ac01f5050%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(2).png"><br/>
<br/>

### 이진 트리
* = 각 정점이 최대 2개의 자식을 갖는 트리
* 탐색을 위한 알고리즘에서 사용 多
* 정점이 n개인 포화 또는 완전 이진 트리의 높이는 log n.
* 높이가 h인 포화 이진 트리는 2^h-1개의 정점 가짐
* 편향 트리는 정점이 n개일 때, 높이가 n이 될 수 있음 (이진 트리 중 최악의 경우임)
* 일반적으로 이진 트리를 사용하는 경우는 적으며 응용을 자주 함 -> 이진 탐색 트리, 힙, AVL 트리 등 구현에 사용됨 <br/>

1. 완전 이진 트리
   * = 마지막 레벨을 제외하고 모든 정점이 채워져 있는 트리
   * 순서대로 들어가야만 완전 이진 트리 - 아래 그림에서 만약 F가 C의 right에 연결되어있다면 완전 이진 X <br/>
    <img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F6713c930-5039-4957-a34c-2eea230a4a5c%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(4).png"><br/>
    
2. 포화 이진 트리
   * = 마지막 레벨도 모두 채워져 있는 트리<br/>
    <img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F8d387fe6-2e0d-4154-923a-ea8ee2dde892%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(3).png"><br/>
    
3. 편향 트리
   * = 한 방향으로만 정점이 이어지는 트리<br/>
    <img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F6de5e48c-80ce-4466-968f-b621912f591e%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(5).png"><br/>
<br/>

### 트리 구현 방법
* 트리는 그래프의 한 종류이므로 인접행렬, 인접리스트 2가지 방식으로 트리 표현 가능
* 일반적인 트리 구현 방법 == 그래프 구현 방법
* 이진 트리의 경우 배열 혹은 링크가 2개 존재하는 연결리스트로 구현
  * 배열로 구현할 경우, 인덱스 값을 /2 또는 *2 를 통해 간단히 자식 및 부모 노드의 인덱스 구하기 가능
  * ex) 현재 나의 인덱스 = index, 부모의 인덱스 = Math.floor(index/2), Left자식의 인덱스 = index*2, Right자식의 인덱스 = index *2+1
<br/>

## 참고
[TIL 6 | 자료구조 - 그래프, 트리, 힙, 트라이](https://velog.io/@grighth12/TIL-6-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%9E%98%ED%94%84-%ED%8A%B8%EB%A6%AC-%ED%9E%99-%ED%8A%B8%EB%9D%BC%EC%9D%B4)
