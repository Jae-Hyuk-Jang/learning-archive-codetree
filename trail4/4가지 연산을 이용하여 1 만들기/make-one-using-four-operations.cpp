#include <iostream>
#include <algorithm>
#include <queue>

#define MX 1000010

using namespace std;

int N;
bool processed[MX];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    queue<pair<int, int>> q;
    q.push({N, 0});

    while(!q.empty()) {
        int cur = q.front().first;
        int cnt = q.front().second;
        q.pop();

        if (cur == 1) {
            cout << cnt << '\n';
            return 0;
        }

        if (cur % 2 == 0 && !processed[cur/2]) { 
            processed[cur/2] = true;
            q.push({cur/2, cnt+1});
        }

        if (cur < N + 1 && !processed[cur + 1]) {
            processed[cur + 1] = true;
            q.push({cur + 1, cnt + 1});
        }

        if (cur % 3 == 0 && !processed[cur/3]) { 
            processed[cur/3] = true;
            q.push({cur/3, cnt+1});
        }

        if (cur > 1 && !processed[cur - 1]) {
            processed[cur - 1] = true;
            q.push({cur - 1, cnt + 1});
        }
        
    }


    return 0;
}