//알고리즘 분류: 최단거리(플로이드 워셜)
#include <bits/stdc++.h>
#define MAX 1e9
using namespace std;

int v, e, a, b, c, dist[401][401], ret = MAX;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    fill(&dist[0][0], &dist[0][0] + 401*401, MAX);
    cin >> v >> e;
    for (int i = 0; i < e; i++) {
        cin >> a >> b >> c;
        dist[a][b] = c;
    }
    for (int k = 1; k <= v; k++) {
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
    //사이클 찾기: i → j, j → i 경로 모두 존재하는 경우
    for (int i = 1; i <= v; i++) {
        for (int j = i + 1; j <= v; j++) {
            if (dist[i][j] != MAX && dist[j][i] != MAX) ret = min(ret, dist[i][j] + dist[j][i]);
        }
    }
    cout << (ret == MAX ? -1 : ret) << '\n';
    return 0;
}