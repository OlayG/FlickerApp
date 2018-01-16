package com.example.admin.flickerapp.view.searchactivity;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.admin.flickerapp.R;
import com.example.admin.flickerapp.view.BaseActivity;
import com.example.admin.flickerapp.view.FlickrApp;

import javax.inject.Inject;

import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class SearchActivity extends BaseActivity implements SearchActivityContract.View {

    @Inject
    SearchActivityPresenter presenter;
    @Inject
    SharedPreferences preferences;
    private SearchView searchView;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        activateToolbar(true);
        ButterKnife.bind(this);
        setupDagger();
    }

    private void setupDagger() {
        DaggerSearchActivityComponent.builder()
                .netComponent(((FlickrApp) getApplicationContext()).getNetComponent())
                .searchActivityModule(new SearchActivityModule(this))
                .build().inject(this);
    }

    @DebugLog
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) searchItem.getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @DebugLog
            @Override
            public boolean onQueryTextSubmit(String query) {
                preferences.edit().putString(FLICKR_QUERY, query).apply();
                Timber.d("Saved to preferences is " + preferences.getString(FLICKR_QUERY, ""));
                searchView.clearFocus();
                finish();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();
                return false;
            }
        });
        return true;
    }
}
