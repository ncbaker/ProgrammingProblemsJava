package com.ncb.DataStructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
    /*https://www.hackerrank.com/challenges/swap-nodes-algo
     * forced to run from main so a refernce to Main was added to reference the inner class node
     * Main sln = new Main();
     * new Node() below would need to be replaced with sln.new Node(); in hacker rank solution
     * Node class was added as a private inner class
     *     private class Node {
                int data;
                Node left;
                Node right;
            }
     * this algorithm is a bit memory heavy in that it has the overhead of the ArrayList<ArrayList<Node>>
     * maybe it could be improved */
    public void swapAlgo()
    {
        //Main sln = new Main();
        java.util.Scanner scan = new java.util.Scanner(System.in);
        short nodeCount = scan.nextShort();

        Node root = new Node();
        root.data = 1;

        int currCount = 1;

        ArrayDeque<Node> previousLevel = new ArrayDeque<Node>();
        previousLevel.add(root);

        ArrayDeque<Node> currentLevel = new ArrayDeque<Node>();

        ArrayList<ArrayList<Node>> nodesByLevel = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> rootLevel = new ArrayList<Node>();
        rootLevel.add(root);
        nodesByLevel.add(rootLevel);

        Node temp;
        while(currCount <= nodeCount)
        {
            ArrayList<Node> lvl = new ArrayList<Node>();
            while(!previousLevel.isEmpty()) {
                temp = previousLevel.pop();

                Node left = new Node();
                left.data = scan.nextShort();
                temp.left = left;
                if(left.data != -1) {
                    currentLevel.add(left);
                    lvl.add(left);
                    currCount++;
                }

                Node right = new Node();
                right.data = scan.nextShort();
                temp.right = right;
                if(right.data != -1) {
                    currentLevel.add(right);
                    lvl.add(right);
                    currCount++;
                }
            }

            if(currentLevel.size() == 0)
                break;

            previousLevel = currentLevel;
            currentLevel = new ArrayDeque<Node>();
            nodesByLevel.add(lvl);
        }


        short swapCount = scan.nextShort();
        short[] height = new short[swapCount];
        for (int i = 0; i < swapCount; i++)
            height[i] = scan.nextShort();

        Node tempFlip;
        for (int i = 0; i < swapCount; i++) {
            for (int j = height[i]; j < nodesByLevel.size(); j=j+height[i]) {
                for (int k= 0; k < nodesByLevel.get(j - 1).size(); k++) {
                    temp = nodesByLevel.get(j - 1).get(k);
                    tempFlip = temp.right;
                    temp.right = temp.left;
                    temp.left = tempFlip;
                }
            }

            InorderPositive(root);
            System.out.println();
        }
    }
    void InorderPositive(Node root) {
        if(root.left != null && root.left.data > 0) {
            InorderPositive(root.left);
            System.out.print(root.data + " ");
        }
        else
            System.out.print(root.data + " ");

        if(root.right != null && root.right.data > 0)
            InorderPositive(root.right);
    }

    /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor */
    public Node lca(Node root,int v1,int v2)
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
    Stack<Node> GetLineage(Node root, int value)
    {
        Stack<Node> q = new Stack<Node>();
        q.push(root);

        Node current = root;
        while(true) {
            if(value < current.data) {
                if(current.left == null) {
                    q.clear();
                    break;
                }
                q.push(current.left);
                current = current.left;
            }
            else if(value > current.data) {
                if(current.right == null){
                    q.clear();
                    break;
                }

                q.push(current.right);
                current = current.right;
            }
            else
                break;
        }

        return q;
    }


    /*https://www.hackerrank.com/challenges/binary-search-tree-insertion */
    public Node Insert(Node root,int value)
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
    void InsertBST(Node root,int value)
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
    public void LevelOrder(Node root)
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
    public void top_view(Node root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");

        if(root.right != null)
            right_view(root.right);
    }
    void left_view(Node root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");
    }
    void right_view(Node root)
    {
        System.out.print(root.data + " ");
        if(root.right != null)
            right_view(root.right);
    }


    /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
    public int height(Node root)
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
    public void Inorder(Node root) {
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
    public void Postorder(Node root) {
        if(root.left != null)
            Postorder(root.left);
        if(root.right != null)
            Postorder(root.right);

        System.out.print(root.data + " ");
    }


    /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
    public void Preorder(Node root) {
        System.out.print(root.data + " ");

        if(root.left != null)
            Preorder(root.left);
        if(root.right != null)
            Preorder(root.right);
    }



    //tree factory type methods
    public Node getTree()
    {
        Node rL = new Node(5, new Node(1), new Node(4));
        Node rR = new Node(2, new Node(6), null);
        return new Node(3, rL, rR);
    }
    public Node getTreeHeight()
    {
        Node rL = new Node(5, new Node(1), new Node(4));
        Node rRL = new Node(6, new Node(7), null);
        Node rR = new Node(2, rRL, null);
        return new Node(3, rL, rR);
    }
    public Node getTreeTopView()
    {
        Node rLL = new Node(1, null, new Node(9));
        Node rL = new Node(5, rLL, new Node(4));

        Node rRR = new Node(7, new Node(8), null);
        Node rR = new Node(2, new Node(6), rRR);

        return new Node(3, rL, rR);
    }
    public Node getTreeBinarySearch()
    {
        Node rL = new Node(2, new Node(1), new Node(3));
        Node rR = new Node(7);
        return new Node(4, rL, rR);
    }
    public Node getTreeLCA()
    {
        Node rL = new Node(2, new Node(1), new Node(3));
        Node rR = new Node(7, new Node(6), null);
        return new Node(4, rL, rR);
    }
    public Node getSwapTree1()
    {
        Node rL = new Node(2, new Node(-1), new Node(-1));
        Node rR = new Node(3, new Node(-1), new Node(-1));
        return new Node(1, rL, rR);
    }
    public Node getSwapTree2()
    {
        Node rL = new Node(2, new Node(-1), new Node(4));
        Node rR = new Node(3, new Node(-1), new Node(5));
        return new Node(1, rL, rR);
    }
    public Node getSwapTree3()
    {
        Node rLLL = new Node(6, new Node(-1), new Node(9));
        Node rLL = new Node(4, rLLL, new Node(-1));
        Node rL = new Node(2, rLL, new Node(-1));

        Node rRLR = new Node(8, new Node(10), new Node(11));
        Node rRL = new Node(5, new Node(7), rRLR);
        Node rR = new Node(3, rRL, new Node(-1));

        return new Node(1, rL, rR);
    }

    private class Node {
        int data;
        Node left;
        Node right;
        Node(){}
        Node(int v) { data = v; }
        Node(int v, Node l, Node r) { data = v; left = l; right = r; }
    }
}
