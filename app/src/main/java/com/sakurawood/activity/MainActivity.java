package com.sakurawood.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.sakurawood.rcvbaseadapter.BaseViewHolder;
import com.sakurawood.rcvbaseadapter.RcvBaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Bean> list;
    RecyclerView recyclerView;

    RcvBaseAdapter RcvBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.list);


        init();

        RcvBaseAdapter=new RcvBaseAdapter<Bean>(getApplicationContext(), R.layout.item,list) {
            @Override
            protected void convert(BaseViewHolder holder, Bean item) {
                holder.setText(R.id.name,item.getName());
                holder.setText(R.id.value,item.getValue());
                holder.setImageResource(R.id.img,item.getPic());
            }

        };
        RcvBaseAdapter.setOnRecyclerViewItemClickListener(new RcvBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "haha"+"   "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(View view, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(RcvBaseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void init(){
        list=new ArrayList<>();
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
        list.add(new Bean("sakura","wood", R.drawable.sakura));
    }
}
