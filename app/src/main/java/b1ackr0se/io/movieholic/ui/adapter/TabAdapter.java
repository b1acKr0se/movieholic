package b1ackr0se.io.movieholic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import b1ackr0se.io.movieholic.ui.fragment.MovieFragment;
import b1ackr0se.io.movieholic.util.Option;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            MovieFragment fragment = new MovieFragment();
            fragment.setType(Option.Type.MOVIE);
            return fragment;
        } else {
            MovieFragment fragment = new MovieFragment();
            fragment.setType(Option.Type.TV);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        int NUMBER_OF_SCREEN = 2;
        return NUMBER_OF_SCREEN;
    }
}
