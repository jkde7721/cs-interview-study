// 알고리즘 분류: DP
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11049 {
	static class Pair{
		int r, c;
		public Pair(int r, int c){
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int n;
	static int[][] dp;
	static Pair[] cal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 행렬의 개수
		n = Integer.parseInt(br.readLine());
		cal = new Pair[n];
		dp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cal[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int j = 0; j < n; j++) dp[i][j] = Integer.MAX_VALUE;
		}
		
		divideConquer(0, n-1);
		System.out.println(dp[0][n-1]);
		br.close();
	}
	
	public static int divideConquer(int s, int e) {
		// 자기 자신일 경우 0을 리턴
		if(s == e) return 0;
		
		// 값이 변경된 적이 있으면 해당 값 리턴
		if(dp[s][e] != Integer.MAX_VALUE) return dp[s][e];
		
		int left, right;
		for(int i = s; i < e; i++) {
			left = divideConquer(s, i);
			right = divideConquer(i+1, e);
			dp[s][e] = Math.min(dp[s][e], left + right + (cal[s].r * cal[i].c * cal[e].c));
		}
		return dp[s][e];
	}
}