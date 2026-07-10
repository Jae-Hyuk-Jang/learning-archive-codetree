import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N;
    public static int[][] grid;
    public static boolean[] processed;

    public static final int MX = Integer.MAX_VALUE;
    public static int answer = MX;

    public static void choose(int cur, int cnt, int sum) {
        if (cnt == N - 1) {
            if (grid[cur][1] == 0) return;
            answer = Math.min(answer, sum + grid[cur][1]);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (processed[i] || grid[cur][i] == 0) continue;

            processed[cur] = true;
            choose(i, cnt + 1, sum + grid[cur][i]);
            processed[cur] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        processed = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        choose(1, 0, 0);

        System.out.println(answer);

    }
}