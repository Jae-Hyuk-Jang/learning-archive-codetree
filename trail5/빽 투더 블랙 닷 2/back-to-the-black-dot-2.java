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

    static int N, M;
    static int red1, red2;

    static ArrayList<Edge>[] graph;

    static final long INF = Long.MAX_VALUE / 4;

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

            if (nowDist != dist[now]) {
                continue;
            }

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

        red1 = sc.nextInt();
        red2 = sc.nextInt();

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        long[] dist1 = dijkstra(red1);
        long[] dist2 = dijkstra(red2);

        long answer = INF;

        for (int start = 1; start <= N; start++) {
            if (start == red1 || start == red2) {
                continue;
            }

            if (dist1[start] == INF || dist2[start] == INF || dist1[red2] == INF) {
                continue;
            }

            long total = dist1[start] + dist1[red2] + dist2[start];

            answer = Math.min(answer, total);
        }

        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}