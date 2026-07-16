import java.util.Scanner;

public class Main {
    public static int N, M;
    public static int[][] grid;

    public static int answer = 0;

    public static int getGoldCount(int cx, int cy, int k) {
        int count = 0;

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                int dist = Math.abs(cx - x) + Math.abs(cy - y);

                if (dist <= k && grid[x][y] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int getCost(int k) {
        return k * k + (k + 1) * (k + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        grid = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int k = 0; k <= 2 * N; k++) {
                    int goldCnt = getGoldCount(x, y, k);
                    int cost = getCost(k);
                    int profit = goldCnt * M;

                    if (profit >= cost) {
                        answer = Math.max(answer, goldCnt);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}