import java.util.Scanner;
public class Main {
    public static int n, m;
    public static int[][] grid;
    public static int answer = -1;

    public static int getCount(int r1, int c1, int r2, int c2) {
        int cnt = 0;
        boolean check = true;

        for (int x = r1; x <= r2; x++) {
            for (int y = c1; y <= c2; y++) {
                if (grid[x][y] > 0) cnt++;
                else check = false;
            }
        }

        if(!check) cnt = -1;

        return cnt;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        grid = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        
        

        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < m; c1++) {
                for (int r2 = r1; r2 < n; r2++) {
                    for (int c2 = c1; c2 < m; c2++) {
                        int cnt = getCount(r1, c1, r2, c2);
                        answer = Math.max(answer, cnt);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}