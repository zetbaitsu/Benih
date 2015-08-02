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

package id.zelory.benih.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import id.zelory.benih.fragment.BenihFragment;
import timber.log.Timber;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihPagerAdapter<Fragment extends BenihFragment> extends
        FragmentStatePagerAdapter
{
    protected List<Fragment> fragments;
    protected List<String> titles;

    public BenihPagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
        Timber.tag(getClass().getSimpleName());
    }

    public BenihPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles)
    {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public abstract Fragment getItem(int position);

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    public List<Fragment> getFragments()
    {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments)
    {
        this.fragments = fragments;
    }

    public List<String> getTitles()
    {
        return titles;
    }

    public void setTitles(List<String> titles)
    {
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles.size() == fragments.size() ? titles.get(position) : super.getPageTitle(position);
    }
}
