import java.util.Scanner;
import java.util.TreeSet;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int K = sc.nextInt();

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) set.add(sc.nextInt());
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int num : set) {
            sb.append(num).append(" ");
            count++;
            if (count == K) break;
        }

        System.out.print(sb.toString().trim());
        sc.close();
    }
}