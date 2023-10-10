from collections import deque
import sys

sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline
N, M = map(int, input().split())                                # 도시 크기, 남은 치킨집 수
arr = [list(map(int, input().split())) for _ in range(N)]       # 도시


def dfs(n, i):      # n: 고른 치킨집 수 i: 고른 치킨집 번호
    global result
    val = 0
    # 모든 치킨집을 골랐다면 치킨거리 계산
    if n == M:
        for h in house:
            hr, hc = h[0], h[1]
            dist = 2*N

            for c in select:
                cr, cc = c[0], c[1]
                tmp = abs(hr-cr) + abs(hc-cc)
                if tmp < dist:
                    dist = tmp
            val += dist

        if val < result:
            result = min(val, result)
            return
	# 고른 치킨 집을 제외하고 dfs
    for idx in range(i, K):
        select.append(chicken[idx])
        dfs(n+1, idx+1)
        select.pop()

chicken = deque([])
house = deque([])

select = deque([])
for a in range(N):
    for b in range(N):
        if arr[a][b] == 1:        # 집 위치 추가
            house.append((a, b))
        elif arr[a][b] == 2:      # 치킨집 위치 추가
            chicken.append((a, b))

K = len(chicken)             # 총 치킨집 수
result = N*2*len(house)      # 총 치킨거리 임의의 큰 값

# 조합 시작
for t in range(K):
    dfs(0, t)

print(result)