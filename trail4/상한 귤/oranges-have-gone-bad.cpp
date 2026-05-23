#include <iostream>
#include <algorithm>
#include <queue>
#include <cstring>
#include <vector>

#define MX 105

using namespace std;
using pii = pair<int, int>;

int N, K;
int mat[MX][MX];
int dist[MX][MX];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

queue<pii> q;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;

    memset(dist, -1, sizeof(dist));

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> mat[i][j];
            if(mat[i][j] == 2) {
                q.push({i, j});
                dist[i][j] = 0;
            }
        }
    }

    while(!q.empty()) {
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
            if(mat[nx][ny] == 0) continue;
            if(dist[nx][ny] != -1) continue;

            dist[nx][ny] = dist[cx][cy] + 1;
            q.push({nx, ny});
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if(mat[i][j] == 0) cout << -1 << ' ';
            else if (dist[i][j] == -1) cout << -2 << ' ';
            else cout << dist[i][j] << ' ';
        }
        cout << '\n';
    }


    return 0;
}
