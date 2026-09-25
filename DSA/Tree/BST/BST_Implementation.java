class Node {
  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class BST {
  Node root;
  int diameter = 0;
  public Node insert(Node root, int value) {
    if (root == null) {
      return new Node(value);
    }

    if (value < root.data) {
      root.left = insert(root.left, value);
    }

    else if (value > root.data) {
      root.right = insert(root.right, value);
    }

    return root;
  }

  public boolean isPresent(Node root, int value) {
    if (root == null) {
      return false;
    }

    if (root.data == value) {
      return true;
    }

    if (root.data > value) {
      return isPresent(root.left, value);
    }

    return isPresent(root.right, value);
  }

  public void inorderTraversal(Node root) {
    if (root == null) {
      return;
    }

    inorderTraversal(root.left);
    System.out.print(root.data + " ");
    inorderTraversal(root.right);
  }

  public int getMaximum(Node root) {
    if (root == null) {
      throw new RuntimeException("Tree is Empty!");
    }

    while (root.right != null) {
      root = root.right;
    }

    return root.data;
  }

  public int getMinimum(Node root) {
    if (root == null) {
      throw new RuntimeException("Tree is Empty!");
    }

    while (root.left != null) {
      root = root.left;
    }

    return root.data;
  }

  public int countNodes(Node root) {
    if (root == null) {
      return 0;
    }

    int left = countNodes(root.left);
    int right = countNodes(root.right);

    return 1 + left + right;
  }

  public int countLeafNodes(Node root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    int count = 0;

    count += getHeight(root.left);
    count += getHeight(root.right);

    return count;
  }

  public int getHeight(Node root) {
    if (root == null) {
      return 0;
    }

    int left = getHeight(root.left);
    int right = getHeight(root.right);

    return 1 + Math.max(left, right);
  }

  public int getDiameter(Node root) {
    if (root == null) {
      return 0;
    }

    helperForDiameter(root);
    return diameter;
  }

  public int helperForDiameter(Node root){
    if(root == null){
        return 0;
    }

    int left = helperForDiameter(root.left);
    int right = helperForDiameter(root.right);

    diameter = Math.max(diameter,left + 1 + right);

    return 1 + Math.max(left,right);

  }

  public Node deleteInBST(Node root, int val){
    if(root == null){
        return null;
    }

    if(root.data > val){
        root.left = deleteInBST(root.left,val);
    }
    else if(root.data < val){
        root.right = deleteInBST(root.right,val);
    }
    else{
        if(root.left == null && root.right == null){
            return null;
        }
        else if(root.left == null){
            return root.right;
        }
        else if(root.right == null){
            return root.left;
        }
        else{
            int successor  = getMinimum(root.right);
            root.data = successor ;
            return deleteInBST(root.right, successor );
        }
    }
    return root;
  }

}

public class BST_Implementation {

  public static void main(String[] args) {

    BST bst = new BST();

    int[] arr = {50, 30, 70, 20, 40, 60, 80};

    for (int num : arr) {
        bst.root = bst.insert(bst.root, num);
    }

    System.out.print("Inorder: ");
    bst.inorderTraversal(bst.root);
    System.out.println();

    System.out.println("Search 40: " + bst.isPresent(bst.root, 40));
    System.out.println("Search 90: " + bst.isPresent(bst.root, 90));

    System.out.println("Minimum: " + bst.getMinimum(bst.root));
    System.out.println("Maximum: " + bst.getMaximum(bst.root));

    System.out.println("Total Nodes: " + bst.countNodes(bst.root));
    System.out.println("Leaf Nodes: " + bst.countLeafNodes(bst.root));

    System.out.println("Height: " + bst.getHeight(bst.root));
    System.out.println("Diameter: " + bst.getDiameter(bst.root));

    bst.root = bst.deleteInBST(bst.root, 20);
    System.out.print("After deleting 20: ");
    bst.inorderTraversal(bst.root);
    System.out.println();

    bst.root = bst.deleteInBST(bst.root, 30);
    System.out.print("After deleting 30: ");
    bst.inorderTraversal(bst.root);
    System.out.println();

    bst.root = bst.deleteInBST(bst.root, 70);
    System.out.print("After deleting 70: ");
    bst.inorderTraversal(bst.root);
    System.out.println();
}
}