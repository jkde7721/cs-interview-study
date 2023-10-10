#i번째 인덱스의 문자를 스캔, (1)가장 많고, (2)사전순으로 앞서는 것
from collections import defaultdict

n, m = map(int, input().split())
dnas = [list(input()) for _ in range(n)]
result = []
sumOfHammingDistance = 0

for x in range(m):
  dict = defaultdict(int)
  for y in range(n):
    dict[dnas[y][x]] += 1
  nucleotide, num = sorted(list(dict.items()), key = lambda x : (-x[1], x[0]))[0]
  result.append(nucleotide)
  sumOfHammingDistance += (n-num)

print("".join(result))
print(sumOfHammingDistance)