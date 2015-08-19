package com.ncb.DataStructures;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Stack;

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

    /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor
    * */
    public static Node lca(Node root,int v1,int v2)
    {
        if(root == null)
            return new Node();

        Stack<Node> v1Q = GetLineage(root, v1);
        Stack<Node> v2Q = GetLineage(root, v2);

        while(v1Q.size() > v2Q.size())
            v1Q.pop();
        while(v2Q.size() > v1Q.size())
            v2Q.pop();

        while(v1Q.peek().data != v2Q.peek().data) {
            v1Q.pop();
            v2Q.pop();
        }

        if(v1Q.size() > 0) {
            System.out.print(v1Q.peek().data);
            return v1Q.peek();
        }

        return new Node();
    }
    static Stack<Node> GetLineage(Node root, int value)
    {
        Stack<Node> q = new Stack<Node>();
        q.push(root);

        Node current = root;
        while(true) {
            if(value < current.data && current.left != null) {
                /*if(current.left == null)
                    break;*/

                q.push(current.left);
                current = current.left;
            }
            else if(value > current.data && current.right != null) {
                /*if(current.right == null)
                    break;*/

                q.push(current.right);
                current = current.right;
            }
            else {
                q.clear();
                break;
            }
        }

        return q;
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


    /* https://www.hackerrank.com/challenges/tree-top-view */
    static void top_view(Node root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");

        if(root.right != null)
            right_view(root.right);
    }
    static void left_view(Node root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");
    }
    static void right_view(Node root)
    {
        System.out.print(root.data + " ");
        if(root.right != null)
            right_view(root.right);
    }


    /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
    static int height(Node root)
    {
        if(root.left == null && root.right == null)
            return 1;

        int leftHeight = 0, rightHeight = 0;
        if(root.left != null)
            leftHeight += height(root.left);
        if(root.right != null)
            rightHeight += height(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    /* https://www.hackerrank.com/challenges/tree-inorder-traversal */
    static void Inorder(Node root) {
        if(root.left != null) {
            Inorder(root.left);
            System.out.print(root.data + " ");
        }
        else
            System.out.print(root.data + " ");

        if(root.right != null)
            Inorder(root.right);
    }


    /* https://www.hackerrank.com/challenges/tree-postorder-traversal  */
    static void Postorder(Node root) {
        if(root.left != null)
            Postorder(root.left);
        if(root.right != null)
            Postorder(root.right);

        System.out.print(root.data + " ");
    }


    /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
    static void Preorder(Node root) {
        System.out.print(root.data + " ");

        if(root.left != null)
            Preorder(root.left);
        if(root.right != null)
            Preorder(root.right);
    }
}
