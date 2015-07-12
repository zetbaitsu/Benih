package id.zelory.benihtes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import id.zelory.benih.BenihActivity;
import id.zelory.benih.networks.ServiceGenerator;
import id.zelory.benih.utils.BenihScheduler;
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
        recyclerView = (BenihRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setUpAsList();

        TaniPediaClient client = ServiceGenerator.createService(TaniPediaClient.class, TaniPediaClient.BASE_URL);

        client.getAllBerita()
                .compose(BenihScheduler.getInstance().applySchedulers(BenihScheduler.Type.IO))
                .subscribe(data -> {
                    adapter = new BeritaRecyclerAdapter(this, data);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener((view, position) -> {
                        Intent intent = new Intent(this, BacaActivity.class);
                        intent.putParcelableArrayListExtra("data", (ArrayList<Berita>) adapter.getData());
                        intent.putExtra("pos", position);
                        startActivity(intent);
                    });
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
