from collections import deque
import math
import copy

def mix(card, k):
  temp = deque()
  result = deque()
  #1단계
  for _ in range(len(card) - 2**k):
    result.append(card.popleft())
  #그 이후 단계
  for i in range(2, k+2):
    for _ in range(len(card) - 2**(k-i+1)):
      temp.append(card.popleft())
    while(temp):
      result.appendleft(temp.pop())
  result.appendleft(card.pop())
  while(result):
    card.append(result.popleft())

n = int(input())
resultCard = deque(map(int, input().split()))

#최대 k구하기
k = math.log(n, 2)
if(int(k) == k):
  k = int(k)-1
else:
  k = int(k)

for firsh_k in range(k):
  a = deque([i for i in range(1, n+1)])
  mix(a, firsh_k+1)
  for second_k in range(k):
    b = copy.deepcopy(a)
    mix(a, second_k+1)
    if(a == resultCard):
      print(firsh_k+1, second_k+1)
      break
    a = b
  else:
    continue
  break

