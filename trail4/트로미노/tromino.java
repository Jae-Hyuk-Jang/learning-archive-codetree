import java.util.Scanner;

public class Main {
    public static int N, M;
    public static int[][] grid;
    public static int answer = 0;

    public static int[][][] dir1 = {
        { {0, 0}, {-1, 0}, {0, 1} },
        { {0, 0}, {0, -1}, {1, 0} },
        { {0, 0}, {0, -1}, {-1, 0} },
        { {0, 0}, {0, 1}, {1, 0} }
    };

    public static int[][][] dir2 = {
        { {0, 0}, {1, 0}, {2, 0} },
        { {0, 0}, {0, 1}, {0, 2} }  
    };



    public static boolean oob(int x, int y) {
        return x < 1 || y < 1 || x > N || y > M;
    }

    public static void check(int x, int y) {
        int sum = 0; boolean ok = true;
        for (int i = 0; i < 4; i++) {
            sum = 0;
            ok = true;
            for (int j = 0; j < 3; j++) {
                int nx = x + dir1[i][j][0];
                int ny = y + dir1[i][j][1];

                if (oob(nx, ny)) { ok = false; break;}
                sum += grid[nx][ny];
            }
            if(ok) answer = Math.max(answer, sum);
        }

        for (int i = 0; i < 2; i++) {
            sum = 0;
            ok = true;
            for (int j = 0; j < 3; j++) {
                int nx = x + dir2[i][j][0];
                int ny = y + dir2[i][j][1];

                if (oob(nx, ny)) { ok = false; break; }
                sum += grid[nx][ny];
            }
            if(ok) answer = Math.max(answer, sum);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        grid = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                check(i, j);
            }
        }

        System.out.println(answer);

    }
}