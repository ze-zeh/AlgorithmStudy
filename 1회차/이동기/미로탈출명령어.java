import java.util.*;
class Solution {
    static int n;
    static int m;
    static int k;
    static int r;
    static int c;
    static String[] moveArr = {"d","l","r","u"};
    static int[] dx = {0,-1,1,0};
    static int[] dy = {1,0,0,-1};
    static StringBuilder path = new StringBuilder();
    static StringBuilder answerPath = new StringBuilder();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(x-c) + Math.abs(y-r);
        if((distance+k)%2 !=0 || distance > k) return "impossible";
        this.n = n;
        this.m = m;
        this.r = r-1;
        this.c = c-1;
        this.k =k;
        dfs(0,y-1,x-1);
        return answerPath.toString();
    }
    
    static void addPath(int xPos, int yPos){
        //아래, 왼, 오, 위
        if(yPos < r){
            for(int i=0; i<r-yPos; i++){
                answerPath.append("d");
            }
        }
        if(xPos > c){
            for(int i=0; i<xPos -c; i++){
                answerPath.append("l");
            }
        }
        if(xPos < c){
            for(int i=0; i<c - xPos; i++){
                answerPath.append("r");
            }
        }
        if(yPos > r){
            for(int i=0; i<yPos - r; i++){
                answerPath.append("u");
            }
        }
    }
    
    static boolean dfs(int depth, int xPos, int yPos){
        int distance = Math.abs(xPos-c) + Math.abs(yPos-r);
        if(distance == k-depth){
            answerPath = path;
            addPath(xPos,yPos);
            return true;
        }
        for(int i =0; i<4; i++){
            int nextX = xPos +dx[i];
            int nextY = yPos +dy[i];
            if(isNullCheck(nextX,nextY)){
                path.append(moveArr[i]);
                if(dfs(depth+1, nextX, nextY)){
                    return true;
                }
                path.deleteCharAt(path.length()-1);
            }
        }
        return false;
    }
    
    static boolean isNullCheck(int x, int y){
        return x<m && x>=0 && y<n && y>=0;
    }
}

/*
bfs로 할 경우 -> 4의 2500제곱...
dfs로 할 경우에도 최악의 경우를 대비해서 처음 시도로 바로 찾을 수 있도록 해야된다. 알파벳 순서로
근데 진짜 뭔 억까 당해서 최악의 경우로 될 경우는 터진다. -> 기도메타
impossible 경우는 모든 탐색을 시도하므로 탐색 전에 검증해야한다.
ex) 거리가 짝수일 때, k가 홀수이면 절대 못간다 + 가야할 거리보다 k가 작으면 못간다.

일단 기도메타 실패
dlru 순으로 dfs는 실패다.
그럼 dlru로 최대한 빠르게 도착한다. 이후 거기서 dlru로 왔다갔다 반복하기 -> 그런데 이 경우에는 시작 지점보다 도착지점이 높거나 같으면 d로 시작하지 않으므로 불가능
그럼 dlru이 가능한대로 이동한다. -> 이동할때마다 남은 k로 도착할 수 있는지 체크 -> 남은 k로 딱 되면 dlru로 최대한 가능하게 이동 -> 이거로 가본다잉 -> 김옥지
*/
