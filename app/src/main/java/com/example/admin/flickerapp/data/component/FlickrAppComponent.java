package com.example.admin.flickerapp.data.component;

import com.example.admin.flickerapp.data.module.FlickrAppModule;
import com.example.admin.flickerapp.view.FlickrApp;

import dagger.Component;

/**
 * Created by Olay G on 10/12/2017.
 */
@Component( modules = {FlickrAppModule.class})
public interface FlickrAppComponent {
    void inject(FlickrApp flickrApp);
}
