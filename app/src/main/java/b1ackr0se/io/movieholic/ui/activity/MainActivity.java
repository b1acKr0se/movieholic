package b1ackr0se.io.movieholic.ui.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.ui.adapter.TabAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_top)Toolbar toolbar;
    @Bind(R.id.button_movie)View buttonMovie;
    @Bind(R.id.button_tv)View buttonTv;
    @Bind(R.id.view_pager)ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        TabAdapter adapter = new TabAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        buttonMovie.setAlpha(1.0f);
                        buttonTv.setAlpha(0.5f);
                        break;
                    case 1:
                        buttonMovie.setAlpha(0.5f);
                        buttonTv.setAlpha(1.0f);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        buttonMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0, true);
                buttonMovie.setAlpha(1.0f);
                buttonTv.setAlpha(0.5f);
            }
        });

        buttonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
                buttonMovie.setAlpha(0.5f);
                buttonTv.setAlpha(1.0f);
            }
        });
    }
}
