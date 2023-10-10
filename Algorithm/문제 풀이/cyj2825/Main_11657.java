// 알고리즘 분류: 최단거리(벨만포드 알고리즘)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11657 {
	static class Edge{
		int start;
		int end;
		int cost;
		
		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	// 도시의 개수 N, 버스의 개수 M
	static int N, M;
	static long[] distance;
	static Edge[] bus;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		bus = new Edge[M+1];
		distance = new long[N+1];
		// 최단거리 배열을 무한대로 채우기
		Arrays.fill(distance, INF);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			bus[i] = new Edge(from, to, cost);
		}
		
		// 음의 cycle이 존재하는 경우 -1 출력
		if(!BellmanFord()) {
			System.out.println(-1);
			
		}
		else {
			for(int i = 2; i <= N; i++) {
				System.out.println(distance[i] == INF ? "-1" : distance[i]);
			}
		}
		br.close();
	}
	
	// 음의 cycle이 있다면 false 리턴, 없다면 true 리턴
	public static boolean BellmanFord() {
		// 시작점 최단거리 0
		distance[1] = 0;
		
		// 정점의 개수만큼 반복
		for(int i = 1; i < N+1; i++) {
			// 간선의 개수만큼 반복
			for(int j = 0; j < M; j++) {
				// 현재 간선
				Edge edge = bus[j];
				
				if(distance[edge.start] == INF) continue;
				// 현재 간선의 들어오는 정점에 대해 비교
				if(distance[edge.end] > distance[edge.start] + edge.cost) {
					distance[edge.end] = distance[edge.start] + edge.cost;
					// N번째 라운드에서 값이 갱신된다면 음수 순환 존재
					if(i == N) return false;
				}
			}
		}
		return true;
	}
}