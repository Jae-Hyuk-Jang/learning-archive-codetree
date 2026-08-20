import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            Integer higher = set.ceiling(x);
            Integer lower = set.floor(x);

            if(higher != null) minDist = Math.min(minDist, higher - x);
            if (lower != null) minDist = Math.min(minDist, x - lower);
            set.add(x);
            System.out.println(minDist);
        }
    }
}