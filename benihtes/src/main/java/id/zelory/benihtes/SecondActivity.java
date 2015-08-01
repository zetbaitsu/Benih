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
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import id.zelory.benih.BenihActivity;
import id.zelory.benihtes.adapter.MyPagerAdapter;
import id.zelory.benihtes.fragment.MyFragment;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class SecondActivity extends BenihActivity
{
    @Bind(R.id.view_pager) ViewPager viewPager;
    @Bind(R.id.tab_layout) TabLayout tabLayout;

    @Override
    protected int getActivityView()
    {
        return R.layout.activity_second;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState)
    {
        List<MyFragment> fragments = Arrays.asList(new MyFragment(), new MyFragment());
        List<String> titles = Arrays.asList("Fragment 1", "Fragment 2");
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
