package com.sakurawood.rcvbaseadapter.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakurawood.rcvbaseadapter.callback.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by sakurawood on 16-5-31.
 */
public abstract class RcvBaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchHelperAdapter {

    private Context mcontext;
    private int mlayoutResId;
    private List<T> mdata;
    private LayoutInflater mLayoutInflater;
    private View loadingView;
    private View headerView;
    private final int headerType = 0;
    private final int customType = 1;
    private final int loadingType = 2;
    private int headerNum = 0;
    private int loadingNum = 0;
    private int i = 0;

    /**
     * click listener of item
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
        mdata.remove(position - headerNum);
        notifyItemRemoved(position);
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        if (type == headerType || type == loadingType) {
            setFullSpan(holder);
        }
    }

    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof
                StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    return (type == headerType || type == loadingType)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.e("baseadapter", "onCreateViewHolder顺序为：" + i++);
        BaseViewHolder vh = null;
        View itemView;
        switch (viewType) {
            case customType:
                itemView = mLayoutInflater.inflate(mlayoutResId, parent, false);
                vh = new BaseViewHolder(mcontext, itemView);
                break;
            case headerType:
                itemView = headerView;
                vh = new BaseViewHolder(mcontext, itemView);
                break;
            case loadingType:
                itemView = loadingView;
                vh = new BaseViewHolder(mcontext, itemView);
                break;
            default:
                break;
        }
        return vh;
    }


    public void setTags(int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.e("baseadapter", "onBindViewHolder顺序为：" + i++);
//        Log.e("baseadapter", "position为：   " + position);
        if (position == 0 && headerView != null) {

        } else if (position == mdata.size() + 1 && loadingView != null) {

        } else {
            try {
                T item = mdata.get(position - headerNum);
                initItemClickListener((BaseViewHolder) holder);
                ((BaseViewHolder) holder).getHoldView().setTag(position);
                convert((BaseViewHolder) holder, item, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void convert(BaseViewHolder holder, T item, int position);

    @Override
    public int getItemCount() {
//        Log.e("baseadapter", "getItemCount顺序为：" + i++);
        int count = mdata.size();
        if (headerView != null) {
            headerNum = 1;
            count++;
        }

        if (loadingView != null) {
            loadingNum = 1;
            count++;
        }
        return count;
    }


    /**
     * initialize click listener
     *
     * @param vh
     */
    private void initItemClickListener(final BaseViewHolder vh) {
        if (onRecyclerViewItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onClick(v, v.getTag());
                }
            });
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onRecyclerViewItemClickListener.onLongClick(v, v.getTag());
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
                onRecyclerViewChildItemClickListener.onChildClick(v, v.getTag());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onRecyclerViewChildItemClickListener != null) {
                return onRecyclerViewChildItemClickListener
                        .onChildLongClick(v, v.getTag());
            }
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        Log.e("baseadapter", "getItemViewType顺序为：" + i++);
        int content_size = mdata.size();
        if (headerView != null && position == 0) {
            return headerType;
        } else if (loadingView != null && position > content_size - 1 + headerNum) {
            return loadingType;
        } else {
            return customType;
        }
    }

    @Override
    public long getItemId(int position) {
//        Log.e("baseadapter", "getItemId顺序为：" + i++);
        return super.getItemId(position);
    }

    public void addFooterView(View view) {
        this.loadingView = view;
        notifyDataSetChanged();
    }

    public void addHeaderView(View view) {
        this.headerView = view;
        notifyDataSetChanged();
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
        void onClick(View view, Object position);

        boolean onLongClick(View view, Object position);
    }

    public interface OnRecyclerViewChildItemClickListener {
        void onChildClick(View view, Object position);

        boolean onChildLongClick(View view, Object position);
    }

}
