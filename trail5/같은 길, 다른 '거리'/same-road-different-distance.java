import java.util.*;


public class Main {
    public static class Edge {
        int to; long cost;
        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Info {
        int from, to; long a, b;
        Info(int from, int to, long a, long b) {
            this.from = from;
            this.to = to;
            this.a = a;
            this.b = b;
        }
    }

    static class Node implements Comparable<Node> {
        int v; long dist;

        Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static int N, M;
    static final long INF = Long.MAX_VALUE / 4;

    public static ArrayList<Edge>[] revA, revB, penaltyGraph;
    public static ArrayList<Info> edges = new ArrayList<>();

    static long[] dijkstra(ArrayList<Edge>[] graph, int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist != dist[cur.v]) continue;

            for (Edge edge : graph[cur.v]) {
                int next = edge.to;
                long nextDist = cur.dist + edge.cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }
        return dist;
    }

    static int getPenalty(Info e, long[] distA, long[] distB) {
        int penalty = 0;
        if (distA[e.from] != e.a + distA[e.to]) penalty++;
        if (distB[e.from] != e.b + distB[e.to]) penalty++;
        return penalty;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();

        revA = new ArrayList[N + 1];
        revB = new ArrayList[N + 1];
        penaltyGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            revA[i] = new ArrayList<>();
            revB[i] = new ArrayList<>();
            penaltyGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            long a = sc.nextLong();
            long b = sc.nextLong();

            edges.add(new Info(from, to, a, b));

            revA[to].add(new Edge(from, a));
            revB[to].add(new Edge(from, b));
        }

        long[] distA = dijkstra(revA, N);
        long[] distB = dijkstra(revB, N);

        for (Info e : edges) {
            int penalty = getPenalty(e, distA, distB);
            penaltyGraph[e.from].add(new Edge(e.to, penalty));
        }

        long[] answer = dijkstra(penaltyGraph, 1);
        System.out.println(answer[N]);
    }
}