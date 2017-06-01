package com.takwolf.android.hfrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

class FixedViewSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final AdapterProxy adapterProxy;
    private final GridLayoutManager gridLayoutManager;

    FixedViewSpanSizeLookup(@NonNull AdapterProxy adapterProxy, @NonNull GridLayoutManager gridLayoutManager) {
        this.adapterProxy = adapterProxy;
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        int viewType = adapterProxy.getItemViewType(position);
        if (viewType == AdapterProxy.TYPE_HEADER || viewType == AdapterProxy.TYPE_FOOTER) {
            return gridLayoutManager.getSpanCount();
        } else {
            return 1;
        }
    }

}