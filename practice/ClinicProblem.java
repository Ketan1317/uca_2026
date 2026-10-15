public class ClinicProblem {
    // Binary search on answer
    public static void main(String[] args) {
        // Find the max
        int[] population = {200, 20, 50};
        int k = 5;

        double s = 0;
        double e = population[0];

        for(int x : population){
            e = Math.max(e,x);
        }

        double ans = e;

        while(e - s > 1e-6){
            double m = s + (e-s)/2;
            if(check(population,m,k)){
                ans = m;
                e = m;
            }
            else{
                s = m;
            }
        }

        System.out.printf("%.2f%n", ans);
    }

    public static  boolean check(int[] arr,double maxLoad,int k){
        int kCnt = 0;
        for(int x : arr){
            kCnt += (int) Math.ceil(x/maxLoad);
            if(kCnt > k){
                return false;
            } 
        }
        return kCnt <= k;
    }
}
