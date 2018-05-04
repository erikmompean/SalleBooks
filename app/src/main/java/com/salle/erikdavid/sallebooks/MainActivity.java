package com.salle.erikdavid.sallebooks;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.orm.SugarContext;
import com.salle.erikdavid.sallebooks.Models.Book;
import com.salle.erikdavid.sallebooks.Utils.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Book> mBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(getApplicationContext());

        // Configuring Tabs
        FloatingActionButton floatingButton = findViewById(R.id.main_add_book_button);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Readed"));
        tabLayout.addTab(tabLayout.newTab().setText("Wished"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Configuring ViewPager
        final ViewPager viewPager =  findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        floatingButton.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ArrayList<Book> books = (ArrayList<Book>) Book.listAll(Book.class);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.main_add_book_button){
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
        }
    }
}
