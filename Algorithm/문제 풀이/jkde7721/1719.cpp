//알고리즘 분류: 최단거리(플로이드 워셜)
#include <bits/stdc++.h>
#define INF 1e9
using namespace std;

int n, m, u, v, w, dist[201][201], idx[201][201];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    fill(&dist[0][0], &dist[0][0] + 201*201, INF);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        cin >> u >> v >> w;
        dist[u][v] = dist[v][u] = min(dist[u][v], w);
        idx[u][v] = v; idx[v][u] = u;
    }
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] > dist[i][k] + dist[k][j]) { //k 중간 집하장을 거쳐 가는게 이동 시간이 더 짧다면
                    dist[i][j] = dist[i][k] + dist[k][j];
                    idx[i][j] = idx[i][k]; //i → k 직접적인 경로 없을 수 있음 
                }
            }
        }
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (i == j) cout << "- ";
            else cout << idx[i][j] << ' ';
        }
        cout << '\n';
    }
    return 0;
}