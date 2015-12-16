package com.karvin.rtmp.common.io.impl;

import com.karvin.rtmp.common.chunk.Chunk;
import com.karvin.rtmp.common.io.ChunkStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by karvin on 15/12/16.
 */
public class ChunkStreamImpl implements ChunkStream {

    private InputStream in;

    private OutputStream out;

    public ChunkStreamImpl(InputStream in,OutputStream out){
        this.in = in;
        this.out = out;
    }

    public Chunk read() throws IOException{
        return null;
    }

    public int write(Chunk chunk) throws IOException{
        byte[] bytes = chunk.getBytes();
        this.out.write(bytes);
        return bytes.length;
    }

    public int getChunkStreamId() {
        return 0;
    }
}
