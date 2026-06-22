#include <iostream>
#include <algorithm>

#define MX 100010

using namespace std;

int N, M;
int parent[MX];
int cnt[MX];

int Find(int x) {
    if(parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}

void Union(int a, int b) {
    a = Find(a);
    b = Find(b);

    if (a == b) return;
    if (a < b) {
        parent[a] = b;
        cnt[b] += cnt[a];
    }
    else {
        parent[b] = a;
        cnt[a] += cnt[b];
    } 
    return;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    cin >> N >> M;

    for (int i = 1; i <= N; i++) parent[i] = i, cnt[i] = 1;

    for (int i = 0; i< M; i++) {
        char x; int a, b;
        cin >> x;
        if (x == 'x') {
            cin >> a >> b;
            Union(a, b);
        }

        else {
            cin >> a;
            int root = Find(a);
            cout << cnt[root] << '\n';
        }
    }

    return 0;
}