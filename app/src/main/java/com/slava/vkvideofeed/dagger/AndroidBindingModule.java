package com.slava.vkvideofeed.dagger;

import com.slava.vkvideofeed.ui.fullscreen.FullscreenActivity;
import com.slava.vkvideofeed.ui.fullscreen.FullscreenActivityModule;
import com.slava.vkvideofeed.ui.login.LoginActivity;
import com.slava.vkvideofeed.ui.login.LoginActivityModule;
import com.slava.vkvideofeed.ui.main.MainActivity;
import com.slava.vkvideofeed.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AndroidBindingModule {

    @ContributesAndroidInjector(modules = {ActivityModule.class, LoginActivityModule.class})
    abstract LoginActivity bindHelloActivity();

    @ContributesAndroidInjector(modules = {ActivityModule.class, MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {ActivityModule.class, FullscreenActivityModule.class})
    abstract FullscreenActivity bindFullscreenActivity();
}
