#<나오면 >나올 때 까지 그냥 푸시
#공백 나올 때까지 모아뒀다가 reverse해서 푸시

from collections import deque

s = input()
dq = deque(s)
temp = deque()
result = deque()

while(dq):
  i = dq.popleft()
  if(i == "<"):
    while(temp):
      result.append(temp.pop())
    result.append(i)
    while(1):
      j = dq.popleft()
      result.append(j)
      if(j == ">"):
        break
  elif(i == " "):
    #temp를 거꾸로 넣어주기
    while(temp):
      result.append(temp.pop())
    result.append(i)
  else:
    temp.append(i)

while(temp):
      result.append(temp.pop())

print("".join(result))