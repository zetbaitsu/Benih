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

package id.zelory.benihtes.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public class Berita implements Parcelable
{
    private String judul;
    private String alamat;
    private String gambar;
    private String tanggal;
    private String deskripsi;
    private String isi;

    public Berita()
    {

    }

    protected Berita(Parcel in)
    {
        judul = in.readString();
        alamat = in.readString();
        gambar = in.readString();
        tanggal = in.readString();
        deskripsi = in.readString();
        isi = in.readString();
    }

    public static final Creator<Berita> CREATOR = new Creator<Berita>()
    {
        @Override
        public Berita createFromParcel(Parcel in)
        {
            return new Berita(in);
        }

        @Override
        public Berita[] newArray(int size)
        {
            return new Berita[size];
        }
    };

    public String getJudul()
    {
        return judul;
    }

    public void setJudul(String judul)
    {
        this.judul = judul;
    }

    public String getAlamat()
    {
        return alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getGambar()
    {
        return gambar;
    }

    public void setGambar(String gambar)
    {
        this.gambar = gambar;
    }

    public String getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(String tanggal)
    {
        this.tanggal = tanggal;
    }

    public String getDeskripsi()
    {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi)
    {
        this.deskripsi = deskripsi;
    }

    public String getIsi()
    {
        return isi;
    }

    public void setIsi(String isi)
    {
        this.isi = isi;
    }

    @Override
    public int describeContents()
    {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(judul);
        dest.writeString(alamat);
        dest.writeString(gambar);
        dest.writeString(tanggal);
        dest.writeString(deskripsi);
        dest.writeString(isi);
    }
}
