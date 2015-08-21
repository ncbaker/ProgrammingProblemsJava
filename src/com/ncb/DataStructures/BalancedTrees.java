package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/18/2015.
 */
public class BalancedTrees {

    /*https://www.hackerrank.com/challenges/self-balancing-tree
    * using NodeBT, must do a find and replace to Node prior to HackerRank submission
    * all that needs to be submitted is insert() method.
    * doInsert() & populateTest() are helpers
    * */
    public static void doInsert() {
        NodeBT root = populateTest1();
        insert(root, 6);
    }

    public static NodeBT insert(NodeBT root, int val) {
        insertNode(root, val);

        int bf = balanceFactor(root);
        NodeBT curr = root;

        while(bf < -1)
        {
            int bfc = balanceFactor(curr.right);
            if(bfc < -1) {
                curr = curr.right;
                continue;
            }

            if(bfc == -1)
                balanceRightLeft(curr);
            balanceRightRight(curr);
        }

        while(bf > 1)
        {
            int bfc = balanceFactor(root.left);
            if(bfc > 1)
                continue;

            if(bfc == 1)
                balanceLeftRight(curr);
            balanceLeftLeft(curr);
        }

        return root;
    }

    static void insertNode(NodeBT root, int value)
    {
        if(value < root.val)
        {
            if(root.left == null) {
                root.left = new NodeBT();
                root.left.val = value;
            }
            else
                insertNode(root.left, value);
        }
        else if(value > root.val)
        {
            if(root.right == null){
                root.right = new NodeBT();
                root.right.val = value;
            }
            else
                insertNode(root.right, value);
        }
    }

    static void balanceRightLeft(NodeBT root)
    {
        NodeBT drop = root.right;
        NodeBT raise = root.right.left;
        drop.left = raise.right;
        raise.right = drop;
        root.right = raise;
    }

    static void balanceRightRight(NodeBT root)
    {
        NodeBT drop = root;
        NodeBT raise = root.right;
        drop.right = raise.left;
        raise.left = drop;
        root = raise;
    }

    static void balanceLeftRight(NodeBT root)
    {
        NodeBT drop = root.left;
        NodeBT raise = root.left.right;
        drop.right = raise.left;
        raise.left = drop;
        root.left = raise;
    }

    static void balanceLeftLeft(NodeBT root)
    {
        NodeBT drop = root;
        NodeBT raise = root.left;
        drop.left = raise.right;
        raise.right = drop;
        root = drop;
    }

    static int balanceFactor(NodeBT root)
    {
        if(root.left == null && root.right == null)
            return 0;

        int left = 0, right = 0;
        if(root.left != null)
            left += balanceFactor(root.left);
        if(root.right != null)
            right += balanceFactor(root.right);

        return left - right;
    }

    private static NodeBT populateTest1()
    {
        NodeBT n5 = new NodeBT(5, 3);
        NodeBT n4 = new NodeBT(4, 2, null, n5);
        NodeBT n2 = new NodeBT(2, 2);
        NodeBT n3 = new NodeBT(3, 1, n2, n4);

        return n3;
    }
}
