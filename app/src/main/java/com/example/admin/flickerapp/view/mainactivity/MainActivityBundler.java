package com.example.admin.flickerapp.view.mainactivity;

import android.os.Bundle;

import com.example.admin.flickerapp.data.entities.FlickrImages;

import org.parceler.Parcels;

import icepick.Bundler;

public class MainActivityBundler implements Bundler<FlickrImages> {
    @Override
    public void put(String s, FlickrImages flickrImages, Bundle bundle) {
        bundle.putParcelable(s, Parcels.wrap(flickrImages));
    }

    @Override
    public FlickrImages get(String s, Bundle bundle) {
        return Parcels.unwrap(bundle.getParcelable(s));
    }
}
