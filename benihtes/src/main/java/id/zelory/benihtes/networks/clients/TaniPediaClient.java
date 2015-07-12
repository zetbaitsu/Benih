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

package id.zelory.benihtes.networks.clients;

import java.util.List;

import id.zelory.benihtes.models.Berita;
import id.zelory.benihtes.models.Cuaca;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public interface TaniPediaClient
{
    String BASE_URL = "http://apitanipedia.appspot.com";

    @GET("/berita")
    Observable<List<Berita>> getAllBerita();

    @GET("/berita")
    Observable<Berita> getBerita(@Query("url") String url);

    @GET("/cuaca")
    Observable<List<Cuaca>> getCuaca(@Query("lat") double lat, @Query("lon") double lon);

    @GET("/cuaca")
    Observable<List<Cuaca>> getCuaca();
}
