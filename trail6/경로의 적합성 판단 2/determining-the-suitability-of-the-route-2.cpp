#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010

using namespace std;

int n, m, k;
int parent[MX];

int findParent(int x) {
    if (parent[x] == x) return x;

    return parent[x] = findParent(parent[x]);
}

void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);

    if (a == b) return;

    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m >> k;

    for (int i = 1; i <= n; i++) {
        parent[i] = i;
    }

    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;

        unionParent(x, y);
    }

    vector<int> path;

    for (int i = 0; i < k; i++) {
        int x;
        cin >> x;

        path.push_back(x);
    }

    for (int i = 0; i + 1 < k; i++) {
        int a = path[i];
        int b = path[i + 1];

        if (findParent(a) != findParent(b)) {
            cout << 0 << '\n';
            return 0;
        }
    }

    cout << 1 << '\n';

    return 0;
}