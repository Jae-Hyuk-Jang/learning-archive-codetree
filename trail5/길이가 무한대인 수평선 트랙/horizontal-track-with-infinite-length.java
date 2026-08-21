import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        long t = sc.nextLong();

        long[] finalPos = new long[n];
        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long v = sc.nextLong();
            // T분 후 방해 없이 달렸을 때의 최종 위치
            finalPos[i] = x + v * t;
        }

        // 가장 뒤에 있는 사람부터 거꾸로 확인
        int groupCount = 1;
        long lastGroupPos = finalPos[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (finalPos[i] < lastGroupPos) {
                // 앞선 그룹을 따라잡지 못한 경우 -> 새로운 그룹 형성
                groupCount++;
                lastGroupPos = finalPos[i];
            }
            // finalPos[i] >= lastGroupPos 라면 앞 그룹에 따라잡혀 흡수됨
        }

        System.out.println(groupCount);
        sc.close();
    }
}