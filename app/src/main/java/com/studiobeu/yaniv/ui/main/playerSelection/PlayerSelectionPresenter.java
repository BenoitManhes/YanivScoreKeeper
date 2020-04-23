package com.studiobeu.yaniv.ui.main.playerSelection;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.application.di.ActivityContext;
import com.studiobeu.yaniv.data.DataManager;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.studiobeu.yaniv.data.Constants.*;

public class PlayerSelectionPresenter extends BasePresenter<PlayerSelectionContract.View>
        implements PlayerSelectionContract.Presenter {


    @Inject
    RoomService mRoomService;

    @Inject
    public PlayerSelectionPresenter(@ActivityContext Context context, DataManager dataManager) {
        super(context, dataManager);
    }

    @Override
    public void onClickAddPlayer() {

    }

    @Override
    public void onClickStartGame() {
        int limite = getLimiteValue();
        ArrayList<Player> playersSelected = mView.getPlayersSelected();

        if (limite < SCORE_LIMITE_MIN && !mView.isInfiniteMode()) {
            mView.showLimitMessage(SCORE_LIMITE_MIN);
        } else if (playersSelected.size() <= 1) {
            mView.showToastMessage(R.string.player_selection_player_message);
        } else {
            createNewGame(playersSelected);
            mView.launchYanivGame();
        }
    }

    @Override
    public void loadPlayers() {
//        showLoadCircle();

        mRoomService.getAllPlayer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<Player>>() {
                    @Override
                    public void onSuccess(List<Player> players) {
                        ArrayList<Player> playersList = new ArrayList<>();
                        for (Player p : players) {
                            if (p != null) {
                                playersList.add(p);
                            }
                        }

                        mView.displayPlayers(playersList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        closeLoadCircle();
                    }
                });
    }

    /* =================== Private methode ================== */
    private int getLimiteValue() {
        return SCORE_LIMITE_DEFAULT;
    }

    private void createNewGame(ArrayList<Player> players){

    }
}
