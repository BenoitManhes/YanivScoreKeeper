package com.studiobeu.yaniv.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.application.di.component.FragmentComponent;
import com.studiobeu.yaniv.ui.base.BaseFragment;
import com.studiobeu.yaniv.ui.custom.view.CardButtonView;

import javax.inject.Inject;
import androidx.annotation.Nullable;
import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.home_menu_card_new)
    CardButtonView mCardNew;

    @BindView(R.id.home_menu_card_resume)
    CardButtonView mCardResume;

    @BindView(R.id.home_menu_card_rules)
    CardButtonView mCardRules;

    private View inflatedView;

    @Inject
    HomePresenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();
        setupListeners();

        FragmentComponent component = getFragmentComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);

//            mPresenter.loadItems(false);
        }

        return inflatedView;
    }

    /** Set up view's elements listeners */
    private void setupListeners() {
        mCardNew.setOnClickListener(v -> mPresenter.onClickNew());
        mCardResume.setOnClickListener(v -> mPresenter.onClickResume());
        mCardRules.setOnClickListener(v -> mPresenter.onClickRules());
    }

    /** initialize the fragment view **/
    @Override
    protected void initViews() {
        //initialize view here
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = (HomePresenter)presenter;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    /** launch new game, show player selection screen */
    @Override
    public void showPlayerSelection() {

    }

    /** resume game, show games selection screen */
    @Override
    public void showGameSelection() {

    }

    /** show Yaniv rules screen */
    @Override
    public void showRules() {

    }
}
