package com.studiobeu.yaniv.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.application.di.component.DaggerFragmentComponent;
import com.studiobeu.yaniv.application.di.component.FragmentComponent;
import com.studiobeu.yaniv.application.di.module.FragmentModule;
import com.studiobeu.yaniv.utils.DialogUtils;
import com.studiobeu.yaniv.utils.GeneralUtils;

public abstract class BaseFragment<T> extends Fragment implements BaseContract.View<T>{

    protected Dialog progressDialog;
    protected BaseActivity mActivity;
    private FragmentComponent mFragmentComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Set listeners and callback attached to the parent activity as null
     * in order to avoid activity leaking
     */

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Custom Progress Dialog with loading dots animation
     * @param message
     */
    @Override
    public void showProgressDialog(String title, @NonNull String message) {
        progressDialog = DialogUtils.createProgressDialog(getContext());
    }

    /**
     * Custom Progress Dialog with loading dots animation
     * @param resId
     */
    @Override
    public void showProgressDialog(@StringRes int resId) {
        String message = getString(resId);
        if(GeneralUtils.checkStringNotEmpty(message))
            showProgressDialog(null,message);
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showToastMessage(@NonNull String message) {
        if (GeneralUtils.checkStringNotEmpty(message))
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastMessage(@StringRes int resId) {
        showToastMessage(getString(resId));
    }

    @Override
    public void showSnackBarMessage(@NonNull String message){
        if(GeneralUtils.checkStringNotEmpty(message))
            showSnackBar(message);
    }

    @Override
    public void showSnackBarMessage(@StringRes int resId){
        showSnackBarMessage(getString(resId));
    }

    /**
     * Creates a SnackBar for message display
     */
    private void showSnackBar(String message) {
        if(getActivity()!=null) {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    message, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(getResources().getColor(R.color.vert_fonce));
            TextView textView = sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
            snackbar.show();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.default_error_message));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    protected FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .applicationComponent(YanivApp.getApp(getActivity()).getApplicationComponent())
                    .fragmentModule(new FragmentModule(getActivity()))
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    protected abstract void initViews();
}
