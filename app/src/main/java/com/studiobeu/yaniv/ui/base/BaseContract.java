package com.studiobeu.yaniv.ui.base;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public interface BaseContract {

    interface View<T> {

        void setPresenter(T presenter);

        void showProgressDialog(String title, @NonNull String message);

        void showProgressDialog(@StringRes int resId);

        void dismissProgressDialog();

        void showToastMessage(@NonNull String message);

        void showToastMessage(@StringRes int stringResourceId);

        void showSnackBarMessage(@NonNull String message);

        void showSnackBarMessage(@StringRes int stringResourceId);

        void onError(String message);

        void onError(@StringRes int resId);

    }

    interface Presenter<V> {

        void onAttach(V view);

        void onDetach();

//        void handleApiError(Throwable throwable);

    }

}
