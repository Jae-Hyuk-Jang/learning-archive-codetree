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
    public static final int MX = 300;

    public static int n;
    public static int m;

    public static int[] uf;

    public static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return false;

        if (a < b) uf[b] = a;
        else uf[a] = b;
        return true;
    }

    public static int getNodeNumber(int row, int col) {
        return row * m + col + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        int totalNode = n * m;
        int totalEdge = n * (m - 1) + (n -1) * m;
        
        uf = new int[totalNode + 1];
        Edge[] edges = new Edge[totalEdge];

        for (int i = 1; i <= totalNode; i++) uf[i] = i;

        int edgeCnt = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m - 1; col++) {
                int cost = sc.nextInt();

                int a = getNodeNumber(row, col);
                int b = getNodeNumber(row, col + 1);

                edges[edgeCnt++] = new Edge(a, b, cost);
            }
        }

        for (int row = 0; row < n -1; row++) {
            for (int col = 0; col < m; col++) {
                int cost = sc.nextInt();

                int a = getNodeNumber(row, col);
                int b = getNodeNumber(row + 1, col);

                edges[edgeCnt++] = new Edge(a, b, cost);
            }
        }

        Arrays.sort(edges);

        long answer = 0;
        int cnt = 0;

        for (int i = 0; i< totalEdge; i++) {
            int a = edges[i].a; 
            int b = edges[i].b;
            int cost = edges[i].cost;

            if (union(a, b)) {
                answer += cost;
                cnt++;

                if (cnt == totalNode - 1) break;
            }
        }
        System.out.println(answer);
    }
}