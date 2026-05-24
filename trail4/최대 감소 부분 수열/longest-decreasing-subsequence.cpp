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
    for (int i = 1; i<= N; i++) cin >> arr[i];
    int answer = 1;
    for (int i = 1; i <= N; i++) {
        dp[i] = 1;
        for(int j =1; j < i; j++) {
            if(arr[j] > arr[i]) {
                dp[i] = max(dp[i], dp[j] +1);
            }
        }
        answer = max(answer, dp[i]);
    }
    cout << answer;
    return 0;
}