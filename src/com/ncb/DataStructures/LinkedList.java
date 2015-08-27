package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/20/2015.
 */
public class LinkedList {

    String toString(Node head) {
        if(head == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Node current = head;
        while(true) {
            sb.append(current.data + " ");
            if(current.next == null)
                break;

            current = current.next;
        }

        return sb.toString().trim();
    }
    void testCase(Node n, String intendedValue, String testName)
    {
        if(!toString(n).equals(intendedValue))
            throw new IllegalArgumentException(testName + " error");
        else
            System.out.println(testName + " pass test case");
    }

    /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list*/
    public void PrintAll()
    {
        Node n = InsertTail(null, 1);
        n = InsertTail(n, 2);
        n = InsertTail(n, 3);
        Print(n);
    }
    public void Print(Node head) {
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
    public void InsertTailAll()
    {
        Node n = InsertTail(null, 2);
        n = InsertTail(n, 3);

        testCase(n, "2 3", "Insert Tail");
    }
    Node InsertTail(Node head,int data) {
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
    public void InsertHeadAll()
    {
        Node n = InsertHead(null, 1);
        n = InsertHead(n, 2);

        testCase(n, "2 1", "Insert Head");
    }
    Node InsertHead(Node head,int x) {
        Node newNode = new Node();
        newNode.data = x;

        if(head == null){
            return newNode;
        }

        newNode.next = head;
        return newNode;
    }

    /*https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list*/
    public void InsertNthAll()
    {
        Node n = InsertNth(null, 3, 0);
        n = InsertNth(n, 5, 1);
        n = InsertNth(n, 4, 2);
        n = InsertNth(n, 2, 4);
        n = InsertNth(n, 10, 1);
        System.out.print(toString(n));

        testCase(n, "3 10 5 4 2", "InsertNth");
    }
    Node InsertNth(Node head, int data, int position) {
        Node newNode = new Node();
        newNode.data = data;
        if(position == 0)
        {
            newNode.next = head != null ? head : null;
            return newNode;
        }

        int ct = 1;
        Node prev = head;
        while(true)
        {
            if(position == ct || prev.next == null) {
                newNode.next = prev.next != null ? prev.next : null;
                prev.next = newNode;
                break;
            }
            ct++;
            prev = prev.next;
        }

        return head;
    }

    /*https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list*/
    public void DeleteAll()
    {
        Node n = InsertNth(null, 1, 0);
        n = InsertNth(n, 2, 1);
        n = InsertNth(n, 3, 2);
        n = Delete(n, 0);
        testCase(n, "2 3", "Delete Case 0");

        n = InsertNth(null, 1, 0);
        n = InsertNth(n, 2, 1);
        n = InsertNth(n, 3, 2);
        n = Delete(n, 1);
        testCase(n, "1 3", "Delete Case 1");

        n = InsertNth(null, 1, 0);
        n = InsertNth(n, 2, 1);
        n = InsertNth(n, 3, 2);
        n = Delete(n, 2);
        testCase(n, "1 2", "Delete Case 2");

        n = InsertNth(null, 4, 0);
        n = InsertNth(n, 3, 1);
        n = InsertNth(n, 2, 2);
        n = InsertNth(n, 5, 3);
        n = InsertNth(n, 1, 4);
        n = Delete(n, 2);
        testCase(n, "4 3 5 1", "Delete Case 3");
    }
    Node Delete(Node head, int position) {
        if(position == 0)
            return head.next;

        int t = 1;
        Node prev = head;

        while(true){
            if(t == position)
            {
                prev.next = prev.next.next != null ? prev.next.next : null;
                break;
            }
            prev = prev.next;
            t++;
            if(prev == null)
                return head;
        }

        return head;
    }

    /*https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list-in-reverse/submissions/code/13329894*/
    public void ReversePrintAll()
    {
        Node n = InsertTail(null, 1);
        n = InsertTail(n, 2);
        ReversePrint(n);

        n = InsertTail(null, 2);
        n = InsertTail(n, 1);
        n = InsertTail(n, 4);
        n = InsertTail(n, 5);
        ReversePrint(n);
    }
    void ReversePrint(Node head) {
        if(head == null)
            return;
        else if(head.next == null)
            System.out.println(head.data);
        else {
            ReversePrint(head.next);
            System.out.println(head.data);
            return;
        }
    }
    private class Node {
        int data;
        Node next;
    }
}
