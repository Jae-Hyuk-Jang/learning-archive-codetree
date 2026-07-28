import java.util.*;

public class Main {
    public static final long INF = Long.MAX_VALUE / 4;

    public static int N, M, P, Q;
    public static long[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        P = sc.nextInt();
        Q = sc.nextInt();

        dist = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            dist[from][to] = Math.min(dist[from][to], cost);
        }

        // 플로이드 워셜: 모든 정점 쌍 최단거리
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (dist[i][k] == INF) continue;

                for (int j = 1; j <= N; j++) {
                    if (dist[k][j] == INF) continue;

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int possibleCount = 0;
        long totalCost = 0;

        for (int i = 0; i < Q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            long best = INF;

            // 빨간 점은 1번부터 P번까지
            for (int red = 1; red <= P; red++) {
                if (dist[a][red] == INF || dist[red][b] == INF) continue;

                best = Math.min(best, dist[a][red] + dist[red][b]);
            }

            if (best != INF) {
                possibleCount++;
                totalCost += best;
            }
        }

        System.out.println(possibleCount);
        System.out.println(totalCost);
    }
}