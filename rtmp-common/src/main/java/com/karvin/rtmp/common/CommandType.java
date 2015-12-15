package com.karvin.rtmp.common;

/**
 * Created by karvin on 15/12/15.
 */
public enum  CommandType {

    CONNECT_COMMAND("connect",1),CALL_COMMAND("call",2),CREATE_STREAM_COMMAND("createStream",3),
    PLAY_COMMAND("play",4),PAUSE_COMMAND("pause",5),DELETE_STREAM_COMMAND("delStream",6),
    CLOSE_STREAM("closeStream",7),RECEIVE_AUDIO("receiveAudio",8),RECEIVE_VEDIO("receiveVedio",9),
    PUBLISH_COMMAND("pushlish",10),SEEK_COMMAND("seek",11);

    private String commandName;
    private int commandType;

    private CommandType(String commandName,int commandType){
        this.commandName = commandName;
        this.commandType = commandType;
    }

}
