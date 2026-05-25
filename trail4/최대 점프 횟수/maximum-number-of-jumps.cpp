#include <iostream>
#include <algorithm>
#include <vector>

#define MX 1010

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
        dp[i] = -1;
    }

    dp[1] = 0;
    for (int i = 1; i <= N; i++) {
        if (dp[i] == -1) continue;

        for (int jump = 1; jump <= arr[i]; jump++) {
            int next = i + jump;
            if(next > N) continue;
            dp[next] = max(dp[next], dp[i] + 1);
        }
    }

    int answer = 0;

    for (int i = 1; i <= N; i++) answer = max(answer, dp[i]);

    cout << answer;
    return 0;
}