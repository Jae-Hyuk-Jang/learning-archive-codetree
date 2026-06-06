#include <iostream>
#include <algorithm>
#include <vector>

#define MX 110

using namespace std;

int N;
int price[MX];
int dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) cin >> price[i];

    dp[0] = 0;

    for (int len = 1; len <= N; len++) {
        for (int cut = 1; cut <= len; cut++) {
            dp[len] = max(dp[len], dp[len - cut] + price[cut]);
        }
    }

    cout << dp[N];

    return 0;
}