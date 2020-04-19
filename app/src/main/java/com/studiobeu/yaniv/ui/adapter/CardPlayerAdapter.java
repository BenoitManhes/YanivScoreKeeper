package com.studiobeu.yaniv.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.custom.listener.AdapterCardListener;
import com.studiobeu.yaniv.ui.custom.view.CardPlayerView;

import java.util.ArrayList;

public class CardPlayerAdapter extends BaseAdapter {

    // All player
    private ArrayList< Player> mDataList = new ArrayList<>();

    // Player selected
    private ArrayList< Player> mPlSelected = new ArrayList<>();

    private Context mContext;
    private AdapterCardListener mListener;

    // Constructor
    public CardPlayerAdapter(Context c, AdapterCardListener listener, ArrayList<Player> dataList) {
        mContext = c;
        mListener = listener;
        setData(dataList);
    }

    public void setData(ArrayList<Player> dataList) {
        mDataList.clear();
        mDataList = (ArrayList<Player>) dataList.clone();
        mPlSelected.clear();
        notifyDataSetChanged();
    }

    public void deletePlayer(Player player) {
        mDataList.remove(player);
        mPlSelected.remove(player);
        notifyDataSetChanged();
    }

    public void addPlayer(Player newPlayer, boolean isSelected) {
        mDataList.add(newPlayer);
        if(isSelected) mPlSelected.add(newPlayer);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Player getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDataList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player item = mDataList.get(position);

        CardPlayerView view = new CardPlayerView(mContext, item);
        view.setSelect( mPlSelected.contains(item));

        // Single Click
        view.setOnClickListener(v -> {
            if(mPlSelected.contains(item)){
                mPlSelected.remove(item);
            }else{
                mPlSelected.add(item);
            }
            view.setSelect( mPlSelected.contains(item));
        });

        // Long Click
        view.setOnLongClickListener(v -> {
            mListener.onLongClikPlayer(item);
            return true;
        });

        return view;
    }

    public ArrayList<Player> getPlayerSelected(){
        return mPlSelected;
    }
}
