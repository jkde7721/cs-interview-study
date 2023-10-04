// 알고리즘 분류: DP
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1915 {
	static int n, m;
	static int[][] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][m+1];
		
		// 전체 게임판의 크기가 1x1인 경우는 바로 1 출력하고 끝냄
		if(n == 1 && m == 1) {
			System.out.println(1);
			br.close();
			return;
		}
		
		int answer = 0;
		int[][] dp = new int[n+1][m+1];
		
		for(int i = 1; i <= n; i++) {
			String[] input = br.readLine().split("");
			for(int j = 1; j <= m; j++) {
				int tmp = Integer.parseInt(input[j-1]);
				
				// 맨 처음 게임판의 숫자는 그대로 dp[i][j]에 저장
				if(i == 1 && j == 1) dp[i][j] = tmp;
				else {
					if(tmp == 1) {
						dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
						answer = Math.max(answer, dp[i][j]);
					}
				}
			}
		}
		System.out.println(answer*answer);
		br.close();
	}
}