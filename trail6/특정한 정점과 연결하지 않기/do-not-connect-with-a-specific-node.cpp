#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010

using namespace std;

int N, M;
int A, B, K;

int parent[MX];
int cnt[MX];

vector<int> sizes;

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

    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        parent[i] = i;
        cnt[i] = 1;
    }

    for (int i = 0; i< M; i++) {
        int x, y; cin >> x >> y;
        Union(x, y);
    }

    cin >> A >> B >> K;
    
    int rootA = Find(A);
    int rootB = Find(B);

    int answer = cnt[rootA];

    for (int i = 1; i <= N; i++) {
        if (Find(i) != i) continue;

        if (i == rootA) continue;
        if (i == rootB) continue;

        sizes.push_back(cnt[i]);
    }
    sort(sizes.begin(), sizes.end(), greater<int>());

    for (int i = 0; i < sizes.size() && i < K; i++) {
        answer += sizes[i];
    }

    cout << answer;
    return 0;
}