package com.studiobeu.yaniv.ui.main.home;

import android.content.Context;

import com.studiobeu.yaniv.application.di.ActivityContext;
import com.studiobeu.yaniv.data.DataManager;
import com.studiobeu.yaniv.ui.base.BasePresenter;
import com.studiobeu.yaniv.ui.base.BaseContract;
import com.studiobeu.yaniv.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter(@ActivityContext Context context, DataManager dataManager) {
        super(context,dataManager);
    }

    @Override
    public void onClickNew() {
        mView.showPlayerSelection();
    }

    @Override
    public void onClickResume() {
        mView.showGameSelection();
    }

    @Override
    public void onClickRules() {
        mView.showRules();
    }
}
