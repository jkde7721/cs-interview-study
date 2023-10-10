// 알고리즘 분류: 구현 & 시뮬레이션
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683 {
	static class CCTV {
		int val;
		int x;
		int y;
		
		public CCTV(int val, int x, int y) {
			super();
			this.val = val;
			this.x = x;
			this.y = y;
		}
	}
	
	// dx, dy => 상하좌우 이동
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	// cctv 종류에 따라 방향의 개수가 다르므로 미리 저장
	static final int[] kind = {0, 4, 2, 4, 4, 1};
	static final int[][][] d = {
			{ },
			{ {0}, {1}, {2}, {3} },                          // 1번 상 하 좌 우
			{ {0, 1}, {2, 3} },                              // 2번 세로 가로
			{ {0, 2}, {0, 3}, {1, 2}, {1, 3} },              // 3번 상우 상좌 하좌 하우 
			{ {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3} },  // 4번 상좌우 상하좌 하좌우 상하우
			{ {0, 1, 2, 3} } };                              // 5번 모든 방향
	
	static int N, M, answer;
	static int[][] room, temp;
	static List<CCTV> cctvList;
	static int[] valList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		temp = new int[N][M];
		cctvList = new ArrayList<CCTV>();
		// 사무실의 세로 크기와 가로 크기인 N과 M의 값이 최대 8이므로 사각 지대의 최소 크기는 64개를 넘지 않는다.
		answer = 100;
		
		// 사무실 상태 입력 받기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				// 만약 CCTV가 존재하는 값인 1~5값이 들어온다면 cctvList에 넣어주기
				if(1 <= room[i][j] && room[i][j]  <= 5) {
					cctvList.add(new CCTV(room[i][j], i, j));
				}
			}
		}
		valList = new int[8];
		dfs(0);
		System.out.println(answer);
		br.close();
	}
	
	// cctvList 크기만큼 CCTV 종류와 방향을 담을 수 있도록 다 돌아주는 dfs(backtracking)사용
	private static void dfs(int index) {
		if(index == cctvList.size()) {
			answer = Math.min(answer, watch());
			return;
		}
		
		// cctvList에서 하나씩 꺼내서 dfs 돌려준다
		CCTV c = cctvList.get(index);
		int k = kind[c.val];
		for(int i = 0; i < k; i++) {
			valList[index] = i;
			dfs(index+1);
		}
	}
	
	private static int watch() {
		deepCopy(room, temp);
		
		int v, x, y, nx, ny;
		for(int i = 0; i < cctvList.size(); i++) {
			v = cctvList.get(i).val;
			x = cctvList.get(i).x;
			y = cctvList.get(i).y;
			
			for(int dir: d[v][valList[i]]) {
				nx = x;
				ny = y;
				while(true) {
					nx += dx[dir];
					ny += dy[dir];
					
					// 사무실 범위를 넘어갈 때 종료
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
					// 값이 6 즉, 벽을 만났을 때 종료
					if(temp[nx][ny] == 6) break;
					if(0 < temp[nx][ny] && temp[nx][ny] < 6) continue;
					temp[nx][ny] = -1;
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(temp[i][j] == 0) ++cnt;
			}
		}
		return cnt;
	}
	
	// dfs함수를 통해 정해놓은 각 cctv 간의 방향 리스트대로 사무실 상태를 변경해주기 위해 배열 복사
	private static void deepCopy(int[][] from, int[][] to) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				temp[i][j] = room[i][j];
			}
		}
	}
}
