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

package id.zelory.benih.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import timber.log.Timber;

import static id.zelory.benih.ui.adapter.BenihRecyclerAdapter.OnItemClickListener;
import static id.zelory.benih.ui.adapter.BenihRecyclerAdapter.OnLongItemClickListener;

/**
 * Created on : July 10, 2015
 * Author     : zetbaitsu
 * Name       : Zetra
 * Email      : zetra@mail.ugm.ac.id
 * GitHub     : https://github.com/zetbaitsu
 * LinkedIn   : https://id.linkedin.com/in/zetbaitsu
 */
public abstract class BenihItemViewHolder<Data> extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnLongClickListener {
    private OnItemClickListener itemClickListener;
    private OnLongItemClickListener longItemClickListener;
    private boolean hasHeader = false;

    public BenihItemViewHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        Timber.tag(getClass().getSimpleName());
        this.itemClickListener = itemClickListener;
        this.longItemClickListener = longItemClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bind(Data data);

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(v, hasHeader ? getAdapterPosition() - 1 : getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longItemClickListener != null) {
            longItemClickListener.onLongItemClick(v, hasHeader ? getAdapterPosition() - 1 : getAdapterPosition());
            return true;
        }
        return false;
    }
}
