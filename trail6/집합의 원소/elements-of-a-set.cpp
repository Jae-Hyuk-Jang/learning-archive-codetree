#include <iostream>
#include <algorithm>

#define MX 100005

using namespace std;

int N, M;
int parent[MX];

int findParent(int x) {
    if(parent[x] == x) return x;
    return parent[x] = findParent(parent[x]);
}

void unionParent(int a, int b) {
    a = findParent(a);
    b = findParent(b);

    if (a== b) return;
    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) parent[i] = i;
    for (int i = 0; i < M; i++) {
        int op, a, b; cin >> op >> a >> b;
        if (op == 0) unionParent(a, b);
        else if (op == 1) {
            cout << ((findParent(a) == findParent(b)) ? 1 : 0) << '\n';
        }
    }
    return 0;
}