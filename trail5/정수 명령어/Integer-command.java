import java.util.TreeSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            TreeSet<Integer> s = new TreeSet<>();
            
            int N = sc.nextInt();
            for (int i = 0; i < N; i++) {
                String st = sc.next();
                int n = sc.nextInt();

                if (st.equals("I")) {
                    s.add(n);
                } else if (st.equals("D")) {
                    if (s.isEmpty()) continue;
                    
                    if (n == 1) {
                        s.pollLast();
                    } else if (n == -1) {
                        s.pollFirst();
                    }
                }
            }

            if (!s.isEmpty()) {
                System.out.println(s.last() + " " + s.first());
            } else {
                System.out.println("EMPTY");
            }
        }
        sc.close();
    }
}