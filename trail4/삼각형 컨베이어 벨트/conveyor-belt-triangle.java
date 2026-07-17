import java.util.Scanner;

public class Main {
    public static int N, T;
    public static int[] arr;

    public static void turn() {
        int temp = arr[3 * N - 1];

        for (int i = 3 * N - 1; i >= 1; i--) arr[i] = arr[i-1];

        arr[0] = temp;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        T = sc.nextInt();

        arr = new int[N * 3];

        for (int i = 0; i < N * 3; i++) arr[i] = sc.nextInt();

        while(T > 0) {
            T--;
            turn();
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i]);
            if (i != N - 1) System.out.print(" ");
            else System.out.println();
        }

        for (int i = N; i < 2 * N; i++) {
            System.out.print(arr[i]);
            if (i != 2 * N - 1) System.out.print(" ");
            else System.out.println();
        }


        for (int i = 2 * N; i < 3 * N; i++) {
            System.out.print(arr[i]);
            if (i != 3 * N - 1) System.out.print(" ");
            else System.out.println();
        }

    }
}