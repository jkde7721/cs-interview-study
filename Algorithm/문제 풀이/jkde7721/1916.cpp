//알고리즘 분류: 다익스트라
//한 정점에서 다른 정점으로 가는 최단 거리 + 양의 간선 → 다익스트라
#include <bits/stdc++.h>
#define INF 1e9
using namespace std;

int n, m, a, b, w, d, dist[1001];
vector<pair<int, int>> adj[1001];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    fill(dist, dist + 1001, INF);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> w;
        adj[a].push_back({b, w});
    }
    cin >> a >> b;
    dist[a] = 0; pq.push({0, a});
    while (pq.size()) {
        tie(d, a) = pq.top(); pq.pop();
        if (dist[a] < d) continue;
        for (auto it : adj[a]) {
            if (dist[it.first] > d + it.second) {
                dist[it.first] = d + it.second;
                pq.push({dist[it.first], it.first});
            }
        }
    }
    cout << dist[b] << '\n';
    return 0;
}