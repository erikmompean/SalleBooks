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
    private ImageView mImage;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mReleaseDate;
    private TextView mDescription;
    private JsonBook mJsonBook;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to);

        mImage = findViewById(R.id.detail_image);
        mTitle = findViewById(R.id.detail_title);
        mAuthor = findViewById(R.id.detail_author);
        mReleaseDate = findViewById(R.id.detail_release_date);
        mDescription = findViewById(R.id.detail_description);

        // Get the information of the last screen
        mJsonBook = (JsonBook) getIntent().getSerializableExtra(KeyConstants.BOOK_BUNDLE);

        mBook = mJsonBook.toBook();

        if(mJsonBook != null){

            mTitle.setText(mJsonBook.getVolumeInfo().getTitle());
            mAuthor.setText(mJsonBook.getVolumeInfo().getAuthors());
            mReleaseDate.setText(mJsonBook.getVolumeInfo().getPublishedDate());
            mDescription.setText(mJsonBook.getVolumeInfo().getDescription());
            String bookImage = mJsonBook.getVolumeInfo().getImageLinks().getSmallThumbnail();
            if (bookImage != null){
                new DownLoadImageTask(mImage).execute(bookImage);
            }

        }

    }

    private void saveAndMain(){
        User user = UserSingleton.getInstance();
        mBook.setEmailSaved(user.getEmail());
        mBook.save();
        Toast.makeText(this, "Guardado Correctamente", Toast.LENGTH_SHORT).show();
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

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
