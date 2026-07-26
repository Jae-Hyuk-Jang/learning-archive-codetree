import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<String, Integer> map = new HashMap<>();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            String str = sc.next();

            int count = map.getOrDefault(str, 0) + 1;
            map.put(str, count);
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}