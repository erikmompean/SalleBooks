package com.salle.erikdavid.sallebooks.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.salle.erikdavid.sallebooks.DetailBook;
import com.salle.erikdavid.sallebooks.Models.Book;
import com.salle.erikdavid.sallebooks.Models.User;
import com.salle.erikdavid.sallebooks.R;
import com.salle.erikdavid.sallebooks.UserSingleton;
import com.salle.erikdavid.sallebooks.Utils.BookAdapter;
import com.salle.erikdavid.sallebooks.Utils.KeyConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WishBooksFragment extends Fragment {

    List<Book> books = new ArrayList<>();
    BookAdapter adapter;
    User user = UserSingleton.getInstance();

    public WishBooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_books, container, false);
        user = UserSingleton.getInstance();
        ListView readedListView = view.findViewById(R.id.wish_list_view);
        adapter = new BookAdapter(getContext(), books);
        readedListView.setAdapter(adapter);
        readedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = books.get(i);
                Intent intent = new Intent(getContext(), DetailBook.class);
                intent.putExtra(KeyConstants.BOOK_BUNDLE, book);
                intent.putExtra(KeyConstants.BOOK_BUNDLE_ID, book.getId());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        books.clear();
        books.addAll(Book.findWithQuery(Book.class, "Select * from Book where email_Saved = ? AND type = ?", user.getEmail(), "1"));
        for (Book book : books) {
            Log.e("lol", book.toString());
        }
        adapter.notifyDataSetChanged();
    }


}
