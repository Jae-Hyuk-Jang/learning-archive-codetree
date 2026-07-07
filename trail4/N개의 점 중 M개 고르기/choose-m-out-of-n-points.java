import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N, M;
    public static final int MX = Integer.MAX_VALUE;

    public static int[][] points;
    public static int answer = MX;
    public static ArrayList<Integer> select = new ArrayList<>();


    public static int distance(int p1, int p2) {
        int x1 = points[p1][0];
        int y1 = points[p1][1];
        int x2 = points[p2][0];
        int y2 = points[p2][1];

        int dx = x1 - x2;
        int dy = y1 - y2;

        return dx * dx + dy * dy;
    }

    public static void check() {
        int maxDist = 0;

        for (int i = 0; i < select.size(); i++) {
            for (int j = i + 1; j < select.size(); j++) {
                int p1 = select.get(i);
                int p2 = select.get(j);

                maxDist = Math.max(maxDist, distance(p1, p2));
            }
        }

        answer = Math.min(answer, maxDist);
    }

    public static void choose(int idx, int cnt) {
        if (cnt == M) {
            check();
            return;
        }

        if (idx == N) return; 

        select.add(idx);
        choose(idx + 1, cnt + 1);
        select.remove(select.size() - 1);
        choose(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();

        points = new int[N][2];

        for (int i = 0; i < N; i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
        }

        choose(0, 0);
        System.out.println(answer);
        
    }
}