package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class AbortMessage extends BasicMessage {

    public AbortMessage(int streamId){
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(2,4,timestamp,streamId);
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((streamId>>24)&0xff);
        bytes[1] = (byte)((streamId>>16)&0xff);
        bytes[2] = (byte)((streamId>>8)&0xff);
        bytes[3] = (byte)(streamId&0xff);
        PayLoad payLoad = new BasicPayLoad(bytes);
        this.setMessageHeader(messageHeader);
        this.setPayLoad(payLoad);
    }

}
