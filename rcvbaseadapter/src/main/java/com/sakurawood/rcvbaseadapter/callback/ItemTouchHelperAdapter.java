package com.sakurawood.rcvbaseadapter.callback;

/**
 * Created by sakurawood on 16-8-20.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
