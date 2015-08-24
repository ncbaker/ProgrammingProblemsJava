package com.ncb.DataStructures;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/**
 * Created by nathanb on 8/18/2015.
 */
public class BalancedTrees {

    /*https://www.hackerrank.com/challenges/self-balancing-tree
    * using NodeBT, must do a find and replace to Node prior to HackerRank submission
    * all that needs to be submitted is insert() method.
    * Inorder(), setBalanceFactors(), testTree(), doInsert(), and populateTest() are debug helpers, not needed as part of upload
    * */
/*    private static Node populateTest1()
    {
        Node n5 = new Node(5, 1);
        Node n4 = new Node(4, 2, null, n5);
        Node n2 = new Node(2, 1);
        Node n3 = new Node(3, 3, n2, n4);

        return n3;
    }*/
    static void Inorder(Node root, StringBuilder sb) {
        if(root.left != null) {
            Inorder(root.left, sb);
            sb.append(root.val + " ");
        }
        else
            sb.append(root.val + " ");

        if(root.right != null)
            Inorder(root.right, sb);
    }

    private static void setBalanceFactors(Node n)
    {
        n.balanceFactor = balanceFactor(n);
        if(n.left != null)
            setBalanceFactors(n.left);
        if(n.right != null)
            setBalanceFactors(n.right);
    }

    private static void testTree(Node node, String inorderResult, String exceptionMsg)
    {
        StringBuilder sb = new StringBuilder();
        Inorder(node, sb);
        if(!sb.toString().trim().equals(inorderResult)) { throw new IllegalArgumentException(exceptionMsg); }
    }

    public static void doInsert() {
        //Node root = populateTest1();

        /* problem test case */
        /**/Node root = new Node();
        insert(root, 3);
        insert(root, 2);
        insert(root, 4);
        insert(root, 5);
        insert(root, 6);
        testTree(root, "2 3 4 5 6", "Fails problem case");

        /* null test case - null becomes root */
        /**/Node n = insert(null, 3);
        insert(n, 2);
        insert(n, 4);
        insert(n, 5);
        insert(n, 6);
        setBalanceFactors(n);
        testTree(n, "2 3 4 5 6", "Fails null case");

        /* negative & zero edge case - this does not work*/
        /**/Node neg = new Node();
        insert(neg, -3);
        insert(neg, 0);
        insert(neg, -4);
        insert(neg, -5);
        insert(neg, -6);
        setBalanceFactors(neg);
        testTree(neg, "-6 -5 -4 -3 0", "Fails negative & zero case");

        /* test case - balanceRightLeft() */
        Node brl = new Node();
        insert(brl, 3);
        insert(brl, 6);
        insert(brl, 4);
        insert(brl, 5);
        setBalanceFactors(brl);
        testTree(brl, "3 4 5 6", "Fails right left shift");

        /* test case - balanceLeftRight() */
        Node blr = new Node();
        insert(blr, 5);
        insert(blr, 3);
        insert(blr, 4);
        insert(blr, 2);
        setBalanceFactors(blr);
        testTree(blr, "2 3 4 5", "Fails left right shift");

        /* test case - 1a) balanceLeftRight() */
        Node aBLR = new Node();
        insert(aBLR, 20);
        insert(aBLR, 4);
        insert(aBLR, 15);
        setBalanceFactors(aBLR);
        testTree(aBLR, "4 15 20", "Fails 1a) left right shift");

        /* test case - 2a) double shift LRR & LLL */
        Node dblShft = new Node();
        insert(dblShft, 20);
        insert(dblShft, 4);
        insert(dblShft, 26);
        insert(dblShft, 3);
        insert(dblShft, 9);
        insert(dblShft, 15);
        setBalanceFactors(dblShft);
        testTree(dblShft, "3 4 9 15 20 26", "Fails 2a) double shift LRR & LLL");

        /* test case - 3a) triple shift LRRR & LLL */
        Node triplShft = new Node();
        insert(triplShft, 20);
        insert(triplShft, 4);
        insert(triplShft, 26);
        insert(triplShft, 3);
        insert(triplShft, 9);
        insert(triplShft, 21);
        insert(triplShft, 30);
        insert(triplShft, 2);
        insert(triplShft, 7);
        insert(triplShft, 11);
        insert(triplShft, 15);
        setBalanceFactors(triplShft);
        testTree(triplShft, "2 3 4 7 9 11 15 20 21 26 30", "3a) Fails triple shift");

        /* test case - 1a) balanceLeftRight() */
        Node aBLRb = new Node();
        insert(aBLRb, 20);
        insert(aBLRb, 4);
        insert(aBLRb, 8);
        setBalanceFactors(aBLRb);
        testTree(aBLRb, "4 8 20", "Fails 1a) left right shift");

        /* test case - 2a) double shift LRR & LLL */
        Node dblShftb = new Node();
        insert(dblShftb, 20);
        insert(dblShftb, 4);
        insert(dblShftb, 26);
        insert(dblShftb, 3);
        insert(dblShftb, 9);
        insert(dblShftb, 8);
        setBalanceFactors(dblShftb);
        testTree(dblShftb, "3 4 8 9 20 26", "Fails 2a) double shift LRR & LLL");

        /* test case - 3a) triple shift LRRR & LLL */
        Node triplShftb = new Node();
        insert(triplShftb, 20);
        insert(triplShftb, 4);
        insert(triplShftb, 26);
        insert(triplShftb, 3);
        insert(triplShftb, 9);
        insert(triplShftb, 21);
        insert(triplShftb, 30);
        insert(triplShftb, 2);
        insert(triplShftb, 7);
        insert(triplShftb, 11);
        insert(triplShftb, 8);
        setBalanceFactors(triplShftb);
        testTree(triplShftb, "2 3 4 7 8 9 11 20 21 26 30", "3a) Fails triple shift");
    }



