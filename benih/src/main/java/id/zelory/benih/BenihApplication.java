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

package id.zelory.benih;

import android.app.Application;
import android.util.Log;

import id.zelory.benih.utils.BenihBus;
import rx.functions.Action1;

/**
 * Created by zetbaitsu on 7/13/15.
 */
public class BenihApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        log("Apps starting");
        BenihBus.receive()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        log("From apps " + o.toString());
                    }
                });
    }

    protected void log(String message)
    {
        try
        {
            Log.d(getClass().getSimpleName(), message);
        } catch (Exception e)
        {
            Log.d(getClass().getSimpleName(), "Null message.");
        }
    }
}
