package id.zelory.benihtes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import id.zelory.benih.BenihActivity;
import id.zelory.benih.networks.ServiceGenerator;
import id.zelory.benihtes.adapters.BeritaRecyclerAdapter;
import id.zelory.benihtes.networks.API;
import id.zelory.benihtes.networks.clients.BeritaClient;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BenihActivity
{
    private RecyclerView recyclerView;
    private BeritaRecyclerAdapter adapter;

    @Override
    protected int getActivityView()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState)
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        BeritaClient client = ServiceGenerator.createService(BeritaClient.class, API.BASE_URL);

        client.getAllBerita()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    adapter = new BeritaRecyclerAdapter(this, data);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener((view, position) -> log(adapter.getData().get(position).getJudul()));
                }, throwable -> log(throwable.getMessage()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
}
