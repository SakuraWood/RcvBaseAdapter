package com.sakurawood.rcvbaseadapter.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by sakurawood on 16-8-20.
 */

public class DragSwipeCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mAdapter;
    private boolean isSwipeEnable;
    private boolean isDragEnable;

    public DragSwipeCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isSwipeEnable;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isDragEnable;
    }

    public boolean isSwipeEnable() {
        return isSwipeEnable;
    }

    public void setSwipeEnable(boolean swipeEnable) {
        isSwipeEnable = swipeEnable;
    }

    public boolean isDragEnable() {
        return isDragEnable;
    }

    public void setDragEnable(boolean dragEnable) {
        isDragEnable = dragEnable;
    }
}
