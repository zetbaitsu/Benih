package id.zelory.benihtes;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.BenihActivity;
import id.zelory.benih.controller.Controller;
import id.zelory.benih.util.BenihBus;
import id.zelory.benih.util.BenihWorker;
import id.zelory.benih.view.BenihRecyclerView;
import id.zelory.benihtes.adapter.BeritaRecyclerAdapter;
import id.zelory.benihtes.controller.BeritaController;
import id.zelory.benihtes.model.Berita;

public class MainActivity extends BenihActivity implements BeritaController.Presenter
{
    private BeritaController beritaController;
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
        subscription = BenihBus.pluck()
                .receive()
                .subscribe(o -> log(o.toString()), throwable -> log(throwable.getMessage()));
        subscriptionCollector.add(subscription);

        setUpAdapter();
        setUpRecyclerView();
        setUpController(savedInstanceState);

        beritaController.doSomeThing();

        doSomeDummyThread();
    }

    private void setUpAdapter()
    {
        adapter = new BeritaRecyclerAdapter(this);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, BacaActivity.class);
            intent.putParcelableArrayListExtra("data", (ArrayList<Berita>) adapter.getData());
            intent.putExtra("pos", position);
            startActivity(intent);
        });
        adapter.setOnLongItemClickListener((view, position) -> adapter.remove(position));
    }

    private void setUpRecyclerView()
    {
        recyclerView = (BenihRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setUpAsList();
        recyclerView.setAdapter(adapter);
    }

    private void setUpController(Bundle bundle)
    {
        if (beritaController == null)
        {
            beritaController = new BeritaController(this);
        }

        if (bundle != null)
        {
            beritaController.loadState(bundle);
        } else
        {
            beritaController.loadListBerita();
        }
    }

    private void doSomeDummyThread()
    {
        for (int i = 0; i < 10; i++)
        {
            final int thread = i;
            subscription = BenihWorker.pluck()
                    .doThis(() -> {
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
            subscriptionCollector.add(subscription);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        BenihBus.pluck().send("onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        BenihBus.pluck().send("onOptionsMenuSelected");
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

    @Override
    public void showListBerita(List<Berita> listBerita)
    {
        adapter.add(listBerita);
    }

    @Override
    public void showBerita(Berita berita)
    {

    }

    @Override
    public void showSomeThing()
    {
        adapter.remove(3);
        Toast.makeText(this, "WOWWOWOWOWO", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(Controller.Presenter presenter, Throwable throwable)
    {
        if (presenter instanceof BeritaController.Presenter)
        {
            Snackbar.make(recyclerView, "Something wrong!", Snackbar.LENGTH_LONG).show();
        } else
        {
            log("another presenter");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        beritaController.saveState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
