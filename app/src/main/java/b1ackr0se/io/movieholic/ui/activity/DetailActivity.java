package b1ackr0se.io.movieholic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.ui.fragment.DetailFragment;
import b1ackr0se.io.movieholic.util.Option;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            Movie movie = intent.getParcelableExtra("Movie");
            Option.Type type = (Option.Type) intent.getSerializableExtra("Type");
            if (movie != null)
            {
                DetailFragment movieDetailsFragment = DetailFragment.getInstance(movie, type);
                getSupportFragmentManager().beginTransaction().add(R.id.frame_container, movieDetailsFragment).commit();
            }
        }

    }
}
