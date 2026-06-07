#include <iostream>
#include <vector>
#include <algorithm>

#define MX 10010

using namespace std;
using ll = long long;

int N;
ll M;
ll e[105];
int t[105];
ll dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    int maxTime = 0;
    for (int i = 1; i <= N; i++) {
        cin >> e[i] >> t[i];
        maxTime += t[i];
    }

    for (int i = 1; i <= N; i++) {
        for (int time = maxTime; time >= t[i]; time--) {
            dp[time] = max(dp[time], dp[time - t[i]] + e[i]);
        }
    }

    for (int time = 0; time <= maxTime; time++) {
        if (dp[time] >= M) {
            cout << time << '\n';
            return 0;
        }
    }
    
    cout << -1 << '\n';

    return 0;
}