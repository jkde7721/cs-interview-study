import sys
input = sys.stdin.readline
from collections import deque

n, k = map(int, input().split())
visit = [False for i in range(100001)]

dq = deque()
dq.append((0, n))

while dq:
  depth, a = dq.popleft()
  if(a == k):
    print(depth)
    break
  visit[a] = True
  if(0<a*2<=100000 and not visit[a*2]):
    visit[a*2] = True
    dq.appendleft((depth, a*2))
  if(0<=a-1<=100000 and not visit[a-1]):
    visit[a-1] = True
    dq.append((depth+1, a-1))
  if(0<=a+1<=100000 and not visit[a+1]):
    visit[a+1] = True
    dq.append((depth+1, a+1))
  
