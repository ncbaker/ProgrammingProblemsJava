package com.ncb;

import com.ncb.DataStructures.*;

public class Main {

    public static void main(String[] args) {


    }

    public static void BalancedTrees() {
        /* ************************ */
        /* Balanced Tree Challenges */
        /* ************************ */

        /*https://www.hackerrank.com/challenges/self-balancing-tree */
        BalancedTrees balTree = new BalancedTrees();
        balTree.doInsert();
    }

    public static void Trees() {
        /* **************** */
        /* Trees Challenges */
        /* **************** */
        Trees tree = new Trees();
        /*https://www.hackerrank.com/challenges/swap-nodes-algo */
        tree.swapAlgo();

        /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor*/
        tree.lca(tree.getTreeLCA(),1,7);

        /*https://www.hackerrank.com/challenges/binary-search-tree-insertion*/
        tree.Insert(tree.getTreeBinarySearch(),6);

        /*https://www.hackerrank.com/challenges/tree-level-order-traversal */
        tree.LevelOrder(tree.getTree());

        /* https://www.hackerrank.com/challenges/tree-top-view */
        tree.top_view(tree.getTreeTopView());

        /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
        tree.height(tree.getTreeHeight());

        /* https://www.hackerrank.com/challenges/tree-inorder-traversal */
        tree.Inorder(tree.getTree());

        /* https://www.hackerrank.com/challenges/tree-postorder-traversal  */
        tree.Postorder(tree.getTree());

        /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
        tree.Preorder(tree.getTree());
    }

    public static void LinkedList() {
        /* ************ */
        /* LINKED LISTS */
        /* ************ */
        LinkedList lnkList = new LinkedList();
        /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list*/
        lnkList.PrintAll();

        /*https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list*/
        lnkList.InsertTailAll();

        /*https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/submissions/code/13241041*/
        lnkList.InsertHeadAll();

        /*https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list*/
        lnkList.InsertNthAll();

        /*https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list*/
        lnkList.DeleteAll();

        /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list-in-reverse/submissions/code/13329894*/
        lnkList.ReversePrintAll();
    }
}