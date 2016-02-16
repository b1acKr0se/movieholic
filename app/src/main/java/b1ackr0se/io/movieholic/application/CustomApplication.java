package b1ackr0se.io.movieholic.application;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

import b1ackr0se.io.movieholic.R;

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
