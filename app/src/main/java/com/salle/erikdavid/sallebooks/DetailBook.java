package com.salle.erikdavid.sallebooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DetailBook extends AppCompatActivity {

    private Book mBook;
    private Long bookId;
    private DetailBookView detailBookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        detailBookView = findViewById(R.id.detail_include);

        // Get the information of the last screen
        mBook = (Book) getIntent().getSerializableExtra(KeyConstants.BOOK_BUNDLE);
        bookId = getIntent().getLongExtra(KeyConstants.BOOK_BUNDLE_ID, 0);

        if (mBook != null) {
            detailBookView.assignValues(mBook.getTitle(), mBook.getAuthors(), mBook.getPublishedDate(), mBook.getDescription(), mBook.getImageURL());

        }
    }

    public void deleteBook(View view) {
        Book book = Book.findById(Book.class, bookId);
        book.delete();
        finish();
    }

}
