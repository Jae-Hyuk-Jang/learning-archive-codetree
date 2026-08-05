import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        TreeSet<Integer> set = new TreeSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String command = sc.next();

            if (command.equals("add")) {
                int x = sc.nextInt();
                set.add(x);
            }

            else if (command.equals("remove")) {
                int x = sc.nextInt();
                set.remove(x);
            }

            else if (command.equals("find")) {
                int x = sc.nextInt();

                if (set.contains(x)) {
                    sb.append("true\n");
                } else {
                    sb.append("false\n");
                }
            }

            else if (command.equals("lower_bound")) {
                int x = sc.nextInt();

                Integer value = set.ceiling(x);

                if (value == null) {
                    sb.append("None\n");
                } else {
                    sb.append(value).append("\n");
                }
            }

            else if (command.equals("upper_bound")) {
                int x = sc.nextInt();

                Integer value = set.higher(x);

                if (value == null) {
                    sb.append("None\n");
                } else {
                    sb.append(value).append("\n");
                }
            }

            else if (command.equals("largest")) {
                if (set.isEmpty()) {
                    sb.append("None\n");
                } else {
                    sb.append(set.last()).append("\n");
                }
            }

            else if (command.equals("smallest")) {
                if (set.isEmpty()) {
                    sb.append("None\n");
                } else {
                    sb.append(set.first()).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}