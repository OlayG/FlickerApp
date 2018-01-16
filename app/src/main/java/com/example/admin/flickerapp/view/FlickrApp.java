package com.example.admin.flickerapp.view;

import android.app.Application;

import com.example.admin.flickerapp.BuildConfig;
import com.example.admin.flickerapp.data.component.DaggerNetComponent;
import com.example.admin.flickerapp.data.component.NetComponent;
import com.example.admin.flickerapp.data.module.FlickrAppModule;
import com.example.admin.flickerapp.data.module.NetModule;

import timber.log.Timber;


/**
 * Created by Olay G on 10/12/2017.
 */

public class FlickrApp extends Application {
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
        setupTimber();
    }

    private void setupDagger() {
        netComponent = DaggerNetComponent.builder()
                .flickrAppModule(new FlickrAppModule(this))
                .netModule(new NetModule("https://api.flickr.com/"))
                .build();
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {

        }
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
