package com.karvin.rtmp.common.shake;

import com.karvin.rtmp.common.Packet;
import com.karvin.rtmp.common.utils.NumberUtils;

/**
 * Created by karvin on 15/12/15.
 */
public class C2Packet implements Packet {
    private byte[] times;
    private byte[] random;

    private static final int MOD = 2^31;
    public C2Packet(byte[] times,byte[] random){
        this.times = times;
        this.random = random;
    }

    public byte[] getBytes() {
        byte[] bytes = new byte[1536];
        System.arraycopy(this.times,0,bytes,0,this.times.length);
        long current = System.currentTimeMillis();
        int mod = (int)(current % MOD);
        byte[] timeArray = NumberUtils.getBytes(mod);
        System.arraycopy(timeArray,0,bytes,timeArray.length,timeArray.length);
        System.arraycopy(this.random,0,bytes,this.times.length+timeArray.length,this.random.length);
        return bytes;
    }

}
