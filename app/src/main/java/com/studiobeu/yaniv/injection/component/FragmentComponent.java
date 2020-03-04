package com.studiobeu.yaniv.injection.component;


import com.studiobeu.yaniv.injection.PerFragment;
import com.studiobeu.yaniv.injection.module.FragmentModule;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

}
