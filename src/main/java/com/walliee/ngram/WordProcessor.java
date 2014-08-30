package com.walliee.ngram;

import com.walliee.datastructures.Posting;
import com.walliee.registry.NGramRegister;
import com.walliee.registry.WordRegister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walliee on 8/29/14.
 */
public class WordProcessor {
    private static final int nGramSize = 3;
    private NGramGenerator nGramGenerator;
    private WordRegister wordRegister;
    private NGramRegister nGramRegister;

    public WordProcessor(NGramGenerator nGramGenerator, WordRegister wordRegister, NGramRegister nGramRegister) {
        this.nGramGenerator = nGramGenerator;
        this.wordRegister = wordRegister;
        this.nGramRegister = nGramRegister;
    }

    public List<Posting> processWord(String word) throws IOException {
        List<String> nGrams = nGramGenerator.generateNGrams(word, nGramSize);
        int wordID = wordRegister.register(word);
        List <Posting> postings = new ArrayList<>(nGrams.size());
        nGrams.stream().forEach(nGram -> {
                                            int nGramID = nGramRegister.register(nGram);
                                            Posting posting = new Posting(nGramID, wordID, 0.0);
                                            postings.add(posting);
                                         });

        return postings;
    }

    public void close() {
        try {
            nGramRegister.close();
            wordRegister.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
