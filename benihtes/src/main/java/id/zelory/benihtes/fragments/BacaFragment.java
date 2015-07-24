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

package id.zelory.benihtes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import id.zelory.benih.fragments.BenihFragment;
import id.zelory.benih.networks.ServiceGenerator;
import id.zelory.benih.utils.BenihScheduler;
import id.zelory.benihtes.R;
import id.zelory.benihtes.models.Berita;
import id.zelory.benihtes.networks.clients.TaniPediaClient;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class BacaFragment extends BenihFragment<Berita>
{
    @Override
    protected int getFragmentView()
    {
        return R.layout.fragment_my;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState, View view)
    {
        TextView textView = (TextView) view.findViewById(R.id.text);

        TaniPediaClient client = ServiceGenerator.createService(TaniPediaClient.class, TaniPediaClient.BASE_URL);

        client.getBerita(data.getAlamat())
                .compose(BenihScheduler.applySchedulers(BenihScheduler.Type.IO))
                .subscribe(berita -> textView.setText(berita.getIsi()), throwable -> log(throwable.getMessage()));

        getSupportActionBar().setTitle(data.getJudul());
    }
}
