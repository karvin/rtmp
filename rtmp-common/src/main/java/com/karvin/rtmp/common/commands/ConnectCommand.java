package com.karvin.rtmp.common.commands;

import com.karvin.rtmp.common.Command;

/**
 * Created by karvin on 15/12/15.
 */
public class ConnectCommand implements Command{

    private static final String CONNECT_COMMAND = "connect";
    private static final int STREAM_ID = 1;
    private ConnectParameter connectParameter;

    public ConnectCommand(ConnectParameter connectParameter){
        this.connectParameter = connectParameter;
    }

    public byte[] getBytes() {
        return new byte[0];
    }

    public int getMessageType() {
        return 17;
    }

    public int getStreamId() {
        return STREAM_ID;
    }

    public String getCommandName() {
        return CONNECT_COMMAND;
    }
}
