#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010
using namespace std;
using ll = long long;

const ll INF = -4e18;

int N, K;
int arr[MX];

ll dp[15];
ll ndp[15];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;
    for (int i = 1; i <= N; i++) cin >> arr[i];
    for (int i = 0; i <= K; i++) dp[i] = INF;
    ll answer = INF;

    for (int i = 1; i <= N; i++) {
        int x = arr[i];
        for (int j = 0; j <= K; j++) ndp[j] = INF;
        
        if (x < 0) {
            for (int cnt = 1; cnt <= K; cnt++) {
                if (cnt == 1) ndp[cnt] = max(ndp[cnt], (ll)x);
                if (dp[cnt - 1] != INF) ndp[cnt] = max(ndp[cnt], dp[cnt-1]+x);
            }
        } else {
            for (int cnt = 0; cnt <= K; cnt++) {
                if (cnt == 0) ndp[cnt] = max(ndp[cnt], (ll)x);
                if (dp[cnt] != INF) ndp[cnt] = max(ndp[cnt], dp[cnt] + x);
            }
        }

        for (int cnt = 0; cnt <= K; cnt++) {
            dp[cnt] = ndp[cnt];
            answer = max(answer, dp[cnt]);
        }
    }

    cout << answer;

    return 0;
}