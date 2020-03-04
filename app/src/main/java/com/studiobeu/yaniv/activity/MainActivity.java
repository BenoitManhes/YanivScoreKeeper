package com.studiobeu.yaniv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.Player;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiobeu.yaniv.activity.PlayerSelect.BUDDLE_EXTRA_NEW;

public class MainActivity extends AppCompatActivity {

    public static String dataGame;
    public static ArrayList<Player> allPlayers;
    public static ArrayList<Integer> listOfColor;

    private SharedPreferences mPreferences;
    private static String PREF_MAIN_PREFERENCES = "main preferences";
    public static String PREF_KEY_LISTPLAYERS = "list all players";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPreferences = getSharedPreferences(PREF_MAIN_PREFERENCES, Context.MODE_PRIVATE);
        initColor();
        loadAllPlayers();
        //testInit();
    }

    @Override
    public void onStop(){
        saveAllPLayers();
        super.onStop();
    }

    @OnClick(R.id.newGame)
    public void newGame(View view){
        Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
        startActivity(intent);
    }

    @OnClick(R.id.resume)
    public void resumeLastGame(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra(BUDDLE_EXTRA_NEW,false);
        startActivity(intent);
    }

    @OnClick(R.id.about)
    public void showAbout(View view){

    }

    private void saveAllPLayers(){
        Gson gson = new Gson();
        mPreferences.edit()
                .putString(PREF_KEY_LISTPLAYERS,gson.toJson(allPlayers))
                .apply();
    }

    private void loadAllPlayers(){
        allPlayers = new ArrayList<>();
        Gson gson = new Gson();

        String json = mPreferences.getString(PREF_KEY_LISTPLAYERS,null);

        if(json != null)
            allPlayers = gson.fromJson(json,new TypeToken<ArrayList<Player>>() {}.getType());
        if(allPlayers == null) allPlayers = new ArrayList<>();
    }

    private void initColor(){
        listOfColor = new ArrayList<>();
        listOfColor.add(Color.parseColor("#08589d"));
        listOfColor.add(Color.parseColor("#d90f17"));
        listOfColor.add(Color.parseColor("#fae91f"));
        listOfColor.add(Color.parseColor("#61ae35"));
        listOfColor.add(Color.parseColor("#e6730b"));
        listOfColor.add(Color.parseColor("#78217f"));
        listOfColor.add(Color.parseColor("#000000"));
        listOfColor.add(Color.parseColor("#e20a7c"));
        listOfColor.add(Color.parseColor("#2f2e7c"));
        listOfColor.add(Color.parseColor("#019542"));
    }
}
