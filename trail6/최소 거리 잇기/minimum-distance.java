import java.util.Scanner;
import java.util.Arrays;


public class Main {
    public static final int MX = 200;
    
    public static int n;
    public static int m;

    public static int[] uf;
    public static int[] x = new int[MX + 1];
    public static int[] y = new int[MX + 1];

    static class Edge implements Comparable<Edge> {
        int a, b;
        double cost;
        Edge(int a, int b, double cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.cost, e.cost);
        }
    }

    public static int find(int v) {
        if (uf[v] == v) return v;
        return uf[v] = find(uf[v]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a < b) uf[b] = a;
        else uf[a] = b;
        return true;
    }

    public static double getDist(int a, int b) {
        double dx = x[a] - x[b];
        double dy = y[a] - y[b];

        return Math.sqrt(dx * dx + dy * dy);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        uf = new int[n + 1];

        for (int i = 1; i <= n; i++) uf[i] = i;

        for (int i = 1; i <= n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a, b);
        }

        int edgeSize = n * (n - 1) / 2;
        Edge[] edges = new Edge[edgeSize];

        int edgeCnt = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double dist = getDist(i, j);
                edges[edgeCnt++] = new Edge(i, j, dist);
            }
        }

        Arrays.sort(edges);
        double answer = 0;

        for (int i = 0; i < edgeCnt; i++) {
            int a = edges[i].a;
            int b = edges[i].b;
            double cost = edges[i].cost;
            if(union(a, b)) answer += cost;
        }

        System.out.printf("%.2f\n", answer);
    }
}