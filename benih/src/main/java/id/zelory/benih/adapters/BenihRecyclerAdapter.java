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
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihRecyclerAdapter<Data, Holder extends BenihViewHolder> extends
        RecyclerView.Adapter<Holder>
{
    protected Context context;
    protected List<Data> data;
    protected OnItemClickListener clickListener;

    public BenihRecyclerAdapter(Context context, List<Data> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public abstract Holder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(Holder holder, int position);

    @Override
    public int getItemCount()
    {
        return data.size();
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
        this.clickListener = itemClickListener;
    }

    public List<Data> getData()
    {
        return data;
    }

    protected void log(String message)
    {
        try
        {
            Log.d(getClass().getSimpleName(), message);
        }catch (Exception e)
        {
            Log.d(getClass().getSimpleName(), "Null message.");
        }
    }
}
