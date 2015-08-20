package com.ncb;

import com.ncb.DataStructures.*;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Main sln = new Main();
        java.util.Scanner scan = new java.util.Scanner(System.in);
        short nodeCount = scan.nextShort();

        Node root = sln.new Node();
        root.data = 1;

        int currCount = 1;

        ArrayDeque<Node> previousLevel = new ArrayDeque<Node>();
        previousLevel.add(root);

        ArrayDeque<Node> currentLevel = new ArrayDeque<Node>();

        ArrayList<ArrayList<Node>> nodesByLevel = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> rootLevel = new ArrayList<Node>();
        rootLevel.add(root);
        nodesByLevel.add(rootLevel);

        Node temp;
        while(currCount <= nodeCount)
        {
            ArrayList<Node> lvl = new ArrayList<Node>();
            while(!previousLevel.isEmpty()) {
                temp = previousLevel.pop();

                Node left = sln.new Node();
                left.data = scan.nextShort();
                temp.left = left;
                if(left.data != -1) {
                    currentLevel.add(left);
                    lvl.add(left);
                    currCount++;
                }

                Node right = sln.new Node();
                right.data = scan.nextShort();
                temp.right = right;
                if(right.data != -1) {
                    currentLevel.add(right);
                    lvl.add(right);
                    currCount++;
                }
            }

            if(currentLevel.size() == 0)
                break;

            previousLevel = currentLevel;
            currentLevel = new ArrayDeque<Node>();
            nodesByLevel.add(lvl);
        }

        //put inputs into array
        short swapCount = scan.nextShort();
        short[] height = new short[swapCount];
        for (int i = 0; i < swapCount; i++)
            height[i] = scan.nextShort();

        Node tempFlip;
        short lastLevel = -1;
        for (int i = 0; i < swapCount; i++) {//iteration of flip
            for (int j = i; j < swapCount; j++) {//flips per iteration
                if(lastLevel == height[j])
                    continue;

                for (int k= 0; k < nodesByLevel.get(height[j] - 1).size(); k++) {
                    lastLevel = height[j];
                    temp = nodesByLevel.get(height[j] - 1).get(k);
                    tempFlip = temp.right;
                    temp.right = temp.left;
                    temp.left = tempFlip;
                }
            }

            InorderPositive(root);
            System.out.println();
            lastLevel = -1;
        }
    }
    static void InorderPositive(Node root) {
        if(root.left != null && root.left.data > 0) {
            InorderPositive(root.left);
            System.out.print(root.data + " ");
        }
        else
            System.out.print(root.data + " ");

        if(root.right != null && root.right.data > 0)
            InorderPositive(root.right);
    }

    private class Node {
        int data;
        Node left;
        Node right;
    }
}
