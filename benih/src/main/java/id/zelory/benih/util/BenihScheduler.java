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

package id.zelory.benih.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public enum BenihScheduler
{
    HARVEST;
    private final Observable.Transformer newThread;
    private final Observable.Transformer io;
    private final Observable.Transformer computation;

    BenihScheduler()
    {
        newThread = new Observable.Transformer()
        {
            @Override
            public Observable call(Object o)
            {
                return ((Observable) o).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        io = new Observable.Transformer()
        {
            @Override
            public Observable call(Object o)
            {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        computation = new Observable.Transformer()
        {
            @Override
            public Observable call(Object o)
            {
                return ((Observable) o).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static BenihScheduler pluck()
    {
        return HARVEST;
    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers(Type type)
    {
        switch (type)
        {
            case NEW_THREAD:
                return (Observable.Transformer<T, T>) newThread;
            case IO:
                return (Observable.Transformer<T, T>) io;
            case COMPUTATION:
                return (Observable.Transformer<T, T>) computation;
        }

        return (Observable.Transformer<T, T>) newThread;
    }

    public enum Type
    {
        NEW_THREAD, IO, COMPUTATION
    }
}
