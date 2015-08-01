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

package id.zelory.benih.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zetbaitsu on 7/10/15.
 */
public class BenihPreferenceUtils
{
    public static void putString(Context context, String key, String isi)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE).edit();
        editor.putString(key, isi);
        editor.apply();
    }

    public static String getString(Context context, String key)
    {
        String isi;
        SharedPreferences sharedPreferences = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE);
        isi = sharedPreferences.getString(key, null);
        return isi;
    }

    public static void putDouble(Context context, String key, double isi)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE).edit();
        editor.putFloat(key, (float) isi);
        editor.apply();
    }

    public static Double getDouble(Context context, String key)
    {
        double isi;
        SharedPreferences sharedPreferences = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE);
        switch (key)
        {
            case "lat":
                isi = sharedPreferences.getFloat(key, -7.7521492f);
                break;
            case "lon":
                isi = sharedPreferences.getFloat(key, 110.377659f);
                break;
            default:
                isi = sharedPreferences.getFloat(key, 0);
        }

        return isi;
    }

    public static void putBoolean(Context context, String key, boolean isi)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, isi);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Zelory", Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }
}
