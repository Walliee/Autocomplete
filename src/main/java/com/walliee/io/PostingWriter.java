package com.walliee.io;

import com.walliee.compression.Compression;
import com.walliee.datastructures.BlockInfo;
import com.walliee.datastructures.Lexicon;
import com.walliee.datastructures.Posting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Walliee on 8/30/14.
 */
public class PostingWriter {
    private int  indexFileNumber, currentOffset;
    private List<Posting> postings;
    private Lexicon lexicon;
    private OutputStream stream;
    private static int indexCounter = 0;

    public static int getIndexCounter() {
        return indexCounter;
    }

    private synchronized int incrementIndexCounter() {
        indexCounter++;
        return indexCounter;
    }

    public PostingWriter(List<Posting> postings, Lexicon lexicon, String path) throws FileNotFoundException {
        this.indexFileNumber = incrementIndexCounter();
        this.currentOffset = 0;
        this.postings = postings;
        this.lexicon = lexicon;
        this.stream = new FileOutputStream(new File(path,(String.valueOf(indexFileNumber))));
    }

    public  void write() {
        System.out.println("Writing index no. " + indexFileNumber);
        Collections.sort(postings);

        Integer prevWordID = postings.get(0).getWordID();
        Integer prevNGramID = postings.get(0).getNgramID();

        List<Integer> wordVector = new ArrayList<Integer>(10000);
        List<Integer> freqVector = new ArrayList<Integer>(10000);
        //List<Integer> offsetInfoStart = new LinkedList<Integer>();
        //List<Integer> offsetInfoLength = new LinkedList<Integer>();

//        List<Integer> offsetVector = new LinkedList<Integer>();
//        List<String> contextVector = new LinkedList<String>();


        int freqCounter = 0;
        //List<Integer> tempOffsetList = new ArrayList<Integer>();
        int index = 0;
        Posting currentPosting;

        while (index < postings.size()) {
            currentPosting = postings.get(index);
            index++;
            if (currentPosting.getNgramID() == prevNGramID) {
                if (currentPosting.getWordID() != prevWordID) {
                    wordVector.add(prevWordID);
                    freqVector.add(freqCounter);
                    freqCounter = 0;
                    prevWordID = currentPosting.getWordID();
                }
                freqCounter++;
            } else {
                try {
                    wordVector.add(prevWordID);
                    freqVector.add(freqCounter);

                    freqCounter = 0;
                    prevWordID = currentPosting.getWordID();

                    currentOffset = PostingWriter.writePosting(prevNGramID, wordVector, freqVector, stream, currentOffset, indexCounter, lexicon);
                    prevWordID = currentPosting.getWordID();
                    freqCounter++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            System.out.println("Size of Lexicon(in No.of Terms) :"+ lexicon.size());
            stream.close();
            System.out.println("Writing " + indexCounter + " Done.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int writePosting(int nGramID, List<Integer> wordVector,
                                   List<Integer> freqVector, OutputStream stream,
                                   int currentOffset, Integer fileNumber,
                                   Lexicon lexicon) throws IOException {

        int start = currentOffset;

        List<Integer> tempList = new ArrayList<Integer>(
                (wordVector.size() * 2));

        for(int temp: Compression.deltaCompress(wordVector))
            tempList.add(temp);

        tempList.addAll(freqVector);

        byte[] bytes = Compression.encode(tempList);
        stream.write(bytes, 0, bytes.length);
        currentOffset += bytes.length;
        int mid = currentOffset;

        tempList = new ArrayList<Integer>();

        int sum = 0;
        for(int i=0;i<freqVector.size();i++)
            sum+=freqVector.get(i);

        lexicon.put(nGramID, sum, new BlockInfo(
                fileNumber, start, mid));

        wordVector.clear();
        freqVector.clear();

        return currentOffset;
    }
}