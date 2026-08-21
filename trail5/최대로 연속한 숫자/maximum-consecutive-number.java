import java.util.Scanner;
import java.util.TreeSet;

class Tuple implements Comparable<Tuple> {
    int len, s, e;
    public Tuple(int len, int s, int e) {
        this.len = len;
        this.s = s;
        this.e = e;
    }

    @Override
    public int compareTo(Tuple t) {
        if(this.len != t.len) return t.len - this.len;
        else if (this.s != t.s) return this.s - t.s;
        else return this.e - t.e;
    }
}

public class Main {

    public static int n, m;
    public static TreeSet<Integer> sNum = new TreeSet<>();
    public static TreeSet<Tuple> sLen = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        sNum.add(-1); sNum.add(n + 1);
        sLen.add(new Tuple(n + 1, -1, n + 1));

        for (int i = 0; i < m; i++) {
            int y = sc.nextInt();
            sNum.add(y);
            int z = sNum.higher(y);
            int x = sNum.lower(y);

            sLen.remove(new Tuple(z - x - 1, x, z));
            sLen.add(new Tuple(y - x - 1, x, y));
            sLen.add(new Tuple(z - y - 1, y, z));

            System.out.println(sLen.first().len);
        }
    }
}