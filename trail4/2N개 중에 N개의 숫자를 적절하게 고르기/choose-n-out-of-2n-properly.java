import java.util.Scanner;


public class Main {
    public static int N;
    public static final int MX = Integer.MAX_VALUE;

    public static int[] arr;
    public static int total = 0;
    public static int answer = MX;

    public static void choose(int idx, int cnt, int sum) {
        if (cnt == N) {
            int otherSum = total - sum;
            int diff = Math.abs(otherSum - sum);

            answer = Math.min(answer, diff);
            return;
        }

        if (idx == 2 * N) return;

        choose(idx + 1, cnt + 1, sum + arr[idx]);
        choose(idx + 1, cnt, sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[2*N];

        for (int i = 0; i < 2 * N; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }

        choose(0, 0, 0);

        System.out.println(answer);

    }
}