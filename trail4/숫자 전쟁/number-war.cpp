#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>

#define MX 1010

using namespace std;

int N;
int A[MX], B[MX];
int dp[MX][MX];

int solve(int i, int j) {
    if (i > N || j > N) return 0;
    if (dp[i][j] != -1) return dp[i][j];

    dp[i][j] = 0;

    if (A[i] < B[j]) dp[i][j] = max(dp[i][j], solve(i + 1, j));
    if (A[i] > B[j]) dp[i][j] = max(dp[i][j], solve(i, j+1) + B[j]);
    dp[i][j] = max(dp[i][j], solve(i+1, j+1));
    return dp[i][j];
}


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i<= N; i++) cin >> A[i];
    for (int i = 1; i<= N; i++) cin >> B[i];

    memset(dp, -1, sizeof(dp));
    cout << solve(1, 1);
    return 0;
}