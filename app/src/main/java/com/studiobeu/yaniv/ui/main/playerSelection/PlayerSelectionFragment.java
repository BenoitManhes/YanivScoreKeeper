package com.studiobeu.yaniv.ui.main.playerSelection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.application.di.component.FragmentComponent;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.adapter.CardPlayerAdapter;
import com.studiobeu.yaniv.ui.base.BaseFragment;

import java.util.ArrayList;
import static com.studiobeu.yaniv.data.Constants.*;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.BindView;


public class PlayerSelectionFragment extends BaseFragment<PlayerSelectionContract.Presenter> implements PlayerSelectionContract.View {


    private View inflatedView;

    @BindView(R.id.player_select_gridLayout)
    GridView mGridView;

    @BindView(R.id.switchMode)
    Switch mSwitch;

    @BindView(R.id.editLimite)
    EditText mEtLimite;

    @BindView(R.id.textLimite)
    TextView mTvLimite;

    @BindView(R.id.add)
    Button mBtnAddPlayer;

    @BindView(R.id.start)
    TextView mBtnStartGame;

    @Inject
    PlayerSelectionPresenter mPresenter;

    private CardPlayerAdapter mCardPlayerAdapter;

    public static PlayerSelectionFragment newInstance() {
        return new PlayerSelectionFragment();
    }

    public PlayerSelectionFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.fragment_player_selection, container, false);

        initViews();
        setupListeners();

        FragmentComponent component = getFragmentComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);

            mPresenter.loadPlayers();
        }

        return inflatedView;
    }

    /** Set up view's elements listeners */
    private void setupListeners() {
        mBtnAddPlayer.setOnClickListener(v -> mPresenter.onClickAddPlayer());
        mBtnStartGame.setOnClickListener(v -> mPresenter.onClickStartGame());
    }

    /** initialize the fragment view **/
    @Override
    protected void initViews() {
        mEtLimite.setText(SCORE_LIMITE_DEFAULT);
    }

    @Override
    public void setPresenter(PlayerSelectionContract.Presenter presenter) {
        this.mPresenter = (PlayerSelectionPresenter)presenter;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void launchYanivGame() {

    }

    @Override
    public void displayPlayers(ArrayList<Player> playersList) {
        mCardPlayerAdapter.setData(playersList);
    }

    @Override
    public void addNewPlayer(Player player) {
        mCardPlayerAdapter.addPlayer(player,true);
    }

    @Override
    public ArrayList<Player> getPlayersSelected() {
        return mCardPlayerAdapter.getPlayerSelected();
    }

    @Override
    public int getScoreLimite() {
        try{
            return Integer.parseInt(mTvLimite.getText().toString());
        } catch (Exception e){
            return SCORE_LIMITE_DEFAULT;
        }
    }

    @Override
    public boolean isInfiniteMode() {
        return !mSwitch.isChecked();
    }

    @Override
    public void showLimitMessage(int min){
        showToastMessage( getString(R.string.player_selection_limit_message) + min);
    }
}
