# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, M = map(int, input().split())
numList = sorted(list(map(int, input().split())))
answer = []
visited = [0]*N

def dfs(n, tlist):
    if n == M:
        answer.append(tlist)
        return
    
    prev = 0
    for i in range(0, N):
        if visited[i] == 0 and prev != numList[i]:
            visited[i] = 1
            prev = numList[i]
            dfs(n+1, tlist + [numList[i]])
            visited[i] = 0

dfs(0, [])
for ans in answer:
    print(*ans)