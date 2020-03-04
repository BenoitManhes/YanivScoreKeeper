package com.studiobeu.yaniv.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.Parametre;
import com.studiobeu.yaniv.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YanivActivity extends AppCompatActivity {

    @BindView(R.id.textYanivName)
    TextView mTextYanivName;

    @BindView(R.id.textYanivScore)
    TextView mTextYanivScore;

    @BindView(R.id.imageYanivPlayer)
    ImageView mImageYaniv;

    @BindView(R.id.editTextYaniv)
    EditText mEditText;

    private int indice;
    private int max;
    Player p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaniv);
        ButterKnife.bind(this);

        indice = 0;
        max = GameActivity.partie.getListPlayers().size();
        refreshData();
    }

    @OnClick(R.id.activityYanivBtInput)
    public void inputScore(View view){
        try {
            int score = Integer.parseInt(mEditText.getText().toString());
            if (score<0) Toast.makeText(view.getContext(),"Entrez un nombre positif",Toast.LENGTH_SHORT).show();
            else enterScore(score);
        }catch (Exception e){
            Toast.makeText(view.getContext(),"Entrez un nombre valide",Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.activityYanivBtYaniv)
    public void yaniv(View view){
        enterScore(0);
    }

    @OnClick(R.id.activityYanivBtAsaf)
    public void asafScore(View view){
        try {
            int score = Integer.parseInt(mEditText.getText().toString());
            if (score<0) Toast.makeText(view.getContext(),"Entrez un nombre positif",Toast.LENGTH_SHORT).show();
            else enterScore(score + Parametre.SCORE_ASAF);
        }catch (Exception e){
            Toast.makeText(view.getContext(),"Entrez un nombre valide",Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshData(){
        p = GameActivity.partie.getListPlayers().get(indice);
        mTextYanivName.setText(p.getName());
        mTextYanivScore.setText(p.getCurrentScore()+"");
        mImageYaniv.setImageBitmap(p.geticon());
        mEditText.setText("");
    }

    private void enterScore(int score){
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
