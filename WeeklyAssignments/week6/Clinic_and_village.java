public class Clinic_and_village {
    public static void main(String[] args) {
        int[] population = {200, 20, 50};
        int k = 5;
       
        double start = 0;
        double end = 0;

        for (int x : population) {
            end = Math.max(end, x);
        }
        double ans = end;
        while (end - start > 1e-6) { // 1 × 10^-6
            double mid = start + (end - start) / 2.0;
            if (check(population, mid, k)) {
                ans = mid;
                end = mid;
            } else {
                start = mid;
            }
        }
        System.out.printf("%.2f\n", ans);
    }

    static boolean check(int[] population, double maxLoad, int k) {
        int kCnt = 0;
        for (int x : population) {
            // min clinics needed in this village
            kCnt += (int) Math.ceil(x / maxLoad);
            if (kCnt > k)
                return false;
        }
        return true;
    }
}