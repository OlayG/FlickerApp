
package com.example.admin.flickerapp.data.entities;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class FlickrImages {

    public Photos photos;
    public String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
