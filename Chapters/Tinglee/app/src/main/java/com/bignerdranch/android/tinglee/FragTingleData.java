package com.bignerdranch.android.tinglee;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.tinglee.DB.Thing;
import com.bignerdranch.android.tinglee.DB.ThingsDB;


public class FragTingleData extends Fragment {

    Button btn_add,btn_listThing;
    private TextView newWhat, newWhere,lastThing;
    private ThingsDB thingsDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB= ThingsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_tingle_data, container, false);
        InitView(view);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    thingsDB.addThing(
                            new Thing( newWhat.getText().toString(),
                                    newWhere.getText().toString()));
                    newWhat.setText(""); newWhere.setText("");
                    updateUI();
                }
            }
        });

        btn_listThing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Put your Code
                FragTingleList fragTingleList = new FragTingleList();
                ((TingleActivity)getActivity()).setFragment(fragTingleList, R.id.frag_container_tingle);

            }
        });

        return  view;
    }


    private void InitView(View view){

        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_listThing = (Button) view.findViewById(R.id.btn_listThing);

        newWhere = (TextView) view.findViewById(R.id.txt_where);
        newWhat = (TextView) view.findViewById(R.id.txt_what);
        lastThing = (TextView) view.findViewById(R.id.txt_lastThing);
    }

    private void updateUI(){
        int s= thingsDB.size();
        if (s>0) lastThing.setText(thingsDB.get(s-1).toString());
    }
}
