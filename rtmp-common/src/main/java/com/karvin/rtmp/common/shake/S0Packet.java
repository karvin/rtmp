package com.karvin.rtmp.common.shake;

import com.karvin.rtmp.common.Constants;
import com.karvin.rtmp.common.Packet;

/**
 * Created by karvin on 15/12/15.
 */
public class S0Packet implements Packet {

    private static final byte[] BYTES = new byte[]{Constants.PROTOCOL_VERSION};

    public byte[] getBytes() {
        return BYTES;
    }

}
