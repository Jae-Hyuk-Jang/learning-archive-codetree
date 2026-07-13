import java.util.Scanner;

public class Main {
    public static int N;
    public static int[] arr;

    public static int answer = 10000;

    public static void jump(int sp, int cnt) {
        if (sp == N) {
            answer = Math.min(answer, cnt);
            return;
        }
        for (int i = 1; i <= arr[sp]; i++) if(sp + i <= N) jump(sp + i, cnt + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N + 2];

        for (int i = 1; i <= N; i++) arr[i] = sc.nextInt();

        jump(1, 0);
        if (answer == 10000) System.out.println(-1);
        else System.out.println(answer);
    }
}