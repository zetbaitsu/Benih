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

package id.zelory.benih.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.adapter.viewholder.BenihListViewHolder;
import id.zelory.benih.util.BenihWorker;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihListAdapter<Data, Holder extends BenihListViewHolder> extends BaseAdapter
{
    protected Context context;
    protected List<Data> data;

    public BenihListAdapter(Context context)
    {
        this.context = context;
        data = new ArrayList<>();
        Timber.tag(getClass().getSimpleName());
    }

    public BenihListAdapter(Context context, List<Data> data)
    {
        this.context = context;
        this.data = data;
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public int getCount()
    {
        try
        {
            return data.size();
        } catch (Exception e)
        {
            return 0;
        }
    }

    @Override
    public Data getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent)
    {
        Holder holder;
        if (itemView == null)
        {
            itemView = LayoutInflater.from(context).inflate(getItemView(), parent, false);
            holder = onCreateViewHolder(itemView);
            itemView.setTag(holder);
        } else
        {
            holder = (Holder) itemView.getTag();
        }

        holder.bind(data.get(position));

        return itemView;
    }

    protected abstract int getItemView();

    public abstract Holder onCreateViewHolder(View itemView);

    public List<Data> getData()
    {
        return data;
    }

    public void add(Data item)
    {
        data.add(item);
        notifyDataSetChanged();
    }

    public void add(Data item, int position)
    {
        data.add(position, item);
        notifyDataSetChanged();
    }

    public void add(final List<Data> items)
    {
        final int size = items.size();
        BenihWorker.pluck()
                .doInComputation(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (int i = 0; i < size; i++)
                        {
                            data.add(items.get(i));
                        }
                    }
                }).subscribe(new Action1<Object>()
        {
            @Override
            public void call(Object o)
            {
                notifyDataSetChanged();
            }
        });
    }

    public void remove(int position)
    {
        data.remove(position);
        notifyDataSetChanged();
    }

    public void remove(Data item)
    {
        int position = data.indexOf(item);
        data.remove(position);
        notifyDataSetChanged();
    }

    public void clear()
    {
        data.clear();
        notifyDataSetChanged();
    }
}
