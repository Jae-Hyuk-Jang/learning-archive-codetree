#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

#define MX 100010

using namespace std;

int N, M;
int k[MX];

set<int> box;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) cin >> k[i];

    for (int i = 1; i <= N; i++) box.insert(i);

    int answer = 0;

    for (int i = 1; i <= M; i++) {
        auto it = box.upper_bound(k[i]);

        if (it == box.begin()) break;

        it--;

        box.erase(it);
        answer++;
    }

    cout << answer;

    
    return 0;
}