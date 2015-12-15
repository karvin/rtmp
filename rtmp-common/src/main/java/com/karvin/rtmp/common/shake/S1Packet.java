package com.karvin.rtmp.common.shake;

import com.karvin.rtmp.common.Packet;
import com.karvin.rtmp.common.utils.NumberUtils;

import java.util.Random;

/**
 * Created by karvin on 15/12/15.
 */
public class S1Packet implements Packet {

    private static final int MOD = 2^31;

    public byte[] getBytes() {
        byte[] bytes = new byte[1536];
        long current = System.currentTimeMillis();
        int mod = (int)(current % MOD);
        byte[] time = NumberUtils.getBytes(mod);
        int index = 0;
        for(int i=0;i<time.length;i++){
            bytes[index++] = time[i];
        }
        for(int i=0;i<4;i++){
            bytes[index++] = 0;
        }
        Random random = new Random(256);
        for(int i=index;i<bytes.length;i++){
            int result = random.nextInt();
            bytes[i] = (byte)(result&0xff);
        }
        return bytes;
    }

}
