import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<Long, Long> minPointMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (minPointMap.containsKey(x)) {
                minPointMap.put(x, Math.min(minPointMap.get(x), y));
            } else minPointMap.put(x, y);
        }
        long sum = 0;
        for (long y : minPointMap.values()) sum += y;
        System.out.println(sum);
    }
}