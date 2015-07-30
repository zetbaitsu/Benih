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

package id.zelory.benihtes;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.BenihActivity;
import id.zelory.benih.util.BenihBus;
import id.zelory.benihtes.adapter.BacaPagerAdapter;
import id.zelory.benihtes.fragment.BacaFragment;
import id.zelory.benihtes.model.Berita;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class BacaActivity extends BenihActivity
{
    @Override
    protected int getActivityView()
    {
        return R.layout.activity_second;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState)
    {
        subscription = BenihBus.pluck()
                .receive()
                .subscribe(o -> log("from BacaActivity : " + o.toString()));
        subscriptionCollector.add(subscription);

        int pos = getIntent().getIntExtra("pos", 0);
        ArrayList<Berita> data = getIntent().getParcelableArrayListExtra("data");
        List<BacaFragment> fragments = new ArrayList<>();

        for (Berita berita : data)
        {
            BacaFragment fragment = new BacaFragment();
            fragment.setData(berita);

            fragments.add(fragment);
        }

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(new BacaPagerAdapter(getSupportFragmentManager(), fragments));
        pager.setCurrentItem(pos);

    }
}
