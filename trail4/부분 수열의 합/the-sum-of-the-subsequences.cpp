#include <iostream>
#include <algorithm>
#include <vector>

#define MX 10010

using namespace std;

int N, M;
int arr[105];
bool dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> arr[i];

    dp[0] = true;

    for (int i = 1; i <= N; i++) {
        for (int sum = M; sum >= arr[i]; sum--) {
            if(dp[sum - arr[i]]) dp[sum] = true;
        }
    }

    cout << ((dp[M] ? "Yes" : "No"));
    return 0;
}