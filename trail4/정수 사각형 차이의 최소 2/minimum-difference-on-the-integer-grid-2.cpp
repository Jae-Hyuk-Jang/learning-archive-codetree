#include <iostream>
#include <algorithm>
#include <cstring>

#define MX 105

using namespace std;

int N;
int mat[MX][MX];
bool dp[MX][MX];

bool canGo(int low, int high) {
    memset(dp, false, sizeof(dp));
    if(mat[1][1] < low || mat[1][1] > high) return false;
    if (mat[N][N] < low || mat[N][N] > high) return false;

    dp[1][1] = true;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (mat[i][j] < low || mat[i][j] > high) continue;

            if (i > 1 && dp[i-1][j]) dp[i][j] = true;
            if (j > 1 && dp[i][j-1]) dp[i][j] = true;
        }
    }
    return dp[N][N];
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> mat[i][j];
        }
    }

    int answer = 987654321;

    for (int low = 1; low <= 100; low++) {
        for (int high = low; high <= 100; high++) {
            if (canGo(low, high)) {
                answer = min(answer, high - low);
            }
        }
    }

    cout << answer;
    return 0;
}