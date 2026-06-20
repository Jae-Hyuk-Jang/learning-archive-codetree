#include <iostream>
#include <algorithm>

#define MX 100010
using namespace std;

int n, m;
int parent[MX];

int findParent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = findParent(parent[x]);
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for (int i = 1; i <= n; i++) parent[i] = i;
    int answer = n;

    for (int i = 0; i < m; i++) {
        int a, b; cin >> a >> b;
        int cur = findParent(a);

        while (cur < b) {
            answer--;
            parent[cur] = findParent(cur + 1);
            cur = findParent(cur);
        }
        cout << answer << '\n';
    }

    return 0;
}