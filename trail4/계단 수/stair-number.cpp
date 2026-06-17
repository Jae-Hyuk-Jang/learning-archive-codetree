#include <iostream>
#include <algorithm>

#define MX 1010
#define MOD 1000000007

using namespace std;
using ll = long long;

int N;
ll dp[MX][10];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for (int num = 1; num <= 9; num++) dp[1][num] = 1;
    dp[1][0] = 0;

    for (int len = 2; len <= N; len++) {
        for (int num = 0; num <= 9; num++) {
            if (num - 1 >= 0) {
                dp[len][num] += dp[len-1][num-1];
                dp[len][num] %= MOD;
            }

            if (num + 1 <= 9) {
                dp[len][num] += dp[len-1][num+1];
                dp[len][num] %= MOD;
            }
        }
    }

    ll answer = 0;
    for (int num = 0; num <= 9; num++) {
        answer += dp[N][num];
        answer %= MOD;
    }

    cout << answer;
    return 0;
}