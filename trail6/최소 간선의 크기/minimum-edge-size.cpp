#include <iostream>
#include <algorithm>
#include <vector>

#define MX 100010

using namespace std;

struct Edge {
    int a; int b; int cost;
};

int N, M;
int A, B;
int parent[MX];

vector<Edge> edges;

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

bool cmp(Edge x, Edge y) {
    return x.cost > y.cost;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    cin >> A >> B;

    for (int i = 1; i <= N; i++) parent[i] = i;

    for (int i = 0; i < M; i++) {
        int a, b, cost; cin >> a >> b >> cost;
        edges.push_back({a, b, cost});
    }

    sort(edges.begin(), edges.end(), cmp);

    for (int i = 0; i < edges.size(); i++) {
        int a = edges[i].a; 
        int b = edges[i].b;
        int cost = edges[i].cost;

        Union(a, b);
        if (Find(A) == Find(B)) {
            cout << cost << '\n';
            return 0;
        }
    }
    return 0;
}