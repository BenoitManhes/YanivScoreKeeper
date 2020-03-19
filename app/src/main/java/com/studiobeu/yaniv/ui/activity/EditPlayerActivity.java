package com.studiobeu.yaniv.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.data.local.RoomService;
import com.studiobeu.yaniv.data.local.entity.Player;
import com.studiobeu.yaniv.ui.base.BaseActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EditPlayerActivity extends BaseActivity {

    @Inject
    RoomService mRoomService;

    @BindView(R.id.imageEditPlayer)
    ImageView profilImage;

    @BindView(R.id.imageColorEditPlayer)
    ImageView colorImage;

    @BindView(R.id.editTextEditPlayer)
    EditText editName;

    private ArrayList<Integer> listOfColor;
    private static final int SELECT_PICTURE = 0;
    private Player currentPlayer;
    private Bitmap bitmap;
    private int color;

    public static final String BUDDLE_EXTRA_PLAYER_CREATED = "player created";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        initPreSelection();
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
        mRoomService.getNewPlayerId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Long>() {
                    @Override
                    public void onSuccess(Long id) {
                        Log.d("ROOM","get id success");
                        createPlayer(id + 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ROOM","get id onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ROOM","get id complete");
                        createPlayer(0);
                    }

                });

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

    private void initPreSelection() {
        loadColor();
        pickRandomColor();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bob);
//        player.setAdressImage(bitmap);
        profilImage.setImageBitmap(bitmap);
        colorImage.setBackgroundColor(color);

    }

    @SuppressLint("CheckResult")
    private void createPlayer(long id) {

        currentPlayer = new Player(id);
        currentPlayer.setName(editName.getText().toString());
//                      player.setAdressImage(bitmap);
        currentPlayer.setColor(color);

        mRoomService.createPlayer(currentPlayer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {  // onComplete()
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(BUDDLE_EXTRA_PLAYER_CREATED,currentPlayer);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();

                }, throwable -> {   // onError()
                    Log.d("ROOM", "Error during player creation");// handle error
                    throwable.printStackTrace();
                });
    }

    private void loadColor(){
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

    private void pickRandomColor(){
        this.color = listOfColor.get( (int) (Math.random() * listOfColor.size()) );
    }

    private void changeColor(View view){

    }
}
