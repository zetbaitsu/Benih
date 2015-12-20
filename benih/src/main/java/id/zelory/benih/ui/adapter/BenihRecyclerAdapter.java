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

package id.zelory.benih.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.ui.adapter.viewholder.BenihItemViewHolder;
import id.zelory.benih.util.BenihWorker;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created on : December 09, 2015
 * Author     : zetbaitsu
 * Name       : Zetra
 * Email      : zetra@mail.ugm.ac.id
 * GitHub     : https://github.com/zetbaitsu
 * LinkedIn   : https://id.linkedin.com/in/zetbaitsu
 */
public abstract class BenihRecyclerAdapter<Data, Holder extends BenihItemViewHolder> extends
        RecyclerView.Adapter<Holder> {
    protected Context context;
    protected List<Data> data;
    protected OnItemClickListener itemClickListener;
    protected OnLongItemClickListener longItemClickListener;

    public BenihRecyclerAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        Timber.tag(getClass().getSimpleName());
    }

    public BenihRecyclerAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
        Timber.tag(getClass().getSimpleName());
    }

    protected View getView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(getItemResourceLayout(viewType), parent, false);
    }

    protected abstract int getItemResourceLayout(int viewType);

    @Override
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return data.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener longItemClickListener) {
        this.longItemClickListener = longItemClickListener;
    }

    public List<Data> getData() {
        return data;
    }

    public void add(Data item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void add(Data item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void add(final List<Data> items) {
        final int size = items.size();
        BenihWorker.pluck()
                .doInComputation(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < size; i++) {
                            data.add(items.get(i));
                        }
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                notifyDataSetChanged();
            }
        });
    }

    public void addOrUpdate(Data item) {
        int i = data.indexOf(item);
        if (i >= 0) {
            data.set(i, item);
            notifyItemChanged(i);
        } else {
            add(item);
        }
    }

    public void addOrUpdate(final List<Data> items) {
        final int size = items.size();
        BenihWorker.pluck()
                .doInComputation(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < size; i++) {
                            Data item = items.get(i);
                            int x = data.indexOf(item);
                            if (x >= 0) {
                                data.set(x, item);
                            } else {
                                add(item);
                            }
                        }
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                notifyDataSetChanged();
            }
        });
    }

    public void remove(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(Data item) {
        int position = data.indexOf(item);
        remove(position);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
}
