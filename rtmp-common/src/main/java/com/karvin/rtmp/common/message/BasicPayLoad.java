package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class BasicPayLoad implements PayLoad {

    private byte[] contents;

    public BasicPayLoad(byte[] contents){
        this.contents = contents;
    }

    public byte[] getContent() {
        return this.contents;
    }
}
