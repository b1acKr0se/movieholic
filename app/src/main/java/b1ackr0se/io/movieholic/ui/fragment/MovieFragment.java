package b1ackr0se.io.movieholic.ui.fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.presenter.listing.IMovieListingFragment;
import b1ackr0se.io.movieholic.presenter.listing.MoviePresenter;
import b1ackr0se.io.movieholic.ui.activity.DetailActivity;
import b1ackr0se.io.movieholic.ui.adapter.MovieAdapter;
import b1ackr0se.io.movieholic.util.Option;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements IMovieListingFragment{

    @Bind(R.id.recycler_view)RecyclerView recyclerView;
    @Bind(R.id.progress_bar)ProgressBar progressBar;

    private MovieAdapter mMovieAdapter;
    private MoviePresenter mMoviePresenter;
    private Subscription mMovieSuscription;
    private Option.Type mFragmentType;
    private ArrayList<Movie> mMovieList = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance(Option.Type type) {
        MovieFragment fragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentType = (Option.Type) getArguments().getSerializable("type");

        switch (mFragmentType) {
            case MOVIE:
                mMoviePresenter = new MoviePresenter(this, Option.MOVIE_POPULAR);
                break;
            case TV:
                mMoviePresenter = new MoviePresenter(this, Option.TV_POPULAR);
                break;
            default:
                mMoviePresenter = new MoviePresenter(this, Option.MOVIE_POPULAR);
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieSuscription = mMoviePresenter.displayData();
    }

    @Override
    public void onDestroyView() {
        if(mMovieSuscription != null && !mMovieSuscription.isUnsubscribed())
            mMovieSuscription.unsubscribe();
        super.onDestroyView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mMovieAdapter = new MovieAdapter(getContext(), mMovieList, this);
        recyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (progressBar.getVisibility() == View.VISIBLE) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        mMovieList.clear();
        mMovieList.addAll(movies);
        mMovieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("Movie", movie);
        intent.putExtra("Type", mFragmentType);
        startActivity(intent);
    }

}
