# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, M = map(int, input().split())
numList = sorted(list(map(int, input().split())))
answer, visited = [], [0]*N

# 배열개수, 시작인덱스, 배열
def dfs(n, s, tlist):
    if n == M:
        answer.append(tlist)
        return

    for j in range(s, N):
        if visited[j] == 0:
            visited[j] = 1
            dfs(n+1, j+1, tlist+[numList[j]])
            visited[j] = 0


dfs(0, 0, [])

for ans in answer:
    print(*ans)
