package com.walliee.datastructures;

/**
 * Created by Walliee on 8/29/14.
 */
public class Posting implements Comparable<Posting>{
    private int ngramID;
    private int wordID;
    private double weight;

    public Posting(int ngramID, int wordID, double weight) {
        this.ngramID = ngramID;
        this.wordID = wordID;
        this.weight = weight;
    }

    @Override
    public int compareTo(Posting o) {
        if(this.getNgramID()!=o.getNgramID())
            return (this.getNgramID()>o.getNgramID()? 1:-1);
        if(this.getWordID()!=o.getWordID())
            return (this.getWordID()>o.getWordID()? 1:-1);

        return 0;
    }

    public int getNgramID() {
        return ngramID;
    }

    public int getWordID() {
        return wordID;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "ngramID=" + ngramID +
                ", wordID=" + wordID +
                ", weight=" + weight +
                '}';
    }
}
