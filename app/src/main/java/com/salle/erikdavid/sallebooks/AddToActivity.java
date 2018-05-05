package com.salle.erikdavid.sallebooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salle.erikdavid.sallebooks.Models.Book;
import com.salle.erikdavid.sallebooks.Models.JsonBook;
import com.salle.erikdavid.sallebooks.Models.User;
import com.salle.erikdavid.sallebooks.Utils.KeyConstants;

import java.io.InputStream;
import java.net.URL;

public class AddToActivity extends AppCompatActivity {
    private DetailBookView detailBookView;
    private JsonBook mJsonBook;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to);

        detailBookView = findViewById(R.id.detail_book_view);

        // Get the information of the last screen
        mJsonBook = (JsonBook) getIntent().getSerializableExtra(KeyConstants.BOOK_BUNDLE);

        mBook = mJsonBook.toBook();

        if(mJsonBook != null){

            String bookImage = mJsonBook.getVolumeInfo().getImageLinks().getSmallThumbnail();
            detailBookView.assignValues(mJsonBook.getVolumeInfo().getTitle(), mJsonBook.getVolumeInfo().getAuthors(), mJsonBook.getVolumeInfo().getPublishedDate(), mJsonBook.getVolumeInfo().getDescription(), bookImage);
        }

    }

    private void saveAndMain(){
        User user = UserSingleton.getInstance();
        mBook.setEmailSaved(user.getEmail());
        mBook.save();
        Toast.makeText(this, R.string.well_saved, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AddToActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void saveReaded(View view) {
        mBook.setType(Book.READED);
        saveAndMain();
    }

    public void saveWished(View view) {
        mBook.setType(Book.WISHED);
        saveAndMain();
    }



}
