package com.example.admin.flickerapp.view.searchactivity;

import com.example.admin.flickerapp.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Olay G on 10/12/2017.
 */
@Module
public class SearchActivityModule {
    private final SearchActivityContract.View view;

    public SearchActivityModule(SearchActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    SearchActivityContract.View providesSearchActivityContractView() {
        return view;
    }
}
