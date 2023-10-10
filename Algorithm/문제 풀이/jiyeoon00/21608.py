import sys
input = sys.stdin.readline

# 일단 학생 돌리는 for문
#_번 학생을 비어있는 자리마다 체크

#여기서 체크란?
#왼, 오, 위, 아래 확인 후 비어있다면 vacant +=1, 사람이 있는데 내가 좋아하는 학생이라면 like += 1

#현재 유력한 자리, 주변 좋아하는 학생 수, 비어있는 칸 수를 가지고 있고 체크 했을 때, 새로운 주좋학이 큰 경우 or 주좋학이 같은데 비칸이 더 큰 경우 갱신

n = int(input())
answer = 0
studentOrder = []
likeStudents = {}
for i in range(n*n):
  a = input().split()
  studentOrder.append(a[0])
  likeStudents[a[0]] = a[1:]
board = [["" for _ in range(n)] for _ in range(n)]

dy = [1,-1,0,0]
dx = [0,0,1,-1]

for student in studentOrder:
  likeStudent = likeStudents[student]
  position =[0, 0]
  likeNum = -1
  vacantNum = -1
  #n*n자리 싹 다 확인
  for y in range(n):
    for x in range(n):
      if(board[y][x] != ""): #이미 앉은 학생이 있음
        continue
      like = 0
      vacant = 0
      for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]
        if(0 <= ny < n and 0 <= nx < n):
          if(board[ny][nx] == ""):
            vacant += 1
          else:
            if(board[ny][nx] in likeStudent):
              like += 1
      if(likeNum < like) or (likeNum == like and vacantNum < vacant):
        position[0], position[1] = y, x
        likeNum = like
        vacantNum = vacant
  board[position[0]][position[1]] = student

#학생의 만족도 구하기
for y in range(n):
  for x in range(n):
    student = board[y][x]
    cnt = 0
    for i in range(4):
      ny = y + dy[i]
      nx = x + dx[i]
      if(0 <= ny < n and 0 <= nx < n):
        if(board[ny][nx] in likeStudents[student]):
          cnt += 1
    answer += int(10**(cnt-1))

print(answer)