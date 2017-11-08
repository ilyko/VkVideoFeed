package com.slava.vkvideofeed.ui.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    @Provides
    MainMvp.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainMvp.Presenter provideMainPresenter(MainMvp.View mainView) {
        return new MainPresenter(mainView);
    }
}
