package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/18/2015.
 */
public class Node {
    int data;
    Node left;
    Node right;
    Node(){}
    Node(int v) { data = v; }
    Node(int v, Node l, Node r) { data = v; left = l; right = r; }
}
