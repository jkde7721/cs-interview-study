// 알고리즘 분류: 이분탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1920 {

	static int n, m;
	static int[] A;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		A = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			binarySearch(Integer.parseInt(st.nextToken()), 0, A.length-1);
		}
	}
	
	private static void binarySearch(int x, int low, int high) {
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if(x == A[mid]) {
				System.out.println(1);
				return;
			}
			else if(x < A[mid]) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		System.out.println(0);
		return;
	}
}