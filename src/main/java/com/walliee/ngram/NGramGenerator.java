package com.walliee.ngram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walliee on 8/29/14.
 */
public class NGramGenerator {
    public static List<String> generateNGrams(String word, int size) {
        if(null == word || word.isEmpty() || size <= 0) {
            throw new IllegalArgumentException();
        }
        List<String> ngrams = new ArrayList<>();
        for(int i=0; i<= word.length()-size; i++) {
            ngrams.add(word.substring(i, i+size));
        }

        return ngrams;
    }

}
