package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class AcknowledgeMessage extends BasicMessage {

    public AcknowledgeMessage(int length){
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(1,4,timestamp,0);
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((length>>24)&0xff);
        bytes[1] = (byte)((length>>16)&0xff);
        bytes[2] = (byte)((length>>8)&0xff);
        bytes[3] = (byte)(length&0xff);
        PayLoad payLoad = new BasicPayLoad(bytes);
        this.setMessageHeader(messageHeader);
        this.setPayLoad(payLoad);
    }

}
