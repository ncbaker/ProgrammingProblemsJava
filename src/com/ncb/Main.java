package com.ncb;

import com.ncb.DataStructures.*;

public class Main {

    public static void main(String[] args) {
        Node r = Trees.getBSTree();
        Trees.lca(r, 1, 7);
    }


}
