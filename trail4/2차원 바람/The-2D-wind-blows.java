import java.util.Scanner;

public class Main {
    public static int N, M, Q;
    public static int[][] grid;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void rotate(int r1, int c1, int r2, int c2) {
        int temp = grid[r1][c1];

        for (int r = r1; r < r2; r++) {
            grid[r][c1] = grid[r + 1][c1];
        }

        for (int c = c1; c < c2; c++) {
            grid[r2][c] = grid[r2][c+1];
        }

        for (int r = r2; r > r1; r--) {
            grid[r][c2] = grid[r -1][c2];
        }

        for (int c =c2; c > c1; c--) {
            grid[r1][c] = grid[r1][c - 1];
        }
        grid[r1][c1 + 1] = temp;
    }

    public static void average(int r1, int c1, int r2, int c2) {
        int[][] next = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                next[i][j] = grid[i][j];
            }
        }

        for (int x = r1; x <= r2; x++) {
            for (int y = c1; y <= c2; y++) {
                int sum = grid[x][y];
                int count = 1;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny)) {
                        sum += grid[nx][ny];
                        count++;
                    }
                }
                next[x][y] = sum / count;
            }
        }
        grid = next;
    }

    public static void simulate(int r1, int c1, int r2, int c2) {
        rotate(r1, c1, r2, c2);
        average(r1, c1, r2, c2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Q = sc.nextInt();

        grid = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                grid[i][j] = sc.nextInt();
        

        for (int i = 0; i < Q; i++) {
            int r1 = sc.nextInt() - 1;
            int c1 = sc.nextInt() - 1;
            int r2 = sc.nextInt() - 1;
            int c2 = sc.nextInt() - 1;

            simulate(r1, c1, r2, c2);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}