package com.agenthun.timer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agenthun.timer.R;
import com.agenthun.timer.activity.NewItemActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Henry on 2015/6/16.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static String DETAIL_TIME = "DETAIL_TIME";
    private Context mContext;
    private ArrayList<String> mDataset;
    private String mTitle;

    public DetailAdapter(Context context, ArrayList<String> objects, String title) {
        this.mContext = context;
        this.mDataset = objects;
        this.mTitle = title;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final String item = mDataset.get(position);
        ((DetailViewHolder) holder).tableTime.setText(item);

        ((DetailViewHolder) holder).tableAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewItemActivity.class);
                intent.putExtra(DETAIL_TIME, mTitle + ((DetailViewHolder) holder).tableTime.getText());
                v.getContext().startActivity(intent);
                updateDataSetChanged();
            }
        });
        String name = mContext.getSharedPreferences(NewItemActivity.ITEM_INFO, 0).getString(mTitle + ((DetailViewHolder) holder).tableTime.getText() + "_NAME", "添加事件");
        String type = mContext.getSharedPreferences(NewItemActivity.ITEM_INFO, 0).getString(mTitle + ((DetailViewHolder) holder).tableTime.getText() + "_TYPE", "暂无状态");
        ((DetailViewHolder) holder).tableAdd.setText(name);
        ((DetailViewHolder) holder).tableStatus.setText(type);

        String typeColor = (String) (((DetailViewHolder) holder).tableStatus.getText());
        switch (typeColor) {
            case "高效工作":
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(43, 175, 43));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(255, 255, 255));
                break;
            case "尽兴娱乐":
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(233, 30, 99));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(255, 255, 255));
                break;
            case "休息放松":
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(255, 193, 7));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(255, 255, 255));
                break;
            case "强迫工作":
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(121, 85, 72));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(255, 255, 255));
                break;
            case "无效拖延":
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(158, 158, 158));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(255, 255, 255));
                break;
            default:
                ((DetailViewHolder) holder).tableStatus.setBackgroundColor(Color.rgb(255, 255, 255));
                ((DetailViewHolder) holder).tableStatus.setTextColor(Color.rgb(128, 128, 128));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateDataSetChanged() {
        notifyDataSetChanged();
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.table_time)
        TextView tableTime;
        @InjectView(R.id.table_add)
        TextView tableAdd;
        @InjectView(R.id.table_status)
        TextView tableStatus;

        public DetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
