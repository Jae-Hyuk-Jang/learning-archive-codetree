import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N;
    public static final int MX = 8;
    public static ArrayList<Integer> select = new ArrayList<>();

    public static boolean[] processed = new boolean[MX + 1];
    
    public static void choose(int currNum) {
        if(currNum == N + 1) {
            for (int i = 0; i < N; i++) System.out.print(select.get(i) + " ");
            System.out.println();
            return;
        }

        for (int i = N ; i >= 1; i--) {
            if (processed[i]) continue;

            processed[i] = true;
            select.add(i);
            choose(currNum + 1);
            processed[i] = false;
            select.remove(select.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        choose(1);
    }
}