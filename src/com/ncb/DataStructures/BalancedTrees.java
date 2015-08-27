package com.ncb.DataStructures;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.*;

/**
 * Created by nathanb on 8/18/2015.
 */
public class BalancedTrees {

    /*https://www.hackerrank.com/challenges/self-balancing-tree
    * all that needs to be submitted is insert() method.
    * Inorder(), setBalanceFactors(), testTree(), doInsert(), and populateTest() are debug helpers, not needed as part of upload
    * */

    public void doInsert() {
        /* problem test case */
        /**/Node n = insert(null, 3);
        insert(n, 2);
        insert(n, 4);
        insert(n, 5);
        insert(n, 6);
        setBalanceFactors(n);
        testTree(n, "2 3 4 5 6", "Fails problem case");

        /* negative & zero edge case - this does not work*/
        /**/Node neg = insert(null, -3);
        insert(neg, 0);
        insert(neg, -4);
        insert(neg, -5);
        insert(neg, -6);
        setBalanceFactors(neg);
        testTree(neg, "-6 -5 -4 -3 0", "Fails negative & zero case");

        /* test case - balanceRightLeft() */
        Node brl = insert(null, 3);
        insert(brl, 6);
        insert(brl, 4);
        insert(brl, 5);
        setBalanceFactors(brl);
        testTree(brl, "3 4 5 6", "Fails right left shift");

        /* test case - balanceLeftRight() */
        Node blr = insert(null, 5);
        insert(blr, 3);
        insert(blr, 4);
        insert(blr, 2);
        setBalanceFactors(blr);
        testTree(blr, "2 3 4 5", "Fails left right shift");

        /* test case - 1a) balanceLeftRight() */
        Node aBLR = insert(null, 20);
        insert(aBLR, 4);
        insert(aBLR, 15);
        setBalanceFactors(aBLR);
        testTree(aBLR, "4 15 20", "Fails 1a) left right shift");

        /* test case - 2a) double shift LRR & LLL */
        Node dblShft = insert(null, 20);
        insert(dblShft, 4);
        insert(dblShft, 26);
        insert(dblShft, 3);
        insert(dblShft, 9);
        insert(dblShft, 15);
        setBalanceFactors(dblShft);
        testTree(dblShft, "3 4 9 15 20 26", "Fails 2a) double shift LRR & LLL");

        /* test case - 3a) triple shift LRRR & LLL */
        Node triplShft = insert(null, 20);
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
        Node aBLRb = insert(null, 20);
        insert(aBLRb, 4);
        insert(aBLRb, 8);
        setBalanceFactors(aBLRb);
        testTree(aBLRb, "4 8 20", "Fails 1a) left right shift");

        /* test case - 2a) double shift LRR & LLL */
        Node dblShftb = insert(null, 20);
        insert(dblShftb, 4);
        insert(dblShftb, 26);
        insert(dblShftb, 3);
        insert(dblShftb, 9);
        insert(dblShftb, 8);
        setBalanceFactors(dblShftb);
        testTree(dblShftb, "3 4 8 9 20 26", "Fails 2a) double shift LRR & LLL");

        /* test case - 3a) triple shift LRRR & LLL */
        Node triplShftb = insert(null, 20);
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
        testTree(triplShftb, "2 3 4 7 8 9 11 20 21 26 30", "3a) Fails triple shift");/* problem test case */

        /**/Node mult1 = insert(null, 3);
        insert(mult1, 2);
        insert(mult1, 4);
        insert(mult1, 5);
        insert(mult1, 6);
        insert(mult1, 6);
        setBalanceFactors(triplShftb);
        testTree(mult1, "2 3 4 5 6 6", "Fails multiple entries case 1");

        mult1 = insert(null, 3);
        insert(mult1, 2);
        insert(mult1, 4);
        insert(mult1, 5);
        insert(mult1, 6);
        insert(mult1, 5);
        setBalanceFactors(triplShftb);
        testTree(mult1, "2 3 4 5 5 6", "Fails multiple entries case 2");

        mult1 = insert(null, 3);
        insert(mult1, 2);
        insert(mult1, 4);
        insert(mult1, 5);
        insert(mult1, 6);
        insert(mult1, 2);
        setBalanceFactors(triplShftb);
        testTree(mult1, "2 2 3 4 5 6", "Fails multiple entries case 3");

        /* test case - 3a) triple shift LRRR & LLL */
        triplShft = insert(null, 20);
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
        insert(triplShft, 8);
        insert(triplShft, 4);
        setBalanceFactors(triplShft);
        testTree(triplShft, "2 3 4 4 7 8 9 11 15 20 21 26 30", "Fails triple shift multiple values");

        /* test mega upload many repeats */
        n = insert(null, 1);
        insert(n, 19);
        insert(n, 16);
        insert(n, 2);
        insert(n, 1);
        insert(n, 11);
        insert(n, 6);
        insert(n, 13);
        insert(n, 7);
        insert(n, 2);

        insert(n, 14);
        insert(n, 10);
        insert(n, 7);
        insert(n, 9);
        insert(n, 5);
        insert(n, 15);
        insert(n, 8);
        insert(n, 9);
        insert(n, 13);
        insert(n, 8);
        setBalanceFactors(n);
        testTree(n, "1 1 2 2 5 6 7 7 8 8 9 9 10 11 13 13 14 15 16 19", "Fails triple shift multiple values");

        /*insert(n, 20);
        insert(n, 2);
        insert(n, 14);
        insert(n, 12);
        insert(n, 3);
        insert(n, 6);
        insert(n, 5);
        insert(n, 2);
        insert(n, 1);
        insert(n, 15);

        insert(n, 2);
        insert(n, 2);
        insert(n, 20);
        insert(n, 11);
        insert(n, 14);
        insert(n, 8);
        insert(n, 2);
        insert(n, 17);
        insert(n, 5);
        insert(n, 16);

        insert(n, 19);
        insert(n, 15);
        insert(n, 17);
        insert(n, 2);
        insert(n, 13);
        insert(n, 3);
        insert(n, 19);
        insert(n, 1);
        insert(n, 9);
        insert(n, 9);*/
        setBalanceFactors(n);

    }

