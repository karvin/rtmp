package com.karvin.rtmp.common.chunk;

/**
 * Created by karvin on 15/12/15.
 */
public class ChunkHeader2 implements ChunkData {

    private int timestampDelta;

    public ChunkHeader2(int timestampDelta){
        this.timestampDelta = timestampDelta;
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[3];
        if(this.timestampDelta >= 0xffffff){
            this.timestampDelta = 0xffffff;
        }
        bytes[0] = (byte)((this.timestampDelta>>16)&0xff);
        bytes[1] = (byte)((this.timestampDelta>>8)&0xff);
        bytes[2] = (byte)(this.timestampDelta&0xff);
        return bytes;
    }
}
