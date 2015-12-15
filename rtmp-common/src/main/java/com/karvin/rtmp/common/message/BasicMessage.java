package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class BasicMessage implements Message {

    private MessageHeader messageHeader;
    private PayLoad payLoad;

    public MessageHeader getMessageHeader() {
        return this.messageHeader;
    }

    public PayLoad getPayLoad() {
        return this.payLoad;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public void setPayLoad(PayLoad payLoad) {
        this.payLoad = payLoad;
    }
}
