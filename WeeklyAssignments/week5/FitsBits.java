public class FitsBits {

    public static void main(String[] args) {
        int res = fitsBits(-4, 3);

        if (res == 0) {
            System.out.println("Does not fit");
        } else {
            System.out.println("Fits");
        }
    }

    static int fitsBits(int x, int n) {
        // - = 2's complement
        int shift = 32 + (~n + 1); // 32 - n
        int restored = (x << shift) >> shift;

        return (restored ^ x) == 0 ? 1 : 0;
    }
}