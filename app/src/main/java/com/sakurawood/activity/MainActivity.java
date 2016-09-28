package com.sakurawood.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.sakurawood.rcvbaseadapter.base.BaseViewHolder;
import com.sakurawood.rcvbaseadapter.base.RcvBaseAdapter;
import com.sakurawood.rcvbaseadapter.callback.DragSwipeCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Bean> list;
    RecyclerView recyclerView;

    RcvBaseAdapter RcvBaseAdapter;
    DragSwipeCallback dscb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);

        init();

        RcvBaseAdapter = new RcvBaseAdapter<Bean>(getApplicationContext(), R.layout.item, list) {

            @Override
            protected void convert(BaseViewHolder holder, Bean item, int positon) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.value, item.getValue());
                holder.setImageResource(R.id.img, item.getPic());
                holder.setOnClickListener(R.id.img, new RcvBaseAdapter.OnItemChildClickListener());
            }

        };
        RcvBaseAdapter.setOnRecyclerViewItemClickListener(new RcvBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Object position) {
                Toast.makeText(MainActivity.this, "haha" + "   " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(View view, Object position) {
                return false;
            }
        });
        RcvBaseAdapter.setOnRecyclerViewChildItemClickListener(new RcvBaseAdapter.OnRecyclerViewChildItemClickListener() {
            @Override
            public void onChildClick(View view, Object position) {
                switch (view.getId()) {
                    case R.id.img:
                        Toast.makeText(MainActivity.this,
                                position + "    " + view.getId(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public boolean onChildLongClick(View view, Object position) {
                return false;
            }
        });
        dscb = new DragSwipeCallback(RcvBaseAdapter);
        dscb.setDragEnable(true);
        dscb.setSwipeEnable(true);
        ItemTouchHelper touchHelper = new ItemTouchHelper(dscb);
//        recyclerView.setAttachHelper(touchHelper);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(RcvBaseAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void init() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new Bean("sakura", "wood", R.drawable.sakura));
        }
    }
}
