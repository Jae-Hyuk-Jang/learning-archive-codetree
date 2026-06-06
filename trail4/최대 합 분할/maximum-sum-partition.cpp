#include <iostream>
#include <algorithm>
#include <vector>

#define MX 200010
#define INF -987654321

using namespace std;

int N;
int arr[105];
int dp[MX];
int ndp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    int total = 0;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
        total += arr[i];
    }

    int offset = total;
    int limit = total * 2;

    for (int i = 0; i <= limit; i++) dp[i] = INF;
    dp[offset] = 0;

    for (int i = 1; i <= N; i++) {
        for (int j = 0; j <= limit; j++) {
            ndp[j] = dp[j];
        }

        for (int j = 0; j <= limit; j++) {
            if (dp[j] == INF) continue;

            int diff = j - offset;

            int nextA = diff + arr[i] + offset;
            int nextB = diff - arr[i] + offset;

            if (0 <= nextA && nextA <= limit) {
                ndp[nextA] = max(ndp[nextA], dp[j] + arr[i]);
            }

            if (0 <= nextB && nextB <= limit) {
                ndp[nextB] = max(ndp[nextB], dp[j] + arr[i]);
            }
        }

        for (int j = 0; j <= limit; j++) dp[j] = ndp[j];
    }

    cout << dp[offset] / 2;
    
    return 0;
}
