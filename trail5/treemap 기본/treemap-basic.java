import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        TreeMap<Integer, Integer> m = new TreeMap<>(); 
        StringBuilder sb = new StringBuilder();

        while (n-- > 0) {
            String command = sc.next();
            
            if (command.equals("add")) {
                int k = sc.nextInt();
                int v = sc.nextInt();
                m.put(k, v);
            } 
            else if (command.equals("remove")) {
                int k = sc.nextInt();
                m.remove(k);
            } 
            else if (command.equals("find")) {
                int k = sc.nextInt();

                if (m.containsKey(k)) sb.append(m.get(k)).append("\n");
                else sb.append("None\n");
            }
            else if (command.equals("print_list")) {
                if (m.isEmpty()) sb.append("None\n");
                else {
                    for (int v : m.values()) sb.append(v).append(" ");
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);

    }
}