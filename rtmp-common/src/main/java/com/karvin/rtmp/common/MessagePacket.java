package com.karvin.rtmp.common;

/**
 * Created by karvin on 15/12/15.
 */
public interface MessagePacket extends Packet{

    Header getHeader();

    PayLoad getPayLoad();

}
