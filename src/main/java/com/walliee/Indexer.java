package com.walliee;

import com.walliee.datastructures.Lexicon;
import com.walliee.datastructures.Posting;
import com.walliee.io.PostingWriter;
import com.walliee.ngram.NGramGenerator;
import com.walliee.ngram.WordProcessor;
import com.walliee.registry.NGramRegister;
import com.walliee.registry.WordRegister;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
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
    private WordProcessor wordProcessor;
    private Lexicon lexicon;
    private int batchSize;
    private static Logger log4j = Logger.getLogger(Indexer.class);

    public Indexer(String inputFile, String outputDirectory, WordProcessor wordProcessor, Lexicon lexicon, int batchSize) {
        this.inputFile = inputFile;
        this.outputDirectory = outputDirectory;
        this.wordProcessor = wordProcessor;
        this.lexicon = lexicon;
        this.batchSize = batchSize;
    }

    public void index() throws IOException {


        File  tempFolder= new File(outputDirectory,"temp");
        tempFolder.mkdir();
        String tempFolderPath = tempFolder.getAbsolutePath();

        File indexFolder = new File(outputDirectory,"index");
        indexFolder.mkdir();
        String indexPath = indexFolder.getAbsolutePath();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String word;

        Long start = System.currentTimeMillis();

        List<Posting> postings = new ArrayList<>();

        while(null != (word = reader.readLine())) {
            List<Posting> nGramPostings = wordProcessor.processWord(word);
            postings.addAll(nGramPostings);

            if(postings.size() >= batchSize) {
                new PostingWriter(postings,lexicon, indexPath).write();
                System.out.println("So far "+ (System.currentTimeMillis()-start)+"ms have passed since execution.");
                postings = null;
                System.gc();

                postings = new ArrayList<Posting>(batchSize);
            }
        }

        if(postings.size()!=0){
            new PostingWriter(postings,lexicon, indexPath).write();
            System.gc();
        }

        File lexiconObjectFile = new File(outputDirectory,"Lexicon");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(lexiconObjectFile));
        objectOutputStream.writeObject(lexicon);
        objectOutputStream.close();

        wordProcessor.close();
    }

    public static void main( String[] args ) throws IOException {
        String outputDirectory = "./output";
        new File(outputDirectory).mkdir();
        WordRegister wordRegister = new WordRegister( ( outputDirectory + "/DocumentID") );
        NGramRegister nGramRegister = new NGramRegister(100000, (outputDirectory));
        NGramGenerator nGramGenerator = new NGramGenerator();

        new Indexer("./data/corncob_lowercase.txt", outputDirectory, new WordProcessor(nGramGenerator, wordRegister, nGramRegister), new Lexicon(), 50000).index();
    }
}
