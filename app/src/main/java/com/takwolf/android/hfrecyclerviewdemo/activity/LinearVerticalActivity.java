package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.LinearVerticalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.util.HandlerUtils;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.ControllerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearVerticalActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    private LinearVerticalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_vertical);
        ButterKnife.bind(this);

        toolbar.setTitle("Linear Vertical");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new ControllerViewHolder(this, recyclerView, ControllerViewHolder.ORIENTATION_VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LinearVerticalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList(35));
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        HandlerUtils.handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                adapter.getIllustList().clear();
                adapter.getIllustList().addAll(ApiClient.buildIllustList(35));
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

        }, 1000);
    }

}
