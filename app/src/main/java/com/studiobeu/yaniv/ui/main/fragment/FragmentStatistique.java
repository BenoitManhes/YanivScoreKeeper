package com.studiobeu.yaniv.ui.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.ui.main.activity.GameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentStatistique extends Fragment {

    @BindView(R.id.graph)
    GraphView graph;
    //Constructor default
    public FragmentStatistique(){ };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistique, container, false);
        ButterKnife.bind(this,view);
        //Every you want add some VIEW you need add findViewId with the Inflater

        //Example
        /*
        TextView ExTV = (TextView)PageOne.findViewById(R.id.Something ID)
         */
        parametreGraph();
        refreshData();

        return view;
    }

    @Override
    public void onResume(){
        refreshData();
        super.onResume();
    }

    /** methode */

    private void refreshData(){
        graph.removeAllSeries();
        for (int i =0; i < GameActivity.partie.getDataGraph().size(); i++) {
            LineGraphSeries<DataPoint> serie = GameActivity.partie.getDataGraph().get(i);
            serie.setColor(GameActivity.partie.getListPlayers().get(i).getColor());
            serie.setTitle( GameActivity.partie.getListPlayers().get(i).getName());
            graph.addSeries(serie);
        }
        scableGraph();
    }

    private void scableGraph(){
        int max = GameActivity.partie.maxScore();
        int echelle = 1;
        int Y = 0;
        do{
            echelle*=10;
            Y = max/echelle;
            Y = (Y+1)*(echelle);
        }while(max>(5*echelle));

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(GameActivity.partie.getTableauScores().get(0).size()-1);

        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(Y);
    }

    private void parametreGraph(){
        graph.getGridLabelRenderer ().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer ().setHorizontalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer ().setVerticalLabelsColor(Color.WHITE);
        graph . getLegendRenderer ().setAlign(LegendRenderer.LegendAlign.BOTTOM);
        graph . getLegendRenderer ().setFixedPosition(0,0);
        graph . getLegendRenderer (). setVisible ( true );
        graph . getLegendRenderer ().setBackgroundColor(Color.TRANSPARENT);

    }
}