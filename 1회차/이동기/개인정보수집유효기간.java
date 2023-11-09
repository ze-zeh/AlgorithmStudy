import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String term : terms){
            String[] termArr = term.split(" ");
            map.put(termArr[0], Integer.parseInt(termArr[1])*28);
        }
        int todayTime = dateToTime(today);
        
        for(int i=0; i<privacies.length; i++) {
            String[] privacyArr = privacies[i].split(" ");
            int privacy = dateToTime(privacyArr[0]) + map.get(privacyArr[1]);
            if(privacy <= todayTime) answer.add(i+1);
        }
        /*
        int[] answerArr = new int[answer.size()];
        for(int i =0; i< answer.size(); i++) answerArr[i] = answer.get(i);
        */
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static int dateToTime(String date) {
        String[] dateArr = date.split("\\.");
        int time =0;
        time+= (Integer.parseInt(dateArr[0]) * 12 * 28) + (Integer.parseInt(dateArr[1])*28) + Integer.parseInt(dateArr[2]);
        return time;
        
    }
}
