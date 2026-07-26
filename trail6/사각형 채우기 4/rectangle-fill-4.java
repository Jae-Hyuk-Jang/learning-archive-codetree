import java.util.*;

public class Main {
    public static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n + 1];

        dp[0] = 1;
        if (n >= 2) dp[2] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = (4 * dp[i - 2] - dp[i - 4]) % MOD;
            if (dp[i] < 0)dp[i] += MOD;
        }
        System.out.println(dp[n]);
    }
}