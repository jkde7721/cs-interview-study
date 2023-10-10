// 알고리즘 분류: BFS
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600 {
	static class Pair{
		int x, y, k;

		public Pair(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	static int K, W, H, answer;
	static int[][] ground;
	// 상하좌우 + 말 이동 8개
	static int[] dx = {-1, 1, 0, 0, -2, -1, -2, -1, 2, 1, 2, 1};
	static int[] dy = {0, 0, -1, 1, -1, -2, 1, 2, -1, -2, 1, 2};
	static int[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ground = new int[H][W];
		visited = new int[H][W][K+1];
		answer = -1;
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(new Pair(0, 0, K));
		System.out.println(answer);
	}
	
	public static void bfs(Pair p) {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(p);
		visited[p.x][p.y][p.k] = 1;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			if(cur.x == H-1 && cur.y == W-1) {
				answer = visited[cur.x][cur.y][cur.k]-1;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				if(visited[nx][ny][cur.k] == 0 && ground[nx][ny] == 0) {
					visited[nx][ny][cur.k] = visited[cur.x][cur.y][cur.k] + 1;
					q.offer(new Pair(nx, ny, cur.k));
				}
			}
			
			if(cur.k > 0) {
				for(int i = 4; i < 12; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
					if(visited[nx][ny][cur.k-1] == 0 && ground[nx][ny] == 0) {
						visited[nx][ny][cur.k-1] = visited[cur.x][cur.y][cur.k] + 1;
						q.offer(new Pair(nx, ny, cur.k-1));
					}
				}
			}
		}
	}
}
