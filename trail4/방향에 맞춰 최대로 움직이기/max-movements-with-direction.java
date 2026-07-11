import java.util.Scanner;

public class Main {
    public static int N;

    public static int[][] dir = {
        {0, 0},
        {-1, 0},   // 1: 위
        {-1, 1},   // 2: 오른쪽 위
        {0, 1},    // 3: 오른쪽
        {1, 1},    // 4: 오른쪽 아래
        {1, 0},    // 5: 아래
        {1, -1},   // 6: 왼쪽 아래
        {0, -1},   // 7: 왼쪽
        {-1, -1}   // 8: 왼쪽 위
    };

    public static int[][] grid, arr;
    public static int answer = 0;

    public static void dfs(int x, int y, int move) {
        answer = Math.max(answer, move);

        int d = arr[x][y];

        for (int i = 1; i < N; i++) {
            int nx = x + dir[d][0] * i;
            int ny = y + dir[d][1] * i;

            if (oob(nx, ny)) {
                break;
            }

            if (grid[nx][ny] > grid[x][y]) {
                dfs(nx, ny, move + 1);
            }
        }
    }

    public static boolean oob(int x, int y) {
        return x < 1 || x > N || y < 1 || y > N;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        grid = new int[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int startX = sc.nextInt();
        int startY = sc.nextInt();

        dfs(startX, startY, 0);

        System.out.println(answer);
    }
}