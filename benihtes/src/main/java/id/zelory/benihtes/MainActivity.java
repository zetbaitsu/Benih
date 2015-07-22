package id.zelory.benihtes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import id.zelory.benih.BenihActivity;
import id.zelory.benih.networks.ServiceGenerator;
import id.zelory.benih.utils.BenihBus;
import id.zelory.benih.utils.BenihScheduler;
import id.zelory.benih.utils.BenihWorker;
import id.zelory.benih.views.BenihRecyclerView;
import id.zelory.benihtes.adapters.BeritaRecyclerAdapter;
import id.zelory.benihtes.models.Berita;
import id.zelory.benihtes.networks.clients.TaniPediaClient;

public class MainActivity extends BenihActivity
{
    private BenihRecyclerView recyclerView;
    private BeritaRecyclerAdapter adapter;

    @Override
    protected int getActivityView()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState)
    {
        BenihBus.receive()
                .subscribe(o -> log(o.toString()), throwable -> log(throwable.getMessage()));

        adapter = new BeritaRecyclerAdapter(this);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, BacaActivity.class);
            intent.putParcelableArrayListExtra("data", (ArrayList<Berita>) adapter.getData());
            intent.putExtra("pos", position);
            startActivity(intent);
        });
        adapter.setOnLongItemClickListener((view, position) -> adapter.remove(position));

        recyclerView = (BenihRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setUpAsList();
        recyclerView.setAdapter(adapter);

        TaniPediaClient client = ServiceGenerator.createService(TaniPediaClient.class, TaniPediaClient.BASE_URL);
        client.getAllBerita()
                .compose(BenihScheduler.applySchedulers(BenihScheduler.Type.IO))
                .subscribe(adapter::add, throwable -> log(throwable.getMessage()));

        BenihWorker.doThis(MainActivity.this::doSomeThing)
                .subscribe(o -> log(o.toString()), throwable -> log(throwable.getMessage()));

        for (int i = 0; i < 10; i++)
        {
            final int thread = i;
            BenihWorker.doThis(() -> {
                for (int j = 0; j < 10000; j++)
                {
                    for (int k = 0; k < j; k++)
                    {
                        int a = j;
                        int b = k;
                        int c = a * k + b * j;
                        c = c / (j - k);
                    }
                }
            }).subscribe(o -> log("Worker " + thread + " is done."), throwable -> log(throwable.getMessage()));
        }
    }

    void doSomeThing()
    {
        for (int i = 0; i < 1000000000; i++)
        {
            int a = i;
            int b = a + 1;
            int c = b * 2;
            a = a * c / b;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        BenihBus.send("onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        BenihBus.send("onOptionsMenuSelected");
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_settings:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
