package com.salle.erikdavid.sallebooks.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.salle.erikdavid.sallebooks.Models.Book;
import com.salle.erikdavid.sallebooks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 11/03/2018.
 */

public class BookAdapter extends BaseAdapter {
    List<Book> books;
    Context context;
    LayoutInflater layoutInflater;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Book book = books.get(i);

        if (convertView == null){
            convertView= layoutInflater.inflate(R.layout.single_title_book, null);

            TextView title = convertView.findViewById(R.id.single_search_title);

            title.setText(book.getTitle());
        }

        return convertView;
    }
}
