class Solution {
    static int[] dcArr = {10,20,30,40};
    static int emoDepth;
    static int[][] userArr;
    static int[] emoticonArr;
    static Info result = new Info(0,0);
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        emoDepth = emoticons.length;
        userArr = users;
        emoticonArr = emoticons;
        int[] dcInfo = new int[emoDepth];
        dfs(0,dcInfo);
        answer[0] = result.cnt;
        answer[1] = result.cost;
        return answer;
    }
    
    static void dfs(int depth,int[] dcInfo) {
        if(depth == emoDepth){
            cal(dcInfo);
            return;
        }
        for(int i=0; i<dcArr.length; i++){
            dcInfo[depth] = dcArr[i];
            dfs(depth+1,dcInfo);
        }
    }
    
    static void cal(int[] dcInfo){
        int allCnt =0;
        int allCost =0;
        for(int[] user : userArr){
            int userDc = user[0];
            int userMaxCost = user[1];
            int cost =0;
            for(int i =0; i<emoDepth; i++){
                int emoCost = emoticonArr[i];
                int dc = dcInfo[i];
                if(userDc <= dc){
                    cost += (emoCost * (100-dc))/100;
                }
                if(cost >= userMaxCost){
                    allCnt++;
                    break;
                }
                if(i==emoDepth-1){
                    allCost += cost;
                }
            }
        }
        Info newInfo = new Info(allCnt, allCost);
        if(newInfo.cnt > result.cnt || (newInfo.cnt == result.cnt && newInfo.cost > result.cost)){
            result = newInfo;
        }
        
    }
    
    static class Info{
        int cnt;
        int cost;
        
        Info(int cnt, int cost){
            this.cnt = cnt;
            this.cost = cost;
        }
    }
}
