package com.walliee.datastructures;

import java.io.Serializable;

/**
 * Created by Walliee on 8/30/14.
 */
public class BlockInfo implements Serializable {
    private static final long serialVersionUID = -7989954888390481175L;
    private int fileNumber;
    private int startOffset;
    private int endOffset;

    public BlockInfo(int fileNumber, int startOffset, int endOffset) {
        this.fileNumber = fileNumber;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    @Override
    public String toString() {
        return "BlockInfo{" +
                "fileNumber=" + fileNumber +
                ", startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                '}';
    }
}
