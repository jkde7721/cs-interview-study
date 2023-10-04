//알고리즘 분류: 구현 & 시뮬레이션
#include <bits/stdc++.h>
using namespace std;

const int dy[] = {-1, 1, 0, 0}, dx[] = {0, 0, -1, 1};
int n, m, _map[49][49], d, s, y, x, ny, nx, ret;
vector<pair<int, int>> path;

void findSearchPath() {
    int y = n / 2, x = n / 2, ny, nx, dy[] = {0, 1, 0, -1}, dx[] = {-1, 0, 1, 0};
    for (int dist = 1, idx = 0; dist <= n; dist++, idx++) { //거리 증가, 방향 전환
        for (int i = 0; i < 2*dist; i++) {
            if (i == dist) idx++; //방향 전환
            ny = y + dy[idx % 4]; nx = x + dx[idx % 4];
            if (nx < 0) break;
            path.push_back({ny, nx}); y = ny; x = nx;
        }
    }
}

void burst(vector<int>& v) {
    for (auto& it : path) v.push_back(_map[it.first][it.second]);
    v.push_back(4); //v가 0으로 끝나는 경우 처리를 위해 유효하지 않은 구슬 번호 추가
    int tcnt[4] = {0,}, num, cnt, st; bool isBurst;
    while (1) {
        num = cnt = 0; isBurst = false;
        for (int i = 0; i < v.size(); i++) {
            if (v[i] == 0) continue;
            if (num == v[i]) { cnt++; continue;}
            else if (cnt >= 4) { //구슬 폭발
                isBurst = true; tcnt[num] += cnt;
                for (int j = st; j < i; j++) v[j] = 0;
            }
            num = v[i]; cnt = 1; st = i;
        }
        //더 이상 폭발 발생X
        if (!isBurst) {
            ret += tcnt[1] + 2*tcnt[2] + 3*tcnt[3];
            return;
        }
    }
}

void changeBall(vector<int>& v) {
    int num = 0, cnt = 0, pos = 0;
    for (int i : v) {
        if (i == 0) continue;
        if (pos >= path.size()) break; //구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우
        if (num == i) { cnt++; continue; }
        if (cnt > 0) {
            _map[path[pos].first][path[pos].second] = cnt;
            if (pos+1 < path.size()) _map[path[pos+1].first][path[pos+1].second] = num;
            pos += 2;
        }
        num = i; cnt = 1;
    }
    for (; pos < path.size(); pos++) _map[path[pos].first][path[pos].second] = 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> n >> m; findSearchPath();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) cin >> _map[i][j];
    }
    //블리자드
    for (int i = 0; i < m; i++) {
        cin >> d >> s; d--; y = x = n / 2;
        while (s--) {
            ny = y + dy[d]; nx = x + dx[d];
            _map[ny][nx] = 0; y = ny; x = nx;
        }
        vector<int> v;
        burst(v); changeBall(v);
    }
    cout << ret << '\n';
    return 0;
}