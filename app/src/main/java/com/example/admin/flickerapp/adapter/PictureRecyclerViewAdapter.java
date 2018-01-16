package com.example.admin.flickerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.flickerapp.R;
import com.example.admin.flickerapp.data.entities.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Olay G on 10/12/2017.
 */

public class PictureRecyclerViewAdapter extends RecyclerView.Adapter<PictureRecyclerViewAdapter.FlickrImageViewHolder> {
    private List<Photo> pictureList;
    private Context context;

    public PictureRecyclerViewAdapter(List<Photo> pictureList, Context context) {
        this.pictureList = pictureList;
        this.context = context;
        Timber.tag("PictureRecyclerViewAdapter");
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        Photo photoItem = pictureList.get(position);

        //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
        String photoUrl = "https://farm" + photoItem.getFarm() + ".staticflickr.com/" + photoItem.getServer() + "/" + photoItem.getId()
                + "_" + photoItem.getSecret() + "_q.jpg";
        // Glide.with(context).load(ph)
        Picasso.with(context).load(photoUrl).into(holder.ivPhoto);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return ((pictureList != null) && (pictureList.size() != 0) ? pictureList.size() : 0);
    }

    public void loadNewData(List<Photo> newPhotos) {
        pictureList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return ((pictureList != null) && (pictureList.size() != 0) ? pictureList.get(position) : null);
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        public FlickrImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
