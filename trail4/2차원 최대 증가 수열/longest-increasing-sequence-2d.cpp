#include <iostream>
#include <vector>
#include <algorithm>

#define MX 55

using namespace std;

int N, M;
int mat[MX][MX];
int dp[MX][MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> mat[i][j];
        }
    }

    dp[1][1] = 1;

    int answer = 1;

    for (int x = 1; x <= N; x++) {
        for (int y = 1; y <= M; y++) {
            if(dp[x][y] == 0) continue;

            for (int nx = x + 1; nx <= N; nx++) {
                for (int ny = y + 1; ny <= M; ny++) {
                    if(mat[nx][ny] <= mat[x][y]) continue;

                    dp[nx][ny] = max(dp[nx][ny], dp[x][y]+1);
                    answer= max(answer, dp[nx][ny]);
                }
            }
        }
    }
    cout << answer;
    return 0;
}