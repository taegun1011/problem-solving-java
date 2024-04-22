package p1938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static final int INF = (int)1e9;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] dir8 = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	private static char[][] board;
	private static int N;
	
	static class Tuple {
		int r;
		int c;
		int d;
		public Tuple(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		
		int sr = -1, sc = -1, er = -1, ec = -1, sd = -1, ed = -1;
		for(int i=0;i<N;i++)
			board[i] = br.readLine().toCharArray();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(board[i][j] == 'B' && sr == -1) {
					int k = find3(i,j,'B');
					if(k >= 0) {
						sr = i + dir[k][0];
						sc = j + dir[k][1];
						sd = k % 2;
					}
				}
				
				if(board[i][j] == 'E' && er == -1) {
					int k = find3(i,j,'E');
					if(k >= 0) {
						er = i + dir[k][0];
						ec = j + dir[k][1];
						ed = k % 2;
					}
				}
			}
		}
		
		//0 : 세로, 1 : 가로
		int[][][] dist = new int[N][N][2];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				dist[i][j][0] = dist[i][j][1] = INF;
		dist[sr][sc][sd] = 0;

		Queue<Tuple> Q = new ArrayDeque<>();
		Q.add(new Tuple(sr, sc, sd));
		
		int ans = 0;
		while(!Q.isEmpty()) {
			Tuple cur = Q.poll();
			int cr = cur.r, cc = cur.c, cd = cur.d;
			
			if(cr == er && cc == ec && cd == ed) {
				if(ans == 0)
					ans = dist[cr][cc][cd];
				else
					ans = Integer.min(ans, dist[cr][cc][cd]);
			}
			
			//UDLR
			for(int i=0;i<4;i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				
				if(!check3(nr,nc,cd))
					continue;
				
				if(dist[nr][nc][cd] > dist[cr][cc][cd] + 1) {
					dist[nr][nc][cd] = dist[cr][cc][cd] + 1;
					Q.add(new Tuple(nr, nc, cd));
				}
			}
			
			//T
			int nd = 1 - cd;
			if(!check8(cr, cc))
				continue;
			if(dist[cr][cc][nd] > dist[cr][cc][cd] + 1) {
				dist[cr][cc][nd] = dist[cr][cc][cd] + 1;
				Q.add(new Tuple(cr, cc, nd));
			}
		}
		
		System.out.println(ans);
	}
	
	private static boolean check3(int cr, int cc, int cd) {
		for(int i=0;i<3;i++) {
			int nr = cr + dir[cd][0] * (i-1);
			int nc = cc + dir[cd][1] * (i-1);
			if(!oob(nr) || !oob(nc))
				return false;
			if(board[nr][nc] == '1')
				return false;
		}
		return true;
	}
	
	private static boolean check8(int cr, int cc) {
		for(int i=0;i<8;i++) {
			int nr = cr + dir8[i][0];
			int nc = cc + dir8[i][1];
			if(!oob(nr) || !oob(nc))
				return false;
			if(board[nr][nc] == '1')
				return false;
		}
		return true;
	}
	
	private static int find3(int cr, int cc, char c) {
	
		for(int i=0;i<4;i++) {
			int nr1 = cr + dir[i][0];
			int nc1 = cc + dir[i][1];
			int nr2 = nr1 + dir[i][0];
			int nc2 = nc1 + dir[i][1];
			if(oob(nr1) && oob(nr2) && oob(nc1) && oob(nc2)) {
				if(board[nr1][nc1] == c && board[nr2][nc2] == c)
					return i;
			}
		}
		
		return -1;
	}
	
	static boolean oob(int x) {
		return x >= 0 && x < N;
	}
}
