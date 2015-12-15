package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public interface Message {

    public MessageHeader getMessageHeader();

    public PayLoad getPayLoad() ;
}
