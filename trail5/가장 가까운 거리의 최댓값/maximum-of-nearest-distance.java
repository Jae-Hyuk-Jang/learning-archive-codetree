import java.util.*;

public class Main {
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        long dist;

        Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static int N, M;
    public static int A, B, C;

    public static ArrayList<Edge>[] graph;

    public static final long INF = Long.MAX_VALUE / 4;

    public static long[] dijkstra(int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            long nowDist = cur.dist;

            if (dist[now] != nowDist) continue;

            for (Edge edge : graph[now]) {
                int next = edge.to;
                long nextDist = nowDist + edge.cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph[start].add(new Edge(end, cost));
            graph[end].add(new Edge(start, cost));
        }

        long[] distA = dijkstra(A);
        long[] distB = dijkstra(B);
        long[] distC = dijkstra(C);

        long answer = 0;

        for (int i = 1; i <= N; i++) {
            long nearest = Math.min(distA[i], distB[i]);
            nearest = Math.min(nearest, distC[i]);

            answer = Math.max(answer, nearest);
        }

        System.out.println(answer);
    }
}