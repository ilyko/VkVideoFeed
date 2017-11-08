package com.slava.vkvideofeed.ui.fullscreen;

import com.slava.vkvideofeed.ui.main.MainActivity;
import com.slava.vkvideofeed.ui.main.MainMvp;
import com.slava.vkvideofeed.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slava on 08.11.17.
 */
@Module
public class FullscreenActivityModule {
    @Provides
    FullscreenMvp.View provideFullscreenView(FullscreenActivity fullscreenActivity) {
        return fullscreenActivity;
    }

    @Provides
    FullscreenMvp.Presenter provideFullscreenPresenter(FullscreenMvp.View fullscreenView) {
        return new FullscreenPresenter(fullscreenView);
    }
}
