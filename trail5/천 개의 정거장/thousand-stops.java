import java.util.*;

public class Main {

    static class Bus {
        long fare;
        int[] stops;

        Bus(long fare, int[] stops) {
            this.fare = fare;
            this.stops = stops; 
        }
    }

    static class Occur {
        int busId;
        int idx;

        Occur(int busId, int idx) {
            this.busId = busId;
            this.idx = idx;
        }
    }

    static class Node implements Comparable<Node> {
        int stop;
        long cost;
        long time;

        Node(int stop, long cost, long time) {
            this.stop = stop;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost != other.cost) {
                return Long.compare(this.cost, other.cost);
            }

            return Long.compare(this.time, other.time);
        }
    }

    static final long INF = Long.MAX_VALUE / 4;

    static int A, B, N;
    static Bus[] buses;
    static ArrayList<Occur>[] stopToBuses;

    static long[] distCost;
    static long[] distTime;

    static boolean isBetter(long newCost, long newTime, int stop) {
        if (newCost < distCost[stop]) return true;
        if (newCost == distCost[stop] && newTime < distTime[stop]) return true;
        return false;
    }


    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distCost[A] = 0;
        distTime[A] = 0;
        pq.add(new Node(A, 0 , 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.stop;
            long nowCost = cur.cost;
            long nowTime = cur.time;

            if (nowCost != distCost[now] || nowTime != distTime[now]) {
                continue;
            }

            for (Occur occur : stopToBuses[now]) {
                Bus bus = buses[occur.busId];
                int startIdx = occur.idx;
                for (int nextIdx = startIdx + 1; nextIdx < bus.stops.length; nextIdx++) {
                    int nextStop = bus.stops[nextIdx];
                    long nextCost = nowCost + bus.fare;
                    long nextTime = nowTime + (nextIdx - startIdx);

                    if (isBetter(nextCost, nextTime, nextStop)) {
                        distCost[nextStop] = nextCost;
                        distTime[nextStop] = nextTime;
                        pq.add(new Node(nextStop, nextCost, nextTime));
                    }
                }
            }

        }
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        N = sc.nextInt();

        buses = new Bus[N];
        stopToBuses = new ArrayList[1001];

        for (int i = 1; i <= 1000; i++) {
            stopToBuses[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            long fare = sc.nextLong();
            int count = sc.nextInt();

            int[] stops = new int[count];

            for (int j = 0; j < count; j++) {
                stops[j] = sc.nextInt();

                stopToBuses[stops[j]].add(new Occur(i, j));
            }
            buses[i] = new Bus(fare, stops);
        }

        distCost = new long[1001];
        distTime = new long[1001];

        Arrays.fill(distCost, INF);
        Arrays.fill(distTime, INF);

        dijkstra();

        if (distCost[B] == INF) {
            System.out.println("-1 -1");
        } else {
            System.out.println(distCost[B] + " " + distTime[B]);
        }
    }
}