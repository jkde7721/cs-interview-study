from collections import deque

n, m = map(int, input().split())
maxHacking = 0
result = []

edges = [[] for _ in range(n+1)]

#단방향그래프 만들기
#5가 4를 신뢰함
#4해킹하면 5해킹
#4에다가 5를 넣어야함
for _ in range(m):
  c1, c2 = map(int, input().split())
  edges[c2].append(c1)
  
for i in range(1, n+1):
  cnt = 0
  visit = [False for _ in range(n+1)]
  dq = deque()
  dq.append(i)
  visit[i] = True
  while(dq):
    cnt += 1
    computer = dq.pop()
    for j in edges[computer]:
      if(visit[j] == False):
        dq.append(j)
        visit[j] = True
  #만약 같으면 그냥 append
  #새로운 값이 더 크면 result = [1]
  #새로운 값이 더 작으면 pass
  if(maxHacking == cnt):
    result.append(i)
  elif(maxHacking < cnt):
    maxHacking = cnt
    result = [i]

print(*sorted(result))


