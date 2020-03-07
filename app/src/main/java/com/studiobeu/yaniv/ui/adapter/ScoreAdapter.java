package com.studiobeu.yaniv.ui.adapter;

import android.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.Parametre;
import com.studiobeu.yaniv.model.Player;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder> {

     ArrayList<Player> PlayerList;
     ArrayList<ArrayList<Integer>> TableauScore;

    public ScoreAdapter (ArrayList<Player> argList, ArrayList<ArrayList<Integer>> argScore){
        this.PlayerList = argList;
        this.TableauScore = argScore;
    }

    @Override
    public int getItemCount() {
        return PlayerList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_scrore_player, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlayerList.get(position).setId(position);
        holder.display(PlayerList.get(position));
    }

    public void add(Player p) {
        PlayerList.add(p);
        this.notifyDataSetChanged();
    }

    public void notif(){
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView score;
        private final ImageView image;
        private final Button fButoon;
        private Player player;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            image = ((ImageView) itemView.findViewById(R.id.imageCell));
            name = ((TextView) itemView.findViewById(R.id.scoreName));
            score = ((TextView) itemView.findViewById(R.id.scoreText));
            fButoon = ((Button) itemView.findViewById(R.id.buttonFix));

            fButoon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    final AlertDialog alert = builder.create();
                    View v = view.inflate(view.getContext(), R.layout.edit_score_dialog,null);
                    alert.setView(v);

                    final EditText editScore = (EditText) v.findViewById(R.id.editScore);
                    Button btnPlus = (Button) v.findViewById(R.id.buttonPlus);
                    Button btnMoins = (Button) v.findViewById(R.id.buttonMoins);

                    btnPlus.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view1) {
                            try {
                                int s = Integer.parseInt(editScore.getText().toString());
                                int ps = player.getCurrentScore();
                                if (s>0 && s<=ps) player.setCurrentScore(ps+s);
                                if(player.getCurrentScore()% Parametre.SCORE_DOWN == 0 && player.getCurrentScore()>0){
                                    player.setCurrentScore(player.getCurrentScore()-Parametre.SCORE_DOWN);
                                }
                                score.setText(player.getCurrentScore()+"");
                                alert.cancel();
                            }catch (Exception e){ }
                        }
                    });

                    btnMoins.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view1) {
                            try {
                                int s = Integer.parseInt(editScore.getText().toString());
                                if (s>0 && s+player.getCurrentScore()>=0) player.setCurrentScore(player.getCurrentScore()-s);
                                if(player.getCurrentScore()% Parametre.SCORE_DOWN == 0 && player.getCurrentScore()>0){
                                    player.setCurrentScore(player.getCurrentScore()-Parametre.SCORE_DOWN);
                                }
                                score.setText(player.getCurrentScore()+"");
                                alert.cancel();
                            }catch (Exception e){ }
                        }
                    });
                    alert.show();
                }
            });
        }

        public void display(Player p) {
            name.setText(p.getName());
            int indice = PlayerList.indexOf(p);
            player = p;
            score.setText(p.getCurrentScore()+"" );
            image.setImageBitmap(p.geticon());
        }



    }

}
