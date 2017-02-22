package com.bignerdranch.android.tinglee;

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

import com.bignerdranch.android.tinglee.DB.ThingsDB;


public class FragTingleList extends Fragment {
    ArrayAdapter listAdapter;
    private static ThingsDB thingsDB;
    ListView tinglesListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tingle_list, container, false);
        tinglesListView = (ListView) view.findViewById(R.id.listViewTingles);
        thingsDB = ThingsDB.get(getActivity());
        PopulateListView();

        tinglesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {


                ShowDialog(position);

            }
        });

        // ((ListView) view.findViewById(R.id.listViewTingles)).setAdapter(listAdapter);



    return  view;
    }

    void ShowDialog(final int index){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Do you want to delete this item ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        thingsDB.deletething(index);
                        Toast.makeText(getActivity(),"Item is deleted!", Toast.LENGTH_SHORT).show();
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

    void PopulateListView(){
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, thingsDB.getThingsDB());

        tinglesListView.setAdapter(listAdapter);
    }


}
