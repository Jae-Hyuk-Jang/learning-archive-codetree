#include <iostream>
#include <algorithm>

#define MX 1010

using namespace std;

int N;
int mat[MX][3];
int dp[MX][3];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> mat[i][0] >> mat[i][1] >> mat[i][2];
    }

    dp[1][0] = mat[1][0];
    dp[1][1] = mat[1][1];
    dp[1][2] = mat[1][2];

    for (int i = 2; i <= N; i++) {
        dp[i][0] = max(dp[i-1][1], dp[i-1][2]) + mat[i][0];
        dp[i][1] = max(dp[i-1][0], dp[i-1][2]) + mat[i][1];
        dp[i][2] = max(dp[i-1][0], dp[i-1][1]) + mat[i][2];
    }

    int answer = max({dp[N][0], dp[N][1], dp[N][2]});
    cout << answer;
    return 0;
}