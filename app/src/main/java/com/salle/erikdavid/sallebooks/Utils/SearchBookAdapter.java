package com.salle.erikdavid.sallebooks.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salle.erikdavid.sallebooks.Models.JsonBook;
import com.salle.erikdavid.sallebooks.R;

import java.util.List;

/**
 * Created by Erik on 10/03/2018.
 */

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.MyViewHolder> {
    private List<JsonBook> books;
    private Context mContext;
    private final OnItemClickListener listener;

    public SearchBookAdapter(Context context, List<JsonBook> books, final OnItemClickListener listener) {
        mContext = context;
        this.books = books;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(JsonBook book);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.single_search_title);
        }

        public void bind(final JsonBook book, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_title_book, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JsonBook book = books.get(position);
        holder.title.setText(book.getVolumeInfo().getTitle());
        holder.bind(books.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
