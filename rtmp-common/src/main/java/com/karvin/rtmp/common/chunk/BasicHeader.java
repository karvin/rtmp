package com.karvin.rtmp.common.chunk;

import com.karvin.rtmp.common.Header;

/**
 * Created by karvin on 15/12/15.
 */
public class BasicHeader implements Header {

    private int chunkId;
    private int chunkType;
    private int length = 0;

    private static final int MASK = 0xc0;
    private static final int CHUNK_ID_MASK = 0x3f;

    public BasicHeader(byte first,int next){
        this.chunkType = (byte)(first&MASK);
        int id = (first&CHUNK_ID_MASK);
        if(id >= 2){
            this.chunkId = id;
            this.length = 1;
        }else if(id == 1){
            this.chunkId = 64 + (next&0xff);
            this.length = 2;
        }else if(id == 0){
            this.chunkId = 64 + (next&0xffff);
            this.length = 3;
        }
    }

    public byte[] getContent() {
        byte[] bytes = new byte[this.length];
        if(this.length == 1){
            bytes[0] = (byte)(this.chunkType<<6|this.chunkId);
        }else if(this.length == 2){
            bytes[0] = (byte)(this.chunkType<<6|0x1);
            bytes[1] = (byte)((this.chunkId-64)>>8);
        }else{
            bytes[0] = (byte)(this.chunkType<<6);
            bytes[1] = (byte)(((this.chunkId-64)>>16)&0xff);
            bytes[2] = (byte)(((this.chunkId-64)>>8)&0xff);
        }
        return bytes;
    }
}
