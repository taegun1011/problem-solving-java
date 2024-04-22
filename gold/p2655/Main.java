package p2655;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Box{
		int wd;
		int h;
		int wt;
		
		public Box(int wd, int h, int w) {
			super();
			this.wd = wd;
			this.h = h;
			this.wt = w;
		}
	}
	
	static class Pair {
		int idx;
		int val;
		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

	private static int N;
	
	//type 0 : 넓이, 1 : 무게
	static int[][][] dp = new int[101][10005][2];
	static Pair[][][] path = new Pair[101][10005][2];
	
	static int re_dp(int idx, int cur, int type, Box[] arr) {
		if(idx == N)
			return 0;
		if(dp[idx][cur][type] != -1)
			return dp[idx][cur][type];
		
		int ret = 0, a = re_dp(idx + 1, cur, type, arr), b = -1;
		if(type == 0) {
			if(arr[idx].wd < cur)
				b = re_dp(idx + 1, arr[idx].wd, type, arr) + arr[idx].h;
			
		} else {
			if(arr[idx].wt < cur)
				b = re_dp(idx + 1, arr[idx].wt, type, arr) + arr[idx].h;
		}
		
		if(a > b) {
			path[idx][cur][type] = new Pair(idx + 1, cur);
			ret = a;
		}
		else {
			path[idx][cur][type] = new Pair(idx + 1, type == 0 ? arr[idx].wd : arr[idx].wt);
			ret = b;
		}
		
		return dp[idx][cur][type] = ret;
	}
	
	//type 0 : wd, 1 : wt
	static List<Integer> findPath(int idx, int cur, int type, Box[] arr, List list) {
		Pair p = path[idx][cur][type];
		if(p == null)
			return list;
		
		list = findPath(p.idx, p.val, type, arr, list);
		if(type == 0) {
			if(arr[idx].wd == p.val)
				list.add(arr[idx].wd);
		}
		else {
			if(arr[idx].wt == p.val)
				list.add(arr[idx].wt);
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Box[] arr = new Box[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		//밑면 순 정렬
		Box[] arr_wd = Arrays.copyOf(arr, N);
		Arrays.sort(arr_wd, (o1, o2) -> -Integer.compare(o1.wd, o2.wd));
		
		//무게 순 정렬
		Box[] arr_wt = Arrays.copyOf(arr, N);
		Arrays.sort(arr_wt, (o1, o2) -> -Integer.compare(o1.wt, o2.wt));
		
		for(int i=0;i<dp.length;i++) {
			for(int j=0;j<dp[i].length;j++)
				dp[i][j][0] = dp[i][j][1] = -1;
		}
		
		for(int i=0;i<path.length;i++) {
			for(int j=0;j<path[i].length;j++)
				path[i][j][0] = path[i][j][1] = null;
		}
		
		//넓이 기준 dp -> 무게는 정렬돼있어야
		//무게 기준 dp -> 넓이는 정렬돼있어야
		int a = re_dp(0, 10001, 0, arr_wt), b = re_dp(0, 10001, 1, arr_wd);

		//경로 찾기는 시간 부족....
		List<Integer> ans = new ArrayList<>(); 
		if(a > b) {
			ans = findPath(0, 10001, 0, arr_wt, ans);
			System.out.println(ans.size());
			
			for(int wd : ans) {
				for(int i=0;i<N;i++) {
					if(arr[i].wd == wd)
						System.out.println(i + 1);
				}
			}
		}
		else {
			ans = findPath(0, 10001, 1, arr_wd, ans);
			System.out.println(ans.size());
			
			for(int wt : ans) {
				for(int i=0;i<N;i++) {
					if(arr[i].wt == wt)
						System.out.println(i + 1);
				}
			}
		}
		
	}
}
