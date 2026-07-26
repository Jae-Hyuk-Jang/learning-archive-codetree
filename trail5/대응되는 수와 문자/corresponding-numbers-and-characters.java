import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        String[] numberToString = new String[N + 1];
        HashMap<String, Integer> stringToNumber = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String str = sc.next();

            numberToString[i] = str;
            stringToNumber.put(str, i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            String query = sc.next();

            if (Character.isDigit(query.charAt(0))) {
                int num = Integer.parseInt(query);
                sb.append(numberToString[num]).append("\n");
            } else {
                sb.append(stringToNumber.get(query)).append("\n");
            }
        }

        System.out.print(sb);
    }
}