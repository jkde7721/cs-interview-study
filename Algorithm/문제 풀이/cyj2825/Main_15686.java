// 알고리즘 분류: 완전탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15686 {
	static int N, M;
	static int[][] data, num, store,home;
	static int cntS, cntH, sumCount;
	static ArrayList<Integer> sumList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int[N][N];
		num = new int[M][2];
		store = new int[N*N][2];
		home = new int[N*N][2];
		
		cntS = 0;
		cntH = 0;
		// 도시의 정보 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cStore =  Integer.parseInt(st.nextToken());
				//System.out.println(cStore);
				data[i][j] = cStore;
				// cStore에는 치킨집 좌표 값만 넣기
				if(cStore == 2) {
					store[cntS][0] = i;
					store[cntS][1] = j;
					cntS++;
				}
				if(cStore == 1) {
					home[cntH][0] = i;
					home[cntH][1] = j;
					cntH++;
				}
			}
		}
		sumCount = 0;
		cStreet(0,0);
		int minVal = 10000;
		for(int x: sumList) {
			if (minVal > x) {
				minVal = x;
			}
			//System.out.println(x);
		}
		System.out.println(minVal);
	}
	
	private static void cStreet(int cnt, int start) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < cntH; i++) {
				int small = 10000;
				for (int j = 0; j < M; j++) {
					int val = 0;
					val = Math.abs(num[j][0] - home[i][0]) + Math.abs(num[j][1] - home[i][1]);
					//System.out.println(val);
					if(small > val) {
						small = val;
					}
				}
				sum += small;
			}
			sumList.add(sum);

			return;
		}
		for (int i = start; i < cntS; i++) {
			num[cnt][0] = store[i][0];
			num[cnt][1] = store[i][1];
			cStreet(cnt+1, i+1);
		}
	}
}
