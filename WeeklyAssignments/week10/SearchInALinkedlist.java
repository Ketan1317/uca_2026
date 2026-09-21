class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class SearchInALinkedlist {
  public int findLength(Node head) {
    int len = 0;
    while (head != null) {
      head = head.next;
      len++;
    }
    return len;
  }

  public boolean isPresent(Node list1, Node list2) {
    if (list1 == null && list2 == null) {
      return true;
    }
    if (list1 == null || list2 == null) {
      return false;
    }

    int len1 = findLength(list1);
    int len2 = findLength(list2);

    if (len2 > len1) {
      return false;
    }

    Node temp1 = list1;
    Node temp2 = list2;

    while (temp1 != null) {
      if (temp1.data == temp2.data) {
        Node a = temp1;
        Node b = temp2;
        while (a != null && b != null && a.data == b.data) {
          a = a.next;
          b = b.next;
        }
        if (b == null)
          return true;
      }
      temp1 = temp1.next;
    }
    return false;
  }
}
