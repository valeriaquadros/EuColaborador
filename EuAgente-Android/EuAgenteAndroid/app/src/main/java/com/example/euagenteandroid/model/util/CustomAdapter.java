package com.example.euagenteandroid.model.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.entity.TipoFoco;

import java.util.List;

public class CustomAdapter  extends BaseAdapter {

    private Context context;
    private List<CheckableItem> checkableItemList;


    public CustomAdapter(Context context, List<CheckableItem> checkableItemArrayList) {
        this.context = context;
        this.checkableItemList = checkableItemArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<CheckableItem> getCheckableItemList() {
        return checkableItemList;
    }

    @Override
    public int getCount() {
        return checkableItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return checkableItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.comp_listview_item, null, true);

            holder.checkBox = convertView.findViewById(R.id.item_checkbox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setText(((TipoFoco) checkableItemList.get(position).getContent()).getDescricao());

        holder.checkBox.setChecked(checkableItemList.get(position).getSelected());

        holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(v -> {

            Integer pos = (Integer)  holder.checkBox.getTag();

            if(checkableItemList.get(pos).getSelected()){
                checkableItemList.get(pos).setSelected(false);
                holder.checkBox.setBackgroundColor(Color.WHITE);
                holder.checkBox.setTextColor(Color.BLACK);
            } else {
                checkableItemList.get(pos).setSelected(true);
                holder.checkBox.setBackgroundColor(Color.parseColor("#5F9EA0"));
                holder.checkBox.setTextColor(Color.WHITE);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        protected CheckBox checkBox;
    }

}