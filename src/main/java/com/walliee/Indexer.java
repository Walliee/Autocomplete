package com.walliee;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Hello world!
 *
 */
public class Indexer
{
    private String inputFile;
    private String outputDirectory;
    private static Logger log4j = Logger.getLogger(Indexer.class);

    public Indexer(String inputFile, String outputDirectory) {
        this.inputFile = inputFile;
        this.outputDirectory = outputDirectory;
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

        while(null != (word = reader.readLine())) {

        }
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
