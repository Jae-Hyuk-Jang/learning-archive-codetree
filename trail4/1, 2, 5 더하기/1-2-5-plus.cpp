#include <iostream>
#include <algorithm>

#define MX 1010
#define MOD 10007

using namespace std;

int N;
int dp[MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    dp[0] = 1;

    for (int i = 1; i <= N; i++) {
        if(i-1 >= 0) {
            dp[i] = (dp[i] + dp[i-1]) % MOD;
        }

        if (i-2 >= 0) {
            dp[i] = (dp[i] + dp[i-2]) % MOD;
        }

        if(i - 5 >= 0) {
            dp[i] = (dp[i] + dp[i-5]) % MOD;
        }
    }

    cout << dp[N];

    return 0;
}