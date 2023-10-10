# 알고리즘 분류: bfs, dfs
from collections import deque
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

key = (
    (-1, 0, 0),
    (0, -1, 0),
    (0, 0, -1),
    (1, 0, 0),
    (0, 1, 0),
    (0, 0, 1)
)
m, n, h = map(int, input().split())
numList = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
visited = [[[False] * m for _ in range(n)] for _ in range(h)]

def bfs():
    q = deque()
    cnt = 0
    
    #! q에 초기데이터 삽입(익은토마토 모두 q에 넣고 시작). 안익은 토마토 카운트
    for i in range(h):
        for j in range(n):
            for k in range(m):
                #! 익은 토마토 넣기
                if numList[i][j][k] == 1:
                    q.append((i, j, k))
                    visited[i][j][k] = True
                #! 안익은 토마토
                elif numList[i][j][k] == 0:
                    cnt += 1
    
    while q:
        ci, cj, ck = q.popleft()
        for di, dj, dk in key:
            ni, nj, nk = di+ci, dj+cj, dk+ck
            #! 안익은 토마토일때 계속 퍼져나감 
            if 0<=ni<h and 0<=nj<n and 0<=nk<m and visited[ni][nj][nk] == False and numList[ni][nj][nk] == 0:
                q.append((ni,nj,nk))
                visited[ni][nj][nk] = True
                numList[ni][nj][nk] = 1+numList[ci][cj][ck]
                cnt -= 1
                
    # 모든 토마토 익음 
    if cnt == 0: 
        return numList[ci][cj][ck] - 1  # numList=1부터 시작해서
    else:
        return -1
    
answer = bfs()
print(answer)