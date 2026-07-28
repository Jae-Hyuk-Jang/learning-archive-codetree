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
    static ArrayList<Edge>[] graph;
    static boolean[][] blocked;

    static final long INF = Long.MAX_VALUE / 4;

    static long[] dijkstra(int start, boolean useBlocked) {
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

                if (useBlocked && blocked[now][next]) {
                    continue;
                }

                long nextDist = nowDist + edge.cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.add(new Node(next, nextDist));
                }
            }
        }

        return dist;
    }

    static void blockAPath(long[] distToN) {
        int cur = 1;

        while (cur != N) {
            for (Edge edge : graph[cur]) {
                int next = edge.to;
                int cost = edge.cost;

                if (distToN[cur] == cost + distToN[next]) {
                    blocked[cur][next] = true;
                    blocked[next][cur] = true;

                    cur = next;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N + 1];
        blocked = new boolean[N + 1][N + 1];

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

        // 사전순 경로 복원을 위해 인접 정점을 번호 오름차순으로 정렬
        for (int i = 1; i <= N; i++) {
            graph[i].sort((e1, e2) -> e1.to - e2.to);
        }

        // A의 사전순 최단 경로를 구하기 위해 N에서 다익스트라
        long[] distToN = dijkstra(N, false);

        // A가 지나간 간선 막기
        blockAPath(distToN);

        // B는 A가 지나간 간선을 사용하지 않고 1 -> N 최단거리
        long[] distB = dijkstra(1, true);

        if (distB[N] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(distB[N]);
        }
    }
}