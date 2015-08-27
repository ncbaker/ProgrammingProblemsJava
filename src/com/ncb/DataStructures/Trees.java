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
    String toString(Node n)
    {
        return toString(n, new StringBuilder());
    }
    String toString(Node root, StringBuilder sb) {
        sb.append(root.data + " ");

        if(root.left != null)
            toString(root.left, sb);
        if(root.right != null)
            toString(root.right, sb);

        return sb.toString().trim();
    }
    void testCase(Node n, String intendedValue, String testName)
    {
        if(!toString(n).equals(intendedValue))
            throw new IllegalArgumentException(testName + " error");
        else
            System.out.println(testName + " pass test case");
    }
    void testCase(String input, String intendedValue, String testName)
    {
        if(!input.equals(intendedValue))
            throw new IllegalArgumentException(testName + " error");
        else
            System.out.println(testName + " pass test case");
    }

    /*https://www.hackerrank.com/challenges/swap-nodes-algo
     * forced to run from main so a reference to Main was added to reference the inner class node
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
    public void RunLCA(){
        Node root = lca(getTreeLCA(),1,7);

        testCase(root, "4 2 1 3 7 6", "Lowest Common Ancestor");
    }
    Node lca(Node root,int v1,int v2)
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
    public void RunInsert(){
        Node root = Insert(getTreeBinarySearch(),6);

        testCase(root, "4 2 1 3 7 6", "Binary Tree Insert");
    }
    Node Insert(Node root,int value)
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
    public void RunLevelOrder(){
        testCase(LevelOrder(getTree()), "3 5 2 1 4 6", "Level Order Traversal");
    }
    String LevelOrder(Node root)
    {
        StringBuilder sb = new StringBuilder();
        java.util.Queue<Node> nodeQ = new java.util.LinkedList<Node>();
        nodeQ.add(root);
        Node n;


        while(!nodeQ.isEmpty())
        {
            n = nodeQ.remove();
            sb.append(n.data + " ");
            if(n.left != null)
                nodeQ.add(n.left);
            if(n.right != null)
                nodeQ.add(n.right);
        }

        return sb.toString().trim();
    }


    /* https://www.hackerrank.com/challenges/tree-top-view */
    public void RunTopView(){
        testCase(top_view(getTreeTopView()), "1 5 3 2 7", "Top View Traversal");
    }
    String top_view(Node root)
    {
        StringBuilder sb = new StringBuilder();
        if(root.left != null)
            left_view(root.left, sb);

        sb.append(root.data + " ");

        if(root.right != null)
            right_view(root.right, sb);

        return sb.toString().trim();
    }
    String left_view(Node root, StringBuilder sb)
    {
        if(root.left != null)
            left_view(root.left, sb);

        sb.append(root.data + " ");

        return sb.toString();
    }
    String right_view(Node root, StringBuilder sb)
    {
        sb.append(root.data + " ");
        if(root.right != null)
            right_view(root.right, sb);

        return  sb.toString();
    }


    /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
    public void RunHeight(){ testCase(String.valueOf(height(getTreeHeight())), "4", "Height"); }
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
    public void RunInorder(){ testCase(InorderTraversal(getTree(), new StringBuilder()).trim(), "1 5 4 3 6 2", "Inorder Traversal"); }
    void Inorder(Node root) {
        System.out.print(InorderTraversal(root, new StringBuilder()));
    }
    String InorderTraversal(Node root, StringBuilder sb) {
        if(root.left != null) {
            InorderTraversal(root.left, sb);
            sb.append(root.data + " ");
        }
        else
            sb.append(root.data + " ");

        if(root.right != null)
            InorderTraversal(root.right, sb);

        return sb.toString();
    }


    /* https://www.hackerrank.com/challenges/tree-postorder-traversal  */
    public void RunPostorder(){ testCase(PostorderTraversal(getTree(), new StringBuilder()).trim(), "1 4 5 6 2 3", "Inorder Traversal"); }
    void Postorder(Node root) {
        System.out.print(PostorderTraversal(root, new StringBuilder()));
    }
    String PostorderTraversal(Node root, StringBuilder sb) {
        if(root.left != null)
            PostorderTraversal(root.left, sb);
        if(root.right != null)
            PostorderTraversal(root.right, sb);

        sb.append(root.data + " ");

        return sb.toString();
    }

    /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
    public void RunPreorder(){ testCase(PreorderTraversal(getTree(), new StringBuilder()).trim(), "3 5 1 4 2 6", "Inorder Traversal"); }
    void Preorder(Node root) {
        System.out.print(PreorderTraversal(root, new StringBuilder()));
    }
    String PreorderTraversal(Node root, StringBuilder sb) {
        sb.append(root.data + " ");;

        if(root.left != null)
            PreorderTraversal(root.left, sb);
        if(root.right != null)
            PreorderTraversal(root.right, sb);

        return sb.toString();
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
