package com.ncb;

import com.ncb.DataStructures.*;

public class Main {

    public static void main(String[] args) {

        BalancedTrees();

        Trees();

        LinkedList();
    }

    public static void BalancedTrees() {
        System.out.println("Balanced Search Tree (AVL)");
        /* ************************************ */
        /* Balanced Search Tree (AVL) Challenge */
        /* ************************************ */

        /*https://www.hackerrank.com/challenges/self-balancing-tree */
        System.out.println("https://www.hackerrank.com/challenges/self-balancing-tree");
        BalancedTrees balTree = new BalancedTrees();
        balTree.doInsert();
        System.out.println();
        System.out.println();
    }

    public static void Trees() {
        System.out.println("Binary Search Trees");
        /* ****************************** */
        /* Binary Search Trees Challenges */
        /* ****************************** */
        Trees tree = new Trees();
        /*https://www.hackerrank.com/challenges/swap-nodes-algo */
        /*System.out.println("https://www.hackerrank.com/challenges/swap-nodes-algo");
        tree.swapAlgo();  //this works but requires manual input.  see above url
        System.out.println();*/

        /*https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor*/
        System.out.println("https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor");
        tree.RunLCA();
        System.out.println();

        /*https://www.hackerrank.com/challenges/binary-search-tree-insertion*/
        System.out.println("https://www.hackerrank.com/challenges/binary-search-tree-insertion");
        tree.RunInsert();
        System.out.println();

        /*https://www.hackerrank.com/challenges/tree-level-order-traversal */
        System.out.println("https://www.hackerrank.com/challenges/tree-level-order-traversal");
        tree.RunLevelOrder();
        System.out.println();

        /* https://www.hackerrank.com/challenges/tree-top-view */
        System.out.println("https://www.hackerrank.com/challenges/tree-top-view");
        tree.RunTopView();
        System.out.println();

        /* https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree */
        System.out.println("https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree");
        tree.RunHeight();
        System.out.println();

        /* https://www.hackerrank.com/challenges/tree-inorder-traversal */
        System.out.println("https://www.hackerrank.com/challenges/tree-inorder-traversal");
        tree.RunInorder();
        System.out.println();

        /* https://www.hackerrank.com/challenges/tree-postorder-traversal  */
        System.out.println("https://www.hackerrank.com/challenges/tree-postorder-traversal");
        tree.RunPostorder();
        System.out.println();

        /* https://www.hackerrank.com/challenges/tree-preorder-traversal */
        System.out.println("https://www.hackerrank.com/challenges/tree-preorder-traversal");
        tree.RunPreorder();
        System.out.println();
        System.out.println();
    }

    public static void LinkedList() {
        System.out.println("LINKED LISTS");
        /* ************ */
        /* LINKED LISTS */
        /* ************ */
        LinkedList lnkList = new LinkedList();
        /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list*/
        System.out.println("https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list");
        lnkList.PrintAll();
        System.out.println();

        /*https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list*/
        System.out.println("https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list");
        lnkList.InsertTailAll();
        System.out.println();


        /*https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/submissions/code/13241041*/
        System.out.println("https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/submissions/code/13241041");
        lnkList.InsertHeadAll();
        System.out.println();


        /*https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list*/
        System.out.println("https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list");
        lnkList.InsertNthAll();
        System.out.println();


        /*https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list*/
        System.out.println("https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list");
        lnkList.DeleteAll();
        System.out.println();


        /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list-in-reverse/submissions/code/13329894*/
        System.out.println("https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list-in-reverse/submissions/code/13329894");
        lnkList.ReversePrintAll();
        System.out.println();
        System.out.println();
    }
}