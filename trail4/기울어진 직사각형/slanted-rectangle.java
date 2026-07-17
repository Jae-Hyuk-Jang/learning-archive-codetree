import java.util.Scanner;


public class Main {
    public static int n;
    public static int[][] grid;

    public static int[] dx = {-1, -1, 1, 1};
    public static int[] dy = {1, -1, -1, 1};

    public static int answer = 0;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    public static int getScore(int sx, int sy, int len1, int len2) {
        int[] length = {len1, len2, len1, len2 };

        int x = sx; int y = sy;
        int sum = grid[x][y];

        for (int dir = 0; dir < 4; dir++) {
            for (int step = 0; step < length[dir]; step++) {
                x += dx[dir];
                y += dy[dir];

                if (!inRange(x, y)) return -1;

                if (!(x == sx && y == sy)) sum += grid[x][y];
            }
        }

        if (x != sx || y != sy) return -1;
        return sum;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        grid = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int len1 = 1; len1 < n; len1++) {
                    for (int len2 = 1; len2 < n; len2++) {
                        int score = getScore(x, y, len1, len2);

                        if (score != -1) {
                            answer = Math.max(answer, score);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
        
    }
}