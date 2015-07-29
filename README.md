Benih
======
Benih merupakan template standar dari aplikasi android yang dibuat oleh Zelory. Benih sudah menangani beberapa hal yang sering menjadi komponen utama sebuah aplikasi android. Dengan Benih, developer tidak perlu dipusingkan lagi dengan hal-hal yang bukan esensial lagi, tapi cukup fokus pada inti dari permasalahan yang ingin diselesaikan. Benih akan mempermudah pengembangan aplikasi android, hal-hal seperti penanganan network, web services, load gambar dari internet, adapter recyclerview, handle item click di recyclerview dsb sudah ditangani dengan optimal. Benih diharapkan bisa mempermudah dan mempercepat proses pengembangan aplikasi.

Contoh Penggunaan
-------
Tambahkan perintah berikut pada build.gradle mu:

```groovy
apply plugin: 'me.tatarka.retrolambda'

android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'me.tatarka:gradle-retrolambda:3.2.0'
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile 'id.zelory.benih:benih:0.0.9'
}
```

Berikut ini adalah salah satu contoh penggunaan Benih untuk melakukan parsing JSON dari web services (API) yang kemudian akan di ubah ke dalam bentuk Object Java dan kemudian ditampilkan dalam RecyclerView. Sangat mudah dan simple, untuk melihat contoh lainnya silahkan lihat pada aplikasi <a href="https://github.com/zetbaitsu/Benih/tree/master/benihtes">benihtes</a>

```java
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
        adapter = new BeritaRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, BacaActivity.class);
            intent.putParcelableArrayListExtra("data", (ArrayList<Berita>) adapter.getData());
            intent.putExtra("pos", position);
            startActivity(intent);
        });

        TaniPediaClient client = ServiceGenerator.createService(TaniPediaClient.class, TaniPediaClient.BASE_URL);

        client.getAllBerita()
                .compose(BenihScheduler.applySchedulers(BenihScheduler.Type.IO))
                .subscribe(data -> {
                    adapter.add(data);
                }, throwable -> log(throwable.getMessage()));
    }
}
```

License
-------
    Copyright (c) 2015 Zetra.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
