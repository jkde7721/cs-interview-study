# 트라이(Trie)
### 트라이란?
* 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조
* 저장된 단어는 끝을 표시하는 변수를 추가해서 저장된 단어의 끝 구분 가능
* 간선은 이전 정점으로부터 새롭게 추가되는 문자 정보를 가짐<br/>
  정점은 이전 정점으로부터 더해진 문자열 정보를 가짐
* 트라이의 노드는 자신의 value와 간선의 값이 연결된 노드 정보를 가지고 있는 해시테이블을 변수로 가짐
* 문자열 탐색 시, 보통의 시간복잡도는 저장되어 있는 문자열의 개수 * 탐색할 문자열의 길이" <br/>
  트라이를 사용할 때의 시간복잡도는 "문자열의 길이" 만큼만! = O(L) 이라 훨씬 빠르며 효율적
* but, 각 노드가 자식들에 포인터들을 배열로 모두 저장하고 있기 때문에, 저장 공간 크기 多
* 자동 완성, 사전 찾기 등에 활용
<br/>

### 트라이 표현
< 트리로 표현할 때 ><br/>
<img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2Ffd419565-5845-4663-b245-3554dcdac7a1%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(6).png"><br/>

< 자료구조 방면에서 생각 >
<img src="https://velog.velcdn.com/images%2Fgrighth12%2Fpost%2F3e722f2c-6c6a-439b-83df-b10e04c4055b%2Fvelog%20%ED%8F%AC%EC%8A%A4%ED%8C%85%EC%9A%A9%20-%20Database%20ER%20diagram%20(crow's%20foot)%20(8).png"><br/>
* Node의 children은 해시 테이블
* key값은 이전 노드의 value에 추가될 값(간선의 값)
* value는 생성된(이전 문자열 + 간선의 값을 value로 하는) Node로 설정
<br/>

## 참고
[TIL 6 | 자료구조 - 그래프, 트리, 힙, 트라이](https://velog.io/@grighth12/TIL-6-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%9E%98%ED%94%84-%ED%8A%B8%EB%A6%AC-%ED%9E%99-%ED%8A%B8%EB%9D%BC%EC%9D%B4)<br/>
[비선형 자료구조 - 트리(Tree)](https://velog.io/@codenmh0822/%EB%B9%84%EC%84%A0%ED%98%95-%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%ED%8A%B8%EB%A6%ACTree)<br/>
