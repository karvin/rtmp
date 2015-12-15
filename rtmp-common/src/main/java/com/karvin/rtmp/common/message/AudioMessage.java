package com.karvin.rtmp.common.message;

import com.karvin.rtmp.common.PayLoad;

/**
 * Created by karvin on 15/12/15.
 */
public class AudioMessage extends BasicMessage{

    public AudioMessage(byte[] audio,int streamId){
        int timestamp = (int)(System.currentTimeMillis()&0xffffffff);
        MessageHeader messageHeader = new MessageHeader(8,audio.length,timestamp,streamId);
        this.setMessageHeader(messageHeader);
        PayLoad payLoad = new BasicPayLoad(audio);
        this.setPayLoad(payLoad);
    }

}
