import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N;
    public static int answer = -Integer.MAX_VALUE;

    public static int[][] grid;
    public static boolean[] processed;

    public static void choose(int cur, int mini) {
        if (cur == N + 1) {
            answer = Math.max(answer, mini);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(processed[i]) continue;

            processed[i] = true;
            int next = Math.min(mini, grid[cur][i]);
            choose(cur + 1, next);
            processed[i] = false;
        }
    } 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        grid = new int[N + 1][N + 1];
        processed = new boolean[N + 1];

        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        choose(1, Integer.MAX_VALUE);

        System.out.println(answer);
        
    }
}