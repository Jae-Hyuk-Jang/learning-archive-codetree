#include <iostream>
#include <algorithm>
#include <vector>

#define MX 105 

using namespace std;

int N;
int mat[MX][MX];
int dp[MX][MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i<= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> mat[i][j];
        }
    }

    dp[1][1] = mat[1][1];

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if(i == 1 && j == 1) continue;

            int fromUp = 1e8;
            int fromLeft = 1e8;
            
            if (i > 1) fromUp = max(dp[i-1][j], mat[i][j]);
            if (j > 1) fromLeft = max(dp[i][j-1], mat[i][j]);

            dp[i][j] = min(fromUp, fromLeft);
        }
    }

    cout << dp[N][N] << '\n';
    
    return 0;
}