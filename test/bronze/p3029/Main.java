package bronze.p3029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[][] time = new int[3][3];
		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ":");
			for (int j = 0; j < 3; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean flag = true;
		for (int i = 0; i < 3; i++) {
			if (time[0][i] != time[1][i])
				flag = false;
		}

		if (flag) {
			System.out.println("24:00:00");
			return;
		}

		for (int i = 2; i > 0; i--) {
			time[2][i] += time[1][i] - time[0][i];
			if (time[2][i] < 0) {
				time[2][i] += 60;
				time[2][i - 1]--;
			}
		}
		time[2][0] += time[1][0] - time[0][0];
		if (time[2][0] < 0)
			time[2][0] += 24;

		for (int i = 0; i < 3; i++) {
			if (time[2][i] < 10)
				sb.append("0");
			sb.append(time[2][i]);
			if (i != 2)
				sb.append(":");
		}

		System.out.println(sb);
	}
}
