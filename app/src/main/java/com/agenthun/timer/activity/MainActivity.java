package com.agenthun.timer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.agenthun.timer.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Henry on 2015/6/16.
 */
public class MainActivity extends AppCompatActivity implements OnDateChangedListener, OnMonthChangedListener {
    public final static String DETAIL = "DETAIL";
    public final static DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance();
    @InjectView(R.id.addBtn)
    FloatingActionButton addBtn;
    @InjectView(R.id.calendarView)
    MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);

        Calendar calendar = Calendar.getInstance();
        calendarView.setSelectedDate(calendar.getTime());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DETAIL, DATE_FORMAT.format(calendarView.getSelectedDate().getDate()));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDateChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

    }

    @Override
    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

    }
}
