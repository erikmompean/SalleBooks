package com.salle.erikdavid.sallebooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salle.erikdavid.sallebooks.Models.JsonBook;
import com.salle.erikdavid.sallebooks.Utils.KeyConstants;
import com.salle.erikdavid.sallebooks.Utils.SearchBookAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

public class SearchActivity extends AppCompatActivity {
    EditText mSearchEdit;
    RecyclerView mRecyclerView;
    LayoutManager mLayoutManager;
    SearchBookAdapter mAdapter;
    ArrayList<JsonBook> books;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        books = new ArrayList<>();

        mSearchEdit = findViewById(R.id.search_edit_search);
        mRecyclerView = findViewById(R.id.search_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchBookAdapter(this, books, new SearchBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(JsonBook book) {
                Intent i = new Intent(SearchActivity.this, AddToActivity.class);
                i.putExtra(KeyConstants.BOOK_BUNDLE, book);
                startActivity(i);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);



        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getBooks(editable.toString());
            }
        });
    }

    private void getBooks(String userInput) {
        if (!userInput.equals("")) {
            // Create the url
            String completeURL = KeyConstants.GOOGLE_BOOKS_URL + userInput.replace(" ", "+");

            // Call url query
            StringRequest stringRequest = new StringRequest(Request.Method.GET, completeURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Clear the array search
                            books.clear();
                            try {
                                // Parse to get the books and fill the list
                                parseJsonGetBooks(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();
                            if(error.getMessage() != null){
                                Log.e("Volley Error", error.getMessage());
                            }

                            if(error.getCause().getMessage() != null){
                                Log.e("Volley Error", error.getCause().getMessage());
                            }
                        }
                    }) {

            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }

    }

    public void parseJsonGetBooks(String response) throws JSONException {


        JSONObject mainObject = new JSONObject(response);
        JSONArray resultBooks = mainObject.getJSONArray("items");
        for(int i = 0; i< resultBooks.length(); i++){
            JSONObject value = resultBooks.getJSONObject(i);

            JsonBook book = gson.fromJson(String.valueOf(value), JsonBook.class);

            books.add(book);

        }
        mAdapter.notifyDataSetChanged();

    }


}
