package com.studiobeu.yaniv.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiobeu.yaniv.R;


public class FragmentGrille extends Fragment {
    //Constructor default
    public FragmentGrille(){

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageOne = inflater.inflate(R.layout.fragment_fragment_grille, container, false);

        //Every you want add some VIEW you need add findViewId with the Inflater

        //Example
        /*
        TextView ExTV = (TextView)PageOne.findViewById(R.id.Something ID)
         */

        return PageOne;
    }
}