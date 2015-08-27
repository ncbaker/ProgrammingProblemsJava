package com.ncb;

import com.ncb.DataStructures.*;

public class Main {

    public static void main(String[] args) {


    }

    public static void Trees() {
        /* ********************** */
        /* Trees & BST Challenges */
        /* ********************** */

        /*https://www.hackerrank.com/challenges/self-balancing-tree */
        BalancedTrees.doInsert();

        /*https://www.hackerrank.com/challenges/swap-nodes-algo */
        Trees.swapAlgo();

        /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor*/
        Trees.lca(Trees.getTreeLCA(),1,7);

        /*https://www.hackerrank.com/challenges/binary-search-tree-insertion*/
        Trees.Insert(Trees.getTreeBinarySearch(),6);

        /*https://www.hackerrank.com/challenges/tree-level-order-traversal */
        Trees.LevelOrder(Trees.getTree());

        /* https://www.hackerrank.com/challenges/tree-top-view */
        Trees.top_view(Trees.getTreeTopView());

        /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
        Trees.height(Trees.getTreeHeight());

        /* https://www.hackerrank.com/challenges/tree-inorder-traversal */
        Trees.Inorder(Trees.getTree());

        /* https://www.hackerrank.com/challenges/tree-postorder-traversal  */
        Trees.Postorder(Trees.getTree());

        /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
        Trees.Preorder(Trees.getTree());
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