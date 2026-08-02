import java.util.HashSet;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashSet<Integer> s = new HashSet<>(); // 정수를 관리할 hashset을 선언합니다. => 빈 set
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while(n-- > 0) {
            String st = sc.next();
            int m = sc.nextInt();
            if (st.equals("add")) {
                s.add(m);
            }
            else if (st.equals("remove")) {
                s.remove(m);
            } 
            else if (st.equals("find")) {
                if(s.contains(m)) System.out.println("true");
                else System.out.println("false");
            } 
            else continue;
        }
    }
}
