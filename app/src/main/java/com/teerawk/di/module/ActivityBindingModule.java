package com.teerawk.di.module;

import com.teerawk.di.ActivityScoped;
import com.teerawk.di.FragmentScoped;
import com.teerawk.flow.repository.RepositoryActivity;
import com.teerawk.flow.repository.detail.ReposDetailActivity;
import com.teerawk.flow.repository.detail.fragment.ReposDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract RepositoryActivity repositoryActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ReposDetailActivity reposDetailActivity();

    @FragmentScoped
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ReposDetailFragment reposDetailFragment();

}