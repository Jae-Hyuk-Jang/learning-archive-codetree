import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        // 각 사람의 T분 후 최종 위치를 저장하는 1차원 배열
        long[] finalPos = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long v = Long.parseLong(st.nextToken());
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
    }
}