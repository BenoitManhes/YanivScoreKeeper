package com.studiobeu.yaniv.ui.main.home;

import com.studiobeu.yaniv.ui.base.BaseContract;

public interface HomeContract {

    interface View extends BaseContract.View<Presenter> {
        void showPlayerSelection();
        void showGameSelection();
        void showRules();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void onClickNew();
        void onClickResume();
        void onClickRules();
    }
}
