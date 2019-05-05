package com.newworld.king.bird.MaterialDesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newworld.king.R;

import java.util.List;

/**
 * Created by jldaii on 2017/5/26.
 */

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitsList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        android.support.v7.widget.CardView cardView;
        ImageView fruitImage;
        TextView fruitName;



        public ViewHolder(View view) {
            super(view);
            cardView = (android.support.v7.widget.CardView) view;
            fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            fruitName = (TextView)view.findViewById(R.id.fruit_name);
        }

    }

    public FruitsAdapter(List<Fruit> fruitList){
        mFruitsList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruits_item,parent,false);

        //跳转页面要写的
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitsList.get(position);
                Intent intent = new Intent(mContext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMADE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });

//        return new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitsList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);

    }

    @Override
    public int getItemCount() {
        return mFruitsList.size();
    }
}
