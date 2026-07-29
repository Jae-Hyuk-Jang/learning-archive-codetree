import java.util.*;

public class Main {
    public static final long INF = Long.MAX_VALUE / 4;

    public static int N, M;
    public static int v1, v2, e;

    public static long[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        v1 = sc.nextInt();
        v2 = sc.nextInt();
        e = sc.nextInt();

        dist = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            dist[a][b] = Math.min(dist[a][b], c);
            dist[b][a] = Math.min(dist[b][a], c);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (dist[i][k] == INF) continue;

                for (int j = 1; j <= N; j++) {
                    if (dist[k][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        long answer = INF;

        for (int mid = 1; mid <= N; mid++) {
            if (dist[v1][mid] == INF) continue;
            if (dist[v2][mid] == INF) continue;
            if (dist[mid][e] == INF) continue;

            long cost = dist[v1][mid] + dist[v2][mid] + dist[mid][e];
            answer = Math.min(answer, cost);
        }

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}