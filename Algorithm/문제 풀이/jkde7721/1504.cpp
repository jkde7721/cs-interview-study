//알고리즘 분류: 다익스트라
#include <bits/stdc++.h>
#define INF 1e9
using namespace std;

int n, e, a, b, c, d, v1, v2, dist[801], dist2[801], dist3[801];
vector<pair<int, int>> adj[801];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

void dijkstra(int st, int dist[]) {
    fill(dist, dist + 801, INF);
    dist[st] = 0; pq.push({0, st});
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
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n >> e;
    for (int i = 0; i < e; i++) {
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
        adj[b].push_back({a, c});
    }
    cin >> v1 >> v2;
    dijkstra(1, dist);
    dijkstra(n, dist2);
    dijkstra(v1, dist3);
    if (dist3[v2] == INF || (dist[v1] == INF || dist2[v2] == INF) && (dist[v2] == INF || dist2[v1] == INF)) cout << -1 << '\n'; //경로 존재X
    else cout << min(dist[v1] + dist3[v2] + dist2[v2], dist[v2] + dist3[v2] + dist2[v1]) << '\n';
    return 0;
}