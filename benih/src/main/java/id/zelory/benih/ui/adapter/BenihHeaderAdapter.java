/*
 * Copyright (c) 2015 Zelory.
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

package id.zelory.benih.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.ui.adapter.viewholder.BenihHeaderViewHolder;
import id.zelory.benih.ui.adapter.viewholder.BenihItemViewHolder;

/**
 * Created on : August 25, 2015
 * Author     : zetbaitsu
 * Name       : Zetra
 * Email      : zetra@mail.ugm.ac.id
 * GitHub     : https://github.com/zetbaitsu
 * LinkedIn   : https://id.linkedin.com/in/zetbaitsu
 */
public abstract class BenihHeaderAdapter<Data, ViewHolder extends BenihItemViewHolder<Data>,
        Header extends BenihHeaderViewHolder> extends
        BenihRecyclerAdapter<Data, BenihItemViewHolder> {
    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;
    protected boolean hasHeader = true;
    protected Header header;
    protected Bundle bundle;

    public BenihHeaderAdapter(Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        if (hasHeader) {
            data.add(null);
        }
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        if (viewType == TYPE_HEADER) {
            return getHeaderLayout();
        } else {
            return getItemLayout(viewType);
        }
    }

    protected abstract int getHeaderLayout();

    protected abstract int getItemLayout(int viewType);

    @Override
    public BenihItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (hasHeader && viewType == TYPE_HEADER) {
            header = onCreateHeaderViewHolder(viewGroup, viewType);
            return header;
        }

        return onCreateItemViewHolder(viewGroup, viewType);
    }

    protected abstract Header onCreateHeaderViewHolder(ViewGroup viewGroup, int viewType);

    public abstract ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(BenihItemViewHolder holder, int position) {
        if (hasHeader && position == 0) {
            header.show();
            return;
        }
        holder.setHasHeader(hasHeader);
        super.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeader) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void showHeader() {
        if (!hasHeader) {
            hasHeader = true;
            data.add(0, null);
        }
    }

    public void hideHeader() {
        if (hasHeader) {
            hasHeader = false;
            data.remove(0);
        }
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    @Override
    public void clear() {
        super.clear();
        if (hasHeader) {
            data.add(null);
        }
    }

    @Override
    public List<Data> getData() {
        return hasHeader ? new ArrayList<>(data.subList(1, data.size())) : super.getData();
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public void add(Data item, int position) {
        if (hasHeader) {
            data.add(position + 1, item);
            notifyItemInserted(position + 1);
        } else {
            super.add(item, position);
        }
    }

    @Override
    public void remove(int position) {
        if (hasHeader) {
            data.remove(position + 1);
            notifyItemRemoved(position + 1);
        } else {
            super.remove(position);
        }
    }
}