    void Inorder(Node root, StringBuilder sb) {
        if(root.left != null) {
            Inorder(root.left, sb);
            sb.append(root.val + " ");
        }
        else
            sb.append(root.val + " ");

        if(root.right != null)
            Inorder(root.right, sb);
    }

    void setBalanceFactors(Node n)
    {
        n.balanceFactor = balanceFactor(n);
        if(n.left != null)
            setBalanceFactors(n.left);
        if(n.right != null)
            setBalanceFactors(n.right);
    }

    void testTree(Node node, String inorderResult, String exceptionMsg)
    {
        StringBuilder sb = new StringBuilder();
        Inorder(node, sb);
        if(!sb.toString().trim().equals(inorderResult)) { throw new IllegalArgumentException(exceptionMsg); }
    }


    /* this method and below are required for hacker rank submission */
    Node insert(Node root, int val) {
        if(root == null) {
            root = new Node();
            root.val = val;
        }
        else {
            Stack<Node> nodes = new Stack<Node>();
            insertNode(root, val, nodes);

            Node c;
            Boolean nodeAdded = nodes.peek().left == null || nodes.peek().right == null; //freshly added nodes can't have both
            while (nodes.size() > 0) {
                c = nodes.pop();
                if (nodeAdded)
                    setHeight(c);
                balanceNode(c);
            }
        }

        return root;
    }

    void balanceNode(Node root)
    {
        int bf = balanceFactor(root);
        Node curr = root;

        while(bf < -1 || bf > 1) {
            if (bf < -1) {
                bf = balanceFactor(curr.right);
                if (bf < -1 || bf > 1) {
                    curr.ht--;
                    curr = curr.right;
                    continue;
                }

                if (bf == 1)
                    balanceRightLeft(curr);
                balanceRightRight(curr);

                bf = balanceFactor(root);
            }
            else if (bf > 1) {
                bf = balanceFactor(curr.left);
                if (bf < -1 || bf > 1) {
                    curr.ht--;
                    curr = curr.left;
                    continue;
                }

                if (bf == -1)
                    balanceLeftRight(curr);
                balanceLeftLeft(curr);

                bf = balanceFactor(root);
            }
        }
    }

    void balanceRightLeft(Node root)
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

    void balanceRightRight(Node root)
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

    void balanceLeftRight(Node root)
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

    void balanceLeftLeft(Node root)
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

    int balanceFactor(Node root)
    {
        int left = root.left != null ? root.left.ht : -1;
        int right = root.right != null ? root.right.ht : -1;
        return left - right;
    }

    void insertNode(Node root, int value, Stack<Node> nodes)
    {
        nodes.add(root);
        if(value < root.val)
            {
            if(root.left == null) {
                root.left = new Node();
                root.left.val = value;
            }
            else
                insertNode(root.left, value, nodes);
        }
        else if(value > root.val)
        {
            if(root.right == null){
                root.right = new Node();
                root.right.val = value;
            }
            else
                insertNode(root.right, value, nodes);
        }
        else if(root.right == null) //if equal and no right node exists add to right
        {
            root.right = new Node();
            root.right.val = value;
        }
        else if(root.right != null) //if equal add right exists run on right
            insertNode(root.right, value, nodes);
    }

    void setHeight(Node node)
    {
        int left = (node.left != null) ? node.left.ht : -1;
        int right = (node.right != null) ? node.right.ht : -1;
        node.ht = Math.max(left, right)+1;
    }

    private class Node {
        int val;   //Value
        int ht;      //Height
        Node left;   //Left child
        Node right;   //Right child
        int balanceFactor;
    }
}
