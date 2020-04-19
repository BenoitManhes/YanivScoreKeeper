package com.studiobeu.yaniv.ui.main.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.main.activity.GameActivity;
import com.studiobeu.yaniv.ui.adapter.ScoreAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentScore extends Fragment {

    @BindView(R.id.listScore)
    RecyclerView rv;

    ScoreAdapter mScoreAdapter;
    ArrayList<Player> list;
    ArrayList<ArrayList<Integer>> tableau;
    //private OnFragmentInteractionListener mListener;

    public FragmentScore(){    }

    public static FragmentScore newInstance() {
        FragmentScore fragment = new FragmentScore();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_score, container, false);
        ButterKnife.bind(this,view);

        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        loadPlayer();
        loadScore();

        mScoreAdapter = new ScoreAdapter(list,tableau);
        rv.setAdapter(mScoreAdapter);
        return view;
    }

    @Override
    public void onResume(){
        mScoreAdapter.notif();
        super.onResume();
    }

    public void loadPlayer(){
        this.list = GameActivity.partie.getListPlayers();
    }

    public void loadScore(){
        this.tableau = GameActivity.partie.getTableauScores();
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}