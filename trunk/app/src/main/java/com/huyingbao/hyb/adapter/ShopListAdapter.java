package com.huyingbao.hyb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huyingbao.hyb.HybApp;
import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.Shop;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by marcel on 09/10/15.
 */
public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    /**
     * 点击回调接口
     */
    public interface OnShopClicked {
        void onClicked(Shop shop);
    }

    private OnShopClicked onShopClickCallBack;

    private ArrayList<Shop> mShopList;


    /**
     * 构造方法,初始化数据list
     */
    public ShopListAdapter() {
        super();
        mShopList = new ArrayList<Shop>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.i_card_repository, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Shop repo = mShopList.get(i);
        viewHolder.setShowData(repo);
        viewHolder.itemView.setOnClickListener(view -> {
            if (onShopClickCallBack != null) onShopClickCallBack.onClicked(repo);
        });
    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    /**
     * 适配数据
     *
     * @param shopList
     */
    public void setShopList(ArrayList<Shop> shopList) {
        this.mShopList = shopList;
        notifyDataSetChanged();
    }

    /**
     * 设置点击回调
     *
     * @param onShopClickCallBack
     */
    public void setOnShopClickCallBack(OnShopClicked onShopClickCallBack) {
        this.onShopClickCallBack = onShopClickCallBack;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.description)
        public TextView descView;
        @Bind(R.id.id)
        public TextView idView;
        @Bind(R.id.name)
        TextView nameView;
        @Bind(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setShowData(Shop shop) {
            nameView.setText(shop.getShopName());
            descView.setText(shop.getShopDesc());
            idView.setText("shop code: " + shop.getCode());
            Glide.with(HybApp.getInstance())
                    .load(shop.getHeadImg())
                    .centerCrop()
                    .placeholder(R.drawable.ic_menu_camera)
                    .crossFade()
                    .into(imageView);

        }
    }
}
