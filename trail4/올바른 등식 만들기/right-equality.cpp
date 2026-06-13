#include <iostream>
#include <algorithm>

#define MX 105
#define OFFSET 20

using namespace std;
using ll = long long;

int N, M;
int arr[MX];

ll dp[MX][45];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> arr[i];
    dp[0][OFFSET] = 1;
    for (int i = 1; i <= N; i++) {
        for (int sum = -20; sum <= 20; sum++) {
            ll cur = dp[i-1][sum + OFFSET];
            if(cur == 0) continue;

            int plus = sum + arr[i];
            int minus = sum - arr[i];

            if (-20 <= plus && plus <= 20) {
                dp[i][plus + OFFSET] += cur;
            }

            if (-20 <= minus && minus <= 20) {
                dp[i][minus + OFFSET] += cur;
            }
        }
    }

    cout << dp[N][M + OFFSET];
    return 0;
}