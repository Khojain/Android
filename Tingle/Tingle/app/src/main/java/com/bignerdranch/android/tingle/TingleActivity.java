package com.bignerdranch.android.tingle;

import android.support.v4.app.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.tingle.DB.ThingsDB;

public class TingleActivity extends FragmentActivity implements TingleFragment.toActivity {
    // GUI variables
    FragmentManager fm;

    private Fragment fragmentListLand;
    @Override
    public void stateChange() {
        //((ListFragment) fragmentListLand).stateChange();

        fragmentListLand= fm.findFragmentById(R.id.frag_container_list);


        if (fragmentListLand != null) {
            // If List fragment is available, we're in two-pane layout...

            // Call a method in the ListFragment to update its content
            ((ListFragment) fragmentListLand).stateChange();
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            ListFragment newFragment = new ListFragment();
            FragmentTransaction transaction = fm.beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);


        //fragmentListLand= fm.findFragmentById(R.id.frag_container_list);

    //startFragment();

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            TingleFragment firstFragment = new TingleFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }


        //---------------old code----------------
        //selectFragment();

        /*TingleFragment fragT = new TingleFragment();
        ListFragment fragL = new ListFragment();
        setFragment(fragT, R.id.frag_container_tingle);
        setFragment(fragL, R.id.frag_container_list);*/

    }

    void startFragment(){
        TingleFragment firstFragment = new TingleFragment();
        firstFragment.setArguments(getIntent().getExtras());

        ListFragment fragment = new ListFragment();

        if (findViewById(R.id.fragment_container) != null ) {

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
        }
        if(findViewById(R.id.fragment_container) != null &&  fragment.isInLayout()){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(firstFragment);
            ft.attach(firstFragment);
            ft.add(R.id.fragment_container, firstFragment);
            ft.addToBackStack(TingleFragment.class.getSimpleName());
            ft.commit();

        }

    }

    public  void setFragment(Fragment fragment, int containID){
        if (fragment != null) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
            fragmentTransaction.replace(containID, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

/*    void selectFragment(){

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragT = fm.findFragmentById(R.id.fragment_container);

        //TingleFragment fragT = (TingleFragment)getSupportFragmentManager().findFragmentById(R.id.frag_container_tingle);
        if (fragT != null) {
            // If fragT is available, we're in two-pane layout...

            // Call a method to update listview content
            stateChange();
        }else{
             fragT = new TingleFragment();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.fragment_container, fragT);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }*/

    /*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frag_container_tingle);
        if (fragment == null) {
            fragment = new TingleFragment();
            fm.beginTransaction().add(R.id.frag_container_tingle, fragment)
                    .commit();
        }

    }*/

}
