package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class SetPeerBandWidthMessage extends BasicMessage {

    public SetPeerBandWidthMessage(int chunkSize){
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(0,4,timestamp,0);
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((chunkSize>>24)&0xff);
        bytes[1] = (byte)((chunkSize>>16)&0xff);
        bytes[2] = (byte)((chunkSize>>8)&0xff);
        bytes[3] = (byte)(chunkSize&0xff);
        PayLoad payLoad = new BasicPayLoad(bytes);
        this.setMessageHeader(messageHeader);
        this.setPayLoad(payLoad);
    }
}
