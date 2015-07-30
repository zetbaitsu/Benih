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

import android.util.Log;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by zetbaitsu on 7/18/15.
 */
public enum BenihBus
{
    HARVEST;
    private final Subject<Object, Object> bus;

    BenihBus()
    {
        bus = new SerializedSubject<>(PublishSubject.create());
        Log.d("BenihBus", "Create");
    }

    public static BenihBus pluck()
    {
        return HARVEST;
    }

    public void send(Object o)
    {
        bus.onNext(o);
    }

    public Observable<Object> receive()
    {
        return bus.compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.NEW_THREAD));
    }

    public boolean hasObservers()
    {
        return bus.hasObservers();
    }
}
