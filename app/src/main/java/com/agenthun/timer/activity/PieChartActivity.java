package com.agenthun.timer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.agenthun.timer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class PieChartActivity extends AppCompatActivity {
    @InjectView(R.id.close_button)
    Button closeBtn;
    @InjectView(R.id.chart)
    PieChartView pieChartView;
    private int[] itemTypeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.inject(this);
        setTitle("一天饼图分析");

        Intent intent = getIntent();
        itemTypeCount = intent.getIntArrayExtra(DetailActivity.ITEM_TYPE_COUNT);
/*        final String[] typeName = getResources().getStringArray(R.array.spinner_item_type);
        typeName[5] = "暂无状态";*/
/*        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Toast.makeText(PieChartActivity.this, sliceValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });*/
        generateData();
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PieChartActivity.this.finish();
            }
        });
    }

    private void generateData() {
        int numValues = 6;
        PieChartData data;
        int[] itemTypeColor = new int[numValues];
        itemTypeColor[0] = getResources().getColor(R.color.efficient_work_color);
        itemTypeColor[1] = getResources().getColor(R.color.enjoy_life_color);
        itemTypeColor[2] = getResources().getColor(R.color.relex_color);
        itemTypeColor[3] = getResources().getColor(R.color.force_work_color);
        itemTypeColor[4] = getResources().getColor(R.color.useless_color);
        itemTypeColor[5] = getResources().getColor(R.color.blank_color);

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; i++) {
            SliceValue sliceValue = new SliceValue((float) (itemTypeCount[i]), itemTypeColor[i]);
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(true);
        pieChartView.setPieChartData(data);
    }

}
