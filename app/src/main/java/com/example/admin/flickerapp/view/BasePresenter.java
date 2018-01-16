package com.example.admin.flickerapp.view;

/**
 * Created by Olay on 10/10/2017.
 */

public interface BasePresenter<V extends BaseView>{

    void attachView(V view);
    void detachView();
}
