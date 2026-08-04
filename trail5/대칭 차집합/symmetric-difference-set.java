import java.util.HashSet;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            set.add(num);
        }

        
        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            set2.add(num);
        }

        int answer = 0;

        for (Integer num : set) {
            if (!set2.contains(num)) answer++;
        }

        for (Integer num : set2) {
            if (!set.contains(num)) answer++;
        }

        System.out.println(answer);
    }
}
