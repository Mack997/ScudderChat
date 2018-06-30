package com.example.mayankagarwal.thechatapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Mayank Agarwal on 07-02-2018.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 7; // The minimum amount of items to have below your current scroll position before loading more.

    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;


}
