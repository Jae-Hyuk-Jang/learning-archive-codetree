import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;

    public static int[] uf;
    public static Edge[] edges;

    static class Edge {
        int a, b, type;

        Edge(int a, int b, int type) {
            this.a = a;
            this.b = b;
            this.type = type;
        }
    }

    public static int find(int x) {
        if (uf[x] == x) return x;
        return uf[x] = find(uf[x]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a < b) uf[b] = a;
        else uf[a] = b;

        return true;
    }

    public static int kruskal(boolean maxZero) {
        uf = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            uf[i] = i;
        }

        if (maxZero) {
            // 0번 간선을 최대한 많이 사용
            Arrays.sort(edges, (e1, e2) -> e1.type - e2.type);
        } else {
            // 0번 간선을 최대한 적게 사용
            Arrays.sort(edges, (e1, e2) -> e2.type - e1.type);
        }

        int zeroCnt = 0;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            int a = edges[i].a;
            int b = edges[i].b;
            int type = edges[i].type;

            if (union(a, b)) {
                if (type == 0) {
                    zeroCnt++;
                }

                cnt++;

                if (cnt == n - 1) {
                    break;
                }
            }
        }

        return zeroCnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, type);
        }

        int maxZero = kruskal(true);
        int minZero = kruskal(false);

        long maxCost = 1L * maxZero * maxZero;
        long minCost = 1L * minZero * minZero;

        System.out.println(maxCost - minCost);
    }
}