package gold.p2239;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int[][] a;
	private static boolean[][] c1;
	private static boolean[][] c2;
	private static boolean[][] c3;
	private static int n;

	static int square(int x, int y) {
		return (x / 3) * 3 + (y / 3);
	}

	static boolean go(int z) {
		if (z == 81) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(a[i][j]);
				}
				System.out.println();
			}
			return true;
		}
		int x = z / n;
		int y = z % n;
		if (a[x][y] != 0) {
			return go(z + 1);
		} else {
			for (int i = 1; i <= 9; i++) {
				if (!c1[x][i] && !c2[y][i] && !c3[square(x, y)][i]) {
					c1[x][i] = c2[y][i] = c3[square(x, y)][i] = true;
					a[x][y] = i;
					if (go(z + 1)) {
						return true;
					}
					a[x][y] = 0;
					c1[x][i] = c2[y][i] = c3[square(x, y)][i] = false;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		a = new int[10][10];
		c1 = new boolean[10][10];
		c2 = new boolean[10][10];
		c3 = new boolean[10][10];
		n = 9;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				a[i][j] = str.charAt(j) - '0';
				if (a[i][j] != 0) {
					c1[i][a[i][j]] = true;
					c2[j][a[i][j]] = true;
					c3[square(i, j)][a[i][j]] = true;
				}
			}
		}
		go(0);
	}
}
