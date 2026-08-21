import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        // 배열 A 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        // 배열 B 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        // 배열 C 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            C[i] = Long.parseLong(st.nextToken());
        }

        // 배열 D 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            D[i] = Long.parseLong(st.nextToken());
        }

        // A[i] + B[j]의 모든 합을 카운팅
        HashMap<Long, Integer> sumAB = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = A[i] + B[j];
                sumAB.put(sum, sumAB.getOrDefault(sum, 0) + 1);
            }
        }

        // C[i] + D[j]를 순회하며 -(C[i] + D[j])의 개수 누적
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long target = -(C[i] + D[j]);
                if (sumAB.containsKey(target)) {
                    ans += sumAB.get(target);
                }
            }
        }

        System.out.println(ans);
    }
}