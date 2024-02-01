package gold.p30407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, H, D, K;
	static int[] damage;

	static int calc(int[] cmd) {

		int hp = H;
		int dist = D;
		for (int i = 0; i < cmd.length; i++) {
			int dmg = Integer.max(damage[i] - dist, 0);
			if (i > 0 && cmd[i - 1] == 2)
				dmg = 0;

			switch (cmd[i]) {
			case 0:
				dmg /= 2;
				break;
			case 1:
				dist += K;
				dmg = Integer.max(dmg - K, 0);
				break;
			}

			hp = Integer.max(hp - dmg, 0);
			if (hp == 0)
				break;
		}

		return hp;
	}

	static int perm(int cnt, int[] cmd) {
		int ret;
		if (cnt == N) {
			ret = calc(cmd);
			for (int i = 0; i < N; i++) {
				int temp = cmd[i];
				cmd[i] = 2;
				ret = Integer.max(ret, calc(cmd));
				cmd[i] = temp;
			}
			return ret;
		}

		cmd[cnt] = 0;
		ret = perm(cnt + 1, cmd);
		cmd[cnt] = 1;
		ret = Integer.max(perm(cnt + 1, cmd), ret);

		return ret;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		damage = new int[N];
		for (int i = 0; i < N; i++)
			damage[i] = Integer.parseInt(br.readLine());

		int result = perm(0, new int[N]);
		System.out.println(result <= 0 ? -1 : result);
	}
}