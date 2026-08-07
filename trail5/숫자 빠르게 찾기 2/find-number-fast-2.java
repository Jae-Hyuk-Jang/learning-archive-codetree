import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int M = sc.nextInt();

        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            set.add(sc.nextInt());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = sc.nextInt();
            
            // target보다 크거나 같은 값 중 가장 작은 값을 반환
            Integer result = set.ceiling(target);

            if (result != null) {
                sb.append(result).append("\n");
            } else {
                sb.append("-1\n");
            }
        }

        System.out.print(sb);
        sc.close();
    }
}