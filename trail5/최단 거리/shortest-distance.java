import java.util.*;

class Edge {
    int x, y, z;
    
    public Edge(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
};

public class Main {    
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int[][] dist = new int[MAX_N + 1][MAX_N + 1];

    public static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1; i <= n; i++) 
            for(int j = 1; j <= n; j++)
                dist[i][j] = sc.nextInt();
        
        for(int k = 1; k <= n; k++) 
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        for (int i = 0; i < m; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            System.out.println(dist[a][b]);
        }
    
    }
}
