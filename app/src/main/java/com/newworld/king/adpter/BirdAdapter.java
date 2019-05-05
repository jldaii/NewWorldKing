package com.newworld.king.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newworld.king.R;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by jldaii on 2017/6/18.
 */

public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    //定义点击事件接口
    public interface OnItemClickListener{
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    //定义构建方法
    public BirdAdapter(Context context, List<String> datas){
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv_bird,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewholder, final int position) {
        viewholder.txt_knowledgePoint.setText(mDatas.get(position));

        if(mOnItemClickListener != null) {
            viewholder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(viewHolder.itemView,position);添加后的占用了1 需要使用视图中的位置
                    int layputPosition = viewholder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewholder.itemView, layputPosition);
                }
            });
            viewholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    mOnItemClickListener.onItemLongClick(viewHolder.itemView,position);
                    int layputPosition = viewholder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewholder.itemView,layputPosition);
                    return false;
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends ViewHolder {
        TextView txt_knowledgePoint;
        ImageView img_likeLogo;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_knowledgePoint = (TextView)itemView.findViewById(R.id.txt_knowledgePoint);
            img_likeLogo = (ImageView)itemView.findViewById(R.id.img_likeLogo);
        }
    }
}
