package com.karvin.rtmp.common.utils;

/**
 * Created by karvin on 15/12/15.
 */
public class Pair<L,R> {

    private L left;
    private R right;

    public Pair(L left,R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft(){
        return this.left;
    }

    public R getRight(){
        return this.right;
    }

}
