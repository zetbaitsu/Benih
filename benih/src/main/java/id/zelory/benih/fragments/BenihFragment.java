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

package id.zelory.benih.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public abstract class BenihFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return LayoutInflater.from(getActivity()).inflate(getFragmentView(), container, false);
    }

    protected abstract int getFragmentView();

    protected void log(String message)
    {
        try
        {
            Log.d(getClass().getSimpleName(), message);
        }catch (Exception e)
        {
            Log.d(getClass().getSimpleName(), "Null message.");
        }
    }
}
