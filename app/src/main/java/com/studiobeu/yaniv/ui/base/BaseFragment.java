package com.studiobeu.yaniv.ui.base;

import androidx.fragment.app.Fragment;

import com.studiobeu.yaniv.application.di.component.DaggerFragmentComponent;
import com.studiobeu.yaniv.application.di.component.FragmentComponent;
import com.studiobeu.yaniv.application.di.module.FragmentModule;
import com.studiobeu.yaniv.application.YanivApp;

public class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;

    public FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .applicationComponent(YanivApp.get(getActivity()).getApplicationComponent())
                    .fragmentModule(new FragmentModule(getActivity()))
                    .build();
        }
        return mFragmentComponent;
    }
}
