// 알고리즘 분류: 구현 & 시뮬레이션
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20056 {

	static class FireBall{
		// 행, 열, 질량, 방향, 속력
		int r, c, m, s, d;
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	// 크기 NxN인 격자에 파이어볼  M개, K번 이동 명령
	static int N, M, K;
	// 파이어볼 이동 정보
	static ArrayList<FireBall>[][] map;
	// 존재하는 모든 파이어볼 정보
	static ArrayList<FireBall> fireballs = new ArrayList<>();
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		// 파이어볼 저장
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			FireBall f = new FireBall(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			fireballs.add(f);
		}
		
		// k번 이동명령 진행
		for(int i = 0; i < K; i++) {
			move();
			mergeAndDivide();
		}
		
		int answer = 0;
		// 남아있는 파이어볼 질량의 합 구하기
        for(FireBall fb : fireballs)
            answer += fb.m;
        System.out.println(answer);
        br.close();
	}
	
	// 파이어볼 이동시키는 함수
	public static void move() {
		for(FireBall fb : fireballs) {
			// 이동하였을 때 음수가 나올 수 있으므로 +N 해야 함
			int nx = (fb.r + N + dx[fb.d] * (fb.s % N)) % N;
			int ny = (fb.c + N + dy[fb.d] * (fb.s % N)) % N;
			fb.r = nx;
			fb.c = ny;
			map[nx][ny].add(fb);
		}
	}
	
	// 여러 파이어볼을 합치고 나누는 함수
	public static void mergeAndDivide() {
		for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                //파이어볼 개수가 2개 미만일 경우
                if(map[i][j].size() < 2){
                    map[i][j].clear();
                    continue;
                }
                
                //파이어볼 2개 이상일 경우
                int n = map[i][j].size();
                int mSum = 0, sSum = 0; 
                int oddCnt = 0, evenCnt = 0;
                
                // 하나의 파이어볼로 합친 후 4개의 파이어볼로 분열
                for(FireBall fb : map[i][j]){
                    mSum += fb.m;	// 질량 더하기
                    sSum += fb.s;	// 속도 더하기
                    if(fb.d % 2 == 1) oddCnt++;
                    else evenCnt++;
                    // 합쳐진 파이어볼은 제거
                    fireballs.remove(fb);
                }
                // 하나로 모두 합친 후에는 해당 위치에 존재하는 파이어볼들을 모두 제거
                map[i][j].clear();
                // 질량 : (합쳐진 파이어볼 질량의 합)/5
                mSum /= 5;
                // 속력 : (합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)
                sSum /= n;
                // 만약 분열된 질량이 0일 경우 소멸
                if(mSum == 0) continue;
                
                // 합쳐지는 파이어볼 방향이 모두 짝수이거나 홀수일 경우
                if(oddCnt == n || evenCnt == n){
                    for(int k = 0; k < 8; k += 2) {
                    	// {0, 2, 4, 6} 방향 분열
                    	fireballs.add(new FireBall(i, j, mSum, sSum, k));
                    }
                }else{
                    for(int k = 1; k < 8; k += 2)	{
                    	// {1, 3, 5, 7} 방향 분열
                    	fireballs.add(new FireBall(i, j, mSum, sSum, k));
                    } 
                }
            }
        }
	}
}