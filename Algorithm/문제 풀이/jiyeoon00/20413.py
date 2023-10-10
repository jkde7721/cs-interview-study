n = int(input())
mvp = list(map(int, input().split()))
dict = {}
dict["B"] = mvp[0]
dict["S"] = mvp[1]
dict["G"] = mvp[2]
dict["P"] = mvp[3]
dict["D"] = 500

grades = input()
beforePay = 0
answer = 0
for grade in grades:
  if(grade == "D"):
    beforePay = dict["P"]
  else:
    beforePay = dict[grade]-beforePay-1
  answer += beforePay
print(answer)