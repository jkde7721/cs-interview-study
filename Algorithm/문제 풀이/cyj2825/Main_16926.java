// 알고리즘 분류: 구현 & 시뮬레이션
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] data = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int val = Math.min(n, m)/2;
		for(int i = 1; i <= r; i++) {
			for(int j = 0; j < val; j++) {
				// 맨 처음값 저장
				int temp = data[j][j];
				// 맨 위 가로
				for(int k = j+1; k < m-j; k++) {
					data[j][k-1] = data[j][k];
				}
					
				// 맨 오른쪽 세로
				for(int k = j+1; k < n-j; k++) {
					data[k-1][m-1-j] = data[k][m-1-j];
				}
					
				// 맨 아래 가로
				for(int k = m-2-j; k >= j; k--) {
					data[n-1-j][k+1] = data[n-1-j][k];
				}
					
				// 맨 왼쪽 세로
				for(int k = n-2-j; k >= j; k--) {
					data[k+1][j] = data[k][j];
				}
				
				data[j+1][j] = temp;
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(data[i][j]+ " ");
			}
			System.out.println();
		}
		
	}
}