#include <iostream>
#include <vector>
#include <algorithm>

#define MX 205 

using namespace std;

const int INF = -987654321;

int N;
int red[MX], blue[MX];
int dp[MX][MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    int total = 2 * N;
    for (int i = 1; i <= total; i++) cin >> red[i] >> blue[i];
    for (int i = 0; i <= total; i++) {
        for (int j = 0; j <= N; j++) {
            dp[i][j] = INF;
        }
    }
    dp[0][0] = 0;

    for (int i = 1; i <= total; i++) {
        for (int r = 0; r <= N; r++) {
            if (dp[i-1][r] == INF) continue;
            dp[i][r] = max(dp[i][r], dp[i-1][r] + blue[i]);

            if (r + 1 <= N) dp[i][r+1] = max(dp[i][r+1], dp[i-1][r] + red[i]);
        }
    }
    cout << dp[total][N];
    return 0;
}