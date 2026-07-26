import java.util.*;

public class Main {
    static int N, K;
    static int[][] cost;
    static int[][] dp;

    static final int INF = 1_000_000_000;

    public static int dfs(int cur, int visited) {
        int visitedCount = Integer.bitCount(visited) - 1;

        if (visitedCount == K) {
            if (cost[cur][0] == 0) return INF;
            return cost[cur][0];
        }

        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }

        dp[cur][visited] = INF;

        for (int next = 1; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            if (cost[cur][next] == 0) continue;

            int nextVisited = visited | (1 << next);
            int result = cost[cur][next] + dfs(next, nextVisited);
            dp[cur][visited] = Math.min(dp[cur][visited], result);
        }
        return dp[cur][visited];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        cost = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        int answer = dfs(0, 1);
        System.out.println(answer);
    }
}