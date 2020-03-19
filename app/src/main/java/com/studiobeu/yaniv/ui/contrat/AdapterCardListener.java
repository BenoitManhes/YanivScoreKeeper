package com.studiobeu.yaniv.ui.contrat;

import com.studiobeu.yaniv.data.local.entity.Player;

public interface AdapterCardListener {

    /**
     * long click on a player card from adapterCard
     * @param player player onLongClick
     */
    void onLongClikPlayer(Player player);
}
