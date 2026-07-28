import java.util.*;

public class Main {    
    public static final int MAX_N = 100;
    public static final int INF = (int) 1e9;
    
    public static int[][] dist = new int[MAX_N + 1][MAX_N + 1];

    public static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            dist[a][b] = Math.min(dist[a][b], c);
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int answer = INF;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                if (dist[i][j] == INF || dist[j][i] == INF) continue;

                answer = Math.min(answer, dist[i][j] + dist[j][i]);
            }
        }

        System.out.println(answer);
    }
}