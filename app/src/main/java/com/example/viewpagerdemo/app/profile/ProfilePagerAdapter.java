package com.example.viewpagerdemo.app.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseIntArray;

import java.util.ArrayList;

/**
 * Fragment pager adapter to hold upper image animation
 *
 * @author pranit
 * @version 1.0
 * @since 24/3/17
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<ProfilePagerFragment> mFragments = new ArrayList<>();
    private SparseIntArray mBackgroundColors = new SparseIntArray();

    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Add a Fragment to the PagerAdapter.
     * @param fragment {@link ProfilePagerFragment} to add.
     * @param backgroundColor Background color for the Fragment to add.
     */
    public void addFragment(ProfilePagerFragment fragment, int backgroundColor) {
        mBackgroundColors.put(mFragments.size(), backgroundColor);
        mFragments.add(fragment);
    }

    /**
     * Return the background color of the page for a given position.
     * @param page Page position
     * @return Background color for the page or <code>0</code> if no match.
     */
    public int getBackgroundColorForPage(int page) {
        return mBackgroundColors.get(page);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
