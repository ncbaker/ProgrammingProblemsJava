package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/21/2015.
 */
public class Node {
    int val;   //Value
    int ht;      //Height
    Node left;   //Left child
    Node right;   //Right child
    int balanceFactor;


    //added these for easy populating
    Node() {
    }

    Node(int value, int height, Node leftNode, Node rightNode) {
        left = leftNode;
        right = rightNode;
        val = value;
        ht = height;
    }

    Node(int value, int height) {
        val = value;
        ht = height;
    }



}