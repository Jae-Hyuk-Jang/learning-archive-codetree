import java.util.Scanner;
import java.util.Arrays;

class Edge implements Comparable<Edge> {
    int a, b, cost;

    Edge(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}


public class Main {

    public static final int MX_N = 10000;
    public static final int MX_M = 100000;

    public static int n;
    public static int m;

    public static int[] parent = new int[MX_N + 1];
    public static Edge[] edges = new Edge[MX_M + 1];

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return false;

        if (a < b) parent[b] = a;
        else parent[a] = b;
        return true;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new Edge(a, b, cost);
        }

        Arrays.sort(edges, 1, m + 1);

        long answer = 0;
        int cnt = 0;

        for (int i = 1; i <= m && cnt < n - 2; i++) {
            int a = edges[i].a;
            int b = edges[i].b;
            int cost = edges[i].cost;

            if(union(a, b)) {
                answer += cost;
                cnt++;
            }
        }

        System.out.println(answer);
    }
}