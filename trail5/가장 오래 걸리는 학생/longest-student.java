import java.util.*;

public class Main {
    public static class Edge {
        int to; int cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static class Node implements Comparable<Node> {
        int vertex; int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static int N, M;
    public static ArrayList<Edge>[] graph;
    public static int[] dist;

    public static final int INF = (int)1e9;

    public static void dijkstra(int goal) {
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[N] = 0;
        pq.add(new Node(N, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            int nowDist = cur.dist;

            if (nowDist != dist[now]) continue;

            for (Edge edge : graph[now]) {
                int next = edge.to;
                int nextDist = nowDist + edge.cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph[b].add(new Edge(a, c));
            graph[a].add(new Edge(b, c));
        }

        dijkstra(N);

        int answer = 0;
        for (int i = 1; i < N; i++) {
            answer = Math.max(answer, dist[i]);
        }

        System.out.println(answer);
    }
}