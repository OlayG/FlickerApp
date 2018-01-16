package com.example.admin.flickerapp.view.mainactivity;

import com.example.admin.flickerapp.data.entities.FlickrImages;

/**
 * Created by Olay G on 10/12/2017.
 */

public interface MainActivityContract {

    interface View  {

        void showPictures(FlickrImages flickrImages);
        void showError(String error);
        void showComplete();

    }

    interface Presenter {

        void loadPictures(String searchText);
    }
}
