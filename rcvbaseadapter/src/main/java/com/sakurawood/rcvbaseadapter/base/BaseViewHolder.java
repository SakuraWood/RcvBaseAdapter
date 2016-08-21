package com.sakurawood.rcvbaseadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by sakurawood on 16-5-31.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View convertView;
    private Context context;
    /**
     * save view
     */
    private SparseArray<View> views;


    public BaseViewHolder(Context context, View itemview) {
        super(itemview);
        this.context = context;
        this.views = new SparseArray<View>();
        this.convertView = itemview;
    }

    /**
     * to get the type of a view
     *
     * @param viewId
     * @param <T>
     * @return view
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * set text of a textview or a button
     *
     * @param viewId
     * @param str
     * @return BaseViewHolder of Chaining
     */
    public BaseViewHolder setText(int viewId, String str) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView textView = getView(viewId);
            textView.setText(str);
        } else {
            Button button = getView(viewId);
            button.setText(str);
        }
        return this;
    }

    /**
     * set color of a text or a button
     *
     * @param viewId
     * @param color
     * @return BaseViewHolder of Chaining
     */
    public BaseViewHolder setTextColor(int viewId, int color) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView textView = getView(viewId);
            textView.setTextColor(color);
        } else {
            Button button = getView(viewId);
            button.setTextColor(color);
        }
        return this;
    }

    /**
     * set image of a imageview
     *
     * @param viewId
     * @param imgId
     * @return BaseViewHolder of Chaining
     */
    public BaseViewHolder setImageResource(int viewId, int imgId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgId);
        return this;
    }

    /**
     * use Glide to get the img from network
     *
     * @param viewId
     * @param url
     * @return
     */
    public BaseViewHolder setImageUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(context).load(url).crossFade().into(view);
        return this;
    }

    /**
     * set the visibility of a view
     *
     * @param viewId
     * @param bl
     * @return
     */
    public BaseViewHolder setVisibility(int viewId, boolean bl) {
        View view = getView(viewId);
        view.setVisibility(bl ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * set the background of a view
     *
     * @param viewId
     * @param ResId
     * @return
     */
    public BaseViewHolder setBackgroundResource(int viewId, int ResId) {
        View view = getView(viewId);
        view.setBackgroundResource(ResId);
        return this;
    }

    /**
     * set the ItemListener of a view
     *
     * @param viewId
     * @param vol
     * @return
     */
    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener vol) {
        View view = getView(viewId);
        view.setOnClickListener(vol);
        return this;
    }

    /**
     * set the ItemChildListener of a view
     *
     * @param viewId
     * @param rocl
     * @return
     */
    public BaseViewHolder setOnClickListener(
            int viewId, RcvBaseAdapter.OnItemChildClickListener rocl) {
        View view = getView(viewId);
        rocl.setViewHolder(this);
        view.setOnClickListener(rocl);
        return this;
    }

}
