package com.ncb;

/**
      3
    /   \
   5     2
  / \   /
 1   4 6
*/
class Node {
    int data;
    Node left;
    Node right;
    Node(int v) { data = v; }
    Node(int v, Node l, Node r) { data = v; left = l; right = r; }
}

public class DataStructures {
    static Node getTree()
    {
        Node rL = new Node(5, new Node(1), new Node(4));
        Node rR = new Node(2, new Node(6), null);
        return new Node(3, rL, rR);
    }

    /*https://www.hackerrank.com/challenges/tree-level-order-traversal
    * BFS concept  */
    static void LevelOrder(Node root)
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
