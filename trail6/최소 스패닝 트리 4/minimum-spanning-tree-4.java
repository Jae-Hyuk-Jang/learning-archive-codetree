import java.util.*;

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
    static final int MAX_N = 10000;
    static final int MAX_M = 100000;

    static int N, M;
    static int[] parent = new int[MAX_N + 1];
    static char[] type = new char[MAX_N + 1];
    static Edge[] edges = new Edge[MAX_M + 1];

    static int Find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = Find(parent[x]);
    }

    static boolean Union(int a, int b) {
        a = Find(a);
        b = Find(b);

        if (a == b) return false;

        if (a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            type[i] = sc.next().charAt(0);
            parent[i] = i;
        }

        int edgeCnt = 0;

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            if (type[a] != type[b]) {
                edges[++edgeCnt] = new Edge(a, b, cost);
            }
        }

        Arrays.sort(edges, 1, edgeCnt + 1);

        long answer = 0;
        int cnt = 0;

        for (int i = 1; i <= edgeCnt; i++) {
            int a = edges[i].a;
            int b = edges[i].b;
            int cost = edges[i].cost;

            if (Union(a, b)) {
                answer += cost;
                cnt++;

                if (cnt == N - 1) {
                    break;
                }
            }
        }

        if (cnt == N - 1) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}