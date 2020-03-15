package com.studiobeu.yaniv.data.local.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class Player {

    public static final String TABLE = "player";
    public static final String CI_NAME = "name";
    public static final String CI_NB_TOTAL_MANCHE = "nb_total_manche";
    public static final String CI_NB_TOTAL_MANCHE_WIN = "nb_total_manche_win";
    public static final String CI_NB_TOTAL_PARTIE = "nb_total_partie";
    public static final String CI_NB_TOTAL_PARTIE_WIN = "nb_partie_win";
    public static final String CI_ICON = "icon";
    public static final String CI_COLOR = "color";

    @PrimaryKey
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = CI_NAME)
    private String name;

    @ColumnInfo(name = CI_NB_TOTAL_MANCHE)
    private int nbTotalManche;

    @ColumnInfo(name = CI_NB_TOTAL_MANCHE_WIN)
    private int nbMancheWin;

    @ColumnInfo(name = CI_NB_TOTAL_PARTIE)
    private int nbTotalPartie;

    @ColumnInfo(name = CI_NB_TOTAL_PARTIE_WIN)
    private int nbPartieWin;

//    @ColumnInfo(name = CI_ICON)
//    private Bitmap icon;

    @ColumnInfo(name = CI_COLOR)
    private int color;

    private int currentScore;

    public Player(long id){
        this.id = id;
        name = "";

    }

    public void incrementeManche(boolean win){
        this.nbTotalManche++;
        if(win) this.nbMancheWin++;
    }

    public void incrementePartie(boolean win){
        this.nbTotalPartie++;
        if(win) this.nbPartieWin++;
    }

    /* ----------- getter & setter ------------ */

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbTotalManche() {
        return nbTotalManche;
    }

    public void setNbTotalManche(int nbTotalManche) {
        this.nbTotalManche = nbTotalManche;
    }

    public int getNbMancheWin() {
        return nbMancheWin;
    }

    public void setNbMancheWin(int nbMancheWin) {
        this.nbMancheWin = nbMancheWin;
    }

    public int getNbTotalPartie() {
        return nbTotalPartie;
    }

    public void setNbTotalPartie(int nbTotalPartie) {
        this.nbTotalPartie = nbTotalPartie;
    }

    public int getNbPartieWin() {
        return nbPartieWin;
    }

    public void setNbPartieWin(int nbPartieWin) {
        this.nbPartieWin = nbPartieWin;
    }

//    public Bitmap getIcon() {
//        return icon;
//    }
//
//    public void setIcon(Bitmap icon) {
//        this.icon = icon;
//    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
