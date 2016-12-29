package com.sakurawood.rcvbaseadapter.base;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    public View getHoldView() {
        return this.convertView;
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
     * set color of a text or a button
     *
     * @param viewId
     * @param resId
     * @return BaseViewHolder of Chaining
     */
    public BaseViewHolder setTextResColor(int viewId, int resId) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            TextView textView = getView(viewId);
            textView.setTextColor(context.getResources().getColor(resId));
        } else {
            Button button = getView(viewId);
            button.setTextColor(context.getResources().getColor(resId));
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
        View view = getView(viewId);
        if (view instanceof ImageView) {
            ImageView imageView = getView(viewId);
            imageView.setImageResource(imgId);
        } else if (view instanceof ImageButton) {
            ImageButton imageButton = getView(viewId);
            imageButton.setImageResource(imgId);
        }
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

    public BaseViewHolder setVisibility(int viewId, int value) {
        View view = getView(viewId);
        view.setVisibility(value);
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
     * set the Listener of a view
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
     * set the LongListener of a view
     *
     * @param viewId
     * @param voll
     * @return
     */
    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener voll) {
        View view = getView(viewId);
        view.setOnLongClickListener(voll);
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

    /**
     * set the adapter of a child recylerview.
     *
     * @param viewId
     * @param rcvBaseAdapter
     * @return
     */
    public BaseViewHolder setAdapter(
            int viewId, RcvBaseAdapter rcvBaseAdapter
            , RecyclerView.LayoutManager rlm
    ) {
        View view = getView(viewId);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(rlm);
            ((RecyclerView) view).setAdapter(rcvBaseAdapter);
        }
        return this;
    }

    /**
     * set the font of a textview
     *
     * @param viewId
     * @param context
     * @return
     */
    public BaseViewHolder setFont(
            int viewId, Context context, String path) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), path);
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(type);
        }
        return this;
    }

    /**
     * set the font of a textview
     *
     * @param viewId
     * @param string
     * @return
     */
    public BaseViewHolder setFontText(
            int viewId, String string, String path) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), path);
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(type);
            ((TextView) view).setText(string);
        }
        return this;
    }

    /**
     * set the size of a textview
     *
     * @param viewId
     * @param size
     * @return
     */
    public BaseViewHolder setTextSize(
            int viewId, float size) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(size);
        }
        return this;
    }

    /**
     * set the bold font of a textview
     *
     * @param viewId
     * @return
     */
    public BaseViewHolder setBoldFont(
            int viewId, String string, String path) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), path);
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(type);
            ((TextView) view).getPaint().setFakeBoldText(true);
            ((TextView) view).setText(string);
        }
        return this;
    }
}
