package com.ncb.DataStructures;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/**
 * Created by nathanb on 8/18/2015.
 */
public class BalancedTrees {

    /*https://www.hackerrank.com/challenges/self-balancing-tree
    * using NodeBT, must do a find and replace to Node prior to HackerRank submission
    * all that needs to be submitted is insert() method.
    * doInsert() & populateTest() are helpers, not needed as part of upload
    * */
    private static Node populateTest1()
    {
        Node n5 = new Node(5, 1);
        Node n4 = new Node(4, 2, null, n5);
        Node n2 = new Node(2, 1);
        Node n3 = new Node(3, 3, n2, n4);

        return n3;
    }

    private static void setBalanceFactors(Node n)
    {
        n.balanceFactor = balanceFactor(n);
        if(n.left != null)
            setBalanceFactors(n.left);
        if(n.right != null)
            setBalanceFactors(n.right);
    }

    public static void doInsert() {
        //Node root = populateTest1();
        Node root = new Node();

        insert(root, 3);
        insert(root, 2);
        insert(root, 4);
        insert(root, 5);

        insert(root, 6);
        setBalanceFactors(root);
    }

    public static Node insert(Node root, int val) {
        insertNode(root, val);
        setHeight(root);

        int bf = balanceFactor(root);
        Node curr = root;

        while(bf < -1)
        {
            int bfc = balanceFactor(curr.right);
            if(bfc < -1) {
                curr.ht--;
                curr = curr.right;
                continue;
            }

            if(bfc == 1)
                balanceRightLeft(curr);
            balanceRightRight(curr);

            bf = balanceFactor(root);
        }

        while(bf > 1)
        {
            int bfc = balanceFactor(root.left);
            if(bfc > 1) {
                curr.ht--;
                curr = curr.left;
                continue;
            }

            if(bfc == -1)
                balanceLeftRight(curr);
            balanceLeftLeft(curr);

            bf = balanceFactor(root);
        }

        return root;
    }

    static void balanceRightLeft(Node root)
    {//root stays the same, swap right-left & right
        Node drop = new Node();
        drop.val = root.right.val;
        if(root.right.left.right != null)
            drop.left = root.right.left.right;
        if(root.right.right != null)
            drop.right = root.right.right;

        root.right.val = root.right.left.val;
        if(root.right.left.left != null)
            root.right.left = root.right.left.left;
        root.right.right = drop;

        setHeight(drop);
        setHeight(root.right);
        setHeight(root);
    }

    static void balanceRightRight(Node root)
    {//move right to root and drop root to left of new root
        Node drop = new Node();
        drop.val = root.val;
        if(root.left != null)
            drop.left = root.left;
        if(root.right.left != null)
            drop.right = root.right.left;

        root.val = root.right.val;
        root.left = drop;
        root.right = root.right.right;

        setHeight(root.left);
        setHeight(root);
    }

    static void balanceLeftRight(Node root)
    {//root stays the same, swap left-right & left
        Node drop = new Node();
        drop.val = root.left.val;
        if(root.left.left != null)
            drop.left = root.left.left;
        if(root.left.right.left != null)
            drop.right = root.left.right.left;

        root.left.val = root.left.right.val;
        if(root.left.right.right != null)
            root.left.right = root.left.right.right;
        root.left.left = drop;

        setHeight(drop);
        setHeight(root.left);
        setHeight(root);
    }

    static void balanceLeftLeft(Node root)
    {//move left to root and drop root to right of new root
        Node drop = new Node();
        drop.val = root.val;
        if(root.right != null)
            drop.right = root.right;
        if(root.left.right != null)
            drop.left = root.left.right;

        root.val = root.left.val;
        root.right = drop;
        root.left = root.left.left;

        setHeight(root.right);
        setHeight(root);
    }

    static int balanceFactor(Node root)
    {
        int left = 0, right = 0;

        if(root.left != null)
            left = root.left.ht;

        if(root.right != null)
            right = root.right.ht;

        return left - right;
    }

    static void insertNode(Node root, int value)
    {
        if(root == null)
        {
            root = new Node();
            root.val = value;
            root.ht = 0;
            return;
        }
        else if(root.val == 0)
        {
            root.val = value;
            root.ht = 0;
            return;
        }
        else if(value < root.val)
        {
            if(root.left == null) {
                root.left = new Node();
                root.left.val = value;
                root.left.ht = 1;
            }
            else {
                root.left.ht++;
                insertNode(root.left, value);
            }
        }
        else if(value > root.val)
        {
            if(root.right == null){
                root.right = new Node();
                root.right.val = value;
                root.right.ht = 1;
            }
            else {
                root.right.ht++;
                insertNode(root.right, value);
            }
        }
    }

    static void setHeight(Node node)
    {
        int left = (node.left != null) ? node.left.ht : -1;
        int right = (node.right != null) ? node.right.ht : -1;
        node.ht = Math.max(left, right)+1;
    }


    /*


    static void balanceRightLeftBK(Node root)
    {
        Node drop = root.right;
        Node raise = root.right.left;
        drop.left = raise.right;
        raise.right = drop;
        root.right = raise;

        setHeight(drop);
        setHeight(raise);
    }

    static void balanceRightRightBK(Node root)
    {
        Node drop = root;
        Node raise = root.right;
        drop.right = raise.left;
        raise.left = drop;
        root = raise;

        setHeight(drop);
        setHeight(raise);

        //root = raise;
        root.val = raise.val;
        root.ht = raise.ht;
        root.left = raise.left;
        root.right = raise.right;
    }

    static void decrementHeight(Node node)
    {
        node.ht--;
        if(node.left != null)
            decrementHeight(node.left);
        if(node.right != null)
            decrementHeight(node.right);
    }

    static void incrementHeight(Node node)
    {//decrementHeight(node);
        node.ht++;
        if(node.left != null)
            incrementHeight(node.left);
        if(node.right != null)
            incrementHeight(node.right);
    }

    static void setHeight(Node node)
    {

    }*/
}
