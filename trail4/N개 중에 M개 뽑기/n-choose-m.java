import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;

    public static int[] selected;
    public static StringBuilder sb = new StringBuilder();

    public static void choose(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]);

                if (i != M - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for (int num = start; num <= N; num++) {
            selected[depth] = num;
            choose(depth + 1, num + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        selected = new int[M];

        choose(0, 1);

        System.out.print(sb);
    }
}