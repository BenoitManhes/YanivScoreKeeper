package com.studiobeu.yaniv.ui.base;

import android.content.Context;

import com.studiobeu.yaniv.application.di.ActivityContext;
import com.studiobeu.yaniv.data.DataManager;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>{

//    private final SchedulerProvider mSchedulerProvider;
//    private final CompositeDisposable mCompositeDisposable;
    protected final Context mContext;
    protected final DataManager mDataManager;

    protected V mView;

    public BasePresenter(@ActivityContext Context context, DataManager dataManager) {
        mContext = context;
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
//        mCompositeDisposable.clear();
        mView = null;
    }

    protected boolean isViewAttached() {
        return mView != null;
    }

    protected V getView(){
        return mView;
    }
//
//    protected SchedulerProvider getSchedulerProvider(){
//        return mSchedulerProvider;
//    }
//
//    protected CompositeDisposable getCompositeDisposable() {return mCompositeDisposable; }

}
