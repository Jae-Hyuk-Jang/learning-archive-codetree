#include <iostream>
#include <algorithm>
#include <cstring>
#include <vector>

using namespace std;
using pii = pair<int, int>;

int N, M;
int mat[51][51];
bool processed[51][51];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

vector<pii> vec;

void dfs(int x, int y, int k) {
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
        if (processed[nx][ny] || mat[nx][ny] <= k) continue;
        processed[nx][ny] = true;
        dfs(nx, ny, k);
    }
}


int remain(int k) {
    memset(processed, false, sizeof(processed));

    int cnt = 0;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            if (processed[i][j] || mat[i][j] <= k) continue;
            processed[i][j] = true;
            cnt++;
            dfs(i, j, k);
        }
    }
    return cnt;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> mat[i][j];
        }
    }


    for (int k = 1; k <= 100; k++) {
        int cnt = remain(k);
        vec.push_back({cnt, k});
    }

    sort(vec.begin(), vec.end(), [](const pii &a, const pii &b){
        if(a.first != b.first) return a.first > b.first;
        return a.second < b.second;
    });

    cout << vec[0].second << ' ' << vec[0].first;

    return 0;
}
