import java.util.Scanner;


public class Main {
    public static int N, M, Q;
    public static int[][] grid;

    public static void shift(int row, int dir) {
        if (dir == 1) {
            int temp = grid[row][M - 1];
            for (int col = M - 1; col >= 1; col--) {
                grid[row][col] = grid[row][col-1];
            }
            grid[row][0] = temp;
        } else {
            int temp = grid[row][0];
            for (int col = 0; col < M - 1; col++) {
                grid[row][col] = grid[row][col + 1];
            }
            grid[row][M - 1] = temp;
        }
    }

    public static boolean canSpread(int row1, int row2) {
        for (int col = 0; col < M; col++) {
            if (grid[row1][col] == grid[row2][col]) return true;
        }
        return false;
    }

    public static void spreadUp(int row, int dir) {
        int curRow = row; 
        int curDir = dir;

        for (int nextRow = row - 1; nextRow >= 0; nextRow--) {
            if (!canSpread(curRow, nextRow)) break;
            curDir *= -1;
            shift(nextRow, curDir);
            curRow = nextRow;
        }
    }

    public static void spreadDown(int row, int dir) {
        int curRow = row; 
        int curDir = dir;

        for (int nextRow = row + 1; nextRow < N; nextRow++) {
            if (!canSpread(curRow, nextRow)) break;
            curDir *= -1;
            shift(nextRow, curDir);
            curRow = nextRow;
        }
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
            int row = sc.nextInt() - 1;
            char wind = sc.next().charAt(0);

            int dir;

            if (wind == 'L') dir = 1;
            else dir = -1;

            shift(row, dir);
            spreadUp(row, dir);
            spreadDown(row, dir);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }
}