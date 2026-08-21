import java.util.Scanner;
import java.util.TreeSet;

class Problem implements Comparable<Problem> {
    int p, l;
    public Problem(int p, int l) {
        this.p = p;
        this.l = l;
    }

    @Override
    public int compareTo(Problem o) {
        if (this.l != o.l) return Integer.compare(o.l, this.l);
        return Integer.compare(o.p, this.p);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();

        TreeSet<Problem> problems = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            int l = sc.nextInt();
            problems.add(new Problem(p, l));
        }

        int M = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String cmd = sc.next();

            if (cmd.equals("rc")) {
                int x = sc.nextInt();
                if (x == 1) {
                    Problem target = problems.first();
                    sb.append(target.p).append("\n");
                }
                if (x == -1) {
                    Problem target = problems.last();
                    sb.append(target.p).append("\n");
                }
            }
            else if (cmd.equals("ad")) {
                int p = sc.nextInt();
                int l = sc.nextInt();
                problems.add(new Problem(p, l));
            } 
            else if (cmd.equals("sv")) {
                int p = sc.nextInt();
                int l = sc.nextInt();
                problems.remove(new Problem(p, l));
            }
        }
        System.out.print(sb);
        sc.close();
    }
}