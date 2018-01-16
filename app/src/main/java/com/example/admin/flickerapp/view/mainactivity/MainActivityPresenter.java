package com.example.admin.flickerapp.view.mainactivity;

import com.example.admin.flickerapp.data.entities.FlickrImages;
import com.example.admin.flickerapp.util.Constants;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import timber.log.Timber;

/**
 * Created by Olay G on 10/12/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    public Retrofit retrofit;
    MainActivityContract.View view;

    @Inject
    public MainActivityPresenter(Retrofit retrofit, MainActivityContract.View view){
        this.retrofit = retrofit;
        this.view = view;
        Timber.tag("MainActivityPresenter");
        Timber.d("Main Presenter initilized \n" + retrofit + "\n" + view);
    }

    @DebugLog
    @Override
    public void loadPictures(String searchText) {
        retrofit.create(FlickrService.class).observablePictureList(searchText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<FlickrImages>() {
                    @DebugLog
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @DebugLog
                    @Override
                    public void onNext(FlickrImages value) {
                        view.showPictures(value);
                    }
                    @DebugLog
                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Timber.d("The error message is: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.showComplete();
                    }
                });
    }

    private interface FlickrService {
        @GET(Constants.FLICKR_PATH)
        Observable<FlickrImages> observablePictureList(
                @Query("text") String text
        );

        @GET(Constants.FLICKR_PATH)
        Single<FlickrImages> singlePictureList(
                @Path("text") String text
        );

        @GET(Constants.FLICKR_PATH)
        Flowable<FlickrImages> flowablePictureList(
                @Path("text") String text
        );

        @GET(Constants.FLICKR_PATH)
        Maybe<FlickrImages> maybePictureList(
                @Path("text") String text
        );
    }
}
