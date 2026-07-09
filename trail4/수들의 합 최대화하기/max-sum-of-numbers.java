import java.util.Scanner;

public class Main {
    public static int N;
    public static int[][] grid;

    public static boolean[] processed;
    public static int answer = 0;

    public static void choose(int row, int sum) {
        if (row == N + 1) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (processed[i]) continue;

            processed[i] = true;
            choose(row + 1, sum + grid[row][i]);
            processed[i] = false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        processed = new boolean[N + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                grid[i][j] = sc.nextInt();
        
        choose(1, 0);
        System.out.println(answer);
    }
}