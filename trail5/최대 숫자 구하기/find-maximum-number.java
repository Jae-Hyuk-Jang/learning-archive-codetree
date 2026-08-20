import java.util.TreeSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Integer> s = new TreeSet<>();

        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i < M; i++) s.add(i+1);
        while (N-- > 0) {
            int n = sc.nextInt();
            s.remove(n);
            System.out.println(s.last());
        }
    }
}


