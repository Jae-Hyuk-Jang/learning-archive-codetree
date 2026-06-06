#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdlib>

#define MX 100010

using namespace std;

int N;
int arr[105];
bool dp[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    int total = 0;
    for (int i = 1; i <= N; i++) {
        cin >> arr[i];
        total += arr[i];
    }

    dp[0] = true;

    for (int i = 1; i <= N; i++) {
        for (int sum = total; sum >= arr[i]; sum--) {
            if(dp[sum - arr[i]]) dp[sum] = true;
        }
    }

    int answer = 98765321;
    for (int sum = 1; sum < total; sum++) {
        if(!dp[sum]) continue;
        int diff = abs(total - 2 * sum);
        answer = min(answer, diff);
    }
    cout << answer;
    return 0;
}