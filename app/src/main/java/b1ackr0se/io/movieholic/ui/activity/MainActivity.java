package b1ackr0se.io.movieholic.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.presenter.MainView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.toolbar_top)Toolbar toolbar;
    @Bind(R.id.button_movie)View buttonMovie;
    @Bind(R.id.button_tv)View buttonTv;
    @Bind(R.id.view_pager)ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    public void setData(ArrayList<Movie> list) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
