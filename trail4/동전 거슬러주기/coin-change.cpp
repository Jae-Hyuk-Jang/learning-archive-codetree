#include <iostream>
#include <vector>
#include <algorithm>

#define MX 10010
#define INF 987654321

using namespace std;

int N, M;
int coin[105];
int dp[MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> coin[i];
    for (int i = 0; i <= M; i++) dp[i] = INF;
    dp[0] = 0;

    for (int money = 1; money <= M; money++) {
        for (int i = 1; i <= N; i++) {
            if(money - coin[i] < 0) continue;
            if(dp[money - coin[i]] == INF) continue;
            dp[money] = min(dp[money], dp[money - coin[i]] + 1);
        }
    }

    cout << ((dp[M] == INF) ? -1 : dp[M]);
    return 0;
}