//알고리즘 분류: 최단거리(플로이드 워셜)
#include <bits/stdc++.h>
using namespace std;

int n, dist[21][21], ret;
bool valid[21][21], impos;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    memset(valid, 1, sizeof(valid));
    cin >> n;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) cin >> dist[i][j];
    }
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != k && j != k && dist[i][j] == dist[i][k] + dist[k][j]) valid[i][j] = false; //i → k, k → j 경로로 갈 수 있으므로 i → j 경로 무효화
                if (dist[i][j] > dist[i][k] + dist[k][j]) impos = true; //불가능한 경우
            }
        }
    }
    if (impos) { cout << -1 << '\n'; return 0; }
    for (int i = 1; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
            if (valid[i][j]) ret += dist[i][j];
        }
    }
    cout << ret << '\n';
    return 0;
}