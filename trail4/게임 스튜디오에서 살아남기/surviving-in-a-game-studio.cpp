#include <iostream>
#include <algorithm>

#define MX 1010
#define MOD 1000000007

using namespace std;
using ll = long long;

int N;
ll dp[MX][3][3];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    dp[0][0][0] = 1;

    for (int day = 0; day < N; day++) {
        for (int tCnt = 0; tCnt < 3; tCnt++) {
            for (int bCnt = 0; bCnt < 3; bCnt++) {
                if (dp[day][tCnt][bCnt] == 0) continue;
                ll cur = dp[day][tCnt][bCnt];

                dp[day + 1][tCnt][0] += cur;
                dp[day + 1][tCnt][0] %= MOD;

                if (bCnt + 1 < 3) {
                    dp[day + 1][tCnt][bCnt + 1] += cur;
                    dp[day + 1][tCnt][bCnt + 1] %= MOD;
                }

                if (tCnt + 1 < 3) {
                    dp[day + 1][tCnt + 1][0] += cur;
                    dp[day + 1][tCnt + 1][0] %= MOD;
                }
            }
        }
    }

    ll answer = 0;
    for (int tCnt = 0; tCnt < 3; tCnt++) {
        for (int bCnt = 0; bCnt < 3; bCnt++) {
            answer += dp[N][tCnt][bCnt];
            answer %= MOD;
        }
    }

    cout << answer;
    return 0;
}