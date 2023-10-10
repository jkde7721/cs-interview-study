# 알고리즘 분류: dp

import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

n, k = map(int, input().split())
valueList = [int(input()) for _ in range(n)]

dp = [0] * (k+1)
dp[0] = 1

for coin in valueList:
    for j in range(coin, k+1):
        dp[j] += dp[j-coin]

print(dp[k])