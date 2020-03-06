package com.studiobeu.yaniv.model;

import android.graphics.Bitmap;

import com.studiobeu.yaniv.ui.activity.MainActivity;
import com.studiobeu.yaniv.ui.activity.PlayerSelect;

public class Player {

    private String name;
    private int nbTotalManche;
    private int nbMancheWin;
    private int nbTotalPartie;
    private int nbPartieWin;
    private int id;
    private Bitmap icon;
    private int currentScore;
    private int color;

    {
        name="";
        nbTotalManche =0;
        nbMancheWin = 0;
        setId();
        setUnusedColor();
        icon = null;
        currentScore = 0;
    }
    public Player(){}
    public Player(String name){
        this.name = name;
    }

    public int getNbTotalManche() {
        return nbTotalManche;
    }

    public void setNbTotalManche(int nbTotalManche) {
        this.nbTotalManche = nbTotalManche;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) { this.name = n;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbMancheWin() {return nbMancheWin;}

    public void setNbMancheWin(int nbMancheWin) {this.nbMancheWin = nbMancheWin; }

    public Bitmap geticon() {        return icon;    }

    public void setAdressImage(Bitmap adressImage) {
        this.icon = adressImage;
    }

    public int getCurrentScore(){ return currentScore;}

    public void setCurrentScore(int a){ this.currentScore = a;}

    public int getNbPartieWin() {
        return nbPartieWin;
    }

    public void setNbPartieWin(int getNbPartieWin) {
        this.nbPartieWin = getNbPartieWin;
    }

    public int getNbTotalPartie() {
        return nbTotalPartie;
    }

    public void setNbTotalPartie(int nbTotalPartie) {
        this.nbTotalPartie = nbTotalPartie;
    }

    public int getColor() {   return color;   }

    public void setColor(int color) {        this.color = color;    }

    /** methode de calcul */
    public void addManchePlayed(int s){ nbTotalManche ++;}

    public void addMancheWin(int s){ nbMancheWin ++;}

    public double getRatio(){ return  (double) this.nbMancheWin/this.nbTotalManche; }

    private void setId(){
        int id = 1;
        boolean disponible = false;
        while(!disponible) {
            disponible = true;
            if (MainActivity.allPlayers != null) {
                if (!MainActivity.allPlayers.isEmpty()) {
                    for (Player player : MainActivity.allPlayers) {
                        if (player.getId() == id) {
                            id++;
                            disponible = false;
                        }
                    }
                }
            }
        }
        this.setId(id);
    }

    private void setUnusedColor(){
        boolean colorFind = false;
        int i = 0;
        while(!colorFind && i<MainActivity.listOfColor.size()){
            colorFind = true;
            System.out.println("boucle color");
            if(!(MainActivity.allPlayers == null || MainActivity.allPlayers.isEmpty()) ) {
                for (Player p : MainActivity.allPlayers) {
                    if (MainActivity.listOfColor.get(i) == p.getColor()) {
                        colorFind = false;
                    }
                }

                if(!colorFind) i++;
            }
        }
        if(colorFind) this.setColor(MainActivity.listOfColor.get(i));
        else {
            int a =(int) (Math.random() * ( MainActivity.listOfColor.size()) );
            this.setColor( MainActivity.listOfColor.get(a));
        }
    }

    public void incrementeManche(boolean win){
        this.nbTotalManche++;
        if(win) this.nbMancheWin++;
    }

    public void incrementePartie(boolean win){
        this.nbTotalPartie++;
        if(win) this.nbPartieWin++;
    }



}
