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

package id.zelory.benihtes.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import id.zelory.benih.ui.adapter.BenihListAdapter;
import id.zelory.benihtes.R;
import id.zelory.benihtes.data.model.Berita;
import id.zelory.benihtes.ui.adapter.viewholder.BeritaListHolder;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public class BeritaListAdapter extends BenihListAdapter<Berita, BeritaListHolder> {
    public BeritaListAdapter(Context context, List<Berita> data) {
        super(context, data);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_berita;
    }

    @Override
    public BeritaListHolder onCreateViewHolder(View itemView) {
        return new BeritaListHolder(itemView);
    }
}
