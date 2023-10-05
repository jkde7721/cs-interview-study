# 완전탐색 
import sys
sys.stdin = open('./input.txt', 'r')
sys.stdout = open('./output.txt', 'w')
input = sys.stdin.readline

S=list(input())
T=list(input())

def dfs(t): # 문자열 T 리스트를 입력받아
    if t==S:
        print(1)
        return 
    
    if len(t)==0:
        return 0
    
    if t[-1]=='A': # 마지막이 A이면 
        dfs(t[:-1]) # 제거해서 재귀
        
    if t[0]=='B': # 처음이 B이면
        dfs(t[1:][::-1]) # B제거하고, 뒤집어서 재귀
        
dfs(T)
print(0)