    public static Node insert(Node root, int val) {
        if(root == null)
            root = new Node();
        if(root.val == 0)//overwrites root if root = 0 could that be valid?  fixes null root parameters
            root.val = val;

        insertNode(root, val);
        setHeight(root);//move into insertNode?

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
            int bfc = balanceFactor(curr.left);
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
        drop.right = root.right.right != null ? root.right.right : null;
        drop.left = root.right.left.right != null ? root.right.left.right : null;

        root.right.val = root.right.left.val;
        root.right.left = root.right.left.left != null ? root.right.left.left : null;
        root.right.right = drop;

        setHeight(drop);
        setHeight(root.right);
        setHeight(root);
    }

    static void balanceRightRight(Node root)
    {//move right to root and drop root to left of new root
        Node drop = new Node();
        drop.val = root.val;
        drop.left = root.left != null ? root.left : null;
        drop.right = root.right.left != null ? root.right.left : null;

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
        drop.left = root.left.left != null ? root.left.left : null;
        drop.right = root.left.right.left != null ? root.left.right.left : null;

        root.left.val = root.left.right.val;
        root.left.right = root.left.right.right != null ? root.left.right.right : null;
        root.left.left = drop;

        setHeight(drop);
        setHeight(root.left);
        setHeight(root);
    }

    static void balanceLeftLeft(Node root)
    {//move left to root and drop root to right of new root
        Node drop = new Node();
        drop.val = root.val;
        drop.right = root.right != null ? root.right : null;
        drop.left = root.left.right != null ? root.left.right : null;

        root.val = root.left.val;
        root.right = drop;
        root.left = root.left.left;

        setHeight(root.right);
        setHeight(root);
    }

    static int balanceFactor(Node root)
    {
        int left = root.left != null ? root.left.ht : -1;
        int right = root.right != null ? root.right.ht : -1;
        return left - right;
    }

    static void insertNode(Node root, int value)
    {
        if(value < root.val)
        {
            if(root.left == null) {
                root.left = new Node();
                root.left.val = value;
                root.left.ht = 0;
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
                root.right.ht = 0;
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
