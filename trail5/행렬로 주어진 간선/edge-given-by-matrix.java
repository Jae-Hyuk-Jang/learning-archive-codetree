import java.util.*;

public class Main {    
    public static final int MAX_N = 100;
    
    public static int[][] dist = new int[MAX_N + 1][MAX_N + 1];

    public static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++)
                dist[i][j] = sc.nextInt();
        }
        
        for(int i = 1; i <= n; i++) {
            dist[i][i] = 1;
        }

        for(int k = 1; k <= n; k++) 
            for(int i = 1; i <= n; i++)
                for(int j = 1; j <= n; j++)
                    if (dist[i][k] == 1 && dist[k][j] == 1)
                        dist[i][j] = 1;


        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
