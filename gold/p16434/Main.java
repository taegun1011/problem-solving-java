package gold.p16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] rooms;

	static boolean check(long HMaxHP, long Hatk) {
		long HCurHP = HMaxHP;
		for (int i = 0; i < rooms.length; i++) {
			switch (rooms[i][0]) {
			case 1:
				long atkTime = (rooms[i][2] % Hatk == 0) ? rooms[i][2] / Hatk : rooms[i][2] / Hatk + 1;
				HCurHP -= rooms[i][1] * (atkTime - 1);
				if (HCurHP <= 0)
					return false;
				break;
			case 2:
				HCurHP = Long.min(HMaxHP, HCurHP + rooms[i][2]);
				Hatk += rooms[i][1];
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		rooms = new int[N][3];
		int Hatk = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rooms[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long S = 0, E = Long.MAX_VALUE;
		// NNNNNYYYYY구하기
		while (S < E) {
			long M = (S + E) / 2;

			if (check(M, Hatk))
				E = M;
			else
				S = M + 1;
		}

		System.out.println(S);
	}
}
