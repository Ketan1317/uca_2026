
public class bitXor {

    public static void main(String[] args) {
        int a = 4;
        int b = 5;
        System.out.println("Answer with ^ operator: " + (a ^ b));

        System.out.println("Answer without ^ operator: " + bitXOR(a, b));
    }

    public static int bitXOR(int x, int y) {
        // ~(x & y) -> It means give 0 where both bits are 1
        // ~(~x & ~y) -> It means give 0 where both bits are 0
        return ~(x & y) & ~(~x & ~y);
    }
}
