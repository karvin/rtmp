package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.Header;

/**
 * Created by karvin on 15/12/15.
 */
public class MessageHeader implements Header {

    private int messageType;
    private int length;
    private int timestamp;
    private int streamId;
    private static final int MASK = 0xff;


    public MessageHeader(int messageType,int length,int timestamp,int streamId){
        this.messageType = messageType;
        this.length = length;
        this.timestamp = timestamp;
        this.streamId = streamId;
    }

    public byte[] getContent() {
        byte[] bytes = new byte[11];
        bytes[0] = (byte)(messageType&0xff);
        byte[] bLength = new byte[3];
        bLength[0] = (byte)((length>>16)&MASK);
        bLength[1] = (byte)((length>>8)&MASK);
        bLength[2] = (byte)(length&MASK);
        System.arraycopy(bLength,0,bytes,1,bLength.length);
        int index = 4;
        for(int i=0;i<4;i++){
            bytes[index++] = (byte)((timestamp>>((3-i)*8))&MASK);
        }
        byte[] bStream = new byte[3];
        bStream[0] = (byte)((streamId>>16)&MASK);
        bStream[1] = (byte)((streamId>>8)&MASK);
        bStream[2] = (byte)(streamId&MASK);
        System.arraycopy(bStream,0,bytes,index,bStream.length);
        return bytes;
    }
}
