package com.karvin.rtmp.common.chunk;

/**
 * Created by karvin on 15/12/15.
 */
public class ChunkHeader0 implements ChunkHeader{

    private int timestamp;
    private int messageLength;
    private int messageTypeId;
    private int steamId;

    public ChunkHeader0(int timestamp, int messageLength, int messageTypeId, int streamId){
        this.timestamp = timestamp;
        this.messageLength = messageLength;
        this.messageTypeId = messageTypeId;
        this.steamId = streamId;
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[11];
        if(timestamp >= 0xffffff){
            bytes[0] = (byte)0xff;
            bytes[1] = (byte)0xff;
            bytes[2] = (byte)0xff;
        }else{
            bytes[0] = (byte)((timestamp>>16)&0xff);
            bytes[1] = (byte)((timestamp>>8)&0xff);
            bytes[2] = (byte)(timestamp&0xff);
        }
        bytes[3] = (byte)((messageLength>>16)&0xff);
        bytes[4] = (byte)((messageLength>>8)&0xff);
        bytes[5] = (byte)(messageLength&0xff);
        bytes[6] = (byte)(messageTypeId&0xff);
        bytes[7] = (byte)((this.steamId>>24)&0xff);
        bytes[8] = (byte)((this.steamId>>16)&0xff);
        bytes[9] = (byte)((this.steamId>>8)&0xff);
        bytes[10] = (byte)(this.steamId&0xff);
        return bytes;
    }

    public int getTimestamp(){
        return this.timestamp;
    }
}
