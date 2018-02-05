package com.example.hh960.uxmlab;

/**
 * Created by hh960 on 2018-02-05.
 */
import java.util.ArrayList;


public class Listing {

    //Properties of Listing
    public String week;
    public String image;
    public ArrayList<String> name = new ArrayList<String>();

    public Listing(String week){
        this.week = week;
    }

    public String toString () {
        return week;
    }

}



