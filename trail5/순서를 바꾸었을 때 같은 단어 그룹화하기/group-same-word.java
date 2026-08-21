import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> groupCount = new HashMap<>();

        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            int count = groupCount.getOrDefault(sortedWord, 0) + 1;
            groupCount.put(sortedWord, count);
            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
    }
}