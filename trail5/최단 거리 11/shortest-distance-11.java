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
    static int a, b;
    static ArrayList<Edge>[] graph;

    static final long INF = Long.MAX_VALUE / 4;

    public static long[] dijkstra(int start) {
        long[] dist = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

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

    public static ArrayList<Integer> getPath(long[] distA, long[] distB) {
        ArrayList<Integer> path = new ArrayList<>();

        int cur = a;
        path.add(cur);

        while (cur != b) {
            int bestNext = -1;

            for (Edge edge : graph[cur]) {
                int next = edge.to;
                int cost = edge.cost;

                if (distA[cur] + cost + distB[next] == distA[b]) {
                    if (bestNext == -1 || next < bestNext) {
                        bestNext = next;
                    }
                }
            }

            cur = bestNext;
            path.add(cur);
        }

        return path;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

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

        a = sc.nextInt();
        b = sc.nextInt();

        long[] distA = dijkstra(a);
        long[] distB = dijkstra(b);

        System.out.println(distA[b]);

        ArrayList<Integer> path = getPath(distA, distB);

        for (int node : path) {
            System.out.print(node + " ");
        }
    }
}