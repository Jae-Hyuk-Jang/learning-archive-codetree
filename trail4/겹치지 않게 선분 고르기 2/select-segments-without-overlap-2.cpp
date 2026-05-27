#include <iostream>
#include <algorithm>
#include <vector>

#define MX 1010

using namespace std;
using pii = pair<int,int>;

int N;
vector<pii> lines;

bool comp(pii a, pii b) {
    if(a.second != b.second) return a.second < b.second;
    return a.first < b.first;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 0; i < N; i++) {
        int x1, x2; cin >> x1 >> x2;
        lines.push_back({x1, x2});
    }

    sort(lines.begin(), lines.end(), comp);
    int answer = 0;
    int lastEnd = -1;

    for (int i = 0; i < lines.size(); i++) {
        int x1 = lines[i].first;
        int x2 = lines[i].second;
        if(x1 > lastEnd) {
            answer++;
            lastEnd = x2;
        }
    }
    cout << answer;
    return 0;
}