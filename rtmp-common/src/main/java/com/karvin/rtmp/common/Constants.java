package com.karvin.rtmp.common;

/**
 * Created by karvin on 15/12/14.
 */
public class Constants {

    public static final int HEAD_SIZE_1 = 1;

    public static final int HEAD_SIZE_4 = 4;

    public static final int HEAD_SIZE_8 = 8;

    public static final int HEAD_SIZE_12 = 12;

    public static final int HEAD_MASK = 0xc0;

    public static final int CHANNEL_MASK = 0x3f;

    public static final byte PROTOCOL_VERSION = 3;

    public static int getHeadLength(byte head){
        int result = (head&HEAD_MASK)>>6;
        if(result == 0){
            return HEAD_SIZE_12;
        }else if(result == 1){
            return HEAD_SIZE_8;
        }else if(result == 2){
            return HEAD_SIZE_4;
        }else{
            return HEAD_SIZE_1;
        }
    }

}
