package com.example.admin.flickerapp.util;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import hugo.weaving.DebugLog;

/**
 * Created by Olay G on 10/15/2017.
 */

public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    public interface OnRecyclerClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private final OnRecyclerClickListener listener;
    private final GestureDetectorCompat gestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnRecyclerClickListener listener) {
        this.listener = listener;
        this.gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @DebugLog
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && listener != null){
                    listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            @DebugLog
            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && listener != null){
                    listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @DebugLog
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        if(gestureDetector != null) {
            boolean result = gestureDetector.onTouchEvent(e);
            return result;
        } else {
            return false;
        }
    }
}
