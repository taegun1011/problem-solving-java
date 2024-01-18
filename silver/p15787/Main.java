package silver.p15787;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] bitfield = new int[N + 1];

		while (M > 0) {
			M--;
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c;

			switch (cmd) {
			case 1:
				c = Integer.parseInt(st.nextToken());
				bitfield[r] |= (1 << (20 - c));
				break;
			case 2:
				c = Integer.parseInt(st.nextToken());
				bitfield[r] &= ~(1 << (20 - c));
				break;
			case 3:
				bitfield[r] >>= 1;
				break;
			case 4:
				bitfield[r] <<= 1;
				bitfield[r] &= ~(1 << 20);
			default:
				break;
			}
		}

		boolean[] arr = new boolean[1 << 20];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			System.out.println(bitfield[i]);
			if (arr[bitfield[i]])
				continue;
			cnt++;
			arr[bitfield[i]] = true;
		}
		System.out.println(cnt);

	}
}
