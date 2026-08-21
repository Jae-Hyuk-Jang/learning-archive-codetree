import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long ans = 0;
        // i < j < k 에서 i번째 원소들의 개수를 관리할 HashMap
        HashMap<Long, Integer> leftCount = new HashMap<>();

        for (int j = 0; j < n; j++) {
            // j보다 오른쪽에 있는 원소 k 탐색
            for (int r = j + 1; r < n; r++) {
                long target = k - arr[j] - arr[r];
                if (leftCount.containsKey(target)) {
                    ans += leftCount.get(target);
                }
            }

            // 다음 j를 위해 현재 j 위치의 원소를 leftCount에 추가
            leftCount.put(arr[j], leftCount.getOrDefault(arr[j], 0) + 1);
        }

        System.out.println(ans);
    }
}