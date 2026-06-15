#include <iostream>
#include <algorithm>
#include <vector>

#define INF -987654321

using namespace std;

int N;
int soccer[1010], baseball[1010];

int dp[12][10];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> soccer[i] >> baseball[i];
    for (int i = 0; i <= 11; i++) {
        for (int j = 0; j <= 9; j++) {
            dp[i][j] = INF;
        }
    }
    dp[0][0] = 0;

    for (int i = 1; i <= N; i++) {
        for (int s = 11; s >= 0; s--) {
            for (int b = 9; b >= 0; b--) {
                if (dp[s][b] == INF) continue;
                if (s + 1 <= 11) {
                    dp[s+1][b] = max(dp[s+1][b], dp[s][b]+soccer[i]);
                }
                if (b + 1 <= 9) {
                    dp[s][b+1] = max(dp[s][b+1], dp[s][b] + baseball[i]);
                }
            }
        }
    }

    cout << dp[11][9];
    return 0;
}