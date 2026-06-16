#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define MX 205
#define INF -987654321

using namespace std;

int N, M;
int s[MX], e[MX], v[MX];
int dp[MX][MX];

bool canWear(int cloth, int day) {
    return s[cloth] <= day && day <= e[cloth];
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i<= N; i++) cin >> s[i] >> e[i] >> v[i];

    for (int day = 0; day <= M; day++) {
        for (int i = 1; i <= N; i++) {
            dp[day][i] = INF;
        }
    }

    for (int i = 1; i <= N; i++) {
        if (canWear(i, 1)) { dp[1][i] = 0; }
    }

    for (int day = 2; day <= M; day++) {
        for (int i = 1; i <= N; i++) {
            if (!canWear(i, day)) continue;
            for (int j = 1; j <= N; j++) {
                if (!canWear(j, day - 1)) continue;
                if (dp[day - 1][j] == INF) continue;

                dp[day][i] = max(dp[day][i], dp[day - 1][j] + abs(v[i] - v[j]));
            }
        }
    }

    int answer = 0;
    for (int i = 1; i <= N; i++) answer = max(answer, dp[M][i]);
    cout << answer;
    return 0;
}