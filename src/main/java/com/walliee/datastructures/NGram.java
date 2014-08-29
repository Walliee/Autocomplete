package com.walliee.datastructures;

/**
 * Created by Walliee on 8/29/14.
 */
public class NGram {
    private String ngram;
    private long ngramID;

    public NGram(String ngram, long ngramID) {
        this.ngram = ngram;
        this.ngramID = ngramID;
    }

    public long getNgramID() {
        return ngramID;
    }

    public String getNgram() {
        return ngram;
    }
}
