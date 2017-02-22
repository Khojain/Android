package com.bignerdranch.android.tingle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bignerdranch.android.tingle.DB.ThingsDB;

/**
 * Created by khojain on 16-02-2017.
 */

public class ListFragment extends Fragment {
    //static private ThingArrayAdapter listAdapter;
    static private ArrayAdapter listAdapter;
    private static ThingsDB thingsDB;
    ListView item;


    public void stateChange() { listAdapter.notifyDataSetChanged(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        thingsDB = ThingsDB.get(getActivity());

        item = (ListView) v.findViewById(R.id.thing_list_view);
        //item.setAdapter(listAdapter);
        PopulateListView();

       // listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thingsDB.getThingsDB());
        ((ListView) v.findViewById(R.id.thing_list_view)).setAdapter(listAdapter);

        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                ShowDialog(position);
            }
        });

        return v;
        }

    public void update(View v){


    }

    void PopulateListView(){
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thingsDB.getThingsDB());
        item.setAdapter(listAdapter);
    }

    void ShowDialog(final int index){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Do you want to delete this item ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        thingsDB.deleteThing(index);
                        Toast.makeText(getActivity(),"Item "+ id +"is deleted!", Toast.LENGTH_LONG).show();
                        PopulateListView();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    }

