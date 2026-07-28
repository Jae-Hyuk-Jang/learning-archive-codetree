import java.util.*;


public class Main {    
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int[][] dist = new int[MAX_N + 1][MAX_N + 1];

    public static int n, m;
    public static final int INF = (int)1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                dist[i][j] = 0;
            }
            dist[i][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            dist[a][b] = 1;
        }

        for(int k = 1; k <= n; k++) 
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= n; j++)
                    if (dist[i][k] == 1 && dist[k][j] == 1)
                        dist[i][j] = 1;
        
        for (int i = 1; i <= n; i++)  {
            int answer = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (dist[i][j] == 0 && dist[j][i] == 0) answer++;
            }
            System.out.println(answer);
        }

    
    }
}