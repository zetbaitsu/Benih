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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public abstract class BenihActivity extends AppCompatActivity
{
    protected Subscription subscription;
    protected CompositeSubscription subscriptionCollector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getActivityView());
        ButterKnife.bind(this);
        Timber.tag(getClass().getSimpleName());
        subscriptionCollector = new CompositeSubscription();
        onViewReady(savedInstanceState);
    }

    protected abstract int getActivityView();

    protected abstract void onViewReady(Bundle savedInstanceState);

    @Override
    protected void onDestroy()
    {
        if (subscription != null)
        {
            subscription.unsubscribe();
            subscription = null;
        }

        if (subscriptionCollector != null)
        {
            subscriptionCollector.unsubscribe();
            subscriptionCollector = null;
        }
        super.onDestroy();
    }
}
