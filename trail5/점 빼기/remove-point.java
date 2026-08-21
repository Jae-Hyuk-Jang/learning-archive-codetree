import java.util.Scanner;
import java.util.TreeSet;

class Point implements Comparable<Point> {
    long x, y;
    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x == o.x) return Long.compare(this.y, o.y);
        return Long.compare(this.x, o.x);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int M = sc.nextInt();

        TreeSet<Point> points = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            points.add(new Point(x, y));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            long k = sc.nextLong();

            Point dummy = new Point(k, 0);
            Point target = points.ceiling(dummy);
            if (target != null) {
                sb.append(target.x).append(" ").append(target.y).append("\n");
                points.remove(target);
            } else {
                sb.append("-1 -1\n");
            }
        }
        System.out.print(sb);
        sc.close();
    }
}