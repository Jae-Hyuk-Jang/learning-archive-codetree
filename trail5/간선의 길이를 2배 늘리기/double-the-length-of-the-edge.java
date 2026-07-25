import java.util.*;

public class Main {

    public static class Edge {
        int id, to, cost;

        Edge(int id, int to, int cost) {
            this.id = id;
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
    public static ArrayList<Edge>[] graph;

    public static int[] prevNode, prevEdge;

    public static final long INF = Long.MAX_VALUE / 4;

    public static long dijkstra(int doubleEdgeId, boolean savePath) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        pq.add(new Node(1, 0));

        if(savePath) {
            Arrays.fill(prevNode, -1);
            Arrays.fill(prevEdge, -1);
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.vertex;
            long nowDist = cur.dist;

            if(nowDist != dist[now]) continue;

            for (Edge edge : graph[now]) {
                int next = edge.to;
                int cost = edge.cost;

                if (edge.id == doubleEdgeId) {
                    cost *= 2;
                }

                long nextDist = nowDist + cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));

                    if (savePath) {
                        prevNode[next] = now;
                        prevEdge[next] = edge.id;
                    }
                }
            }
        }
        return dist[N];
    }

    public static ArrayList<Integer> getShortestPathEdges() {
        ArrayList<Integer> pathEdges = new ArrayList<>();

        int cur = N;

        while (cur != 1) {
            pathEdges.add(prevEdge[cur]);
            cur = prevNode[cur];
        }

        return pathEdges;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N + 1];
        prevNode = new int[N + 1];
        prevEdge = new int[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int id = 0; id < M; id++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            graph[a].add(new Edge(id, b, cost));
            graph[b].add(new Edge(id, a, cost));
        }

        long originalDist = dijkstra(-1, true);

        ArrayList<Integer> pathEdges = getShortestPathEdges();

        long maxDist = originalDist;

        for (int edgeId : pathEdges) {
            long newDist = dijkstra(edgeId, false);
            maxDist = Math.max(maxDist, newDist);
        }
        System.out.println(maxDist - originalDist);
    }
}