package b1ackr0se.io.movieholic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import b1ackr0se.io.movieholic.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @Bind(R.id.image_gallery)ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        String filePath = getIntent().getStringExtra("image");
        Glide.with(this).load(filePath)
                .into(mImageView);
    }
}
