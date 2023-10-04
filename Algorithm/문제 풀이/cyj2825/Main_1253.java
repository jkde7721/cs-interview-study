// 알고리즘 분류: 이분탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253 {
	static int N, count, find;
	static int[] data;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		data = new int[N];
		
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);
		
		for(int i = 0; i < N; i++) {
			find = data[i];
			int a = 0;
			int b = N-1;
			
			while (a < b) {
				if ((data[a] + data[b]) == find) {
					if (a != i && b != i) {
						count++;
						break;
					}
					else if (a == i) a++;
					else if (b == i) b--;
				}
				else if ((data[a] + data[b]) < find) a++;
				else b--;
			}
		}
		System.out.println(count);
	}
}   