import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N, M;
    public static int[][] grid;
    public static int[][] prefix;

    static class Rect {
        int r1, c1, r2, c2;
        int sum;
        Rect(int r1, int c1, int r2, int c2, int sum) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.sum = sum;
        }
    }

    public static ArrayList<Rect> rectangles = new ArrayList<>();

    public static int getSum(int r1, int c1, int r2, int c2) {
        return prefix[r2][c2] - prefix[r1-1][c2] - prefix[r2][c1- 1] + prefix[r1-1][c1-1];
    }

    public static boolean isOverlap(Rect a, Rect b) {
        if (a.r2 < b.r1 || b.r2 < a.r1) return false;
        if (a.c2 < b.c1 || b.c2 < a.c1) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        grid = new int[N + 1][M + 1];
        prefix = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                grid[i][j] = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefix[i][j] = grid[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        for (int r1 = 1; r1 <= N; r1++) {
            for (int c1 = 1; c1 <= M; c1++) {
                for (int r2 = r1; r2 <= N; r2++) {
                    for (int c2 = c1; c2 <= M; c2++) {
                        int sum = getSum(r1, c1, r2, c2);
                        rectangles.add(new Rect(r1, c1, r2, c2, sum));
                    }
                }
            }
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < rectangles.size(); j++) {
                Rect a = rectangles.get(i);
                Rect b = rectangles.get(j);

                if(!isOverlap(a, b)) {
                    answer = Math.max(answer, a.sum + b.sum);
                }
            }
        }
        System.out.println(answer);
    }
}