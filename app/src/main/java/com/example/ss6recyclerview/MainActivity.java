package com.example.ss6recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ss6recyclerview.adapter.ContactAdapter;
import com.example.ss6recyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.IOnClickAdapter {

    List<Contact> listData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //b1:data Sourse
        initData();
        //b2:Apdapter
        ContactAdapter adapter = new ContactAdapter(this, listData);
        //b7:Start register event(onclick)
        adapter.registerClick(this);
        //b3:layoutManager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        //b4:RecyclerView
        RecyclerView rvContact = findViewById(R.id.rvContact);
        rvContact.setLayoutManager(layoutManager);


    }

    private void  initData(){
        //seeding data
        listData.add(new Contact("Nguyen Van Anh","09876543",R.drawable.user1));
        listData.add(new Contact("Nguyen Van Anh","09876543",R.drawable.user1));
        listData.add(new Contact("Nguyen Van Anh","09876543",R.drawable.user1));
        listData.add(new Contact("Nguyen Van Anh","09876543",R.drawable.user1));
        listData.add(new Contact("Nguyen Van Anh","09876543",R.drawable.user1));


    }

    private void gotoDetail(String name){
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("NAME",name);
        startActivity(intent);
    }
    //B6.1:
    @Override
    public void onClickItem(int position) {
        Contact model = listData.get(position);
        gotoDetail(model.getName());
    }
    //b6.2:
    @Override
    public void onClickEdit(int position) {
        Contact model = listData.get(position);
        gotoDetail(model.getName());
    }
}
