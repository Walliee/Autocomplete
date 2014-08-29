package com.walliee;

import com.walliee.ngram.NGramGenerator;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Hello world!
 *
 */
public class Indexer
{
    private String inputFile;
    private String outputDirectory;
    private int batchSize;
    private static Logger log4j = Logger.getLogger(Indexer.class);

    public Indexer(String inputFile, String outputDirectory, int batchSize) {
        this.inputFile = inputFile;
        this.outputDirectory = outputDirectory;
        this.batchSize = batchSize;
    }

    public void index() throws IOException {
        new File(outputDirectory).mkdir();

        File  tempFolder= new File(outputDirectory,"temp");
        tempFolder.mkdir();
        String tempFolderPath = tempFolder.getAbsolutePath();

        File indexFolder = new File(outputDirectory,"index");
        indexFolder.mkdir();
        String indexPath = indexFolder.getAbsolutePath();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String word;
        Map<String, String> postings = new TreeMap<>();

        while(null != (word = reader.readLine())) {
            List<String> ngrams = NGramGenerator.generateNGrams(word, 3);
            for (String ngram : ngrams) {
                postings.put(ngram, word);
            }

            if(postings.size() >= batchSize) {
            }
        }
    }

    public static void main( String[] args ) throws IOException {
        new Indexer("./data/corncob_lowercase.txt", "", 50000).index();
    }
}
