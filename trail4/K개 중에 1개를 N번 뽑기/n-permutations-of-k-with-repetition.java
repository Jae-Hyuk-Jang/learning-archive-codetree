import java.util.Scanner;

public class Main {
    public static int K;
    public static int N;

    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void choose(int depth) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]);

                if (i != N - 1) sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int num = 1; num <= K; num++) {
            arr[depth] = num;
            choose(depth + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        N = sc.nextInt();

        arr = new int[N];

        choose(0);
        System.out.print(sb);
    }
}