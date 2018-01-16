package com.example.admin.flickerapp.data.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Olay G on 10/12/2017.
 */
@Module
public class FlickrAppModule {
    Application mApplication;

    public FlickrAppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
