// 알고리즘 분류: BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636 {
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int h, w;
	// 상하좌우 이동
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] board;
	static boolean[][] visited;
	// 치즈 개수가 들어갈 list
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		board = new int[h][w];
		visited = new boolean[h][w];
		
		// 치즈가 없는 칸은 0, 치즈가 있는 칸은 1
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < w; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 치즈가 녹아 없어지면 flag는 false가 됨
		boolean flag = true;
		int time = 0;
		// 초기 상태 치즈의 개수
		int firstCnt = getCount();
		
		while(flag) {
			time++;
			// 항상 (0, 0)부터 시작
			bfs(new Pair(0, 0));
			
			// 치즈가 없는 칸은 0, 치즈가 있는 칸은 1
			for(int i = 0; i < h; i++) {
				// 다음 시간에 치즈를 녹이기 위해 visited 배열 다시 초기화
				Arrays.fill(visited[i], false);
			}
			
			// 한 시간이 지난 후 치즈 개수 세기
			int count = getCount();
			
			// 치즈 개수가 0이라면 모든 치즈가 녹았으므로 종료
			if(count == 0) flag = false;
			// 만약 모든 치즈가 녹지 않았다면 해당 값 치즈 개수 리스트에 넣어줌
			else list.add(count);
		}
		System.out.println(time);
		
		// 치즈가 전부 녹는데 2시간 이상인 경우 맨 마지막 값을 가져와 출력
		if(list.size() > 0) System.out.println(list.get(list.size() - 1));
		// 1시간만에 치즈가 다 녹는다면 치즈 개수 리스트에 값이 없으므로 firstCnt값으로 출력
		else System.out.println(firstCnt);
	}
	
	// (0, 0)부터 탐색
	public static void bfs(Pair p) {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(p);
		visited[p.x][p.y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(0 <= nx && nx < h && 0 <= ny && ny < w) {
					if(!visited[nx][ny]) {
						// 이렇게 마주친 1의 값은 치즈의 모서리를 의미함
						if(board[nx][ny] == 1) {
							board[nx][ny] = 2;
							visited[nx][ny] = true;
						}
						// 치즈가 존재하지 않는 0값을 만나면 q에 넣어서 계속 나아감
						if(board[nx][ny] == 0) {
							visited[nx][ny] = true;
							q.offer(new Pair(nx, ny));
						}
					}
				}
			}
		}
		// while문을 종료하면 한 시간의 활동이 끝났으므로 치즈를 제거하는 함수 호출
		removeCheese();
	}
	
	// 존재하는 치즈 개수 세는 함수
	public static int getCount() {
		int count = 0;
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(board[i][j] == 1) count++;
			}
		}
		return count;
	}
	
	// 배열의 값이 2일때는 치즈의 모서리이므로 0으로 바꾸어 녹여버리는 함수
	public static void removeCheese() {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(board[i][j] == 2) board[i][j] = 0;
			}
		}
	}
}
