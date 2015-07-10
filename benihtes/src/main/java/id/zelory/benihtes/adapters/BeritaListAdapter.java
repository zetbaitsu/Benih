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

package id.zelory.benihtes.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.zelory.benih.adapters.BenihListAdapter;
import id.zelory.benihtes.R;
import id.zelory.benihtes.models.Berita;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public class BeritaListAdapter extends BenihListAdapter<Berita>
{
    public BeritaListAdapter(Context context, List<Berita> data)
    {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);

            holder = new ViewHolder();
            holder.judul = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.judul.setText(data.get(position).getJudul());

        return convertView;
    }

    private class ViewHolder
    {
        TextView judul;
    }
}
