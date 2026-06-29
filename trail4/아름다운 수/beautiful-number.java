import java.util.Scanner;

public class Main {
    public static int n;
    public static int answer = 0;

    public static void choose(int length) {
        if(length == n) {
            answer++;
            return;
        }

        if (length > n) {
            return;
        }

        for (int num = 1; num <= 4; num++) {
            choose(length + num);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        choose(0);

        System.out.println(answer);
    }
}