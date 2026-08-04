import java.util.HashSet;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> set = new HashSet<>();

        int n = sc.nextInt();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if(set.contains(num)) continue;
            else { set.add(num); answer++; }
        }

        System.out.println(answer);

    }
}
