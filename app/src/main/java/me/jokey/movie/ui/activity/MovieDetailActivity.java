package me.jokey.movie.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.jokey.movie.R;
import me.jokey.movie.contract.MovieDetailContract;
import me.jokey.movie.model.MovieDetailEntity;
import me.jokey.movie.model.PersonageEntity;
import me.jokey.movie.presenter.MovieDetailPresenter;
import me.jokey.movie.ui.adapter.PersonageAdapter;

import static me.jokey.movie.R.id.toolbar;

/**
 * Created by wz on 2017/9/7 16:40.
 * desc:电影详情页
 */
public class MovieDetailActivity extends MvpActivity<MovieDetailPresenter> implements
        MovieDetailContract.View {

    private static final String MOVIE_ID = "id";

    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cover)
    ImageView mCover;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.directors)
    TextView mDirectors;
    @BindView(R.id.casts)
    TextView mCasts;
    @BindView(R.id.countries)
    TextView mCountries;
    @BindView(R.id.genres)
    TextView mGenres;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.ratingText)
    TextView mRating;
    @BindView(R.id.summary)
    TextView mSummary;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private String mShareUrl = null;

    public static Intent newIntent(Context context, String movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        String movieId = getIntent().getStringExtra(MOVIE_ID);
        if (TextUtils.isEmpty(movieId)) {
            return;
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(view -> finish());
        mToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_share) {
                if (TextUtils.isEmpty(mShareUrl)) {
                    Toast.makeText(this, "空白页不能分享", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("text/html");
                it.putExtra(Intent.EXTRA_TEXT, mShareUrl);
                startActivity(Intent.createChooser(it, "分享到"));
                return true;
            }
            return false;
        });
        mPresenter.getMovieDetail(movieId);
    }

    @Override
    public void loadMovieDetail(MovieDetailEntity movie) {
        if (movie == null) return;
        mShareUrl = movie.getMobileUrl();
        mToolbar.setTitle(movie.getTitle());
        Glide.with(this)
                .load(movie.getImages().getLarge())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mCover);
        mTitle.setText(movie.getTitle());
        mDirectors.setText(getString(R.string.movie_detail_directors, listToString(movie.getDirectors(), '/')));
        mCasts.setText(getString(R.string.movie_detail_casts, listToString(movie.getCasts(), '/')));
        mCountries.setText(getString(R.string.movie_detail_countries, listToString(movie.getCountries(), '/')));
        mGenres.setText(getString(R.string.movie_detail_genres, listToString(movie.getGenres(), '/')));
        float average = movie.getRating().getAverage();
        if (average != 0.0) {
            mRatingBar.setMax(movie.getRating().getMax());
            mRatingBar.setRating(movie.getRating().getAverage());
        } else {
            mRatingBar.setVisibility(View.GONE);
            mRating.setText(R.string.movie_detail_rating_no);
        }
        mSummary.setText(movie.getSummary());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        List<PersonageEntity> list = new ArrayList<>();
        list.addAll(movie.getDirectors());
        list.addAll(movie.getCasts());
        PersonageAdapter adapter = new PersonageAdapter(this, list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected MovieDetailPresenter createPresenter() {
        return new MovieDetailPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail, menu);
        return true;
    }

    private String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if (object instanceof String) {
                sb.append(object).append(separator);
            } else if (object instanceof PersonageEntity) {
                PersonageEntity entity = (PersonageEntity) object;
                sb.append(entity.getName()).append(separator);
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
