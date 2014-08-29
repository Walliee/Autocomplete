package com.walliee.registry;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Walliee on 8/29/14.
 */
public class WordRegister implements Serializable{
    private int nextID = 0;
    private FileWriter fileWriter;

    public WordRegister(String path) throws IOException {
        this.fileWriter = new FileWriter(path);
    }

    public void close() throws IOException{
        fileWriter.close();
    }

    public synchronized int register(String word) throws IOException{
        fileWriter.write(word+"$$"+String.valueOf(nextID));
        fileWriter.write("\n");
        return nextID++;
    }
}
