package com.salle.erikdavid.sallebooks.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.salle.erikdavid.sallebooks.Fragments.ReadedBooksFragment;
import com.salle.erikdavid.sallebooks.Fragments.WishBooksFragment;

/**
 * Created by Erik on 07/03/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ReadedBooksFragment tab1 = new ReadedBooksFragment();
                return tab1;
            case 1:
                WishBooksFragment tab2 = new WishBooksFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
