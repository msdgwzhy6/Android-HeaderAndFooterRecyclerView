package com.takwolf.android.hfrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HeaderAndFooterRecyclerView extends RecyclerView {

    private final LinearLayout headerParent;
    private final LinearLayout footerParent;
    private final ProxyAdapter proxyAdapter;

    public HeaderAndFooterRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderAndFooterRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        headerParent = new LinearLayout(context);
        footerParent = new LinearLayout(context);
        proxyAdapter = new ProxyAdapter(this);
        super.setAdapter(proxyAdapter);
    }

    @NonNull
    public LinearLayout getHeaderParent() {
        return headerParent;
    }

    public int getHeaderViewCount() {
        return headerParent.getChildCount();
    }

    public void addHeaderView(@NonNull View view) {
        headerParent.addView(view);
        proxyAdapter.notifyHeaderInserted();
    }

    public void addHeaderView(@NonNull View view, int index) {
        headerParent.addView(view, index);
        proxyAdapter.notifyHeaderInserted();
    }

    public void removeHeaderView(@NonNull View view) {
        headerParent.removeView(view);
        proxyAdapter.notifyHeaderRemoved();
    }

    public void removeHeaderView(int index) {
        headerParent.removeViewAt(index);
        proxyAdapter.notifyHeaderRemoved();
    }

    @NonNull
    public LinearLayout getFooterParent() {
        return footerParent;
    }

    public int getFooterViewCount() {
        return footerParent.getChildCount();
    }

    public void addFooterView(@NonNull View view) {
        footerParent.addView(view);
        proxyAdapter.notifyFooterInserted();
    }

    public void addFooterView(@NonNull View view, int index) {
        footerParent.addView(view, index);
        proxyAdapter.notifyFooterInserted();
    }

    public void removeFooterView(@NonNull View view) {
        footerParent.removeView(view);
        proxyAdapter.notifyFooterRemoved();
    }

    public void removeFooterView(int index) {
        footerParent.removeViewAt(index);
        proxyAdapter.notifyFooterRemoved();
    }

    void adjustFixedViewParentLayoutParamsAndOrientation(@NonNull LinearLayout fixedViewParent) {
        if (getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) getLayoutManager();
            GridLayoutManager.LayoutParams layoutParams;
            int orientation;
            if (fixedViewParent.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                layoutParams = (GridLayoutManager.LayoutParams) fixedViewParent.getLayoutParams();
                if (gridLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    orientation = LinearLayout.HORIZONTAL;
                }
            } else {
                if (gridLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams = new GridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    orientation = LinearLayout.HORIZONTAL;
                }
            }
            fixedViewParent.setLayoutParams(layoutParams);
            fixedViewParent.setOrientation(orientation);
        } else if (getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
            LayoutParams layoutParams;
            int orientation;
            if (fixedViewParent.getLayoutParams() instanceof LayoutParams) {
                layoutParams = (LayoutParams) fixedViewParent.getLayoutParams();
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    orientation = LinearLayout.HORIZONTAL;
                }
            } else {
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    orientation = LinearLayout.HORIZONTAL;
                }
            }
            fixedViewParent.setLayoutParams(layoutParams);
            fixedViewParent.setOrientation(orientation);
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            StaggeredGridLayoutManager.LayoutParams layoutParams;
            int orientation;
            if (fixedViewParent.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                layoutParams = (StaggeredGridLayoutManager.LayoutParams) fixedViewParent.getLayoutParams();
                if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    orientation = LinearLayout.HORIZONTAL;
                }
            } else {
                if (staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL) {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    orientation = LinearLayout.VERTICAL;
                } else {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    orientation = LinearLayout.HORIZONTAL;
                }
            }
            layoutParams.setFullSpan(true);
            fixedViewParent.setLayoutParams(layoutParams);
            fixedViewParent.setOrientation(orientation);
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            FixedViewSpanSizeLookup fixedViewSpanSizeLookup = null;
            if (gridLayoutManager.getSpanSizeLookup() == null || gridLayoutManager.getSpanSizeLookup().getClass() == GridLayoutManager.DefaultSpanSizeLookup.class) {
                fixedViewSpanSizeLookup = new FixedViewSpanSizeLookup();
                gridLayoutManager.setSpanSizeLookup(fixedViewSpanSizeLookup);
            } else if (gridLayoutManager.getSpanSizeLookup().getClass() == FixedViewSpanSizeLookup.class) {
                fixedViewSpanSizeLookup = (FixedViewSpanSizeLookup) gridLayoutManager.getSpanSizeLookup();
            }
            if (fixedViewSpanSizeLookup != null) {
                fixedViewSpanSizeLookup.setTargets(gridLayoutManager, proxyAdapter);
            }
        }
        super.setLayoutManager(layoutManager);
    }

    @NonNull
    public ProxyAdapter getProxyAdapter() {
        return proxyAdapter;
    }

    @Override
    public Adapter getAdapter() {
        return proxyAdapter.getAdapter();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(null);
        if (adapter != null) {
            proxyAdapter.setHasStableIds(adapter.hasStableIds());
        } else {
            proxyAdapter.setHasStableIds(false);
        }
        proxyAdapter.setAdapter(adapter);
        super.setAdapter(proxyAdapter);
    }

    @Override
    public void swapAdapter(Adapter adapter, boolean removeAndRecycleExistingViews) {
        super.swapAdapter(null, removeAndRecycleExistingViews);
        if (adapter != null) {
            proxyAdapter.setHasStableIds(adapter.hasStableIds());
        } else {
            proxyAdapter.setHasStableIds(false);
        }
        proxyAdapter.setAdapter(adapter);
        super.swapAdapter(proxyAdapter, removeAndRecycleExistingViews);
    }

}
