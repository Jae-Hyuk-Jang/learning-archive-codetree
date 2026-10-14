#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>
#include <queue> 
using namespace std;
using ll = long long;

const int MX = 55;

int N, T;
ll faith[MX][MX];
int food[MX][MX];

bool visited_[MX][MX];
bool defended[MX][MX];

int dx[4] = { -1, 1, 0, 0 }; // 0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽
int dy[4] = { 0, 0, -1, 1 };

struct Rep {
    int r, c;
    int mask;         // 음식 비트마스크
    ll power;  // 점심 끝난 직후 대표자의 신앙심
};

int toMask(char ch) {
    if (ch == 'T') return 1; // 민트
    if (ch == 'C') return 2; // 초코
    return 4;                // 우유
}

int bitCnt(int x) {
    int cnt = 0;
    while (x) {
        cnt += (x & 1);
        x >>= 1;
    }
    return cnt;
}

bool betterRep(int r1, int c1, int r2, int c2) {
    if (faith[r1][c1] != faith[r2][c2]) return faith[r1][c1] > faith[r2][c2];
    if (r1 != r2) return r1 < r2;
    return c1 < c2;
}

bool cmpRep(const Rep& a, const Rep& b) {
    int ca = bitCnt(a.mask);
    int cb = bitCnt(b.mask);

    // 단일(1비트) -> 이중(2비트) -> 삼중(3비트)
    if (ca != cb) return ca < cb;

    // 같은 부류 안에서는 신앙심 큰 순
    if (a.power != b.power) return a.power > b.power;

    // 행, 열 오름차순
    if (a.r != b.r) return a.r < b.r;
    return a.c < b.c;
}

// 아침
void simul1() {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            faith[i][j]++;
        }
    }
}

// 점심
vector<Rep> simul2() {
    memset(visited_, false, sizeof(visited_));

    vector<Rep> reps;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (visited_[i][j]) continue;

            queue<pair<int, int> > q;
            vector<pair<int, int> > cells;

            visited_[i][j] = true;
            q.push(make_pair(i, j));
            cells.push_back(make_pair(i, j));

            int rr = i;
            int cc = j;
            int mask = food[i][j];

            while (!q.empty()) {
                int x = q.front().first;
                int y = q.front().second;
                q.pop();

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                    if (visited_[nx][ny]) continue;
                    if (food[nx][ny] != mask) continue;

                    visited_[nx][ny] = true;
                    q.push(make_pair(nx, ny));
                    cells.push_back(make_pair(nx, ny));

                    if (betterRep(nx, ny, rr, cc)) {
                        rr = nx;
                        cc = ny;
                    }
                }
            }

            int sz = (int)cells.size();

            // 대표자에게 몰아주기
            for (int k = 0; k < sz; k++) {
                int x = cells[k].first;
                int y = cells[k].second;

                if (x == rr && y == cc) faith[x][y] += (sz - 1);
                else faith[x][y] -= 1;
            }

            Rep cur;
            cur.r = rr;
            cur.c = cc;
            cur.mask = mask;
            cur.power = faith[rr][cc]; // 점심 끝난 뒤 기준으로 저장
            reps.push_back(cur);
        }
    }

    sort(reps.begin(), reps.end(), cmpRep);
    return reps;
}

// 저녁
void simul3(const vector<Rep>& reps) {
    memset(defended, false, sizeof(defended));

    for (int i = 0; i < (int)reps.size(); i++) {
        int r = reps[i].r;
        int c = reps[i].c;

        // 다른 대표자에게 맞았으면 오늘 전파 못 함
        if (defended[r][c]) continue;

        ll b = faith[r][c];
        ll x = b - 1;   // 간절함
        int mask = food[r][c];
        int dir = (int)(b % 4);

        faith[r][c] = 1; // 자기 신앙심은 1만 남김

        if (x <= 0) continue;

        int nr = r + dx[dir];
        int nc = c + dy[dir];

        while (nr >= 1 && nr <= N && nc >= 1 && nc <= N && x > 0) {
            // 완전히 같은 음식이면 그냥 지나감
            if (food[nr][nc] == mask) {
                nr += dx[dir];
                nc += dy[dir];
                continue;
            }

            long long y = faith[nr][nc];

            // 다른 대표자에게 전파당했으므로 방어상태
            defended[nr][nc] = true;

            if (x > y) {
                // 강한 전파
                x -= (y + 1);
                food[nr][nc] = mask;
                faith[nr][nc] += 1;
            } else {
                // 약한 전파
                food[nr][nc] |= mask;
                faith[nr][nc] += x;
                x = 0;
                break;
            }

            nr += dx[dir];
            nc += dy[dir];
        }
    }
}

void printAnswer() {
    ll sum[8] = {0};

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            sum[food[i][j]] += faith[i][j];
        }
    }

    // 민트초코우유, 민트초코, 민트우유, 초코우유, 우유, 초코, 민트
    cout << sum[7] << ' ' << sum[3] << ' ' << sum[5] << ' '
         << sum[6] << ' ' << sum[4] << ' ' << sum[2] << ' ' << sum[1] << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> T;

    for (int i = 1; i <= N; i++) {
        string s;
        cin >> s;
        for (int j = 1; j <= N; j++) {
            food[i][j] = toMask(s[j - 1]);
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> faith[i][j];
        }
    }

    while (T--) {
        simul1();
        vector<Rep> reps = simul2();
        simul3(reps);
        printAnswer();
    }

    return 0;
}