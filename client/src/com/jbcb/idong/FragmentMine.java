package com.jbcb.idong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName FragmentMine.java
 * @author Clame
 * 
 */
public class FragmentMine extends Fragment {
    // the type of show (0: show the login activity, other: show the Mine fragment)
    public int showType = 0;

    public FragmentMine() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        // get the showType
        showType = getArguments().getInt("showMine");

        if (showType == 0) {
            Intent loginIntent = new Intent();
            loginIntent.setClass(this.getActivity().getBaseContext(), LoginActivity.class);
            startActivity(loginIntent);
            this.getActivity().finish();
            return null;
        } else {
            LayoutInflater myInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = myInflater.inflate(R.layout.fragment_mine, container, false);
            return layout;
        }
    }
}
