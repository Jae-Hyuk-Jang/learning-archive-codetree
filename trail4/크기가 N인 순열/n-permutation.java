import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int N;
    public static int[] arr;
    public static ArrayList<Integer> select = new ArrayList<>();
    public static boolean[] processed;

    public static void choose(int idx) {
        if (idx == N + 1) {
            for (int i = 0; i < N; i++) {
                System.out.print(select.get(i));
                if(i != N -1) System.out.print(" ");
            }
            System.out.println();
            return;
        }


        for (int i = 1; i <= N; i++) {
            if (processed[i]) continue;
            processed[i] = true;
            select.add(arr[i]);
            choose(idx + 1);
            processed[i] = false;
            select.remove(select.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N  = sc.nextInt();
        arr = new int[N + 1];
        processed = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
            processed[i] = false;
        }
        
        choose(1);
    }
}