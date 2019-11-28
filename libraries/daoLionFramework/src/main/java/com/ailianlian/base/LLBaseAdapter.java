package com.ailianlian.base;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-10-11 15:22
 */

public abstract class LLBaseAdapter<T> extends BaseAdapter{
    
    protected Context context;
    protected List<T> data;
    protected LayoutInflater layoutInflater;
    
    
    public LLBaseAdapter(Fragment fragment, List<T> data){
        
        this.context=fragment.getContext();
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    public LLBaseAdapter(Context context, List<T> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount(){
        return data.size();
    }
    
    @Override
    public T getItem(int position){
        return data.get(position);
    }
    
    @Override
    public long getItemId(int position){
        return position;
    }
    
}
