from collections import deque

N, M = map(int, input().split())
answer = 0
trains = [deque([0 for _ in range(20)]) for _ in range(N)]
for _ in range(M):
  order = list(map(int, input().split()))
  if(order[0] == 1):
    trains[order[1]-1][order[2]-1] = 1
  elif(order[0] == 2):
    trains[order[1]-1][order[2]-1] = 0
  elif(order[0] == 3):
    trains[order[1]-1].rotate(1)
    trains[order[1]-1][0] = 0
  else:
    trains[order[1]-1].rotate(-1)
    trains[order[1]-1][-1] = 0

#시간초과 : 
isAbleTrain = [False for _ in range(N)]
for i in range(N):
  if(isAbleTrain[i] == False):
    answer += 1
    isAbleTrain[i] = True
  else:
    continue
  for j in range(i+1, N):
    if(isAbleTrain[j] == False and trains[i] == trains[j]):
      isAbleTrain[j] = True 

#통과 : N*N
# state = []
# for i in range(N):
#   if(trains[i] not in state):
#     state.append(trains[i])
#     answer += 1

print(answer)

