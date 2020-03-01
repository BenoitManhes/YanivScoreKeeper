package com.studiobeu.yaniv.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.CartePlayer;
import com.studiobeu.yaniv.model.Player;

import java.util.ArrayList;
import java.util.Vector;

import static java.lang.System.out;

public class PlayerSelect extends AppCompatActivity {

    public static ArrayList<Player> playerSelected;
    GridLayout mGridLayout;
    Vector<CartePlayer> vectCarte;

    private Switch mSwitch;
    private EditText mEditText;
    private TextView mTextView;

    public static String BUDDLE_EXTRA_LIMITE = "limite";
    public static String BUDDLE_EXTRA_NEW = "new game";
    public static  int LIMITE_MIN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        playerSelected = new ArrayList<>();

        mGridLayout = ((GridLayout) findViewById(R.id.gridLayout));

        mSwitch = (Switch) findViewById(R.id.switchMode);
        mEditText = (EditText) findViewById(R.id.editLimite);
        mTextView = (TextView) findViewById(R.id.textLimite);

        mSwitch.setChecked(false);

        initGridLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkNewPlayer();
    }



    public void onClickSwitch(View view){
        if (mSwitch.isChecked()){
            mTextView.setEnabled(false);
            mEditText.setEnabled(false);
        }else{
            mTextView.setEnabled(true);
            mEditText.setEnabled(true);
        }
    }
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

    public void onClickAdd(View view){
        Intent intent = new Intent(PlayerSelect.this, EditPlayerActivity.class);
        startActivity(intent);
        /*
        AlertDialog.Builder alert = new AlertDialog.Builder(PlayerSelect.this);
        View v = PlayerSelect.this.getLayoutInflater().inflate(R.layout.dialog_add_player, null);
        alert.setView(v);
        final EditText noms = (EditText)v.findViewById(R.id.enterNoms);

        alert.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Player p = new Player();
                p.setName(noms.getText().toString());
                System.out.println(noms.getText().toString());
                MainActivity.allPlayers.add(p);
                addCarte(p,true);
            }
        });

        alert.show();*/
    }

    public void initGridLayout(){
        vectCarte = new Vector<>();

        for(final Player player : MainActivity.allPlayers) {
            addCarte(player, false);
        }
    }

    public void addCarte(Player player, boolean select){
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
                                MainActivity.allPlayers.remove(  carte.getPlayer() );
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
        if(vectCarte.size() != MainActivity.allPlayers.size()) {
            for (Player player : MainActivity.allPlayers) {
                if ( !haveCard(player) ) addCarte(player,true);
            }
        }
    }

    private boolean haveCard(Player player) {
        for (CartePlayer cp : vectCarte) {
            if ( cp.getPlayer() == player) return true;
        }
        return false;
    }
}
