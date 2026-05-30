#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int N;
int mat[26][26];
bool processed[26][26];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

vector<int> vec;

int dfs(int x, int y, int cnt) {
    int result = 1;
    for(int i = 0; i< 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
        if (processed[nx][ny] || !mat[nx][ny]) continue;
        processed[nx][ny] = true;
        result += dfs(nx, ny, cnt + 1);
    }
    
    return result;
}


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> mat[i][j];
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (processed[i][j] || !mat[i][j]) continue;
            processed[i][j] = true;
            int cnt = dfs(i, j, cnt);
            vec.push_back(cnt);
        }
    }

    sort(vec.begin(), vec.end());

    cout << vec.size() << '\n';
    for (int v : vec) cout << v << '\n';


    return 0;
}