package com.bignerdranch.android.tinglee;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TingleActivity extends AppCompatActivity {

   //  FragmentManager fragManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);


        FragTingleData fragTingleData = new FragTingleData();
        FragTingleList fragTingleList = new FragTingleList();
        setFragment(fragTingleData, R.id.frag_container_tingle);
        setFragment(fragTingleList, R.id.frag_container_tingle_list);

    }

    public  void setFragment(Fragment fragment, int containID){
        if (fragment != null) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragManager.beginTransaction();
            fragmentTransaction.replace(containID, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }



}
