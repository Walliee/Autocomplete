package com.walliee.datastructures;

/**
 * Created by Walliee on 8/29/14.
 */
public class Word {
    private String word;
    private long wordID;

    public Word(String word, long wordID) {
        this.word = word;
        this.wordID = wordID;
    }

    public long getWordID() {
        return wordID;
    }

    public String getWord() {
        return word;
    }
}
