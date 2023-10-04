//알고리즘 분류: 완전탐색
#include <bits/stdc++.h>
using namespace std;

int l, c;
char alpha[15];

void go(int idx, string pwd) {
    if (pwd.size() == l) {
        int cnt = 0;
        for (char ch : pwd) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') cnt++;
        }
        //최소 한 개의 모음과 최소 두 개의 자음으로 구성된 경우에만 출력
        if (cnt >= 1 && pwd.size() - cnt >= 2) cout << pwd << '\n';
        return;
    }
    if (idx == c) return;
    go(idx + 1, pwd + alpha[idx]);
    go(idx + 1, pwd);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> l >> c;
    for (int i = 0; i < c; i++) cin >> alpha[i];
    sort(alpha, alpha + c);
    go(0, "");
    return 0;
}