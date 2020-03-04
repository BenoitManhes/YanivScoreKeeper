package com.studiobeu.yaniv.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.model.Player;

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPlayerActivity extends AppCompatActivity {

    @BindView(R.id.imageEditPlayer)
    ImageView profilImage;

    @BindView(R.id.imageColorEditPlayer)
    ImageView colorImage;

    @BindView(R.id.editTextEditPlayer)
    EditText editName;

    private static final int SELECT_PICTURE = 0;
    private Player player;
    private Bitmap bitmap;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        ButterKnife.bind(this);

        player = new Player();
        initPlayer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("result");
        //Detects request codes
        if(requestCode==SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            profilImage.setImageBitmap(bitmap);
        }
    }

    @OnClick(R.id.activity_edit_player_button_import)
    public void importImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), SELECT_PICTURE);
    }

    @OnClick(R.id.activity_edit_player_button_create)
    public void createPlayer(View view){
        player.setName(editName.getText().toString());
        player.setAdressImage(bitmap);
        player.setColor(color);
        if(!MainActivity.allPlayers.contains(player)){
            MainActivity.allPlayers.add(player);
        }
        finish();
    }

    @OnClick(R.id.activity_edit_player_button_cancel)
    public void cancel(View view){
        finish();
    }

    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }

    private void initPlayer() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bob);
        player.setAdressImage(bitmap);
        profilImage.setImageBitmap(bitmap);

        color = player.getColor();
        colorImage.setBackgroundColor(color);

    }

    private void changeColor(View view){

    }
}
