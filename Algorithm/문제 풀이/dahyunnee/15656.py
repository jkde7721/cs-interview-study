# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, M = map(int, input().split())
numList = sorted(list(map(int, input().split())))
answer = []

def dfs(n, target):
    if n == M:
        answer.append(target)
        return
    
    for j in range(N):
        dfs(n+1, target + [numList[j]])


dfs(0, [])

for lst in answer:
    print(*lst)