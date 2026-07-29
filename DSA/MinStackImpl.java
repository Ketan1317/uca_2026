
public class MinStackImpl {

    public static void main(String[] args) {
        MinStack st = new MinStack();
        st.push(1);
        st.push(2);
        st.push(4);
        st.push(-10);
        System.out.println(st.getMin());
    }
}

class MinStack {

    class Node {
        int min;
        int val;
        Node next;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    Node head;

    public MinStack() {
        head = null;
    }

    public void push(int value) {
        if (head == null) {
            head = new Node(value, value);
        } else {
            Node curr = new Node(value, Math.min(value, head.min));
            curr.next = head;
            head = curr;
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        if (head == null) {
            return -1;
        }
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
