package com.karvin.rtmp.common.io;

import com.karvin.rtmp.common.MessagePacket;

/**
 * Created by karvin on 15/12/15.
 */
public interface MessageStream {

    int write(MessagePacket message);

    MessagePacket read();

}
