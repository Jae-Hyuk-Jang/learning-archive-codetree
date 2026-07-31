import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Integer st = sc.nextInt();
            if (m.containsKey(st)) continue;
            m.put(st, i+1);
        }


        Iterator<Entry<Integer, Integer>> it = m.entrySet().iterator();
        while(it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            System.out.println(entry.getKey() + " " +  entry.getValue());
        }
    }
}


