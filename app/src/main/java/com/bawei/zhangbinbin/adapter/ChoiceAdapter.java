package com.bawei.zhangbinbin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhangbinbin.R;
import com.bawei.zhangbinbin.bean.MyBean;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zhang on 2017/11/23.
 */

public class ChoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyBean.RetBean.ListBean> list;
    private Context context;

    public ChoiceAdapter(List<MyBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        MyBean.RetBean.ListBean listBean = list.get(position);
        if (listBean.getShowType().equals("banner")) {
            return 0;
        } else {
            return 1;
        }
        //return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            MyViewHodelr myViewHodelr = new MyViewHodelr(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.topbanner, parent, false));
            return myViewHodelr;
        } else {
            MylistViewHodler mylistViewHodler = new MylistViewHodler(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false));
            return mylistViewHodler;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final List<MyBean.RetBean.ListBean.ChildListBean> childList = list.get(position).getChildList();
            if (holder instanceof MyViewHodelr) {
                final List<String> imgs = new ArrayList<>();
                List<MyBean.RetBean.ListBean.ChildListBean> ban = list.get(0).getChildList();
                for (int i = 0; i < ban.size(); i++) {
                    imgs.add(ban.get(i).getPic());
                }
                ((MyViewHodelr) holder).banner.setData(imgs,null);
                ((MyViewHodelr) holder).banner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(context).load(imgs.get(position)).into((ImageView) view);
                    }
                });

            } else if (holder instanceof MylistViewHodler) {
                ((MylistViewHodler) holder).itemTv.setText(childList.get(0).getTitle());
                if(childList.get(0).getPic()!=null){
                    Uri uri = Uri.parse(childList.get(0).getPic());
                    ((MylistViewHodler) holder).img.setImageURI(uri);
                }

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyViewHodelr extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        XBanner banner;

        public MyViewHodelr(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static  class MylistViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        SimpleDraweeView img;
        @BindView(R.id.item_tv)
        TextView itemTv;

        public MylistViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
