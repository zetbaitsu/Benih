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

import android.support.v4.app.FragmentManager;

import java.util.List;

import id.zelory.benih.ui.adapter.BenihPagerAdapter;
import id.zelory.benihtes.ui.fragment.MyFragment;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class MyPagerAdapter extends BenihPagerAdapter<MyFragment> {
    public MyPagerAdapter(FragmentManager fm, List<MyFragment> myFragments, List<String> titles) {
        super(fm, myFragments, titles);
    }

    @Override
    public MyFragment getItem(int position) {
        fragments.get(position).setData("This is fragment " + (position + 1));
        return fragments.get(position);
    }
}
