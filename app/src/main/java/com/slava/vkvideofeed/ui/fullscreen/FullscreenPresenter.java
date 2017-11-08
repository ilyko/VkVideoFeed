package com.slava.vkvideofeed.ui.fullscreen;

import com.slava.vkvideofeed.ui.main.MainMvp;

import javax.inject.Inject;

/**
 * Created by slava on 08.11.17.
 */

public class FullscreenPresenter implements FullscreenMvp.Presenter{
    private FullscreenMvp.View view;

    @Inject
    public FullscreenPresenter(FullscreenMvp.View view) {
        this.view = view;
    }
}
