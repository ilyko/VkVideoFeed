package com.slava.vkvideofeed.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }

/*    @Provides
    SchedulerProvider getSchedulerProvider(){return new AppRxSchedulers();}*/

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
