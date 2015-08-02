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
