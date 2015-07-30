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

package id.zelory.benihtes.adapter;

import android.content.Context;
import android.view.ViewGroup;

import id.zelory.benih.adapter.BenihRecyclerAdapter;
import id.zelory.benihtes.R;
import id.zelory.benihtes.adapter.viewholder.BeritaHolder;
import id.zelory.benihtes.model.Berita;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public class BeritaRecyclerAdapter extends BenihRecyclerAdapter<Berita, BeritaHolder>
{
    public BeritaRecyclerAdapter(Context context)
    {
        super(context);
    }

    @Override
    protected int getItemView(int viewType)
    {
        return R.layout.item_berita;
    }

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new BeritaHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
