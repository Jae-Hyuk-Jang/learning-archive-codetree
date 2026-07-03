import java.util.Scanner;

public class Main {
    public static int N, M, K;
    public static int[] move;
    public static int[] pos;
    public static int answer = 0;

    public static void dfs(int turn, int score) {
        if (turn == N) {
            answer = Math.max(answer, score);
            return;
        }
        
        if (score == K) {
            answer = Math.max(answer, score);
            return;
        }

        for (int i = 0; i < K; i++) {
            int oldPos = pos[i];

            if (oldPos == M) {
                dfs(turn + 1, score);
                continue;
            }

            int nextPos = oldPos + move[turn];
            int addScore = 0;

         
            if (nextPos >= M) {
                nextPos = M;
                addScore = 1;
            }

            pos[i] = nextPos;

            dfs(turn + 1, score + addScore);

            pos[i] = oldPos;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        move = new int[N];
        pos = new int[K];

        for (int i = 0; i < N; i++) {
            move[i] = sc.nextInt();
        }

        for (int i = 0; i < K; i++) {
            pos[i] = 1;
        }

        dfs(0, 0);

        System.out.println(answer);
    }
}