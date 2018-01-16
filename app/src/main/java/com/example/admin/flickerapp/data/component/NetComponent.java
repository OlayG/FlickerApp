package com.example.admin.flickerapp.data.component;

import android.content.SharedPreferences;

import com.example.admin.flickerapp.data.module.NetModule;
import com.example.admin.flickerapp.data.module.FlickrAppModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Olay G on 10/12/2017.
 */
@Singleton
@Component( modules = {FlickrAppModule.class, NetModule.class} )
public interface NetComponent {

    Retrofit retrofit();
    SharedPreferences preferences();
}
