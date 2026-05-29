#include <iostream>
using namespace std;

int dx[2] = {1, 0};
int dy[2] = {0, 1};

int N, M;
int mat[101][101];
bool processed[101][101];

bool answer = false;

void dfs(int x, int y) {

    if (answer) return;

    if(x == N && y == M) {
        answer = true;
        return;
    }

    for (int i = 0; i< 2; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
        if (processed[nx][ny] || !mat[nx][ny]) continue;
        processed[nx][ny] = true;
        dfs(nx, ny);
    }
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

    processed[1][1] = true;
    dfs(1, 1);

    cout << answer;




    return 0;
}