package platinum.p14865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static final int UP = 1;
	static final int DOWN = -1;

	static class Pair {
		int x;
		int dir;

		public Pair(int x, int dir) {
			this.x = x;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		// 스택으로 관리한다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] X = new int[N + 1];
		int[] Y = new int[N + 1];

		int start = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X[i] = Integer.parseInt(st.nextToken());
			Y[i] = Integer.parseInt(st.nextToken());
			// 시작점을 맨 왼쪽 아래로 고정한다
			if (X[i] <= X[start] && Y[i] <= Y[start])
				start = i;
		}
		X[N] = X[0];
		Y[N] = Y[0];

		ArrayList<Integer> AL = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int cur = (start + i) % N, nxt = (cur + 1) % N;
			if ((Y[cur] > 0) != (Y[nxt] > 0))
				AL.add(X[cur]);
		}

		int[] arr = new int[AL.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = AL.get(i);
		Arrays.sort(arr);

		int[] dir = new int[arr.length];

		int prev = 0;
		for (int i = 0; i < N; i++) {
			int cur = (start + i) % N, nxt = (cur + 1) % N;
			// 올라갈때 : X좌표를 저장한다
			if (Y[cur] < 0 && Y[nxt] > 0)
				prev = X[cur];
			// 내려갈 때 :현재 X좌표와 이전 X좌표를 크기순으로 (-1, 1)로 저장한다
			else if (Y[cur] > 0 && Y[nxt] < 0) {
				int min = Integer.min(prev, X[cur]);
				int max = Integer.max(prev, X[cur]);
				dir[Arrays.binarySearch(arr, min)] = UP;
				dir[Arrays.binarySearch(arr, max)] = DOWN;
			}
		}

		Stack<Integer> S = new Stack<>();
		boolean[] in = new boolean[arr.length];
		boolean[] out = new boolean[arr.length];
		for (int i = 0; i < dir.length; i++) {
			if (dir[i] == UP) {
				if (S.isEmpty())
					out[i] = true;
				S.add(i);
			} else {
				if (i - S.pop() == 1)
					in[i] = true;
			}
		}

		int OUT = 0, IN = 0;
		for (int i = 0; i < arr.length; i++) {
			OUT += out[i] ? 1 : 0;
			IN += in[i] ? 1 : 0;
		}
		System.out.println(String.format("%d %d", OUT, IN));
	}
}
