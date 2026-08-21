import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.BufferedReader;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Integer> set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < m; i++) {
            int query = Integer.parseInt(st.nextToken());
            Integer target = set.floor(query);

            if (target != null) {
                sb.append(target).append("\n");
                set.remove(target);
            }else {
                sb.append("-1\n");
            }
        }

        System.out.print(sb);


    }
}