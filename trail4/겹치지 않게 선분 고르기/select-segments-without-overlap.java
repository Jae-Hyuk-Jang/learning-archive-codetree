import java.util.Scanner;

public class Main {
    public static final int MX = 15;

    public static int n;
    public static int answer = 0;

    public static int[] l = new int[MX];
    public static int[] r = new int[MX];

    public static int[] selected = new int[MX];
    public static int selectedCnt = 0;

    public static boolean canChoose(int idx) {
        for (int i = 0; i < selectedCnt; i++) {
            int prev = selected[i];

            if (!(r[idx] < l[prev] || r[prev] < l[idx])) {
                return false;
            }
        }
        return true;
    }

    public static void choose(int idx) {
        if (idx == n) {
            answer = Math.max(answer, selectedCnt);
            return;
        }

        choose(idx + 1);

        if (canChoose(idx)) {
            selected[selectedCnt] = idx;
            selectedCnt++;
            choose(idx+1);
            selectedCnt--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            l[i] = sc.nextInt();
            r[i] = sc.nextInt();
        }

        choose(0);

        System.out.println(answer);
    }
}