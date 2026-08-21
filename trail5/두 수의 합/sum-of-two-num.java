import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        HashMap<Long, Integer> countMap = new HashMap<>();
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(st.nextToken());
            long target = k - x;

            if (countMap.containsKey(target)) {
                ans += countMap.get(target);
            }
            countMap.put(x, countMap.getOrDefault(x, 0) + 1);
        }
        System.out.println(ans);
    }
}