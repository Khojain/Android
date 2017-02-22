package com.bignerdranch.android.tingle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.tingle.DB.Thing;
import com.bignerdranch.android.tingle.DB.ThingsDB;

/**
 * Created by khojain on 16-02-2017.
 */

public class TingleFragment extends Fragment {
    // GUI variables
    private Button addThing;
    private TextView lastAdded;
    private TextView newWhat, newWhere;
    private Button mListAllThings;

    //fake database
    private ThingsDB thingsDB;

    toActivity mListener;

    public interface toActivity { public void stateChange(); }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if((activity instanceof toActivity)){mListener = (toActivity) activity;}
        else{throw new ClassCastException(activity.toString() + " must implement toActivity");}

        /*try {
            mListener = (toActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement toActivity");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB= ThingsDB.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        //fillThingsDB();

        //Accessing GUI element
        lastAdded= (TextView) v.findViewById(R.id.last_thing);
        updateUI();

        // Button
        addThing= (Button) v.findViewById(R.id.add_button);

        // Textfields for describing a thing
        newWhat= (TextView) v.findViewById(R.id.what_text);
        newWhere= (TextView) v.findViewById(R.id.where_text);

        // view products click event
        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){
                    thingsDB.addThing(
                            new Thing( newWhat.getText().toString(),
                                    newWhere.getText().toString()));
                    newWhat.setText(""); newWhere.setText("");
                    updateUI();

                    /*
                    inform the Activity about the change state
                    interface defintion
                    */
                   // mListener.stateChange();
                    ((TingleActivity)getActivity()).startFragment();

                  // ((toActivity) getActivity()).stateChange();
                }
            }
        });

        mListAllThings = (Button) v.findViewById(R.id.list_All_Thing_button);
        mListAllThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getActivity(), ListActivity.class);
                startActivity(i);*/

                ListFragment fml = new ListFragment();
                ((TingleActivity)getActivity()).setFragment(fml, R.id.fragment_container);
            }
        });


        return v;
    }

    // This method fill a few things into ThingsDB for testing
    private void fillThingsDB() {
        thingsDB.addThing(new Thing("Android Pnone", "Desk"));
        thingsDB.addThing(new Thing("Big Nerd book", "Desk"));
    }
    private void updateUI(){
        int s= thingsDB.size();
        if (s>0) lastAdded.setText(thingsDB.get(s-1).toString());
    }

    private void updateFragment(){
        ListFragment fragment = new ListFragment();
        if (fragment==null || ! fragment.isInLayout()) {
            // start new fragment
            ((TingleActivity)getActivity()).setFragment(fragment, R.id.fragment_container);
        }
        else {
            //fragment.update();
            ((TingleActivity)getActivity()).setFragment(fragment, R.id.fragment_container);
        }
    }
}

