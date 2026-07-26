import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String command = sc.next();

            if (command.equals("add")) {
                int k = sc.nextInt();
                int v = sc.nextInt();

                map.put(k, v);
            }

            else if (command.equals("remove")) {
                int k = sc.nextInt();
                map.remove(k);
            }
             else if (command.equals("find")) {
                int k = sc.nextInt();
                if (map.containsKey(k)) {
                    System.out.println(map.get(k));
                }
                else {
                    System.out.println("None");
                }
             }
        }
    }
}