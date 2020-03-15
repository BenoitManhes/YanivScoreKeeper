package com.studiobeu.yaniv.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.List;

import com.google.gson.Gson;
import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.ui.base.BaseActivity;
import com.studiobeu.yaniv.ui.fragment.FragmentGrille;
import com.studiobeu.yaniv.ui.fragment.FragmentScore;
import com.studiobeu.yaniv.ui.fragment.FragmentStatistique;
import com.studiobeu.yaniv.model.Partie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity {

    @BindView(R.id.MyTabs)
    TabLayout MyTabs;

    @BindView(R.id.MyPage)
    ViewPager MyPage;

    public static Partie partie = new Partie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        MyTabs.setupWithViewPager(MyPage);
        SetUpViewPager(MyPage);

        // Recuperation du type de partie
        Bundle extra = getIntent().getExtras();
        int limite = extra.getInt(PlayerSelect.BUDDLE_EXTRA_LIMITE);
        boolean isNew = extra.getBoolean(PlayerSelect.BUDDLE_EXTRA_NEW);

        if(isNew) newPartie(limite);
        else resumePartie();
    }

    @Override
    public void onStop(){
        savePartie();
        super.onStop();
    }

    @OnClick(R.id.buttonYaniv)
    public void onYaniv(View view){
        Intent intent = new Intent(GameActivity.this, YanivActivity.class);
        startActivity(intent);
    }
    public void SetUpViewPager (ViewPager viewpage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

        Adapter.AddFragmentPage(new FragmentGrille(), "Grille");
        Adapter.AddFragmentPage(new FragmentScore(), "Score");
        Adapter.AddFragmentPage(new FragmentStatistique(), "Statistique");

        /*
        You can add more Fragment Adapter
        But the minimum of the ViewPager is 3 index Page
         */
        //We Need Fragment class now

        viewpage.setAdapter(Adapter);
        // Fragment 2 en fragment par default
        viewpage.setCurrentItem(1);

    }

    public void savePartie(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        sharedPreferences.edit()
                .putString("partie",gson.toJson(partie))
                .apply();
    }

    public void resumePartie(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("partie",null);

        partie = null;
        if(json != null)
            partie = gson.fromJson(json,Partie.class);
    }

    public void newPartie(int limite){
        // Creation de la partie
        partie = new Partie(PlayerSelect.playerSelected);
        if(limite==-1) partie.setInfiniteMode(true);
        else{
            partie.setInfiniteMode(false);
            partie.setLimiteScore(limite);
        }
    }

    //Custom Adapter Here
    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTittle = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        public void AddFragmentPage(Fragment Frag, String Title){
            MyFragment.add(Frag);
            MyPageTittle.add(Title);
        }


        @Override
        public Fragment getItem(int position) {
            return MyFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MyPageTittle.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
