package com.salle.erikdavid.sallebooks;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;


public class DetailBookView extends RelativeLayout {

    private Context context;
    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView releaseDate;
    private TextView description;

    public DetailBookView(Context context) {
        super(context);
        init(null, 0);

    }

    public DetailBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs, 0);
    }

    public DetailBookView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            inflater.inflate(R.layout.book_detail_frame,this);
        } catch (NullPointerException e) {
            Log.e("DetailBookView INIT", e.getMessage());
        }

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DetailBookView, defStyle, 0);


        a.recycle();

    }

    /**
     * Search the components in the view.
     */
    private void assignComponent() {
        image = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        author = findViewById(R.id.detail_author);
        releaseDate = findViewById(R.id.detail_release_date);
        description = findViewById(R.id.detail_description);
    }

    public void assignValues(String title, String authors, String releaseDate,String description, String imageURL){
        this.title.setText(title);
        this.author.setText(authors);
        this.releaseDate.setText(releaseDate);
        this.description.setText(description);
        if (image != null){
            new DownLoadImageTask(image).execute(imageURL);
        }
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
