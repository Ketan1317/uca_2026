import java.util.*;

public class Postfix_Eval {
    public static void main(String[] args) {
        String s = "231*+9-";
        System.out.println(postfix(s));
    }

    public static int postfix(String s) {
        Stack<Integer> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                st.push(ch - '0');
            } else {
                int b = st.pop();
                int a = st.pop();
                switch (ch) {
                    case '+':
                        st.push(a + b);
                        break;
                    case '-':
                        st.push(a - b);
                        break;
                    case '/':
                        st.push(a / b);
                        break;
                    case '*':
                        st.push(a * b);
                        break;
                    case '^':
                        st.push((int)Math.pow(a,b));
                        break;
                    default:
                        break;
                }
            }
        }

        return st.pop();
    }
}
