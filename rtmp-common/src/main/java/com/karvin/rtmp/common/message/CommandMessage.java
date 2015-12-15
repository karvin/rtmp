package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.Command;
import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class CommandMessage extends BasicMessage {

    public CommandMessage(Command command){
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(command.getMessageType(),command.getBytes().length,timestamp,command.getStreamId());
        this.setMessageHeader(messageHeader);
        PayLoad payLoad = new BasicPayLoad(command.getBytes());
        this.setPayLoad(payLoad);
    }

}
