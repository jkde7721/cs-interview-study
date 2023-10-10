# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, M = map(int, input().split())
numList = sorted(list(map(int, input().split())))
answer = []

def dfs(n, startIdx, target):
    
    if n == M:
        answer.append(target)
        return
    
    for i in range(startIdx, N):
        dfs(n+1, i, target+[numList[i]])

dfs(0, 0, [])

for ans in answer:
    print(*ans)