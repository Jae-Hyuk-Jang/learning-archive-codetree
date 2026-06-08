#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010

using namespace std;

int N;
int arr[MX];
int dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
    }

    dp[1] = arr[1];
    int answer = dp[1];

    for (int i = 2; i <= N; i++) {
        dp[i] = max(dp[i-1] + arr[i], arr[i]);
        answer = max(answer, dp[i]);
    }
    cout << answer;
    return 0;
}