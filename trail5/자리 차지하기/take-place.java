import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Integer> set = new TreeSet<>();

        if(!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 1; i <= M; i++) set.add(i);

        int count = 0;
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            Integer targetSeat = set.floor(a);
            if (targetSeat == null) break;
            set.remove(targetSeat);
            count++;
        }

        System.out.println(count);
        sc.close();


    }
}