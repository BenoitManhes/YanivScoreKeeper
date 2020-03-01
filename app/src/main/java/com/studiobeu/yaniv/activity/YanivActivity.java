package com.studiobeu.yaniv.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.Parametre;
import com.studiobeu.yaniv.model.Player;

public class YanivActivity extends AppCompatActivity {

    TextView mTextYanivName, mTextYanivScore;
    ImageView mImageYaniv;
    EditText mEditText;

    private int indice;
    private int max;
    Player p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaniv);

        mTextYanivName = ((TextView) findViewById(R.id.textYanivName));
        mTextYanivScore = ((TextView) findViewById(R.id.textYanivScore));
        mImageYaniv = ((ImageView) findViewById(R.id.imageYanivPlayer));
        mEditText = ((EditText) findViewById(R.id.editTextYaniv));

        indice = 0;
        max = GameActivity.partie.getListPlayers().size();
        refreshData();
    }

    public void onClickInputScore(View view){
        try {
            int score = Integer.parseInt(mEditText.getText().toString());
            if (score<0) Toast.makeText(view.getContext(),"Entrez un nombre positif",Toast.LENGTH_SHORT).show();
            else enterScore(score);
        }catch (Exception e){
            Toast.makeText(view.getContext(),"Entrez un nombre valide",Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickYanivScore(View view){
        enterScore(0);
    }

    public void onClickAsafScore(View view){
        try {
            int score = Integer.parseInt(mEditText.getText().toString());
            if (score<0) Toast.makeText(view.getContext(),"Entrez un nombre positif",Toast.LENGTH_SHORT).show();
            else enterScore(score + Parametre.SCORE_ASAF);
        }catch (Exception e){
            Toast.makeText(view.getContext(),"Entrez un nombre valide",Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshData(){
        p = GameActivity.partie.getListPlayers().get(indice);
        mTextYanivName.setText(p.getName());
        mTextYanivScore.setText(p.getCurrentScore()+"");
        mImageYaniv.setImageBitmap(p.geticon());
        mEditText.setText("");
    }

    public void enterScore(int score){
        // stat
        if(score == 0) p.incrementeManche(true);
        else p.incrementeManche(false);

        //score
        int newScore = p.getCurrentScore() + score;

        // test de score down
        if (newScore == GameActivity.partie.getLimiteScore() && !GameActivity.partie.isInfiniteMode()){
            // partie fini
        }
        else if(newScore%Parametre.SCORE_DOWN == 0 && newScore>0) newScore-=Parametre.SCORE_DOWN;
        p.setCurrentScore(newScore);

        // joueur suivant
        indice++;
        if(indice<max) refreshData();
        else {
            // maj statistique
            GameActivity.partie.nextStep();
            finish();
        }
    }
}
