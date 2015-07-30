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

package id.zelory.benihtes.controller;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import id.zelory.benih.controller.Controller;
import id.zelory.benih.util.BenihScheduler;
import id.zelory.benih.util.BenihWorker;
import id.zelory.benihtes.model.Berita;
import id.zelory.benihtes.network.TaniPediaService;

/**
 * Created by zetbaitsu on 7/29/15.
 */
public class BeritaController extends Controller<BeritaController.Presenter>
{
    private List<Berita> listBerita;
    private Berita berita;

    public BeritaController(Presenter presenter)
    {
        super(presenter);
    }

    public void loadListBerita()
    {
        TaniPediaService.pluck()
                .getApi()
                .getAllBerita()
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                .subscribe(listBerita -> {
                    this.listBerita = listBerita;
                    presenter.showListBerita(listBerita);
                }, throwable -> {
                    log(throwable.getMessage());
                    presenter.showError(presenter, throwable);
                });
    }

    public void loadBerita(String url)
    {
        TaniPediaService.pluck()
                .getApi()
                .getBerita(url)
                .compose(BenihScheduler.pluck().applySchedulers(BenihScheduler.Type.IO))
                .subscribe(berita -> {
                    this.berita = berita;
                    presenter.showBerita(berita);
                }, throwable -> {
                    log(throwable.getMessage());
                    presenter.showError(presenter, throwable);
                });
    }

    private void doThing()
    {
        for (int i = 0; i < 1000000000; i++)
        {
            int a = i;
            int b = a + 1;
            int c = b * 2;
            a = a * c / b;
        }
    }

    public void doSomeThing()
    {
        BenihWorker.pluck()
                .doThis(this::doThing)
                .subscribe(o -> {
                    log(o.toString());
                    presenter.showSomeThing();
                }, throwable -> log(throwable.getMessage()));
    }

    @Override
    public void saveState(Bundle bundle)
    {
        bundle.putParcelableArrayList("listBerita", (ArrayList<Berita>) listBerita);
        bundle.putParcelable("berita", berita);
    }

    @Override
    public void loadState(Bundle bundle)
    {
        listBerita = bundle.getParcelableArrayList("listBerita");
        if (listBerita != null)
        {
            presenter.showListBerita(listBerita);
        } else
        {
            presenter.showError(presenter, new Throwable("Error"));
        }

        berita = bundle.getParcelable("berita");
        if (berita != null)
        {
            presenter.showBerita(berita);
        } else
        {
            presenter.showError(presenter, new Throwable("Error"));
        }
    }

    public interface Presenter extends Controller.Presenter
    {
        void showListBerita(List<Berita> listBerita);

        void showBerita(Berita berita);

        void showSomeThing();
    }
}
