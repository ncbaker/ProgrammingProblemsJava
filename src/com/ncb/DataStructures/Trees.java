package com.ncb.DataStructures;

/**
 Level Order Traversal
      3
    /   \
   5     2
  / \   /
 1   4 6

 BSTree Insertion & Lowest Common Ancestor
     4
    / \
   2   7
  / \
 1   3


*/


public class Trees {
    public static Node getTree()
    {
        Node rL = new Node(5, new Node(1), new Node(4));
        Node rR = new Node(2, new Node(6), null);
        return new Node(3, rL, rR);
    }
    public static Node getBSTree()
    {
        Node rL = new Node(2, new Node(1), new Node(3));
        Node rR = new Node(7);
        return new Node(4, rL, rR);
    }

    /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor*/
    public static Node lca(Node root,int v1,int v2)
    {
        return new Node();
    }

    /*https://www.hackerrank.com/challenges/binary-search-tree-insertion
    * */
    public static Node Insert(Node root,int value)
    {
        if(root == null)
        {
            root = new Node();
            root.data = value;
        }
        else
            InsertBST(root, value);

        return root;
    }
    public static void InsertBST(Node root,int value)
    {
        if(value < root.data)
        {
            if(root.left == null) {
                root.left = new Node();
                root.left.data = value;
            }
            else
                InsertBST(root.left, value);
        }
        else if(value > root.data)
        {
            if(root.right == null) {
                root.right = new Node();
                root.right.data = value;
            } else
                InsertBST(root.right, value);
        }
    }

    /*https://www.hackerrank.com/challenges/tree-level-order-traversal
    * BFS concept  */
    public static void LevelOrder(Node root)
    {
        java.util.Queue<Node> nodeQ = new java.util.LinkedList<Node>();
        nodeQ.add(root);
        Node n;

        while(!nodeQ.isEmpty())
        {
            n = nodeQ.remove();
            System.out.print(n.data + " ");
            if(n.left != null)
                nodeQ.add(n.left);
            if(n.right != null)
                nodeQ.add(n.right);
        }
    }
}
