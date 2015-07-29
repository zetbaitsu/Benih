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

package id.zelory.benih.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.adapters.viewholder.BenihViewHolder;
import id.zelory.benih.utils.BenihWorker;
import rx.functions.Action1;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihRecyclerAdapter<Data, Holder extends BenihViewHolder> extends
        RecyclerView.Adapter<Holder>
{
    protected Context context;
    protected List<Data> data;
    protected OnItemClickListener itemClickListener;
    protected OnLongItemClickListener longItemClickListener;

    public BenihRecyclerAdapter(Context context)
    {
        this.context = context;
        data = new ArrayList<>();
    }

    public BenihRecyclerAdapter(Context context, List<Data> data)
    {
        this.context = context;
        this.data = data;
    }

    protected View getView(ViewGroup parent, int viewType)
    {
        return LayoutInflater.from(context).inflate(getItemView(viewType), parent, false);
    }

    protected abstract int getItemView(int viewType);

    @Override
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(Holder holder, int position)
    {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount()
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
    public long getItemId(int position)
    {
        return position;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface OnLongItemClickListener
    {
        void onLongItemClick(View view, int position);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener longItemClickListener)
    {
        this.longItemClickListener = longItemClickListener;
    }

    public List<Data> getData()
    {
        return data;
    }

    public void add(Data item)
    {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void add(Data item, int position)
    {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void add(final List<Data> items)
    {
        final int size = items.size();
        BenihWorker.doThis(new Runnable()
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
        notifyItemRemoved(position);
    }

    public void remove(Data item)
    {
        int position = data.indexOf(item);
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear()
    {
        data.clear();
        notifyDataSetChanged();
    }

    protected void log(String message)
    {
        try
        {
            Log.d(getClass().getSimpleName(), message);
        } catch (Exception e)
        {
            Log.d(getClass().getSimpleName(), "Null message.");
        }
    }
}
