package com.sakurawood.rcvbaseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by leesure on 16-5-31.
 */
public abstract class RcvBaseAdapter<T> extends RecyclerView.Adapter {

    private Context mcontext;
    private int mlayoutResId;
    private List<T> mdata;
    private LayoutInflater mLayoutInflater;
    /**clicklistener of item*/
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item=mdata.get(position);
        convert((BaseViewHolder)holder,item);
    }

    protected abstract void convert(BaseViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh=null;
        View itemView=mLayoutInflater.inflate(mlayoutResId,parent,false);
        vh=new BaseViewHolder(mcontext,itemView);
        initItemClickListener(vh);
        return vh;
    }

    /**
     * initialize clicklistener
     * @param vh
     */
    private void initItemClickListener(final BaseViewHolder vh) {
        if(onRecyclerViewItemClickListener!=null){
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onClick(v,vh.getLayoutPosition());
                }
            });
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onRecyclerViewItemClickListener.onLongClick(v,vh.getLayoutPosition());
                }
            });

        }
    }


    public RcvBaseAdapter(Context context, int layoutResId, List<T> data){
        this.mcontext=context;
        this.mlayoutResId=layoutResId;
        this.mdata=data;
        this.mLayoutInflater=LayoutInflater.from(context);
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
     * set clicklistener of item
     * @param onRecyclerViewItemClickListener
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener {
        public void onClick(View view, int position);
        public boolean onLongClick(View view, int position);
    }
}
