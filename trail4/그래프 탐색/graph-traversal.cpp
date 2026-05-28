#include <iostream>
#include <algorithm>
#include <vector>

#define MX 1001

using namespace std;

int N, M;
vector<int> adj[MX];
bool processed[MX];

int cnt = 0;

void dfs(int edge) {

    for (int next : adj[edge]) {
        if(processed[next]) continue;
        processed[next] = true;
        cnt++;
        dfs(next);
    }
    return;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 0; i< M; i++) {
        int a, b; cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    processed[1] = true;
    dfs(1);

    cout << cnt;



    return 0;
}