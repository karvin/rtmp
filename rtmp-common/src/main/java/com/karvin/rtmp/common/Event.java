package com.karvin.rtmp.common;

/**
 * Created by karvin on 15/12/15.
 */
public interface Event {

    int getEventType();

    byte[] getEventData();

    byte[] getBytes();

}
