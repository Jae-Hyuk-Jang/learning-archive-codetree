import java.util.*;

public class Main {
    public static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static class Node implements Comparable<Node> {
        int vertex; long dist;

        Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static int N, M, X;
    public static ArrayList<Edge>[] graph;
    public static ArrayList<Edge>[] reverseGraph;
    
    public static final long INF = Long.MAX_VALUE / 4;

    public static long[] dijkstra(ArrayList<Edge>[] g, int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            long nowDist = cur.dist;

            if (nowDist != dist[now]) continue;

            for (Edge edge : g[now]) {
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
        X = sc.nextInt();

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            graph[from].add(new Edge(to, cost));
            reverseGraph[to].add(new Edge(from, cost));
        }
        
        long[] distFromX = dijkstra(graph, X);
        long[] distToX = dijkstra(reverseGraph, X);

        long answer = 0;

        for (int i = 1; i <= N; i++) {
            long roundTrip = distToX[i] + distFromX[i];
            answer = Math.max(answer, roundTrip);
        }
        System.out.println(answer);
    }
}