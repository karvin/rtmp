package com.karvin.rtmp.common.chunk;

/**
 * Created by karvin on 15/12/15.
 */
public class ChunkHeader1 implements ChunkData {

    private int timestampDelta;
    private int messageLength;
    private int messageType;

    public ChunkHeader1(int timestampDelta, int messageLength, int messageType){
        if(this.timestampDelta >= 0xffffff){
            this.timestampDelta = 0xffffff;
        }else {
            this.timestampDelta = timestampDelta;
        }
        this.messageLength = messageLength;
        this.messageType = messageType;
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[7];
        bytes[0] = (byte)((this.timestampDelta>>16)&0xff);
        bytes[1] = (byte)((this.timestampDelta>>8)&0xff);
        bytes[2] = (byte)(this.timestampDelta&0xff);
        bytes[3] = (byte)((this.messageLength>>16)&0xff);
        bytes[4] = (byte)((this.messageLength>>8)&0xff);
        bytes[5] = (byte)(this.messageLength&0xff);
        bytes[6] = (byte)(this.messageType&0xff);
        return bytes;
    }
}
