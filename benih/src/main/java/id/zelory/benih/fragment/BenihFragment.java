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

package id.zelory.benih.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import id.zelory.benih.BenihActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihFragment<Data extends Parcelable> extends RxFragment
{
    protected Data data;

    public BenihFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Timber.tag(getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = LayoutInflater.from(getActivity()).inflate(getFragmentView(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
        {
            data = savedInstanceState.getParcelable("data");
        }
        onViewReady(savedInstanceState);
    }

    public void setData(Data data)
    {
        this.data = data;
    }

    public Data getData()
    {
        return data;
    }

    protected abstract int getFragmentView();

    protected abstract void onViewReady(@Nullable Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelable("data", data);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy()
    {
        data = null;
        super.onDestroy();
    }

    public void replace(int containerId, BenihFragment fragment, boolean addToBackStack)
    {
        if (addToBackStack)
        {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        } else
        {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    public void replace(int containerId, BenihFragment fragment, boolean addToBackStack, int transitionStyle)
    {
        if (addToBackStack)
        {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        } else
        {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        }
    }

    protected ActionBar getSupportActionBar()
    {
        return ((BenihActivity) getActivity()).getSupportActionBar();
    }

    protected void setSupportActionBar(Toolbar toolbar)
    {
        ((BenihActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
