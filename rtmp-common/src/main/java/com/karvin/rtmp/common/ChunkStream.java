package com.karvin.rtmp.common;

import com.karvin.rtmp.common.chunk.Chunk;

/**
 * Created by karvin on 15/12/15.
 */
public interface ChunkStream {

    Chunk read();

    int write(Chunk chunk);

}
