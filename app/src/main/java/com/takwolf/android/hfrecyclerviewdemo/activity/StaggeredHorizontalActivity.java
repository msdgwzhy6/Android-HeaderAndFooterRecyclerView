package com.takwolf.android.hfrecyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.takwolf.android.hfrecyclerview.HeaderAndFooterRecyclerView;
import com.takwolf.android.hfrecyclerviewdemo.R;
import com.takwolf.android.hfrecyclerviewdemo.adapter.StaggeredHorizontalAdapter;
import com.takwolf.android.hfrecyclerviewdemo.listener.NavigationFinishClickListener;
import com.takwolf.android.hfrecyclerviewdemo.model.ApiClient;
import com.takwolf.android.hfrecyclerviewdemo.viewholder.ControllerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggeredHorizontalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    HeaderAndFooterRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_horizontal);
        ButterKnife.bind(this);

        toolbar.setTitle("Staggered Horizontal");
        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        new ControllerViewHolder(this, recyclerView, ControllerViewHolder.ORIENTATION_HORIZONTAL);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        StaggeredHorizontalAdapter adapter = new StaggeredHorizontalAdapter(this);
        adapter.getIllustList().addAll(ApiClient.buildIllustList(35));
        recyclerView.setAdapter(adapter);
    }

}
