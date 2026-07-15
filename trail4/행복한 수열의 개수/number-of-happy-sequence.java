import java.util.Scanner;


public class Main {
    public static int N, M;
    public static int[][] arr;

    public static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            int before = arr[i][1];
            int cnt = 1;
            int tmp = 1;
            for (int j = 2; j <= N; j++) {
                if(before == arr[i][j]) {
                    tmp++;
                    cnt = Math.max(cnt, tmp);
                }
                else {
                    before = arr[i][j];
                    tmp = 1;
                }
            }
            if (cnt >= M) answer++;
        }


        for (int i = 1; i <= N; i++) {
            int before = arr[1][i];
            int cnt = 1;
            int tmp = 1;
            for (int j = 2; j <= N; j++) {
                if(before == arr[j][i]) {
                    tmp++;
                    cnt = Math.max(cnt, tmp);
                }
                else {
                    before = arr[j][i];
                    tmp = 1;
                }
            }
            if (cnt >= M) answer++;
        }

        System.out.println(answer);

    }
}