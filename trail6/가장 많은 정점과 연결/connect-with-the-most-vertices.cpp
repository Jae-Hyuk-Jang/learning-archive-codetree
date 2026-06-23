#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010

using namespace std;
using ll = long long;

int N, M;
ll K;

int parent[MX];
ll value[MX];
ll compMin[MX];

int Find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}

void Union(int a, int b) {
    a = Find(a);
    b = Find(b);

    if (a == b) return;

    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M >> K;
    for (int i = 1; i <= N; i++) {
        cin >> value[i]; 
        parent[i] = i;
        compMin[i] = 1e18;
    }

    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        Union(a, b);
    }
 
    for (int i = 1; i <= N; i++) {
        int root = Find(i);

        compMin[root] = min(compMin[root], value[i]);
    }

    vector<ll> mins;

    for (int i = 1; i <= N; i++) {
        if (Find(i) == i) mins.push_back(compMin[i]);
    }

    if (mins.size() == 1) {
        cout << 0 << '\n';
        return 0;
    }

    sort(mins.begin(), mins.end());

    ll answer = 0;
    ll base = mins[0];

    for (int i = 1; i < mins.size(); i++) {
        answer += (base + mins[i]);
    }

    if (answer > K) cout << "NO" << '\n';
    else cout << answer << '\n';


    return 0;
}