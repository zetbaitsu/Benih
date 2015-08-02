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
import rx.Subscriber;

/**
 * Created by zetbaitsu on 7/22/15.
 */
public enum BenihWorker
{
    HARVEST;

    public static BenihWorker pluck()
    {
        return HARVEST;
    }

    public Observable<Object> doInComputation(final Runnable runnable)
    {
        return Observable.create(new Observable.OnSubscribe<Object>()
        {
            @Override
            public void call(Subscriber<? super Object> subscriber)
            {
                runnable.run();
                if (!subscriber.isUnsubscribed())
                {
                    subscriber.onNext("We are done bro!");
                }
            }
        }).compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.COMPUTATION));
    }

    public Observable<Object> doInIO(final Runnable runnable)
    {
        return Observable.create(new Observable.OnSubscribe<Object>()
        {
            @Override
            public void call(Subscriber<? super Object> subscriber)
            {
                runnable.run();
                if (!subscriber.isUnsubscribed())
                {
                    subscriber.onNext("We are done bro!");
                }
            }
        }).compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO));
    }

    public Observable<Object> doInNewThread(final Runnable runnable)
    {
        return Observable.create(new Observable.OnSubscribe<Object>()
        {
            @Override
            public void call(Subscriber<? super Object> subscriber)
            {
                runnable.run();
                if (!subscriber.isUnsubscribed())
                {
                    subscriber.onNext("We are done bro!");
                }
            }
        }).compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.NEW_THREAD));
    }
}
