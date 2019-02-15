package com.achekulov.to_doder.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.achekulov.to_doder.Fragment.CurrentTaskFragment;
import com.achekulov.to_doder.Fragment.DoneTaskFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public static final  int CURRENT_TASK_FRAGMENT_POSITION= 0;
    public static final  int DONE_TASK_FRAGMENT_POSOTION = 1;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        currentTaskFragment = new CurrentTaskFragment();
        doneTaskFragment = new DoneTaskFragment();
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case CURRENT_TASK_FRAGMENT_POSITION: return currentTaskFragment;
            case DONE_TASK_FRAGMENT_POSOTION: return doneTaskFragment;
            default: return null;
        }
    }

}
