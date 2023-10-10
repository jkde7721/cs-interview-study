// 알고리즘 분류: 완전탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17471 {
	static int N, small;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int[] people;
	static boolean[] isSelected;
	static List<Integer> check;
	static List<Integer> check2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		small = 10000;
		// 인접리스트 초기화
		list = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 구역의 인구 수를 순서대로 배열에 넣기
		people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		// 1번부터 N번까지 순서대로 각 구역과 인접한 구역의 정보 인접리스트에 넣기
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int link = Integer.parseInt(st.nextToken());
			for(int j = 0; j < link; j++) {
				int x = Integer.parseInt(st.nextToken());
				list[i].add(x);
			}
		}
		
		// 부분집합함수를 사용하여 집합들을 두 개로 나눈 뒤에 각각 집합이 서로 연결되어 있는지 확인하고 다 연결되어 있을 경우 각각의 인구 계산하기
		isSelected = new boolean[N];
		subset(0);
		
		if(small == 10000) {
			System.out.println(-1);
		}else {
			System.out.println(small);
		}
	}
	
	private static void dfs(int cur, List<Integer> check) {
		visited[cur] = true;

		if(!check.isEmpty()) {
			check.remove((Object) cur);
		}
		
		for(int i = 0, end = list[cur].size(); i < end; i++) {
			int ad = list[cur].get(i);
			if(!visited[ad] && check.contains(ad)) dfs(ad, check);
		}
	}
	
	private static void subset(int cnt) {
		if(cnt == N) {
			int aVal = 0;
			int bVal = 0;
			
			check = new ArrayList<>();
			check2 = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) {
					check.add(i+1);
				}
				else check2.add(i+1);
			}
			
			if(check.size() != 0 && check.size() != N) {
				// check 값들, check2 값들이 서로 각각 연결되어 있는지 확인해야 함!
				visited = new boolean[N+1];
				dfs(check.get(0), check);
				
				dfs(check2.get(0), check2);
				
				int count=0;
				for (int i = 1; i <=N; i++) {
					if(visited[i]) count++;
				}
				
				if(count== N) {
					for(int i = 0; i < N; i++) {
						if(isSelected[i]) aVal += people[i+1];
						else bVal += people[i+1];
					}
					small = Math.min(small, Math.abs(aVal - bVal));
				}
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}
}
