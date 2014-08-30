package com.walliee.datastructures;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Walliee on 8/30/14.
 */
public class LexiconValue implements Serializable {
    private static final long serialVersionUID = -8826253705045189110L;
    private int globalFrequency;
    private List<BlockInfo> blockInfos;


    public LexiconValue(LinkedList<BlockInfo> linkedList, int globalFrequency) {
        this.blockInfos = linkedList;
        this.globalFrequency = globalFrequency;
    }

    public List<BlockInfo> getBlockInfos() {
        return blockInfos;
    }

    public void incrementFrequency(int freq) {
        globalFrequency+= freq;
    }

    public int getGlobalFrequency() {
        return globalFrequency;
    }

    @Override
    public String toString() {
        return "LexiconValue{" +
                "globalFrequency=" + globalFrequency +
                ", blockInfos=" + blockInfos +
                '}';
    }
}
