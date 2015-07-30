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
public class Cuaca implements Parcelable
{
    public Cuaca()
    {

    }

    private String lokasi;
    private String suhuMax;
    private String suhuMin;
    private String suhu;
    private String tanggal;
    private String cuaca;
    private String kegiatan;

    protected Cuaca(Parcel in)
    {
        lokasi = in.readString();
        suhuMax = in.readString();
        suhuMin = in.readString();
        suhu = in.readString();
        tanggal = in.readString();
        cuaca = in.readString();
        kegiatan = in.readString();
    }

    public static final Creator<Cuaca> CREATOR = new Creator<Cuaca>()
    {
        @Override
        public Cuaca createFromParcel(Parcel in)
        {
            return new Cuaca(in);
        }

        @Override
        public Cuaca[] newArray(int size)
        {
            return new Cuaca[size];
        }
    };

    public String getLokasi()
    {
        return lokasi;
    }

    public void setLokasi(String lokasi)
    {
        this.lokasi = lokasi;
    }

    public String getSuhuMax()
    {
        return suhuMax;
    }

    public void setSuhuMax(String suhuMax)
    {
        this.suhuMax = suhuMax;
    }

    public String getSuhuMin()
    {
        return suhuMin;
    }

    public void setSuhuMin(String suhuMin)
    {
        this.suhuMin = suhuMin;
    }

    public String getSuhu()
    {
        return suhu;
    }

    public void setSuhu(String suhu)
    {
        this.suhu = suhu;
    }

    public String getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(String tanggal)
    {
        this.tanggal = tanggal;
    }

    public String getCuaca()
    {
        return cuaca;
    }

    public void setCuaca(String cuaca)
    {
        this.cuaca = cuaca;
    }

    public String getKegiatan()
    {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan)
    {
        this.kegiatan = kegiatan;
    }

    @Override
    public int describeContents()
    {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(lokasi);
        dest.writeString(suhuMax);
        dest.writeString(suhuMin);
        dest.writeString(suhu);
        dest.writeString(tanggal);
        dest.writeString(cuaca);
        dest.writeString(kegiatan);
    }
}
