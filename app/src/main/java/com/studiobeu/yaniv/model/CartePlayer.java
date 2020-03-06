package com.studiobeu.yaniv.model;

import android.widget.ImageView;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.ui.activity.PlayerSelect;

public class CartePlayer {

    private int imageSelectAdress;
    private int imageUnselectAdress;
    private boolean select;
    public ImageView imageFond;
    public ImageView imagePlayer;
    private Player player;

    public CartePlayer(Player p, ImageView imgFond, ImageView imgPlayer){
        this.select = false;
        this.player = p;
        loadImage();
        this.imageFond= imgFond;
        this.imagePlayer= imgPlayer;
        imageFond.setBackgroundResource(imageUnselectAdress);

    }

    public void loadImage(){
        imageSelectAdress = R.drawable.carte_verso;
        imageUnselectAdress = R.drawable.carte2;
    }

    public void click(){
        this.select = !this.select;
        if (this.select) {
            this.imageFond.setBackgroundResource(this.imageSelectAdress);
            this.imagePlayer.setImageBitmap(this.player.geticon());
            if(!PlayerSelect.playerSelected.contains(player)) PlayerSelect.playerSelected.add(player);

        }else {
            this.imageFond.setBackgroundResource(this.imageUnselectAdress);
            this.imagePlayer.setImageBitmap(null);
            if(PlayerSelect.playerSelected.contains(player)) PlayerSelect.playerSelected.remove(player);
        }



    }

    /** getter & setter */
    public Player getPlayer() {
        return player;
    }
}
