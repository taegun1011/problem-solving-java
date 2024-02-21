package gold.p15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static ArrayList<Pair> house = new ArrayList<>();

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static boolean np(int[] arr) {
		int N = arr.length;
		for (int i = N - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				for (int j = N - 1; j >= i; j--) {
					if (arr[j] > arr[i - 1]) {
						swap(arr, i - 1, j);
						break;
					}
				}
				for (int s = i, e = N - 1; s < e; s++, e--)
					swap(arr, s, e);
				return true;
			}
		}

		return false;
	}

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int BFS(int[][] graph, Pair p) {
		int N = graph.length;
		int[][] dist = new int[N][N];

		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], (int) 1e9);

		Queue<Pair> Q = new ArrayDeque<>();
		Q.add(p);
		dist[p.r][p.c] = 0;

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			int cr = cur.r;
			int cc = cur.c;
			if (graph[cr][cc] == 2)
				return dist[cr][cc];

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (dist[nr][nc] > dist[cr][cc] + 1) {
					dist[nr][nc] = dist[cr][cc] + 1;
					Q.add(new Pair(nr, nc));
				}
			}
		}

		return 0;
	}

	static int calc(int[][] graph, int min) {
		int sum = 0;
		for (Pair p : house) {
			sum += BFS(graph, p);
			if (sum >= min)
				return min;
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][N];
		ArrayList<Pair> shop = new ArrayList<>();

		int K = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 1)
					house.add(new Pair(i, j));
				else if (graph[i][j] == 2) {
					shop.add(new Pair(i, j));
					K++;
				}
			}
		}

		int[] choice = new int[K];
		for (int i = 0; i < M; i++)
			choice[K - 1 - i] = 1;

		int min = (int) 1e9;
		do {
			for (int i = 0; i < K; i++) {
				if (choice[i] == 0) {
					Pair p = shop.get(i);
					graph[p.r][p.c] = 0;
				}
			}

			min = calc(graph, min);

			for (int i = 0; i < K; i++) {
				if (choice[i] == 0) {
					Pair p = shop.get(i);
					graph[p.r][p.c] = 2;
				}
			}
		} while (np(choice));

		System.out.println(min);
	}
}
