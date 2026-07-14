import java.util.Scanner;

public class Main {
    public static int N;
    public static int[][] grid;

    public static int getCoinCount(int row, int col) {
        int count = 0;

        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int answer = 0;

        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= N - 3; j++) {
                answer = Math.max(answer, getCoinCount(i, j));
            }
        }

        System.out.println(answer);
    }
}