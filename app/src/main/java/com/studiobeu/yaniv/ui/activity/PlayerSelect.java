package com.studiobeu.yaniv.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.model.CartePlayer;
import com.studiobeu.yaniv.ui.adapter.CardPlayerAdapter;
import com.studiobeu.yaniv.ui.base.BaseActivity;
import com.studiobeu.yaniv.ui.contrat.AdapterCardListener;
import com.studiobeu.yaniv.ui.view.CardPlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class PlayerSelect extends BaseActivity implements AdapterCardListener {

    @Inject
    RoomService mRoomService;

    public static ArrayList<Player> playerSelected;

    @BindView(R.id.player_select_gridLayout)
    GridView mGridView;

    @BindView(R.id.switchMode)
    Switch mSwitch;

    @BindView(R.id.editLimite)
    EditText mEditText;

    @BindView(R.id.textLimite)
    TextView mTextView;

    Vector<CartePlayer> vectCarte;

    private CardPlayerAdapter mCardPlayerAdapter;
    private final ArrayList<Player> allPlayers = new ArrayList<Player>();

    public static final String BUDDLE_EXTRA_LIMITE = "limite";
    public static final String BUDDLE_EXTRA_NEW = "new game";
    private static final int LAUNCH_EDIT_PLAYER_ACTIVITY = 1;
    public static int LIMITE_MIN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_player_select);

        ButterKnife.bind(this);

        playerSelected = new ArrayList<>();
        initGridLayout();
        loadData();
    }

    @Override
    protected void onStart(){
        super.onStart();

        mSwitch.setChecked(false);
    }

    @OnClick(R.id.add)
    public void onClickAdd(View view){
        Intent intent = new Intent(PlayerSelect.this, EditPlayerActivity.class);
        startActivityForResult(intent, LAUNCH_EDIT_PLAYER_ACTIVITY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_EDIT_PLAYER_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                try {
                    Player newPLayer = (Player) data
                            .getSerializableExtra(EditPlayerActivity.BUDDLE_EXTRA_PLAYER_CREATED);
                    allPlayers.add(newPLayer);
                    mCardPlayerAdapter.addPlayer(newPLayer,true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClick(R.id.switchMode)
    public void onSwitch(View view){
        if (mSwitch.isChecked()){
            mTextView.setEnabled(false);
            mEditText.setEnabled(false);
        }else{
            mTextView.setEnabled(true);
            mEditText.setEnabled(true);
        }
    }

    @OnClick(R.id.start)
    public void onClickStart(View view){
        int limite = (int) Integer.parseInt(mEditText.getText().toString());
        if(limite<LIMITE_MIN && mSwitch.isChecked()){
            Toast.makeText(this, "Entrez un limite superieur à "+LIMITE_MIN, Toast.LENGTH_SHORT).show();
        }else{
        if(playerSelected.size()>1) {
            Intent intent = new Intent(PlayerSelect.this, GameActivity.class);
            if(mSwitch.isChecked()) intent.putExtra(BUDDLE_EXTRA_LIMITE,-1);
            else intent.putExtra(BUDDLE_EXTRA_LIMITE,limite);
            intent.putExtra(BUDDLE_EXTRA_NEW,true);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Selectioner au moins 2 joueurs : "+playerSelected.size(), Toast.LENGTH_SHORT).show();
        }}

    }

    private void initGridLayout(){
        mCardPlayerAdapter = new CardPlayerAdapter(this,this, allPlayers);
        mGridView.setAdapter(mCardPlayerAdapter);

    }

    /**
     * Load all player from database (Room)
     */
    private void loadData() {
            showLoadCircle();
            allPlayers.clear();

            mRoomService.getAllPlayer()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<List<Player>>() {
                        @Override
                        public void onSuccess(List<Player> players) {
                            for (Player p : players) {
                                if(p != null) {
                                    allPlayers.add(p);
                                }
                                mCardPlayerAdapter.setData(allPlayers);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            closeLoadCircle();
                        }
                    });

    }

    private boolean haveCard(Player player) {
        for (CartePlayer cp : vectCarte) {
            if ( cp.getPlayer().getId() == player.getId()) return true;
        }
        return false;
    }

    @Override
    public void onLongClikPlayer(Player player) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Supprimer le joueur ?")
                .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePlayer(player);
                    }
                })
                .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setCancelable(true)
                .create()
                .show();
    }

    /**
     * Delete a player from database
     * If success delete the corresponding card
     * @param player Player to delete
     */
    @SuppressLint("CheckResult")
    public void deletePlayer(Player player){
        mRoomService.deletePlayer(player)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {  // onComplete()
                    playerSelected.remove(player);
                    allPlayers.remove(player);
                    mCardPlayerAdapter.deletePlayer(player);
                }, throwable -> {   // onError()
                    Log.d("ROOM", "Error during player deletion");// handle error
                    throwable.printStackTrace();
                });
    }

    private void showLoadCircle(){
        Log.d("ROOM","Load player");
    }

    private void closeLoadCircle(){
        Log.d("ROOM","Load player finish");
    }

}
