#include <iostream>
#include <algorithm>
#include <vector>

#define MX 10010

using namespace std;

int N, M;
int w[105], v[105];
int dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> w[i] >> v[i];

    for (int i = 1; i <= N; i++) {
        for (int weight = M; weight >= w[i]; weight--) {
            dp[weight] = max(dp[weight], dp[weight - w[i]] + v[i]);
        }
    }

    cout << dp[M];

    return 0;
}