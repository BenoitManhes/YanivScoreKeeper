package com.studiobeu.yaniv.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.model.CartePlayer;
import com.studiobeu.yaniv.ui.base.BaseActivity;

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

public class PlayerSelect extends BaseActivity {

    @Inject
    RoomService mRoomService;

    public static ArrayList<Player> playerSelected;

    @BindView(R.id.gridLayout)
    GridLayout mGridLayout;

    @BindView(R.id.switchMode)
    Switch mSwitch;

    @BindView(R.id.editLimite)
    EditText mEditText;

    @BindView(R.id.textLimite)
    TextView mTextView;

    Vector<CartePlayer> vectCarte;

    public static final String BUDDLE_EXTRA_LIMITE = "limite";
    public static final String BUDDLE_EXTRA_NEW = "new game";
    public static int LIMITE_MIN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_player_select);

        ButterKnife.bind(this);

        playerSelected = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();

        mSwitch.setChecked(false);
        initGridLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(mPlayerDao.getAll() != null){
//            checkNewPlayer();
//        }
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

    @OnClick(R.id.add)
    public void onClickAdd(View view){
        Intent intent = new Intent(PlayerSelect.this, EditPlayerActivity.class);
        startActivity(intent);
    }

    private void initGridLayout(){
        vectCarte = new Vector<>();
        loadData();
    }

    private void loadData() {
            showLoadCircle();

            mRoomService.getAllPlayer()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<List<Player>>() {
                        @Override
                        public void onSuccess(List<Player> players) {
                            for (Player p : players) {
                                if(p != null) {
                                    addCarte(p, false);
                                }
                            }
                            closeLoadCircle();
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

    private void addCarte(Player player, boolean select){
        final View v = PlayerSelect.this.getLayoutInflater().inflate(R.layout.carte_player, null);
        v.setLayoutParams(new ViewGroup.LayoutParams(200, 300));

        TextView nom = ((TextView) v.findViewById(R.id.carte_textView));
        ImageView fond = ((ImageView) v.findViewById(R.id.fond_carte));
        ImageView profil = ((ImageView) v.findViewById(R.id.image_carte));
        nom.setText(player.getName());

        final CartePlayer carte = new CartePlayer(player, fond, profil );
        if(select) carte.click();
        vectCarte.add(carte);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carte.click();

            }
        });

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Context context = view.getContext();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Supprimer le joueur ?")
                        .setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // End the activity
//                                MainActivity.allPlayers.remove(  carte.getPlayer() );
                                playerSelected.remove(carte.getPlayer());
                                vectCarte.removeElement(carte);
                                mGridLayout.removeView(v);
                            }
                        })
                        .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }
                            })
                        .setCancelable(true)
                        .create()
                        .show();
            return true;
            }
        });
        mGridLayout.addView(v,mGridLayout.getChildCount());
    }

    private void checkNewPlayer() {
//        if(vectCarte.size() != mPlayerDao.getAll().size()) {
//            for (Player player : mPlayerDao.getAll()) {
//                if ( !haveCard(player) ) addCarte(player,true);
//            }
//        }
    }

    private boolean haveCard(Player player) {
        for (CartePlayer cp : vectCarte) {
            if ( cp.getPlayer().getId() == player.getId()) return true;
        }
        return false;
    }

    private void showLoadCircle(){
        Log.d("ROOM","Load player");
    }

    private void closeLoadCircle(){
        Log.d("ROOM","Load player finish");
    }
}
