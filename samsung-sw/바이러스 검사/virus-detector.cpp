#include <iostream>
#include <algorithm>
using namespace std;
using ll = long long;

ll n, ldr, mbr;
ll arr[1000001];


int main() {
    cin >> n;
    for (int i = 0; i < n; i++) cin >> arr[i];
    cin >> ldr >> mbr;

    ll answer = 0;

    for (int i = 0; i < n; i++) {
        answer++;
        if(arr[i] == 0) continue;
        arr[i] -= ldr;
        if (arr[i] < 0) continue;
        ll member = arr[i] / mbr;
        ll extra = (arr[i] % mbr != 0) ? 1 : 0;
        member += extra;
        answer += member;
    }
    cout << answer;
    return 0;
}