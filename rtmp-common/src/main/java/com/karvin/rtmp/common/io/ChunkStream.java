package com.karvin.rtmp.common.io;

import com.karvin.rtmp.common.chunk.Chunk;

import java.io.IOException;

/**
 * Created by karvin on 15/12/15.
 */
public interface ChunkStream {

    Chunk read() throws IOException;

    int write(Chunk chunk) throws IOException;

    int getChunkStreamId();

}
