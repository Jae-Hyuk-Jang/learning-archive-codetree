import java.util.*;
import java.math.BigInteger;

public class Main {

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class OriginalEdge {
        int a, b, cost;

        OriginalEdge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static class DagEdge {
        int from, to;

        DagEdge(int from, int to) {
            this.from = from;
            this.to = to;
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
    static ArrayList<Edge>[] graph;
    static OriginalEdge[] edges;

    static ArrayList<DagEdge>[] dag;
    static ArrayList<DagEdge> dagEdges = new ArrayList<>();

    static final long INF = Long.MAX_VALUE / 4;

    static long[] dijkstra(int start) {
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

        graph = new ArrayList[N + 1];
        dag = new ArrayList[N + 1];
        edges = new OriginalEdge[M];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dag[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new OriginalEdge(a, b, cost);

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        long[] distStart = dijkstra(1);
        long[] distEnd = dijkstra(N);

        long shortest = distStart[N];

        // 최단 경로에 포함될 수 있는 간선만 방향을 정해서 DAG에 넣습니다.
        for (OriginalEdge edge : edges) {
            int a = edge.a;
            int b = edge.b;
            int cost = edge.cost;

            if (distStart[a] + cost + distEnd[b] == shortest) {
                DagEdge e = new DagEdge(a, b);
                dag[a].add(e);
                dagEdges.add(e);
            }

            if (distStart[b] + cost + distEnd[a] == shortest) {
                DagEdge e = new DagEdge(b, a);
                dag[b].add(e);
                dagEdges.add(e);
            }
        }

        Integer[] order = new Integer[N];

        for (int i = 0; i < N; i++) {
            order[i] = i + 1;
        }

        Arrays.sort(order, (x, y) -> Long.compare(distStart[x], distStart[y]));

        BigInteger[] fromStart = new BigInteger[N + 1];
        BigInteger[] toEnd = new BigInteger[N + 1];

        for (int i = 1; i <= N; i++) {
            fromStart[i] = BigInteger.ZERO;
            toEnd[i] = BigInteger.ZERO;
        }

        fromStart[1] = BigInteger.ONE;

        // 1번에서 각 정점까지 가는 최단 경로 개수
        for (int now : order) {
            for (DagEdge edge : dag[now]) {
                fromStart[edge.to] = fromStart[edge.to].add(fromStart[now]);
            }
        }

        toEnd[N] = BigInteger.ONE;

        // 각 정점에서 N번까지 가는 최단 경로 개수
        for (int i = N - 1; i >= 0; i--) {
            int now = order[i];

            for (DagEdge edge : dag[now]) {
                toEnd[now] = toEnd[now].add(toEnd[edge.to]);
            }
        }

        BigInteger totalPathCount = fromStart[N];

        int answer = 0;

        for (DagEdge edge : dagEdges) {
            BigInteger pathUsingEdge = fromStart[edge.from].multiply(toEnd[edge.to]);

            if (pathUsingEdge.equals(totalPathCount)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}