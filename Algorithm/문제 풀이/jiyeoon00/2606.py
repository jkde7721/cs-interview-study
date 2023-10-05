from collections import deque

computerNum = int(input())
edgeNum = int(input())

edges = [[] for _ in range(computerNum+1)]

for _ in range(edgeNum):
  c1, c2 = map(int, input().split())
  edges[c1].append(c2)
  edges[c2].append(c1)
  
result = 0
visit = [False for i in range(computerNum+1)]
dq = deque()
dq.append(1)
visit[1] = True
while(dq):
  result += 1
  computer = dq.pop()
  for i in edges[computer]:
    if(visit[i] == False):
      dq.append(i)
      visit[i] = True
print(result-1)