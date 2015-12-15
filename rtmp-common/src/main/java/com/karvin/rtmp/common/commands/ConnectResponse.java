package com.karvin.rtmp.common.commands;

import com.karvin.rtmp.common.utils.Pair;

import java.util.List;

/**
 * Created by karvin on 15/12/15.
 */
public class ConnectResponse {

    private String commandName;
    private int streamId;
    private List<Pair<String,String>> properties;
    private List<Pair<String,String>> information;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<Pair<String, String>> getInformation() {
        return information;
    }

    public void setInformation(List<Pair<String, String>> information) {
        this.information = information;
    }

    public List<Pair<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(List<Pair<String, String>> properties) {
        this.properties = properties;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }
}
