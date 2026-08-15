import java.util.TreeSet;
import java.util.Scanner;

class Pair implements Comparable<Pair> {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair p) {        
        if(this.x != p.x) return this.x - p.x;  
        else return this.y - p.y;  
    }
};

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Pair> s = new TreeSet<>();

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            s.add(new Pair(x, y));
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Pair answer = s.ceiling(new Pair(x, y));
            if(answer != null) System.out.println(answer.x + " " + answer.y);
            else System.out.println(-1 + " " + -1);
        }
        
    }
}
