package com.java.group8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    final static int RESPONSE_FROM_CATEGORY = 0;

    private RecyclerView recyclerView;
    private List<NewsCategory> myTags;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter = new HomeAdapter());
        adapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("category", position);
                setResult(RESPONSE_FROM_CATEGORY, intent);
                finish();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                onItemClick(view, position);
            }
        });
//设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("category", -1);
                setResult(RESPONSE_FROM_CATEGORY, intent);
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initData()
    {
        myTags = new ArrayList<NewsCategory>();
        for (int i = 1; i <= 12; i++)
        {
            myTags.add(NewsCategory.valueOf(i));
        }
    }

    public String stringOfCategory(NewsCategory c) {
        switch (c) {
            case SCIENCE:
                return getString(R.string.label_science);
            case EDUCATION:
                return getString(R.string.label_education);
            case MILITARY:
                return getString(R.string.label_military);
            case DOMESTIC:
                return getString(R.string.label_domestic);
            case SOCIETY:
                return getString(R.string.label_society);
            case CULTURE:
                return getString(R.string.label_culture);
            case CAR:
                return getString(R.string.label_car);
            case INTERNATIONAL:
                return getString(R.string.label_international);
            case SPORT:
                return getString(R.string.label_sport);
            case ECONOMY:
                return getString(R.string.label_economy);
            case HEALTH:
                return getString(R.string.label_health);
            case ENTERTAINMENT:
                return getString(R.string.label_entertainment);
        }
        return null;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(CategoryActivity.this).inflate(R.layout.recyclerview_item, parent,
                    false));
            return holder;
        }

        @Override
        public int getItemCount()
        {
            return myTags.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }

        private OnItemClickLitener onItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.onItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position)
        {
            holder.tv.setText(stringOfCategory(myTags.get(position)));

            // 如果设置了回调，则设置点击事件
            if (onItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        onItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        onItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }
    }
}
