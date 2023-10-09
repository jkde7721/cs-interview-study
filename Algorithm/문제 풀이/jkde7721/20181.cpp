//알고리즘 분류: DP + 투포인터 + 누적합
#include <bits/stdc++.h>
typedef long long ll;
using namespace std;

//dp 배열: 해당 위치까지 이동했을때 축적된 탈피 에너지의 최대값
int n, k, sum, eat[100001];
ll dp[100001];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n >> k;
    for (int s = 1, e = 1; e <= n; e++) {
        cin >> eat[e]; sum += eat[e];
        dp[e] = dp[e-1]; //현재 위치에서 먹지 않은 경우(초기화)
        while (sum >= k) {
            dp[e] = max(dp[e], dp[s-1] + sum - k);
            sum -= eat[s++];
        }
    }
    cout << dp[n] << '\n';
    return 0;
}