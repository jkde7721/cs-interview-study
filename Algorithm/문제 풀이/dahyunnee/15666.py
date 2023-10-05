# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, M = map(int, input().split())
numList = sorted(list(map(int, input().split())))
answer = []

def dfs(n, startIdx, tlist):
    if n == M:
        answer.append(tlist)
        return
    
    prev = 0
    for i in range(startIdx, N):
        if prev != numList[i]:
            prev = numList[i]
            dfs(n+1, i, tlist+[numList[i]])
    

dfs(0, 0, [])

for ans in answer:
    print(*ans)