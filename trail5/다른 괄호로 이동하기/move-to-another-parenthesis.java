import java.util.*;

public class Main {
    

    static class Node implements Comparable<Node> {
        int x, y; long dist;
        
        Node(int x, int y, long dist) {
            this.x = x; this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static int N;
    public static long A, B;
    public static char[][] grid;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static final long INF = Long.MAX_VALUE / 4;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static long dijkstra(int sx, int sy) {
        long[][] dist = new long[N][N];

        for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[sx][sy] = 0;
        pq.add(new Node(sx, sy, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int x = cur.x; int y = cur.y;
            long nowDist = cur.dist;

            if (nowDist != dist[x][y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!inRange(nx, ny)) continue;

                long cost;
                if (grid[x][y] == grid[nx][ny]) cost = A;
                else cost = B;

                long nextDist = nowDist + cost;

                if (nextDist < dist[nx][ny]) {
                    dist[nx][ny] = nextDist;
                    pq.add(new Node(nx, ny, nextDist));
                }

            }
        }

        long maxDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxDist = Math.max(maxDist, dist[i][j]);
            }
        }

        return maxDist;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();

        grid = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        long answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dijkstra(i, j));
            }
        }
        
        System.out.println(answer);
    }
}