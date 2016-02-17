package b1ackr0se.io.movieholic.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import b1ackr0se.io.movieholic.ui.fragment.MovieFragment;
import b1ackr0se.io.movieholic.util.Option;

public class TabAdapter extends FragmentPagerAdapter {

    public static final int NUMBER_OF_SCREEN = 2;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return MovieFragment.newInstance(Option.Type.MOVIE);
        else
            return MovieFragment.newInstance(Option.Type.TV);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_SCREEN;
    }
}
