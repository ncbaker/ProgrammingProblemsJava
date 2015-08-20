package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/20/2015.
 */
public class LinkedList {

    /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list*/
    void Print(Node head) {
        if(head == null)
            return;

        Node current = head;
        while(true) {
            System.out.println(current.data);
            if(current.next == null)
                break;

            current = current.next;
        }
    }

    /*https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list*/
    Node Insert1(Node head,int data) {
        Node newNode = new Node();
        newNode.data = data;

        if(head == null){
            return newNode;
        }

        Node current = head;
        while(true) {
            if(current.next == null)
            {
                current.next = newNode;
                break;
            }

            current = current.next;
        }

        return head;
    }

    /*https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/submissions/code/13241041*/
    Node Insert2(Node head,int x) {
        Node newNode = new Node();
        newNode.data = x;

        if(head == null){
            return newNode;
        }

        newNode.next = head;
        return newNode;
    }

    private class Node {
        int data;
        Node next;
    }
}
