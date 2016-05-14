package com.jifalops.btleadvertise.Item;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by client on 2016. 5. 13..
 */
public class MyCardListViewItem {
    private Drawable iconDrawable ;
    private Bitmap imageView;
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public Drawable getIcon() {
        return this.iconDrawable ;
    }

    public void setImageView(Bitmap image){
        this.imageView = image;
    }
    public Bitmap getImageView()
    {
        return this.imageView;
    }

}
