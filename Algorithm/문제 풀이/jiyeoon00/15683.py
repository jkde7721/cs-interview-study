# 1번 : 4방향 회전
# 2번 : 2방향 회전
# 3번 : 4방향 회전
# 4번 : 4방향 회전
# 5번 : 회전 없음

import copy

#up, right, down, left
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

n, m = map(int, input().split())
arr = [input().split() for _ in range(n)]
result = n * m #최대값

mod = [
    0, 
    [[0], [2], [1], [3]],
    [[0, 2], [3, 1]],
    [[0, 1], [1, 2], [2, 3], [3, 0]],
    [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],
    [[0, 2, 1, 3]]
]

#일단 cctv를 다 찾고 그 cctv를 방향별로 탐색해야함(dfs)
cctvs = []
for y in range(n):
  for x in range(m):
    if(arr[y][x] != "0" and arr[y][x] != "6"): #cctv면
      cctvs.append([arr[y][x], y, x])

#dfs돌다가 모든 cctv를 돌았다! 그러면 사각지대 계산하고 return
def dfs(depth, board):
  global result
  if(depth == len(cctvs)):
    result = min(result, calBlindSpot(board))
    return
  temp = copy.deepcopy(board)
  cctv = cctvs[depth]
  for m in mod[int(cctv[0])]:
    #해당 방향을 선택한 거! => cctv비추는 곳 #으로 바꿔줘야 함!
    for d in m:
      mark(temp, d, cctv[1], cctv[2])
    dfs(depth+1, temp)
    temp = copy.deepcopy(board)
  
def calBlindSpot(board):
  cnt = 0
  for y in range(n):
    for x in range(m):
      if (board[y][x] == "0"):
        cnt += 1
  return cnt

#시작점에서 해당 방향으로 #표시하는 합수
def mark(board, d, start_y, start_x):
    ny = start_y + dy[d]
    nx = start_x + dx[d]
    if (0 <= ny < n and 0 <= nx < m):
      if(board[ny][nx] == "6"):
        return
      elif(board[ny][nx] == "0"):
        board[ny][nx] = "#"
      mark(board, d, ny, nx)

dfs(0, arr)
print(result)
