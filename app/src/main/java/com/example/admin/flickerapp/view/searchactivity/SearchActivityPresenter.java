package com.example.admin.flickerapp.view.searchactivity;

import javax.inject.Inject;

/**
 * Created by Olay G on 10/12/2017.
 */

public class SearchActivityPresenter implements SearchActivityContract.Presenter {
    SearchActivityContract.View view;

    @Inject
    public SearchActivityPresenter(SearchActivityContract.View view) {
        this.view = view;
    }
}
