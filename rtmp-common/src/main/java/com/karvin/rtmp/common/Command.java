package com.karvin.rtmp.common;

/**
 * Created by karvin on 15/12/15.
 */
public interface Command {

    byte[] getBytes();

    int getMessageType();

    int getStreamId();

    String getCommandName();

}
