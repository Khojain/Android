package com.bignerdranch.android.tinglee.DB;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khojain on 17-02-2017.
 */

public class ThingsDB {
    private static ThingsDB sThingsDB;
    //fake database
    private List<Thing> mThingsDB;

    public static ThingsDB get(Context context) {
        if (sThingsDB == null) {
            sThingsDB= new ThingsDB(context);
        }
        return sThingsDB;
    }
    public List<Thing> getThingsDB() {return mThingsDB; }
    public void addThing(Thing thing) { mThingsDB.add(thing); }
    public  void deletething(int index){
        mThingsDB.remove(index);
    }

    public int size() {return mThingsDB.size(); }
    public Thing get(int i){ return mThingsDB.get(i); }
    // Fill database for testing purposes
    private ThingsDB(Context context) {

        mThingsDB= new ArrayList<Thing>();
        mThingsDB.add(new Thing("Android Pnone", "Desk"));
        mThingsDB.add(new Thing("Big Nerd book", "Desk"));
        /*mThingsDB= new ArrayList<Thing>();

        for(int i = 0; i<5; i++){
            mThingsDB.add(new Thing("My car Key "+i, "Frederiksberg "+i));
        }*/
    }
}
