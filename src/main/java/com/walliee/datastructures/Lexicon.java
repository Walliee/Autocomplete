package com.walliee.datastructures;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Walliee on 8/30/14.
 */
public class Lexicon implements Serializable {
    private static final long serialVersionUID = 5995840378295938710L;
    private Map<Integer, LexiconValue> map;

    public Lexicon()  {
        this.map = new HashMap<Integer, LexiconValue>();
    }

    //returns list of all the blockInfos for all the indexfiles
    public List<BlockInfo> get(Object arg0) {
        return map.get(arg0).getBlockInfos();
    }

    //returns the blockInfo for a particular index file
    public BlockInfo get(Object arg0, int fileNumber){
        for(BlockInfo blockInfo: map.get(arg0).getBlockInfos()){
            if(blockInfo.getFileNumber()==fileNumber)
                return blockInfo;
        }
        return null;
    }

    // Adds entry to Lexicon
    public synchronized void put(Integer nGramID,int freq ,BlockInfo blockInfo){
        if(!map.containsKey(nGramID))
            map.put(nGramID, new LexiconValue( new LinkedList<BlockInfo>(),0));
        map.get(nGramID).getBlockInfos().add(blockInfo);
        map.get(nGramID).incrementFrequency(freq);
    }

    public int size() {
        return map.size();
    }

    public boolean containsKey(Object arg0) {
        return map.containsKey(arg0);
    }

    public Set<Integer> keySet() {
        return map.keySet();
    }

    public LexiconValue remove(Object arg0) {
        return map.remove(arg0);
    }

    @Override
    public String toString() {
        return "Lexicon [map=" + map + "]";
    }
}
