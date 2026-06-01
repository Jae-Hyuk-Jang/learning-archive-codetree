#include <iostream>
#include <algorithm>
#include <vector>

#define MX 10010
#define INF 987654321
using namespace std;

int N, M;
int arr[105];
int dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> arr[i];
    for (int i = 0; i <= M; i++) dp[i] = INF;
    dp[0] = 0;

    for (int i = 1; i <= N; i++) {
        for (int sum = M; sum >= arr[i]; sum--) {
            if(dp[sum - arr[i]] == INF) continue;
            dp[sum] = min(dp[sum], dp[sum - arr[i]] + 1);
        }
    }

    if(dp[M] == INF) cout << -1;
    else cout << dp[M];
    
    return 0;
}