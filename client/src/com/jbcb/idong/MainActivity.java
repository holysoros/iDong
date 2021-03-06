package com.jbcb.idong;

import com.jbcb.idong.widget.BottomBar;
import com.jbcb.idong.widget.BottomBar.OnItemChangedListener;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

/**
 * @ClassName MainActivity.java
 * @author Clame
 * 
 */
public class MainActivity extends FragmentActivity {
    // the flag of show register view or not
    private int showMine = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare the bottombar, and add the ItemChangedListener
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.main_bottom_bar);
        bottomBar.setOnItemChangedListener(new OnItemChangedListener() {
            @Override
            public void onItemChanged(int index) {
                showDetails(index);
            }
        });

        // get the index and showMine flag
        Intent intent = this.getIntent();
        int index = 0;
        if (intent.getExtras() != null) {
            index = intent.getExtras().getInt("index");
            showMine = intent.getExtras().getInt("showMine");
        } else {
            showMine = 0;
        }
        bottomBar.setSelectedState(index);
    }

    /**
     * @FunName showDetails
     * @Description switch the fragment content according to the selected item on bottombar
     * @param index
     * @return N/A
     * 
     */
    private void showDetails(int index) {
        Fragment details = (Fragment) getSupportFragmentManager().findFragmentById(R.id.main_details);

        // set the target fragment according to the index
        switch (index) {
        case 0:
            details = new FragmentSuggest();
            break;
        case 1:
            details = new FragmentSearch();
            break;
        case 2:
            details = new FragmentMine();
            break;
        }

        // set the bundle for the fragment
        Bundle bundle = new Bundle();
        bundle.putInt("showMine", showMine);
        details.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_details, details);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
