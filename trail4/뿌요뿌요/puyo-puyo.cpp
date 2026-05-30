#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int N;
int mat[101][101];
bool processed[101][101];

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

vector<int> vec;

int dfs(int x, int y, int cnt) {
    int result = 1;
    for(int i = 0; i< 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
        if (processed[nx][ny] || mat[x][y] != mat[nx][ny]) continue;
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

    int count = 0;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (processed[i][j]) continue;
            processed[i][j] = true;
            int cnt = dfs(i, j, 1);
            if(cnt >= 4) count++;
            vec.push_back(cnt);
        }
    }

    sort(vec.begin(), vec.end(), [](const int& a, const int& b) {return a > b;});

    cout << count << ' ' << vec[0];
    


    return 0;
}