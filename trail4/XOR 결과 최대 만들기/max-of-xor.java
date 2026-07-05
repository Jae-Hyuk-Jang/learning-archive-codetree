import java.util.Scanner;


public class Main {

    public static int N;
    public static int M;

    public static int[] arr;
    public static int answer = 0;

    public static void choose(int depth, int start, int xorValue) {
        if (depth == M) {
            answer = Math.max(answer, xorValue);
            return;
        }

        for (int i = start; i <= N; i++) {
            choose(depth + 1, i + 1, xorValue ^ arr[i]);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) arr[i] = sc.nextInt();

        choose(0, 1, 0);

        System.out.println(answer);


    }
}