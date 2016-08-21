package com.sakurawood.rcvbaseadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakurawood.rcvbaseadapter.callback.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by sakurawood on 16-5-31.
 */
public abstract class RcvBaseAdapter<T> extends RecyclerView.Adapter implements ItemTouchHelperAdapter {

    private Context mcontext;
    private int mlayoutResId;
    private List<T> mdata;
    private LayoutInflater mLayoutInflater;
    /**
     * clicklistener of item
     */
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnRecyclerViewChildItemClickListener onRecyclerViewChildItemClickListener;

    public RcvBaseAdapter(Context context, int layoutResId, List<T> data) {
        this.mcontext = context;
        this.mlayoutResId = layoutResId;
        this.mdata = data;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mdata, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mdata.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = mdata.get(position);
        convert((BaseViewHolder) holder, item);
    }

    protected abstract void convert(BaseViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;
        View itemView = mLayoutInflater.inflate(mlayoutResId, parent, false);
        vh = new BaseViewHolder(mcontext, itemView);
        initItemClickListener(vh);
        return vh;
    }

    /**
     * initialize clicklistener
     *
     * @param vh
     */
    private void initItemClickListener(final BaseViewHolder vh) {
        if (onRecyclerViewItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onClick(v, vh.getLayoutPosition());
                }
            });
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onRecyclerViewItemClickListener.onLongClick(v, vh.getLayoutPosition());
                }
            });

        }
    }

    /**
     * implements this OnclickListener
     * make the view has the click event from user
     */
    public class OnItemChildClickListener implements View.OnClickListener, View.OnLongClickListener {
        BaseViewHolder viewHolder;

        public OnItemChildClickListener() {

        }

        public BaseViewHolder getViewHolder() {
            return viewHolder;
        }

        public void setViewHolder(BaseViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            if (onRecyclerViewChildItemClickListener != null) {
                onRecyclerViewChildItemClickListener.onChildClick(v, viewHolder.getLayoutPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onRecyclerViewChildItemClickListener != null) {
                return onRecyclerViewChildItemClickListener
                        .onChildLongClick(v, viewHolder.getLayoutPosition());
            }
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * set click listener of item
     *
     * @param onRecyclerViewItemClickListener
     */
    public void setOnRecyclerViewItemClickListener(
            OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    /**
     * set click child view listener of item
     *
     * @param onRecyclerViewChildItemClickListener
     */
    public void setOnRecyclerViewChildItemClickListener(
            OnRecyclerViewChildItemClickListener onRecyclerViewChildItemClickListener) {
        this.onRecyclerViewChildItemClickListener = onRecyclerViewChildItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener {
        public void onClick(View view, int position);

        public boolean onLongClick(View view, int position);
    }

    public interface OnRecyclerViewChildItemClickListener {
        public void onChildClick(View view, int position);

        public boolean onChildLongClick(View view, int position);
    }
}
