import sys
input = sys.stdin.readline

#dp배열에 금액을 만드는 방법의 수를 갱신하며 저장
#5와7로 22를 만든다면 
#17을 만드려면 17-5=12에 5를 더하면 되므로 
#dp[17]에 저징될 값은 dp[12]의 값을 더한것이 된다. 

t = int(input())
for i in range(t):
  n = int(input())
  coins = list(map(int, input().split()))
  money = int(input())
  dp = [1] + [0 for i in range(money)]
  for coin in coins:
    for i in range(coin, money+1):
      dp[i] += dp[i-coin]
  print(dp[money])