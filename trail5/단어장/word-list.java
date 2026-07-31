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
            m.put(st, m.getOrDefault(st, 0) + 1);
        }


        Iterator<Entry<String, Integer>> it = m.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}


