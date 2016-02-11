package b1ackr0se.io.movieholic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import b1ackr0se.io.movieholic.ui.fragment.MovieFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private final int NUMBER_OF_SCREEN = 2;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 1)
            return MovieFragment.newInstance();
        else
            return MovieFragment.newInstance();
    }

    @Override
    public int getCount() {
        return NUMBER_OF_SCREEN;
    }
}
