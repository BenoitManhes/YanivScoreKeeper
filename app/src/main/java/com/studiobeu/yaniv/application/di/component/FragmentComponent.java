package com.studiobeu.yaniv.application.di.component;


import com.studiobeu.yaniv.application.di.PerFragment;
import com.studiobeu.yaniv.application.di.module.FragmentModule;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

}
