package com.karvin.rtmp.common.utils;

/**
 * Created by karvin on 15/12/15.
 */
public class NumberUtils {

    public static byte[] getBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((number>>24)&0xff);
        bytes[1] = (byte)((number>>16)&0xff);
        bytes[2] = (byte)((number>>8)&0xff);
        bytes[3] = (byte)(number&0xff);
        return bytes;
    }

}
