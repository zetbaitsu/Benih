/*
 * Copyright (c) 2015 Zetra.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package id.zelory.benih.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by zetbaitsu on 7/28/15.
 */
public abstract class BenihRecyclerListener extends RecyclerView.OnScrollListener
{
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 3;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private int currentPage = 0;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    public BenihRecyclerListener(LinearLayoutManager linearLayoutManager)
    {
        this.linearLayoutManager = linearLayoutManager;
    }

    public BenihRecyclerListener(GridLayoutManager gridLayoutManager)
    {
        this.gridLayoutManager = gridLayoutManager;
    }

    public BenihRecyclerListener(StaggeredGridLayoutManager staggeredGridLayoutManager)
    {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
    }

    public BenihRecyclerListener(LinearLayoutManager linearLayoutManager, int visibleThreshold)
    {
        this.linearLayoutManager = linearLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    public BenihRecyclerListener(GridLayoutManager gridLayoutManager, int visibleThreshold)
    {
        this.gridLayoutManager = gridLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    public BenihRecyclerListener(StaggeredGridLayoutManager staggeredGridLayoutManager, int visibleThreshold)
    {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        if (linearLayoutManager != null)
        {
            totalItemCount = linearLayoutManager.getItemCount();
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        } else if (gridLayoutManager != null)
        {
            totalItemCount = gridLayoutManager.getItemCount();
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
        } else if (staggeredGridLayoutManager != null)
        {
            totalItemCount = staggeredGridLayoutManager.getItemCount();
            int[] tmp = null;
            tmp = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(tmp);
            if (tmp != null && tmp.length > 0)
            {
                firstVisibleItem = tmp[0];
            }
        }

        if (loading && totalItemCount > previousTotal)
        {
            loading = false;
            previousTotal = totalItemCount;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold))
        {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
