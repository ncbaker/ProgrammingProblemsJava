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
    public static void swapAlgo()
    {
        //Main sln = new Main();
        java.util.Scanner scan = new java.util.Scanner(System.in);
        short nodeCount = scan.nextShort();

        NodeTree root = new NodeTree();
        root.data = 1;

        int currCount = 1;

        ArrayDeque<NodeTree> previousLevel = new ArrayDeque<NodeTree>();
        previousLevel.add(root);

        ArrayDeque<NodeTree> currentLevel = new ArrayDeque<NodeTree>();

        ArrayList<ArrayList<NodeTree>> nodesByLevel = new ArrayList<ArrayList<NodeTree>>();
        ArrayList<NodeTree> rootLevel = new ArrayList<NodeTree>();
        rootLevel.add(root);
        nodesByLevel.add(rootLevel);

        NodeTree temp;
        while(currCount <= nodeCount)
        {
            ArrayList<NodeTree> lvl = new ArrayList<NodeTree>();
            while(!previousLevel.isEmpty()) {
                temp = previousLevel.pop();

                NodeTree left = new NodeTree();
                left.data = scan.nextShort();
                temp.left = left;
                if(left.data != -1) {
                    currentLevel.add(left);
                    lvl.add(left);
                    currCount++;
                }

                NodeTree right = new NodeTree();
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
            currentLevel = new ArrayDeque<NodeTree>();
            nodesByLevel.add(lvl);
        }


        short swapCount = scan.nextShort();
        short[] height = new short[swapCount];
        for (int i = 0; i < swapCount; i++)
            height[i] = scan.nextShort();

        NodeTree tempFlip;
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
    static void InorderPositive(NodeTree root) {
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
    public static NodeTree lca(NodeTree root,int v1,int v2)
    {
        if(root == null)
            return new NodeTree();

        Stack<NodeTree> v1Q = GetLineage(root, v1);
        Stack<NodeTree> v2Q = GetLineage(root, v2);

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

        return new NodeTree();
    }
    static Stack<NodeTree> GetLineage(NodeTree root, int value)
    {
        Stack<NodeTree> q = new Stack<NodeTree>();
        q.push(root);

        NodeTree current = root;
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
    public static NodeTree Insert(NodeTree root,int value)
    {
        if(root == null)
        {
            root = new NodeTree();
            root.data = value;
        }
        else
            InsertBST(root, value);

        return root;
    }
    static void InsertBST(NodeTree root,int value)
    {
        if(value < root.data)
        {
            if(root.left == null) {
                root.left = new NodeTree();
                root.left.data = value;
            }
            else
                InsertBST(root.left, value);
        }
        else if(value > root.data)
        {
            if(root.right == null) {
                root.right = new NodeTree();
                root.right.data = value;
            } else
                InsertBST(root.right, value);
        }
    }


    /*https://www.hackerrank.com/challenges/tree-level-order-traversal
    * BFS concept  */
    public static void LevelOrder(NodeTree root)
    {
        java.util.Queue<NodeTree> nodeQ = new java.util.LinkedList<NodeTree>();
        nodeQ.add(root);
        NodeTree n;


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
    public static void top_view(NodeTree root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");

        if(root.right != null)
            right_view(root.right);
    }
    static void left_view(NodeTree root)
    {
        if(root.left != null)
            left_view(root.left);

        System.out.print(root.data + " ");
    }
    static void right_view(NodeTree root)
    {
        System.out.print(root.data + " ");
        if(root.right != null)
            right_view(root.right);
    }


    /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
    public static int height(NodeTree root)
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
    public static void Inorder(NodeTree root) {
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
    public static void Postorder(NodeTree root) {
        if(root.left != null)
            Postorder(root.left);
        if(root.right != null)
            Postorder(root.right);

        System.out.print(root.data + " ");
    }


    /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
    public static void Preorder(NodeTree root) {
        System.out.print(root.data + " ");

        if(root.left != null)
            Preorder(root.left);
        if(root.right != null)
            Preorder(root.right);
    }



    //tree factory type methods
    public static NodeTree getTree()
    {
        NodeTree rL = new NodeTree(5, new NodeTree(1), new NodeTree(4));
        NodeTree rR = new NodeTree(2, new NodeTree(6), null);
        return new NodeTree(3, rL, rR);
    }
    public static NodeTree getTreeHeight()
    {
        NodeTree rL = new NodeTree(5, new NodeTree(1), new NodeTree(4));
        NodeTree rRL = new NodeTree(6, new NodeTree(7), null);
        NodeTree rR = new NodeTree(2, rRL, null);
        return new NodeTree(3, rL, rR);
    }
    public static NodeTree getTreeTopView()
    {
        NodeTree rLL = new NodeTree(1, null, new NodeTree(9));
        NodeTree rL = new NodeTree(5, rLL, new NodeTree(4));

        NodeTree rRR = new NodeTree(7, new NodeTree(8), null);
        NodeTree rR = new NodeTree(2, new NodeTree(6), rRR);

        return new NodeTree(3, rL, rR);
    }
    public static NodeTree getTreeBinarySearch()
    {
        NodeTree rL = new NodeTree(2, new NodeTree(1), new NodeTree(3));
        NodeTree rR = new NodeTree(7);
        return new NodeTree(4, rL, rR);
    }
    public static NodeTree getTreeLCA()
    {
        NodeTree rL = new NodeTree(2, new NodeTree(1), new NodeTree(3));
        NodeTree rR = new NodeTree(7, new NodeTree(6), null);
        return new NodeTree(4, rL, rR);
    }
    public static NodeTree getSwapTree1()
    {
        NodeTree rL = new NodeTree(2, new NodeTree(-1), new NodeTree(-1));
        NodeTree rR = new NodeTree(3, new NodeTree(-1), new NodeTree(-1));
        return new NodeTree(1, rL, rR);
    }
    public static NodeTree getSwapTree2()
    {
        NodeTree rL = new NodeTree(2, new NodeTree(-1), new NodeTree(4));
        NodeTree rR = new NodeTree(3, new NodeTree(-1), new NodeTree(5));
        return new NodeTree(1, rL, rR);
    }
    public static NodeTree getSwapTree3()
    {
        NodeTree rLLL = new NodeTree(6, new NodeTree(-1), new NodeTree(9));
        NodeTree rLL = new NodeTree(4, rLLL, new NodeTree(-1));
        NodeTree rL = new NodeTree(2, rLL, new NodeTree(-1));

        NodeTree rRLR = new NodeTree(8, new NodeTree(10), new NodeTree(11));
        NodeTree rRL = new NodeTree(5, new NodeTree(7), rRLR);
        NodeTree rR = new NodeTree(3, rRL, new NodeTree(-1));

        return new NodeTree(1, rL, rR);
    }

}
