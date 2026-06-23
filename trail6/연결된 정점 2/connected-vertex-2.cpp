#include <iostream>
#include <algorithm>

#define MX 100010

using namespace std;

int N;
int parent[MX];
int cnt[MX];

int Find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}

void Union(int a, int b) {
    a = Find(a);
    b = Find(b);

    if (a == b) return;

    if (a < b) {
        parent[b] = a;
        cnt[a] += cnt[b];
    }
    else {
        parent[a] = b;
        cnt[b] += cnt[a];
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i < MX; i++) {
        parent[i] = i;
        cnt[i] = 1;
    }

    for (int i = 0; i < N; i++) {
        int a, b; cin >> a >> b;
        Union(a, b);
        int root =Find(a);
        cout << cnt[root] << '\n';
    }
    return 0;
}