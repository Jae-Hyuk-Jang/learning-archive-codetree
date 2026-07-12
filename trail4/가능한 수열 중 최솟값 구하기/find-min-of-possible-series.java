import java.util.Scanner;

public class Main {
    public static int N;
    public static StringBuilder seq = new StringBuilder();

    public static boolean isPossible() {
        int len = seq.length();

        for (int size = 1; size <= len / 2; size++) {
            boolean same = true;
            for (int i = 0; i < size; i++) {
                char a = seq.charAt(len - 1 - i);
                char b = seq.charAt(len - 1 - size - i);

                if (a != b) {
                    same = false;
                    break;
                }
            }
            if(same) {return false;}
        }
        return true;
    }

    public static void choose() {
        if (seq.length() == N) {
            System.out.println(seq);
            System.exit(0);
        }

        for (char num = '4'; num <= '6'; num++) {
            seq.append(num);
            if (isPossible()) {
                choose();
            }
            seq.deleteCharAt(seq.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        choose();
    }
}