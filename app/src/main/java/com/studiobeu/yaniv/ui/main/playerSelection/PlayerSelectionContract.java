package com.studiobeu.yaniv.ui.main.playerSelection;

import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.base.BaseContract;

import java.util.ArrayList;

public interface PlayerSelectionContract {

    interface View extends BaseContract.View<Presenter> {

        void launchYanivGame();

        void showLimitMessage(int limitMin);

        /** Display players in GridView */
        void displayPlayers(ArrayList<Player> playersList);

        /** Add a new player to display in the gridView*/
        void addNewPlayer(Player player);

        /** @return players selected arraylist */
        ArrayList<Player> getPlayersSelected();

        /** @return score limit */
        int getScoreLimite();

        /** @return */
        boolean isInfiniteMode();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void onClickAddPlayer();
        void onClickStartGame();

        /** Load all players data */
        void loadPlayers();
    }
}
