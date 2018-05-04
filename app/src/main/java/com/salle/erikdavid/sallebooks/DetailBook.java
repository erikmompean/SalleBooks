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

    private ImageView mImage;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mReleaseDate;
    private TextView mDescription;
    private Book mBook;
    private Long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        mImage = findViewById(R.id.detail_image);
        mTitle = findViewById(R.id.detail_title);
        mAuthor = findViewById(R.id.detail_author);
        mReleaseDate = findViewById(R.id.detail_release_date);
        mDescription = findViewById(R.id.detail_description);

        // Get the information of the last screen
        mBook = (Book) getIntent().getSerializableExtra(KeyConstants.BOOK_BUNDLE);
        bookId = getIntent().getLongExtra(KeyConstants.BOOK_BUNDLE_ID, 0);

        if(mBook != null){

            mTitle.setText(mBook.getTitle());
            mAuthor.setText(mBook.getAuthors());
            mReleaseDate.setText(mBook.getPublishedDate());
            mDescription.setText(mBook.getDescription());
            String bookImage = mBook.getImageURL();
            if (bookImage != null){
                new DetailBook.DownLoadImageTask(mImage).execute(bookImage);
            }

        }

    }

    public void deleteBook(View view) {
        Book book = Book.findById(Book.class, bookId);
        book.delete();
        finish();
    }


    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
