public class bitAnd {
    public static void main(String[] args) {
        int a = 6;
        int b = 5;
        System.out.println("Answer with & operator: " + (a&b));
        
        System.out.println("Answer without & operator: " + bitAndOp(a, b));
    }
    public static int bitAndOp(int x, int y) {
        // ~(x&y) = ~x | ~y
        // ~(~(x&y)) = ~(~x | ~y)
        return ~(~x | ~y);
    }
}
