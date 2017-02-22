package com.bignerdranch.android.tingle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bignerdranch.android.tingle.DB.ThingsDB;

public class ListActivity extends AppCompatActivity {

    ArrayAdapter listAdapter;
    private static ThingsDB thingsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        thingsDB= ThingsDB.get(this);
        listAdapter = new ArrayAdapter<>(ListActivity.this,android.R.layout.simple_list_item_1, thingsDB.getThingsDB() );
        ((ListView) findViewById(R.id.thing_list_view)).setAdapter(listAdapter);
    }

}


