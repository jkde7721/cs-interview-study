# 트리(Tree)
### 트리란?
* 데이터의 각 요소들을 계층적으로 연관되도록 구조화시킬 때 사용하는 비선형 자료구조
* 데이터의 각 요소들의 단순한 나열X -> 부모-자식 관계의 계층적 구조O
* 방향 그래프의 일종으로 최소 연결 트리라고도 불림
* 노드와 간선으로 이루어진 자료구조
* 아래와 같은 규칙 및 제약 존재
  
  > * 트리는 하나의 루트 노드를 가짐
  > * 루트 노드는 0개 이상의 자식 노드를 가짐
  > * 각 노드들은 서로를 연결하는 간선으로 이어져있음
  > * 루트 노드을 제외한 모든 정점은 반드시 하나의 부모 노드를 가짐
  > * 노드가 N개인 트리는 반드시 N-1개의 간선을 가짐
  > * 하나의 부모 노드를 가지기 때문에, 루트에서 특정 노드(정점)로 가는 경로는 반드시 하나만 존재
* 트리에는 사이클 존재 불가 -> 임의의 두 노드간의 경로가 유일
  * 사이클이 없는 하나의 연결 그래프
  * 루트 노드에서 시작하여 같은 방향으로 다시 루트 노드에 도착 불가
  * DAG(방향성이 있는 비순화 그래프)의 한 종류 
<br/>
<br/>

### 용어
<img src="https://velog.velcdn.com/images%2Fcodenmh0822%2Fpost%2Fa12e3c90-5f33-4d71-8355-c3a0a3f17798%2Fimage.png"><br/>
* 루트 노드(root node) : 가장 상위의 정점
* 단말 노드(leaf node) : 더 이상의 자식이 없는 정점
* 내부 도드(internal node) : 단말 노드가 아닌 노드
* 간선(edge) : 노드를 연결하는 선. link, branch 라고도 불림ㅇㅇ
* 간선 수(degree) : 한 정점에서 뻗어나가는 정점의 수. 각 노드가 지닌 다음 레벨의 하위 가지의 수
* 형제(sibling): 같은 부모를 가지는 노드
* 노드의 크기(size): 자신을 포함한 모든 자손 노드의 개수
* 노드의 깊이(depth): 루트에서 어떤 노드에 도달하기 위해 거쳐야 하는 간선의 수
* 노드의 레벨(level): 루트로부터 몇 번째 깊이인지 표현
* 노드의 차수(degree): 하위 트리 개수
* 트리의 차수(degree of tree): 트리의 최대 차수
* 트리의 높이(height): 루트 노드에서 가장 깊숙히 있는 노드의 깊이
<br/>
<br/>

### BFS(Breadth First Search) : 너비우선탐색
<img src="https://velog.velcdn.com/images%2Fcodenmh0822%2Fpost%2F782491bf-13d4-4c42-a0df-7d839820b792%2Fimage.png"><br/>
* 레벨 순회 방식
* 트리 전체를 탐색하되, 인접한 노드들을 차례대로 방문하도록 구현한 방법
* 즉, 트리의 레벨이 가장 낮고 왼쪽의 노드 부터 순서대로 방문하는 것!
* 큐 자료구조를 활용 시, 구현 편리
* 위 이미지의 트리의 순회 결과는 : A -> B -> C -> D -> E -> F -> G -> H -> J
 ```
  bfs() {
    let curNode = this.root;

    const queue = [curNode];

    let printTree = '';

    while(queue.length) {
      curNode = queue.shift();
      printTree += curNode.value + ' ';

      if(curNode.left) {
        queue.push(curNode.left);
      }

      if(curNode.right) {
        queue.push(curNode.right);
      }
    }

    console.log(printTree);

    return;
  }
  ```
<br/>
<br/>

### DFS(Depth First Search) : 깊이우선탐색
1. 전위 순회(Preorder Traversal)<br/>
<img src="https://velog.velcdn.com/images%2Fcodenmh0822%2Fpost%2F6e69891b-806f-4469-a008-777fab8b2078%2Fimage.png"><br/>
  * 탐색법 : 루트노드 방문 -> 왼쪽 서브트리 전위 순회 -> 오른쪽 서브트리 전위 순회
  * 위 트리의 전위 순회 결과 'A->B->C->D->E->F->G'
    ```
    preOrder(root = this.root) {
      // 데이터가 없는 경우
      if (!root) return;

      console.log(root.value);
      this.preOrder(root.left);
      this.preOrder(root.right);
    }
    ```
    
2. 중위 순회(Inorder Traversal)<br/>
<img src="https://velog.velcdn.com/images%2Fcodenmh0822%2Fpost%2F532c13b1-698e-44c0-a107-20a4417e22a1%2Fimage.png"><br/>
  * 탐색법 : 왼쪽 서브트리 중위 순회 -> 루트노드 방문 -> 오른쪽 서브트리 중위 순회
  * 위 트리의 중위 순회 결과 'D->B->E->A->F->C->G'
    ```
    inOrder(root = this.root) {
      // 데이터가 없는 경우
      if (!root) return;

      this.inOrder(root.left);
      console.log(root.value);
      this.inOrder(root.right);
    }
    ```
    
3. 후위 순회(Postorder Treaversal)<br/>
  * 탐색법 : 왼쪽 서브트리 후위 순회 -> 오른쪽 서브트리 후위 순회 -> 루트노드 방문
  * 위 트리의 후위 순회 결과 'D->E->B->F->G->C->A'
    ```
    postOrder(root = this.root) {
      // 데이터가 없는 경우
      if (!root) return;

      this.postOrder(root.left);
      this.postOrder(root.right);
      console.log(root.value);
    }
    ```
<br/>
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
    
3. 편향 이진 트리
   * = 한 방향으로만 정점이 이어지는 트리<br/>
    <img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F6de5e48c-80ce-4466-968f-b621912f591e%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(5).png"><br/>

4. 정 이진 트리
   * = 모든 노드가 0개 혹은 2개의 자식 노드를 가지는 트리<br/>
    <img src="https://velog.velcdn.com/images%2Fcodenmh0822%2Fpost%2F1c666be8-65ab-47f5-98d1-81eb548b387a%2Fimage.png"><br/>
<br/>
<br/>

### 트리 구현 방법
* 트리는 그래프의 한 종류이므로 인접행렬, 인접리스트 2가지 방식으로 트리 표현 가능
* 일반적인 트리 구현 방법 == 그래프 구현 방법
* 이진 트리의 경우 배열 혹은 링크가 2개 존재하는 연결리스트로 구현
  * 배열로 구현할 경우, 인덱스 값을 /2 또는 *2 를 통해 간단히 자식 및 부모 노드의 인덱스 구하기 가능
  * ex) 현재 나의 인덱스 = index, 부모의 인덱스 = Math.floor(index/2), Left자식의 인덱스 = index*2, Right자식의 인덱스 = index *2+1
<br/>

## 참고
[TIL 6 | 자료구조 - 그래프, 트리, 힙, 트라이](https://velog.io/@grighth12/TIL-6-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%9E%98%ED%94%84-%ED%8A%B8%EB%A6%AC-%ED%9E%99-%ED%8A%B8%EB%9D%BC%EC%9D%B4)<br/>
[비선형 자료구조 - 트리(Tree)](https://velog.io/@codenmh0822/%EB%B9%84%EC%84%A0%ED%98%95-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%ED%8A%B8%EB%A6%ACTree)
