# 알고리즘 분류: bfs dfs
import sys
from collections import deque
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

n, m = map(int, input().split())
numList = [list(map(int, input().split())) for _ in range(n)]
visited = [[0] * m for _ in range(n)]
answer = [[-1] * m for _ in range(n)]  # ! 못가는 곳은 -1이어야 하므로 초기화를 -1로


def bfs(startI, startJ):
    q = deque()
    q.append((startI, startJ))
    answer[startI][startJ] = 0
    visited[startI][startJ] = 1

    while q:
        ci, cj = q.popleft()

        for di, dj in ((-1, 0), (0, 1), (1, 0), (0, -1)):
            ni, nj = ci+di, cj+dj
            if 0 <= ni < n and 0 <= nj < m and visited[ni][nj] == 0:
                #! bfs로 들러야 0으로 바꿈. bfs로 들리지 못하면, numList가 0이어도 여전히 -1 
                if numList[ni][nj] == 0:
                    visited[ni][nj] = 1
                    answer[ni][nj] = 0
                else:
                    q.append((ni, nj))
                    visited[ni][nj] = 1
                    #! 현재 answer값에 더해줘야함
                    #! answer[ni][nj] += 1 아님
                    answer[ni][nj] = 1 + answer[ci][cj]


for i in range(n):
    for j in range(m):
        if numList[i][j] == 2 and visited[i][j] == 0:
            bfs(i, j)

for i in range(n):
    for j in range(m):
        # 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
        # bfs를 통해 갈 수 없는 땅은 여전히 -1로 초기화되어있어서 0으로 바꿔주기 
        if numList[i][j] == 0:
            print(0, end=" ")
        else:
            print(answer[i][j], end=" ")
    print()
