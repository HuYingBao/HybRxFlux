package com.huyingbao.hyb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huyingbao.hyb.R;
import com.huyingbao.hyb.model.Shop;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by marcel on 09/10/15.
 */
public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    private ArrayList<Shop> repos;
    private OnRepoClicked callback;

    public ShopListAdapter() {
        super();
        repos = new ArrayList<Shop>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.i_card_repository, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Shop repo = repos.get(i);
        viewHolder.setUp(repo);
        viewHolder.itemView.setOnClickListener(view -> {
            if (callback != null) callback.onClicked(repo);
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setRepos(ArrayList<Shop> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }

    public void setCallback(OnRepoClicked callback) {
        this.callback = callback;
    }

    public interface OnRepoClicked {
        void onClicked(Shop repo);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.description)
        public TextView descView;
        @Bind(R.id.id)
        public TextView idView;
        @Bind(R.id.name)
        TextView nameView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUp(Shop repo) {
            nameView.setText(repo.getShopName());
            descView.setText(repo.getShopDesc());
            idView.setText("shop code: " + repo.getCode());
        }
    }
}
