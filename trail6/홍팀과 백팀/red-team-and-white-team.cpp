#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

#define MX 100010

using namespace std;

int N, M;
vector<int> graph[MX];
int color[MX];

bool bfs(int start) {
    queue<int> q;
    q.push(start);
    color[start] = 0;

    while(!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur][i];

            if (color[next] == -1) {
                color[next] = 1 - color[cur];
                q.push(next);
            } 
            else {
                if (color[next] == color[cur]) {
                    return false;
                }
            }
        }
    }

    return true;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) color[i] = -1;
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    for (int i = 1; i <= N; i++) {
        if (color[i] == -1) {
            if(!bfs(i)) {
                cout << 0 << '\n';
                return 0;
            }
        }
    }

    cout << 1 << '\n';

    return 0;
}