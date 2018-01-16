package com.example.admin.flickerapp.view.mainactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.admin.flickerapp.R;
import com.example.admin.flickerapp.adapter.PictureRecyclerViewAdapter;
import com.example.admin.flickerapp.data.entities.FlickrImages;
import com.example.admin.flickerapp.data.entities.Photo;
import com.example.admin.flickerapp.util.RecyclerItemClickListener;
import com.example.admin.flickerapp.util.SearchQueryEvent;
import com.example.admin.flickerapp.view.BaseActivity;
import com.example.admin.flickerapp.view.FlickrApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import icepick.State;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainActivityContract.View, RecyclerItemClickListener.OnRecyclerClickListener {

    @Inject
    MainActivityPresenter presenter;
    @Inject
    SharedPreferences preferences;
    @BindView(R.id.rvPhotos)
    RecyclerView rvPhotos;
    private PictureRecyclerViewAdapter pictureRecyclerViewAdapter;
    @State(MainActivityBundler.class) String message;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activateToolbar(false);
        ButterKnife.bind(this);
        setupDagger();
        setupRecyclerView();
    }

    @DebugLog
    @Override
    protected void onResume() {
        super.onResume();
        String queryResult = preferences.getString(FLICKR_QUERY, "");

        if(!queryResult.isEmpty())
            presenter.loadPictures(queryResult);
        else
            presenter.loadPictures("kittens");
    }

    private void setupRecyclerView() {
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhotos.addOnItemTouchListener(new RecyclerItemClickListener(this, rvPhotos, this));
        rvPhotos.setLayoutTransition(null);
        rvPhotos.setFocusableInTouchMode(true);
        pictureRecyclerViewAdapter = new PictureRecyclerViewAdapter(new ArrayList<Photo>(), this);
        pictureRecyclerViewAdapter.setHasStableIds(true);
        rvPhotos.setAdapter(pictureRecyclerViewAdapter);
    }

    private void setupDagger() {
        DaggerMainActivityComponent.builder()
                .netComponent(((FlickrApp) getApplicationContext()).getNetComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @DebugLog
    @Override
    public void showPictures(FlickrImages flickrImages) {
        pictureRecyclerViewAdapter.loadNewData(flickrImages.getPhotos().getPhoto());
        Toast.makeText(this, "Show Pictures", Toast.LENGTH_SHORT).show();
    }

    @DebugLog
    @Override
    public void showError(String error) {
    }

    @DebugLog
    @Override
    public void showComplete() {
        Toast.makeText(this, "Completed Download", Toast.LENGTH_SHORT).show();
    }

    @DebugLog
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Normal tap at " + position, Toast.LENGTH_SHORT).show();
    }

    @DebugLog
    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this, "Long tap at " + position, Toast.LENGTH_SHORT).show();
    }

    @DebugLog
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchQueryEvent(SearchQueryEvent queryEvent) {
        preferences.edit().putString(FLICKR_QUERY, queryEvent.getQueryText()).apply();
        Timber.d("Saved to preferences is " + preferences.getString(FLICKR_QUERY, ""));
        presenter.loadPictures(queryEvent.getQueryText());
    }

}
