#include <iostream>
#include <algorithm>
#include <cstring>
#include <vector>

#define MX 1010

using namespace std;
using ll = long long;

struct Work {int s, e, p;};

int N;
Work work[MX];
ll dp[MX];

bool cmp(Work a, Work b) {
    if (a.e != b.e) return a.e < b.e;
    return a.s < b.s;
}

int findPrev(int idx) {
    int left = 1;
    int right = idx - 1;
    int ret = 0;

    while (left <= right) {
        int mid = (left + right) / 2;

        if (work[mid].e < work[idx].s) {
            ret = mid;
            left = mid + 1;
        }
        else {
            right = mid - 1;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        cin >> work[i].s >> work[i].e >> work[i].p;
    }

    sort(work + 1, work + N + 1, cmp);

    for (int i = 1; i <= N; i++) {
        int prev = findPrev(i);
        dp[i] = max(dp[i-1], dp[prev] + work[i].p);
    }
    cout << dp[N];
    return 0;
}