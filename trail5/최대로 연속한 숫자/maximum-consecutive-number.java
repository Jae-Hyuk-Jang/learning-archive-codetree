import java.util.Scanner;
import java.util.TreeSet;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 지워진 위치들을 저장하는 TreeSet (경계값 포함)
        TreeSet<Integer> s_num = new TreeSet<>();
        s_num.add(-1);
        s_num.add(n + 1);

        // 현재 존재하는 연속 구간들의 길이를 카운트하는 TreeMap (Multiset 역할)
        TreeMap<Integer, Integer> s_len = new TreeMap<>();
        s_len.put(n + 1, 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int y = sc.nextInt();

            // y 양옆에 이미 지워져 있는 가장 가까운 위치 탐색
            int l = s_num.floor(y);
            int r = s_num.ceiling(y);

            // 기존 구간 [l + 1, r - 1]의 길이를 제거
            int oldLen = r - l - 1;
            int count = s_len.get(oldLen);
            if (count == 1) {
                s_len.remove(oldLen);
            } else {
                s_len.put(oldLen, count - 1);
            }

            // y가 지워지면서 새로 분할된 두 구간의 길이를 추가
            int leftLen = y - l - 1;
            int rightLen = r - y - 1;
            s_len.put(leftLen, s_len.getOrDefault(leftLen, 0) + 1);
            s_len.put(rightLen, s_len.getOrDefault(rightLen, 0) + 1);

            // y를 지워진 위치 집합에 추가
            s_num.add(y);

            // 현재 존재하는 구간 중 최댓값 출력
            sb.append(s_len.lastKey()).append("\n");
        }

        System.out.print(sb);
        sc.close();
    }
}