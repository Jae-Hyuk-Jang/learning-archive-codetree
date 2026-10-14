#include <iostream>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;
using pii = pair<int, int>;
using ll = long long;
using vii = vector<pii>;

int N, Q;
vector<vector<int> > board;
vector<int> areaSize;

// 현재 board를 스캔해서 각 id가 차지한 칸 목록을 만든다.
vector<vii> collectCells() {
    vector<vii> cells(Q + 1);
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            int id = board[x][y];
            if (id != 0) cells[id].push_back({x, y});
        }
    }
    return cells;
}

// 특정 id의 남아 있는 칸들이 하나의 연결 성분인지 확인
bool isConnected(const vii& cells, int id) {
    if (cells.empty()) return true;

    vector<vector<int> > visited(N, vector<int>(N, 0));
    queue<pii> q;

    q.push(cells[0]);
    visited[cells[0].first][cells[0].second] = 1;

    int cnt = 1;
    int dx[4] = {1, -1, 0, 0};
    int dy[4] = {0, 0, 1, -1};

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;
            if (board[nx][ny] != id) continue;

            visited[nx][ny] = 1;
            q.push({nx, ny});
            cnt++;
        }
    }

    return cnt == (int)cells.size();
}

// 새 미생물 id를 직사각형 [x1, x2) x [y1, y2)에 투입
void insertMicrobe(int id, int x1, int y1, int x2, int y2) {
    set<int> touched;

    for (int x = x1; x < x2; x++) {
        for (int y = y1; y < y2; y++) {
            if (board[x][y] != 0) touched.insert(board[x][y]);
            board[x][y] = id;
        }
    }

    areaSize[id] = (x2 - x1) * (y2 - y1);

    // 덮어씌워진 기존 무리들이 찢어졌는지 검사
    vector<vii> cells = collectCells();

    for (int oldId : touched) {
        if (cells[oldId].empty()) {
            areaSize[oldId] = 0;
            continue;
        }

        if (!isConnected(cells[oldId], oldId)) {
            // 둘 이상으로 분리되면 전부 사라짐
            for (auto cell : cells[oldId]) {
                board[cell.first][cell.second] = 0;
            }
            areaSize[oldId] = 0;
        } else {
            areaSize[oldId] = (int)cells[oldId].size();
        }
    }
}

// 현재 모양을 좌상단 기준 상대좌표로 변환
vii makeRelative(const vii& cells, int& width, int& height) {
    int minX = N, minY = N;
    int maxX = -1, maxY = -1;

    for (auto cell : cells) {
        minX = min(minX, cell.first);
        minY = min(minY, cell.second);
        maxX = max(maxX, cell.first);
        maxY = max(maxY, cell.second);
    }

    vii rel;
    for (auto cell : cells) {
        rel.push_back({cell.first - minX, cell.second - minY});
    }

    width = maxX - minX + 1;
    height = maxY - minY + 1;
    return rel;
}

// 새 배양 용기로 이동
void moveAll() {
    vector<vii> cells = collectCells();
    vector<int> ids;

    for (int id = 1; id <= Q; id++) {
        if (!cells[id].empty()) ids.push_back(id);
    }

    sort(ids.begin(), ids.end(), [&](int a, int b) {
        if (areaSize[a] != areaSize[b]) return areaSize[a] > areaSize[b];
        return a < b;
    });

    vector<vector<int> > nextBoard(N, vector<int>(N, 0));

    for (int id : ids) {
        int width, height;
        vector<pii> rel = makeRelative(cells[id], width, height);

        bool placed = false;

        // x가 가장 작게, 그 다음 y가 가장 작게
        for (int baseX = 0; baseX + width <= N && !placed; baseX++) {
            for (int baseY = 0; baseY + height <= N && !placed; baseY++) {
                bool ok = true;

                for (auto cell : rel) {
                    int nx = baseX + cell.first;
                    int ny = baseY + cell.second;
                    if (nextBoard[nx][ny] != 0) {
                        ok = false;
                        break;
                    }
                }

                if (!ok) continue;

                for (auto cell : rel) {
                    int nx = baseX + cell.first;
                    int ny = baseY + cell.second;
                    nextBoard[nx][ny] = id;
                }

                placed = true;
            }
        }

        // 어디에도 못 놓으면 사라짐
        if (!placed) areaSize[id] = 0;
    }

    board = nextBoard;
}

// 현재 실험 결과 점수 계산
ll getScore() {
    set<ll> used;
    ll score = 0;

    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            int a = board[x][y];
            if (a == 0) continue;

            // 오른쪽
            if (x + 1 < N) {
                int b = board[x + 1][y];
                if (b != 0 && a != b) {
                    int u = min(a, b);
                    int v = max(a, b);
                    ll key = 1LL * u * (Q + 1) + v;
                    if (used.insert(key).second) {
                        score += 1LL * areaSize[u] * areaSize[v];
                    }
                }
            }

            // 위쪽
            if (y + 1 < N) {
                int b = board[x][y + 1];
                if (b != 0 && a != b) {
                    int u = min(a, b);
                    int v = max(a, b);
                    ll key = 1LL * u * (Q + 1) + v;
                    if (used.insert(key).second) {
                        score += 1LL * areaSize[u] * areaSize[v];
                    }
                }
            }
        }
    }

    return score;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> Q;

    board.assign(N, vector<int>(N, 0));
    areaSize.assign(Q + 1, 0);

    for (int id = 1; id <= Q; id++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;

        insertMicrobe(id, x1, y1, x2, y2);
        moveAll();
        cout << getScore() << '\n';
    }

    return 0;
}