package com.slava.vkvideofeed.dagger;

import android.app.Application;

import com.slava.vkvideofeed.MvpApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                VkModule.class,
                AppModule.class,
                AndroidBindingModule.class
        }
)

public interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(MvpApp mvpApp);
}