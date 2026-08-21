import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        HashMap<Character, Integer> countMap = new HashMap<>();

        // 각 문자의 등장 횟수 카운팅
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }

        // 원래 문자열 순서대로 확인하여 빈도수가 1인 최초의 문자 탐색
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (countMap.get(ch) == 1) {
                System.out.println(ch);
                return;
            }
        }

        // 단 한 번만 등장하는 문자가 없는 경우
        System.out.println("None");
    }
}