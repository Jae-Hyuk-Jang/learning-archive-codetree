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
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[] dist;

    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[N] = 0;
        pq.add(new Node(N, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            int nowDist = cur.dist;

            if (nowDist != dist[now]) {
                continue;
            }

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
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph[end].add(new Edge(start, cost));
        }

        dijkstra();

        int answer = 0;
        for (int i = 1; i < N; i++) {
            answer = Math.max(answer, dist[i]);
        }
        System.out.print(answer);
    }
}