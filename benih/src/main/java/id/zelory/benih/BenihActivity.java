package id.zelory.benih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

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
