import java.util.*;


public class Main {

    public static class Edge {
        int to, len, c;
        Edge(int to, int len, int c) {
            this.to = to;
            this.len = len;
            this.c = c;
        }
    }

    public static class Node implements Comparable<Node> {
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
    public static long X;

    public static ArrayList<Edge>[] graph;
    public static ArrayList<Integer> cValues = new ArrayList<>();

    public static long INF = Long.MAX_VALUE / 4;

    public static long dijkstra(int minC) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            long nowDist = cur.dist;

            if (nowDist != dist[now]) continue;

            for (Edge edge : graph[now]) {
                if (edge.c < minC) continue;

                int next = edge.to;
                long nextDist = nowDist + edge.len;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }
        return dist[N];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextLong();

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int len = sc.nextInt();
            int c =sc.nextInt();

            graph[from].add(new Edge(to, len, c));
            graph[to].add(new Edge(from, len, c));

            cValues.add(c);
        }

        long answer = INF;

        for (int minC : cValues) {
            long dist = dijkstra(minC);

            if (dist == INF) continue;
            long totalTime = dist + X / minC;
            answer = Math.min(answer, totalTime);
        }

        System.out.println(answer);
    }
}