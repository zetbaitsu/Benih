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
        classpath 'me.tatarka:gradle-retrolambda:3.2.3'
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile 'id.zelory.benih:benih:0.2.0'
}
```

Berikut ini adalah salah satu contoh penggunaan Benih untuk melakukan parsing JSON dari web services (API) yang kemudian akan di ubah ke dalam bentuk Object Java dan kemudian ditampilkan dalam RecyclerView. Sangat mudah dan simple, untuk melihat contoh lainnya silahkan lihat pada aplikasi <a href="https://github.com/zetbaitsu/Benih/tree/master/benihtes">benihtes</a>

#Model
```java
public class Berita {
    private String judul;
    private String alamat;
    private String gambar;
    private String tanggal;
    private String deskripsi;
    private String isi;

    //Getter and Setter...
}
```
#API Services
```java
public enum TaniPediaService {
    HARVEST;
    private final Api api;

    TaniPediaService() {
        api = BenihServiceGenerator.createService(Api.class, Api.BASE_URL);
    }

    public static TaniPediaService pluck() {
        return HARVEST;
    }

    public Api getApi() {
        return api;
    }

    public interface Api {
        String BASE_URL = "http://alamat-api.com";

        @GET("/berita")
        Observable<List<Berita>> getAllBerita();
    }
}
```

#Presenter
```java
public class BeritaPresenter extends BenihPresenter<BeritaPresenter.View> {

    public BeritaPresenter(View view) {
        super(view);
    }

    public void loadListBerita() {
        TaniPediaService.pluck()
                .getApi()
                .getAllBerita()
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                .subscribe(view::showListBerita, view::showError);
    }

    public interface View extends BenihPresenter.View {
        void showListBerita(List<Berita> listBerita);
    }
}
```

#Adapter
```java
public class BeritaRecyclerAdapter extends BenihRecyclerAdapter<Berita, BeritaHolder> {

    public BeritaRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_berita;
    }

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeritaHolder(getView(parent, viewType), itemClickListener, longItemClickListener);
    }
}
```

#ItemViewHolder
```java
public class BeritaHolder extends BenihItemViewHolder<Berita> {
    @Bind(R.id.text) TextView judul;

    public BeritaHolder(View itemView, OnItemClickListener itemClickListener, OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Berita berita) {
        judul.setText(berita.getJudul());
    }
}
```

#Activity atau Fragment
```java
public class MainActivity extends BenihActivity implements BeritaPresenter.View {
    @Bind(R.id.recycler_view) BenihRecyclerView recyclerView;
    private BeritaPresenter beritaPresenter;
    private BeritaRecyclerAdapter adapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        adapter = new BeritaRecyclerAdapter(this);
        recyclerView.setUpAsList();
        recyclerView.setAdapter(adapter);
        beritaPresenter = new BeritaPresenter(this);
        beritaPresenter.loadListBerita();
    }

    @Override
    public void showListBerita(List<Berita> listBerita) {
        adapter.add(listBerita);
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(recyclerView, "Tidak dapat terhubung ke server!", Snackbar.LENGTH_LONG).show();
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
