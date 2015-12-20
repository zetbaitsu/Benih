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

package id.zelory.benihtes.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import id.zelory.benih.ui.BenihActivity;
import id.zelory.benih.util.BenihBus;
import id.zelory.benihtes.R;
import id.zelory.benihtes.ui.adapter.BacaPagerAdapter;
import id.zelory.benihtes.ui.fragment.BacaFragment;
import id.zelory.benihtes.data.model.Berita;
import timber.log.Timber;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class BacaActivity extends BenihActivity {
    @Bind(R.id.view_pager) ViewPager pager;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BenihBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d("from BacaActivity : " + o.toString()));

        int pos = getIntent().getIntExtra("pos", 0);
        ArrayList<Berita> data = getIntent().getParcelableArrayListExtra("data");
        List<BacaFragment> fragments = new ArrayList<>();

        for (Berita berita : data) {
            BacaFragment fragment = new BacaFragment();
            fragment.setData(berita);

            fragments.add(fragment);
        }

        pager.setAdapter(new BacaPagerAdapter(getSupportFragmentManager(), fragments));
        pager.setCurrentItem(pos);

    }
}
