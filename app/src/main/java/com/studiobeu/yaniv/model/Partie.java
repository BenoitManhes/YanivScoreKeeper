package com.studiobeu.yaniv.model;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.studiobeu.yaniv.data.local.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Partie {

    private ArrayList<Player> listPlayers;
    private ArrayList<ArrayList<Integer>> tableauScores;
    private boolean infiniteMode;
    private int limiteScore;

    /** getter & setters */
    public ArrayList<Player> getListPlayers() {return listPlayers; }

    public ArrayList<ArrayList<Integer>> getTableauScores() {return tableauScores; }

    public boolean isInfiniteMode() {
        return infiniteMode;
    }

    public void setInfiniteMode(boolean infiniteMode) {
        this.infiniteMode = infiniteMode;
    }

    public int getLimiteScore() {
        return limiteScore;
    }

    public void setLimiteScore(int limiteScore) {
        this.limiteScore = limiteScore;
    }


    public Partie(){
        listPlayers = new ArrayList<>();
        tableauScores = new ArrayList<>();
        infiniteMode = false;
        limiteScore = Parametre.LIMITE_DEFAULT;
    }

    public Partie(ArrayList<Player> listOfPlayer){
        listPlayers = listOfPlayer;
        tableauScores = new ArrayList<>();
        for (int i = 0; i < listOfPlayer.size(); i++) {
            this.tableauScores.add(new ArrayList<Integer>());
        }
        start();
    }

    /** moteur de jeu */
    public void start(){
        for (Player p: listPlayers) {
            p.setCurrentScore(0);
        }
        for (ArrayList list: tableauScores ) {
            list.clear();
            list.add(0);
        }
    }

    public boolean gameIsOver(){
        if (this.infiniteMode) return false;

        boolean over = false;
        for (Player p: this.listPlayers) {
            if(p.getCurrentScore() >= this.limiteScore) over = true;
        }
        
        return over;
    }
    
    public void nextStep(){
        for (int i = 0; i < tableauScores.size(); i++) {
            tableauScores.get(i).add(listPlayers.get(i).getCurrentScore());
        }
    }

    public ArrayList<LineGraphSeries<DataPoint>> getDataGraph(){
        ArrayList<LineGraphSeries<DataPoint>> listSeries = new ArrayList<>();

        for (ArrayList<Integer> list :tableauScores) {


            DataPoint[] dataPoints = new DataPoint[list.size()]; // declare an array of DataPoint objects with the same size as your list
            for (int i = 0; i < list.size(); i++) {
                // add new DataPoint object to the array for each of your list entries
                dataPoints[i] = new DataPoint(i, list.get(i)); // not sure but I think the second argument should be of type double
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
            listSeries.add(series);
        }

        return listSeries;
    }

    public int maxScore(){
        ArrayList<Integer> maxs = new ArrayList<>();
        for (ArrayList<Integer> list : this.tableauScores ) {
            maxs.add(Collections.max(list));
        }
        return Collections.max(maxs);
    }

}
