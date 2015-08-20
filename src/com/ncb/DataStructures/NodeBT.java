package com.ncb.DataStructures;

/**
 * Created by nathanb on 8/20/2015.
 */
public class NodeBT {
    int val;   //Value
    int ht;      //Height
    NodeBT left;   //Left child
    NodeBT right;   //Right child

    //added these for easy populating
    NodeBT(){}
    NodeBT(int value, int height, NodeBT leftNode, NodeBT rightNode)
    {
        left = leftNode;
        right = rightNode;
        val = value;
        ht = height;
    }
    NodeBT(int value, int height)
    {
        val = value;
        ht = height;
    }
}
