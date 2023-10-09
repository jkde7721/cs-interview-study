//알고리즘 분류: 완전탐색
#include <bits/stdc++.h>
using namespace std;

int n, p, q, ret, x[8], group[8];

//ex. 곱하기 연산자가 2개라면 3개의 그룹으로 나눔, 각 그룹에 속한 숫자들끼리 더하기 연산 수행
void go(int idx) {
    if (idx == n) {
        int num = 1;
        for (int i = 0; i <= q; i++) num *= group[i];
        ret = max(ret, num); return;
    }
    for (int i = 0; i <= q; i++) {
        group[i] += x[idx];
        go(idx + 1);
        group[i] -= x[idx];
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n;
    for (int i = 0; i < n; i++) cin >> x[i];
    cin >> p >> q;
    go(0);
    cout << ret << '\n';
    return 0;
}