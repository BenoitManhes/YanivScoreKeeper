package com.studiobeu.yaniv.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.data.local.dao.PlayerDao;
import com.studiobeu.yaniv.data.local.database.AppDataBase;
import com.studiobeu.yaniv.ui.base.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiobeu.yaniv.ui.activity.PlayerSelect.BUDDLE_EXTRA_NEW;

public class MainActivity extends BaseActivity {

    private static String PREF_MAIN_PREFERENCES = "main preferences";
    public static String PREF_KEY_LISTPLAYERS = "list all players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadAllPlayers();
        //testInit();

    }

    @Override
    public void onStop(){
        saveAllPLayers();
        super.onStop();
    }

    @OnClick(R.id.home_menu_card_new)
    public void newGame(View view){
        Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
        startActivity(intent);
    }

    @OnClick(R.id.home_menu_card_resume)
    public void resumeLastGame(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra(BUDDLE_EXTRA_NEW,false);
        startActivity(intent);
    }

    @OnClick(R.id.home_menu_card_rules)
    public void showAbout(View view){

    }

    private void saveAllPLayers(){
//        Gson gson = new Gson();
//        preferenceService.edit()
//                .putString(PREF_KEY_LISTPLAYERS,gson.toJson(allPlayers))
//                .apply();
    }

    private void loadAllPlayers(){
//        Gson gson = new Gson();
//
//        String json = preferenceService.getString(PREF_KEY_LISTPLAYERS,null);
//
//        if(json != null)
//            allPlayers = gson.fromJson(json,new TypeToken<ArrayList<Player>>() {}.getType());
//        if(allPlayers == null) allPlayers = new ArrayList<>();
    }
}
