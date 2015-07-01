package com.agenthun.timer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.agenthun.timer.R;
import com.agenthun.timer.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends AppCompatActivity {
    public final static String ITEM_TYPE_COUNT = "ITEM_TYPE_COUNT";
    DetailAdapter detailAdapter;
    @InjectView(R.id.detailRecyclerView)
    RecyclerView detailRecyclerView;
    @InjectView(R.id.analyses)
    Button analysesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        final String title = (String) intent.getStringExtra(MainActivity.DETAIL);
        setTitle(title);

        detailRecyclerView.setLayoutManager(new LinearLayoutManager((this)));

        final String[] data = new String[]{
                "07:00-07:30", "07:30-08:00",
                "08:00-08:30", "08:30-09:00",
                "09:00-09:30", "09:30-10:00",
                "10:00-10:30", "10:30-11:00",
                "11:00-11:30", "11:30-12:00",
                "12:00-12:30", "12:30-13:00",
                "13:00-13:30", "13:30-14:00",
                "14:00-14:30", "14:30-15:00",
                "15:00-15:30", "15:30-16:00",
                "16:00-16:30", "16:30-17:00",
                "17:00-17:30", "17:30-18:00",
                "18:00-18:30", "18:30-19:00",
                "19:00-19:30", "19:30-20:00",
                "20:00-20:30", "20:30-21:00",
                "21:00-21:30", "21:30-22:00",
                "22:00-22:30", "22:30-23:00",
                "23:00-23:30", "23:30-24:00"};
        ArrayList<String> mDataSet = new ArrayList<>(Arrays.asList(data));

        detailAdapter = new DetailAdapter(this, mDataSet, title);
        detailRecyclerView.setAdapter(detailAdapter);

        analysesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailAdapter.notifyDataSetChanged();
                int[] itemTypeCount = {0, 0, 0, 0, 0, 0};
                for (int i = 0; i < data.length; i++) {
                    String type = getApplicationContext().getSharedPreferences(NewItemActivity.ITEM_INFO, 0).getString(title + data[i] + "_TYPE", "暂无状态");
                    switch (type) {
                        case "高效工作":
                            itemTypeCount[0]++;
                            break;
                        case "尽兴娱乐":
                            itemTypeCount[1]++;
                            break;
                        case "休息放松":
                            itemTypeCount[2]++;
                            break;
                        case "强迫工作":
                            itemTypeCount[3]++;
                            break;
                        case "无效拖延":
                            itemTypeCount[4]++;
                            break;
                        default:
                            itemTypeCount[5]++;
                            break;
                    }
                }

                Intent intent = new Intent(DetailActivity.this, PieChartActivity.class);
                intent.putExtra(ITEM_TYPE_COUNT, itemTypeCount);
                startActivity(intent);
            }
        });

    }
}
