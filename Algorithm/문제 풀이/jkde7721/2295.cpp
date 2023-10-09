//알고리즘 분류: 이분탐색
//완전탐색의 경우 2^30(3개의 조합) * 10 시간복잡도 → 2^20(2개의 조합) + 2^20 * 20 시간복잡도로 분리
#include <bits/stdc++.h>
using namespace std;

int n, num[1000];
vector<int> pairs;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n;
    for (int i = 0; i < n; i++) cin >> num[i];
    sort(num, num + n);
    //두 숫자 간 합의 모든 경우
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) pairs.push_back(num[i] + num[j]);
    }
    sort(pairs.begin(), pairs.end());
    for (int i = n - 1; i > 0; i--) { //큰 수부터 체크
        for (int j = 0; j < i; j++) {
            if (binary_search(pairs.begin(), pairs.end(), num[i] - num[j])) {
                cout << num[i] << '\n'; return 0;
            }
        }
    }
    return 0;
}