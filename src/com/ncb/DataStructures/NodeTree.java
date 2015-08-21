package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/18/2015.
 */
public class NodeTree {
    int data;
    NodeTree left;
    NodeTree right;
    NodeTree(){}
    NodeTree(int v) { data = v; }
    NodeTree(int v, NodeTree l, NodeTree r) { data = v; left = l; right = r; }
}
