from collections import deque
import sys

input = sys.stdin.readline

#한 바퀴 돌면서 방문 안한곳이 있다?
#왼오위아 확인해주고 문 열리면 방문 표시하고 사람 수, 나라 수 갱신하기 => 연결된 거 다 확인하면 사람수/나라수 해서 갱신해주기(연결된 거 저장해줘야 겠네?)

#한 바퀴를 싹 돌면 day += 1, 연합이 하나도 없다면 break

dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]

n, l, r = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(n)]
answer = 0

while (1):
    visit = [[False for _ in range(n)] for _ in range(n)]
    cnt = 0  #인구이동이 발생하지 않는 경우를 위한 flag
    for y in range(n):
        for x in range(n):
            peopleNum = 0
            connectedCountry = []
            dq = deque()
            if (visit[y][x] == False):
                cnt += 1
                visit[y][x] = True
                connectedCountry.append([y, x])
                peopleNum += A[y][x]
                dq.append([y, x])
                while (dq):
                    pop_y, pop_x = dq.popleft()
                    for i in range(4):
                        ny = pop_y + dy[i]
                        nx = pop_x + dx[i]
                        if (0 <= ny < n and 0 <= nx < n
                                and visit[ny][nx] == False and
                                l <= abs(A[pop_y][pop_x] - A[ny][nx]) <= r):
                            visit[ny][nx] = True
                            connectedCountry.append([ny, nx])
                            peopleNum += A[ny][nx]
                            dq.append([ny, nx])
                #연결된거 다 찾음
                newPeopleNum = peopleNum // len(connectedCountry)
                for c_y, c_x in connectedCountry:
                    A[c_y][c_x] = newPeopleNum
    if (cnt == n * n):  #인구이동이 발생하지 않음
        print(answer)
        break

    answer += 1
