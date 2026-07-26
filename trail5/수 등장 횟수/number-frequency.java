import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int query = sc.nextInt();
            sb.append(map.getOrDefault(query, 0)).append(" ");
        }
        System.out.println(sb);
    }
}