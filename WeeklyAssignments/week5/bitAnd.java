public class bitAnd {
    public static void main(String[] args) {
        int a = 6;
        int b = 5;
        System.out.println("Answer with & operator: " + (a&b));
        // ~(a&b) = ~a | ~b
        // ~(~(a&b)) = ~(~a | ~b)
        int ans = ~(~a | ~b);
        System.out.println("Answer without & operator: " + ans);
    }
}
