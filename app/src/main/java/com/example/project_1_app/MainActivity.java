package com.example.project_1_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.project_1_app.ui.main.PhoneBook;
import com.example.project_1_app.ui.main.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.example.project_1_app.ui.main.SectionsPagerAdapter;
import com.example.project_1_app.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TabListener tabListener;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    public ArrayList<PhoneBook> mPhonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
/*        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        tabListener = new TabListener(this);
        tabs.addOnTabSelectedListener(tabListener);

        mPhonebook = new ArrayList<PhoneBook>();
    }

    public class TabListener implements TabLayout.OnTabSelectedListener{
        private MainActivity activity;

        public TabListener(MainActivity main){
            activity = main;
        }
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if(tab.getPosition() == 0){
                recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                recyclerAdapter = new RecyclerAdapter();
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));

                for(int i=1;i<=10;i++){
                    activity.mPhonebook.add(new PhoneBook(i+"번째 사람","000-0000-000"+i));
                }
                recyclerAdapter.setMyPhoneBook(activity.mPhonebook);
/*                TextView nameView = (TextView) findViewById(R.id.result_name);
                TextView phoneView = (TextView) findViewById(R.id.result_phone);

                DBHelper helper = new DBHelper(activity);
                SQLiteDatabase db = helper.getWritableDatabase();

                Cursor cursor = db.rawQuery("select name, phone from tb_contact order by _id desc limit 1",null);
                while(cursor.moveToNext()){
                    nameView.setText(cursor.getString(0));
                    phoneView.setText(cursor.getString(1));
                }
                db.close();*/
            }
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}
        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    }
}