import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static int N;

    public static String[] grid;

    public static int sr, sc;
    public static int er, ec;

    public static int answer = Integer.MAX_VALUE;

    static class Coin implements Comparable<Coin> {
        int num, r, c;
        Coin(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Coin other) {
            return this.num - other.num;
        }
    }

    public static ArrayList<Coin> coins = new ArrayList<>();

    public static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static void dfs(int idx, int cnt, int r, int c, int dist) {
        if (cnt == 3) {
            answer = Math.min(answer, dist + getDist(r, c, er, ec));
        }

        for (int i = idx; i < coins.size(); i++) {
            Coin coin = coins.get(i);

            int nextDist = dist + getDist(r, c, coin.r, coin.c);

            dfs(i + 1, cnt + 1, coin.r, coin.c, nextDist);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        N = scanner.nextInt();

        grid = new String[N];

        for (int i = 0; i < N; i++) {
            grid[i] = scanner.next();

            for (int j = 0; j < N; j++) {
                char ch = grid[i].charAt(j);

                if (ch == 'S') { sr= i; sc = j;}
                else if (ch == 'E') { er = i; ec = j;}
                else if ('1' <= ch && ch <= '9') { coins.add(new Coin(ch - '0', i, j));}
            }
        }
        Collections.sort(coins);

        dfs(0, 0, sr, sc, 0);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}