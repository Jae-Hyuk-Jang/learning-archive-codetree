#include <iostream>
#include <algorithm>

#define MX 100010

using namespace std;

int N, M;
int parent[MX];

int Find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}

void Union(int a, int b) {
    a = Find(a);
    b = Find(b);

    if (a==b) return;

    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;

    for (int i = 1; i <= N; i++) parent[i] = i;
    for (int i = 1; i <= M; i++) {
        int a, b; cin >> a >> b;
        if (Find(a) == Find(b)) {
            cout << i << '\n';
            return 0;
        }

        Union(a, b);
    }

    cout << "happy" << '\n';
    return 0;
}