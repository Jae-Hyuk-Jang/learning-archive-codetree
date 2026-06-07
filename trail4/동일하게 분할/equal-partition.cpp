#include <iostream>
#include <algorithm>
#include <vector>

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

    if (total % 2 == 1) {
        cout << "No" << '\n';
        return 0;
    }

    int target = total / 2;
    dp[0] = true;

    for (int i = 1; i <= N; i++) {
        for (int sum = target; sum >= arr[i]; sum--) {
            if (dp[sum - arr[i]]) {
                dp[sum] = true;
            }
        }
    }

    cout << ((dp[target] ? "Yes" : "No")) << '\n';
    return 0;
}