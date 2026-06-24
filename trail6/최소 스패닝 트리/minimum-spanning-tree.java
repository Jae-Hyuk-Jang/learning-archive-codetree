import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int a; 
        int b;
        int cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int N, M;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

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
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, cost));
        }

        edges.sort((x, y) -> x.cost - y.cost);

        long answer = 0;
        int cnt = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);

            int a = edge.a;
            int b = edge.b;
            int cost = edge.cost;

            if (Union(a, b)) {
                answer += cost;
                cnt++;

                if (cnt == N - 1) break;
            }
        }

        System.out.println(answer);

    }
}