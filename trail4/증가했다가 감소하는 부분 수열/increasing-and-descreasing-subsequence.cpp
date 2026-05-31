#include <iostream>
#include <algorithm>
#include <vector>

#define MX 1010

using namespace std;

int N;
int arr[MX];

int up[MX];
int down[MX];


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for (int i = 1; i <= N; i++) cin >> arr[i];
    
    for (int i = 1; i <= N; i++) {
        up[i] = 1;
        for (int j = 1; j < i; j++) {
            if(arr[j] < arr[i]) {
                up[i] = max(up[i], up[j] + 1);
            }
        }
    }

    for (int i = N; i >= 1; i--) {
        down[i] = 1;
        for (int j = N; j > i; j--) {
            if(arr[j] < arr[i]) {
                down[i] = max(down[i], down[j] + 1);
            }
        }
    }

    int answer = 1;
    for (int i = 1; i <= N; i++) answer = max(answer, up[i] + down[i] - 1);

    cout << answer;

    return 0;
}