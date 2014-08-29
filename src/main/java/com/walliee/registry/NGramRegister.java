package com.walliee.registry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Walliee on 8/29/14.
 */
public class NGramRegister {
    private Integer nextID = 0;
    private Map<String, Integer> ngramMap;
    private FileWriter fileWriter;

    public NGramRegister(int wordIdGeneratorBaseSize, String path) throws IOException {
        File file = new File(path,"WordID");
        fileWriter = new FileWriter(file);
        ngramMap = new HashMap<>(wordIdGeneratorBaseSize);
    }

    public long register(String ngram) {
        if(!ngramMap.containsKey(ngram)){
            ngramMap.put(ngram, nextID);
            return nextID++;
        }
        return ngramMap.get(ngram);
    }

    public void close() throws IOException {
        for(String word: ngramMap.keySet() ){
            fileWriter.write((word+"$$"+ngramMap.get(word)));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
