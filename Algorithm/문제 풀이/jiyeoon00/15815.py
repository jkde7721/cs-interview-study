import sys
input = sys.stdin.readline

def isCard(num):
  left = 0
  right = n-1
  while(left <= right):
    mid = (left+right)//2
    if(card[mid] > num):
      right = mid -1
    elif(card[mid] < num):
      left = mid + 1
    else:
      print(1, end = " ")
      break
  else:
    print(0, end = " ")

n = int(input())
card = list(map(int, input().split()))
card.sort()

m = int(input())
number = list(map(int, input().split()))

for i in number:
  isCard(i)
    