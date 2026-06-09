#include <iostream>
#include <algorithm>
#include <vector>

#define MX 1010

using namespace std;

int N;
int coin[MX];
int dp[MX][4];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for (int i = 1; i <= N; i++) cin >> coin[i];
    for (int i = 0; i <= N; i++) {
        for (int cnt = 0; cnt <= 3; cnt++) {
            dp[i][cnt] = -1;
        }
    }

    dp[0][0] = 0;
    for (int i = 1; i <= N; i++) {
        for (int cnt = 0; cnt <= 3; cnt++) {
            if (i - 1 >= 0 && cnt - 1 >= 0 && dp[i-1][cnt-1] != -1) {
                dp[i][cnt] = max(dp[i][cnt], dp[i-1][cnt -1] + coin[i]);
            }
            if (i - 2 >= 0 && dp[i-2][cnt] != -1) {
                dp[i][cnt] = max(dp[i][cnt], dp[i-2][cnt] + coin[i]);
            }
        }
    }

    int answer = 0;
    for (int cnt = 0; cnt <= 3; cnt++) {
        answer = max(answer, dp[N][cnt]);
    }
    cout << answer;
    return 0;
}