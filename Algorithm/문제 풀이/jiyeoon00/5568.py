n = int(input())
k = int(input())
card = [input() for _ in range(n)]

visit = [False for _ in range(n)]
selectedCard = []
number = set()

def dfs():
  if(len(selectedCard) == k):
    number.add("".join(selectedCard))
    return
  for j in range(n):
    if(visit[j] == False):
      visit[j] = True
      selectedCard.append(card[j])
      dfs()
      visit[j] = False
      selectedCard.pop()

dfs()
print(len(number))