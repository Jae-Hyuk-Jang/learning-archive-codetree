import java.util.Scanner;


public class Main {
    public static final int MX_N = 20;
    public static final int MX_BOMB = 10;

    public static int n;
    public static int bombCnt = 0;
    public static int answer = 0;

    public static int[] bombR = new int[MX_BOMB];
    public static int[] bombC = new int[MX_BOMB];

    public static int[][] visited = new int[MX_N][MX_N];

    public static int[][] dr = {
        {-2, -1, 0, 1, 2},
        {-1, 0, 0, 0, 1},
        {-1, -1, 0, 1, 1}
    };

    public static int[][] dc = {
        {0, 0, 0, 0, 0},
        {0, -1, 0, 1, 0},
        {-1, 1, 0, -1, 1}
    };

    public static boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    public static void choose(int idx, int cnt) {
        if (idx == bombCnt) {
            answer = Math.max(answer, cnt);
            return;
        }

        int r = bombR[idx];
        int c = bombC[idx];

        for (int type = 0; type < 3; type++) {
            int added = 0;

            for (int i = 0; i < 5; i++) {
                int nr = r + dr[type][i];
                int nc = c + dc[type][i];

                if (!inRange(nr, nc)) continue;
                if (visited[nr][nc] == 0) added++;
                visited[nr][nc]++;
            }

            choose(idx+1, cnt + added);

            for (int i = 0; i < 5; i++) {
                int nr = r + dr[type][i];
                int nc = c + dc[type][i];
                if(!inRange(nr, nc)) continue;
                visited[nr][nc]--;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = sc.nextInt();
                if (value == 1) {
                    bombR[bombCnt] = i;
                    bombC[bombCnt] = j;
                    bombCnt++;
                }
            }
        }

        choose(0, 0);

        System.out.println(answer);
    }
}