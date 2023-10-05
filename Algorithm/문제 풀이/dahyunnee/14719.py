import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

# 알고리즘 분류: 구현
H, W = map(int, input().split())
blockList = list(map(int, input().split()))

sum = 0

#! 각각의 블럭 관점으로 생각
for i in range(1, W-1):
    leftMax = max(blockList[:i])
    rightMax = max(blockList[i+1:])
    minBlock = min(leftMax, rightMax)
    
    #! 좌우 블럭 각각의 최댓값 중 더 작은 값이 현재 높이보다 작다면 물 고일 수 없음
    if minBlock > blockList[i]:
        sum += minBlock - blockList[i]
        
print(sum)