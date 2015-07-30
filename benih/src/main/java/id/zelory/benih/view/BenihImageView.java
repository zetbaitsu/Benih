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

package id.zelory.benih.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by zetbaitsu on 7/27/15.
 */
public class BenihImageView extends ImageView
{
    private String imageUrl;

    public BenihImageView(Context context)
    {
        super(context);
    }

    public BenihImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BenihImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(String url)
    {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderResourceId, int errorResourceId)
    {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .placeholder(placeHolderResourceId)
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, int placeHolderDrawable, Drawable errorDrawable)
    {
        imageUrl = url;
        Glide.with(getContext())
                .load(url)
                .placeholder(placeHolderDrawable)
                .error(errorDrawable)
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar)
    {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>()
                {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar, int errorResourceId)
    {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>()
                {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorResourceId)
                .into(this);
    }

    public void setImageUrl(String url, final ProgressBar progressBar, Drawable errorDrawable)
    {
        imageUrl = url;
        progressBar.setVisibility(VISIBLE);
        Glide.with(getContext())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>()
                {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
                    {
                        progressBar.setVisibility(GONE);
                        return false;
                    }
                })
                .error(errorDrawable)
                .into(this);
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
}
