package com.timothyborghouts.shareameal.data;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MealAsyncLoader extends AsyncTaskLoader<String> {


    MealAsyncLoader(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getMeal();
    }
}
