import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

class Element implements Comparable<Element> {
    int num;
    int count;

    public Element(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Element o) {
        // 1. 등장 횟수 기준 내림차순
        if (this.count != o.count) {
            return Integer.compare(o.count, this.count);
        }
        // 2. 숫자의 크기 기준 내림차순
        return Integer.compare(o.num, this.num);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> countMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // HashMap의 데이터를 정렬을 위해 리스트로 변환
        ArrayList<Element> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            list.add(new Element(entry.getKey(), entry.getValue()));
        }

        // 조건에 맞춰 정렬
        Collections.sort(list);

        // 상위 K개 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(list.get(i).num).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}