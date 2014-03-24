package com.smaatlearn.module4homework;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This is the "top-level" fragment, showing a list of items that the
 * user can pick.  Upon picking an item, it takes care of displaying the
 * data to the user as appropriate based on the currrent UI layout.
 */

public  class TitlesFragment extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;
    int mShownCheckPosition = -1;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, Shakespeare.TITLES));

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            mShownCheckPosition = savedInstanceState.getInt("shownChoice", -1);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
        outState.putInt("shownChoice", mShownCheckPosition);
    }
}