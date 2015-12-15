package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.Event;
import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class ControlMessage extends BasicMessage {
    
    public ControlMessage(Event event,int streamId){
        byte[] bytes = event.getBytes();
        PayLoad payLoad = new BasicPayLoad(bytes);
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(4,bytes.length,timestamp,streamId);
        this.setMessageHeader(messageHeader);
        this.setPayLoad(payLoad);
    }

}
