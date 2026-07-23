import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Count_Smaller_Numbers_After_Self {
    public static void main(String[] args) {
        int[] arr = {4,32,5,2,34,3};
        System.out.println(countSmaller(arr));
    }
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> sorted = new ArrayList<>();
        Integer[] ans = new Integer[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            int idx = lowerBound(sorted,nums[i]);
            ans[i] = idx;
            sorted.add(idx,nums[i]);
        }
        return Arrays.asList(ans);
    }
    public static int lowerBound(List<Integer> list,int t){
        int s = 0;
        int e = list.size();
        while(s < e){
            int m = s + (e-s)/2;
            if(t > list.get(m)){
                s = m+1;
            }
            else{
                e = m;
            }
        }
        return e;
    }
}
