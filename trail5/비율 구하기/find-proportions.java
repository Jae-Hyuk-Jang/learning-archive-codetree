import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> m = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String st = sc.next();
            int cnt = 0;
            if (m.containsKey(st)) {
                cnt = m.get(st);
            }
            m.put(st, cnt + 1);
        }


        Iterator<Entry<String, Integer>> it = m.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            double p = (double) entry.getValue() / n * 100;
            
            System.out.print(entry.getKey() + " ");
            System.out.printf("%.4f%n", p);
        }
    }
}


