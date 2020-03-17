package com.example.myapplication.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    protected ItemClickHandler itemClickHandler;
    protected View.OnClickListener clickListener;

    protected List<T> mDatas;
    protected Context mContext;


    public BaseAdapter(List<T> data, Context context){
        mDatas = data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayout(),parent,false);
        BaseViewHolder holder = new BaseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickHandler != null){
                    itemClickHandler.itemClick(holder.getLayoutPosition(),holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T t = mDatas.get(position);
        bindData((BaseViewHolder) holder,t);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void updata(List<T> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void refreshList(List<T> list){
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public abstract int getLayout();

    public abstract void bindData(BaseViewHolder holder,T t);

    public static class BaseViewHolder extends RecyclerView.ViewHolder{

        private SparseArray items;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            items = new SparseArray();
        }

        public View getView(int id){
            View view = (View) items.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                items.append(id,view);
            }
            return view;
        }
    }

    public void setOnItemClickHandler(ItemClickHandler clickHandler){
        this.itemClickHandler = clickHandler;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.clickListener = listener;
    }

    public interface ItemClickHandler{
        void itemClick(int position, BaseViewHolder holder);
    }

}
