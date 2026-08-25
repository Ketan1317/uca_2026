class Pair {
    int val;
    int listIdx;
    int pointer;

    Pair(int x, int y, int z) {
        val = x;
        listIdx = y;
        pointer = z;
    }
}

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {

        int k = nums.size();
        int[] idxArr = new int[k];

        int[] ans = { -1000000, 1000000 };
        int best = Integer.MAX_VALUE;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        int maxEle = Integer.MIN_VALUE;
        int minEleIdx = 0;

        for (int i = 0; i < k; i++) {
            int val = nums.get(i).get(0);
            maxEle = Math.max(maxEle, val);
            pq.add(new Pair(val,i,0));
        }

        while(pq.size() == k){
            Pair p = pq.poll();
            int listIdx = p.listIdx;
            int val = p.val;
            int i = p.pointer;
            maxEle = Math.max(maxEle,val);
            int temp = maxEle - val;
            if(temp < best || (temp == best) && val < ans[0]){
                best = temp;
                ans[0] = val;
                ans[1] = maxEle;
                if(best == 0) return ans;
            }

            if(i+1 >= nums.get(listIdx).size()){
                break;
            }
            int nextVal = nums.get(listIdx).get(i+1); 
            pq.add(new Pair(nextVal,listIdx,i+1));
            maxEle = Math.max(maxEle,nextVal);

        }

        return ans;
    }
}