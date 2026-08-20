import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        long M = sc.nextLong();

        long[] arr = new long[N];
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextLong();
            set.add(arr[i]);
        }

        long minDiff = Long.MAX_VALUE;
        boolean found = false;

        // 각 원소 x에 대해, x + M 보다 크거나 같은 최소의 수를 TreeSet에서 찾음
        for (int i = 0; i < N; i++) {
            Long target = set.ceiling(arr[i] + M);

            if (target != null) {
                long diff = target - arr[i];
                minDiff = Math.min(minDiff, diff);
                found = true;
            }
        }

        if (found) {
            System.out.println(minDiff);
        } else {
            System.out.println(-1);
        }

        sc.close();
    }
}