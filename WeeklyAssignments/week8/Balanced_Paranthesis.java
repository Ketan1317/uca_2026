import java.util.Stack;

public class Balanced_Paranthesis {
    public static void main(String[] args) {
        String s1 = "[()]{}()()}";
        System.out.println(isBalanced(s1));

    }

    public static boolean isBalanced(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                char peek = st.peek();
                if((peek == '{' && ch == '}') || (peek == '[' && ch == ']') || (peek == '(' && ch == ')')){
                    st.pop();
                }
                else{
                    return false;
                }

            }
        }
        return st.isEmpty();
    }

}