# 백트래킹
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

N, S = map(int, input().split())
numList = list(map(int, input().split()))

answer = 0

# index, 합, count
def dfs(n, sum, cnt):
    global answer
    
    if n == N:
        if sum == S and cnt > 0 :
            answer += 1
        return
    
    dfs(n+1, sum + numList[n], cnt+1) # 포함
    dfs(n+1, sum, cnt) # 포함 x
    

dfs(0, 0, 0)
print(answer)