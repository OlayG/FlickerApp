package com.example.admin.flickerapp.view.mainactivity;

import com.example.admin.flickerapp.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Olay G on 10/12/2017.
 */
@Module
public class MainActivityModule {
    private final MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainActivityContract.View providesMainActivityContractView() {
        return view;
    }
}
